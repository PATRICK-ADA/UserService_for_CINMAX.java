using Cinmax.Api.Gateway.Models;
using Cinmax.Api.Gateway.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Memory;

namespace Cinmax.Api.Gateway.Endpoints;

public static class MovieEndpoints
{
    public static void MapMovieEndpoints(this WebApplication app)
    {
        app.MapGet("/api/movies", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, IMemoryCache cache, CacheKeyTracker cacheKeyTracker, [AsParameters] ListMoviesRequestDto request) =>
        {
            // Try to get the movies from the cache
            string cacheKey = $"movies_{request.Page}_{request.Size}";
            if (cache.TryGetValue(cacheKey, out ListMoviesResponseDto cachedMovies))
            {
                return Results.Ok(cachedMovies);
            }

            var grpcRequest = new Cinmax.Api.Gateway.Protos.ListMoviesRequest { Page = request.Page, Size = request.Size };
            var response = await userService.ListMoviesAsync(grpcRequest);
            var moviesResponse = new ListMoviesResponseDto
            {
                Movies = response.Movies.Select(m => new MovieDto
                {
                    Id = m.Id,
                    Title = m.Title,
                    Description = m.Description,
                    Category = m.Category,
                    BuyPrice = m.BuyPrice,
                    RentalPrice = m.RentalPrice,
                    RentalDurations = m.RentalDurations.ToList()
                }).ToList(),
                CurrentPage = response.CurrentPage,
                PageSize = response.PageSize,
                TotalPages = response.TotalPages,
                TotalElements = response.TotalElements
            };

            // Cache the movies without expiration (write-through invalidation)
            cache.Set(cacheKey, moviesResponse);
            cacheKeyTracker.AddKey(cacheKey);

            return Results.Ok(moviesResponse);
        });

        app.MapGet("/api/movies/{id:long}/stream", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, long id, string ticketId, long userId) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.GetMovieStreamRequest { MovieId = id, TicketId = ticketId, UserId = userId };
            var response = await userService.GetMovieStreamAsync(grpcRequest);
            return Results.Ok(new MovieStreamResponseDto
            {
                MasterPlaylistUrl = response.MasterPlaylistUrl,
                Qualities = response.Qualities.Select(q => new StreamQualityDto
                {
                    Resolution = q.Resolution,
                    Bandwidth = q.Bandwidth,
                    PlaylistUrl = q.PlaylistUrl,
                    Width = q.Width,
                    Height = q.Height
                }).ToList(),
                ProcessedForStreaming = response.ProcessedForStreaming
            });
        });


        app.MapPost("/api/movies/upload", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, IMemoryCache cache, CacheKeyTracker cacheKeyTracker, UploadMovieRequestDto request) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.UploadMovieRequest
            {
                Title = request.Title,
                Description = request.Description,
                Category = request.Category,
                FileData = Google.Protobuf.ByteString.CopyFrom(request.FileData),
                FileName = request.FileName,
                ContentType = request.ContentType
            };
            var response = await userService.UploadMovieAsync(grpcRequest);

            // Cache the uploaded movie for the first page (assuming it will appear there)
            string cacheKey = "movies_1_10";
            if (cache.TryGetValue(cacheKey, out ListMoviesResponseDto cachedMovies))
            {
                // Add the new movie to the cached list
                cachedMovies.Movies.Add(new MovieDto
                {
                    Id = response.Movie.Id,
                    Title = response.Movie.Title,
                    Description = response.Movie.Description,
                    Category = response.Movie.Category,
                    BuyPrice = response.Movie.BuyPrice,
                    RentalPrice = response.Movie.RentalPrice,
                    RentalDurations = response.Movie.RentalDurations.ToList()
                });
                cache.Set(cacheKey, cachedMovies);
            }

            return Results.Ok(new MovieResponseDto
            {
                Movie = new MovieDto
                {
                    Id = response.Movie.Id,
                    Title = response.Movie.Title,
                    Description = response.Movie.Description,
                    Category = response.Movie.Category,
                    BuyPrice = response.Movie.BuyPrice,
                    RentalPrice = response.Movie.RentalPrice,
                    RentalDurations = response.Movie.RentalDurations.ToList()
                }
            });
        }).RequireAuthorization(policy => policy.RequireRole("SUPER_ADMIN", "ADMIN", "PARTNER"));

        app.MapPost("/api/movies/upload-multiple", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, IMemoryCache cache, CacheKeyTracker cacheKeyTracker, UploadMoviesRequestDto request) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.UploadMoviesRequest();
            foreach (var movie in request.Movies)
            {
                grpcRequest.Movies.Add(new Cinmax.Api.Gateway.Protos.UploadMovieRequest
                {
                    Title = movie.Title,
                    Description = movie.Description,
                    Category = movie.Category,
                    FileData = Google.Protobuf.ByteString.CopyFrom(movie.FileData),
                    FileName = movie.FileName,
                    ContentType = movie.ContentType
                });
            }
            var response = await userService.UploadMoviesAsync(grpcRequest);

            // Cache the uploaded movies for the first page (assuming they will appear there)
            string cacheKey = "movies_1_10";
            if (cache.TryGetValue(cacheKey, out ListMoviesResponseDto cachedMovies))
            {
                // Add the new movies to the cached list
                foreach (var m in response.Movies)
                {
                    cachedMovies.Movies.Add(new MovieDto
                    {
                        Id = m.Id,
                        Title = m.Title,
                        Description = m.Description,
                        Category = m.Category,
                        BuyPrice = m.BuyPrice,
                        RentalPrice = m.RentalPrice,
                        RentalDurations = m.RentalDurations.ToList()
                    });
                }
                cache.Set(cacheKey, cachedMovies);
            }

            return Results.Ok(new UploadMoviesResponseDto
            {
                Movies = response.Movies.Select(m => new MovieDto
                {
                    Id = m.Id,
                    Title = m.Title,
                    Description = m.Description,
                    Category = m.Category,
                    BuyPrice = m.BuyPrice,
                    RentalPrice = m.RentalPrice,
                    RentalDurations = m.RentalDurations.ToList()
                }).ToList(),
                UploadedCount = response.UploadedCount
            });
        }).RequireAuthorization(policy => policy.RequireRole("SUPER_ADMIN", "ADMIN", "PARTNER"));

        app.MapDelete("/api/movies/{id:long}", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, CacheKeyTracker cacheKeyTracker, long id) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.DeleteMovieRequest { MovieId = id };
            var response = await userService.DeleteMovieAsync(grpcRequest);

            // Invalidate the cache for all movie lists
            cacheKeyTracker.RemoveKeysByPrefix("movies_");

            return Results.Ok(new { success = response.Success });
        }).RequireAuthorization(policy => policy.RequireRole("SUPER_ADMIN", "ADMIN"));
    }
}