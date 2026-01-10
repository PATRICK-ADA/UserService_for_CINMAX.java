using Cinmax.Api.Gateway.Data;
using Cinmax.Api.Gateway.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Caching.Memory;

namespace Cinmax.Api.Gateway.Endpoints;

public static class UserEndpoints
{
    public static void MapUserEndpoints(this WebApplication app)
    {
        app.MapGet("/api/users/{id:long}", async (ApplicationDbContext db, IMemoryCache cache, long id) =>
        {
            // Try to get the user from the cache
            string cacheKey = $"user_{id}";
            if (cache.TryGetValue(cacheKey, out UserResponseDto cachedUser))
            {
                return Results.Ok(cachedUser);
            }

            var user = await db.Users
                .Where(u => u.Id == id)
                .Select(u => new UserResponseDto
                {
                    Id = u.Id,
                    Name = u.Name,
                    Email = u.Email,
                    Tickets = new List<TicketDto>()
                })
                .FirstOrDefaultAsync();

            if (user == null)
            {
                return Results.NotFound();
            }

            // Cache the user without expiration (write-through invalidation)
            cache.Set(cacheKey, user);

            return Results.Ok(user);
        });

        app.MapPut("/api/users/{id:long}", async (ApplicationDbContext db, IMemoryCache cache, long id, UpdateUserRequestDto request) =>
        {
            var user = await db.Users.FindAsync(id);
            if (user == null)
            {
                return Results.NotFound();
            }

            user.Name = request.Name;
            user.Email = request.Email;
            await db.SaveChangesAsync();

            // Invalidate the cache for this user
            string cacheKey = $"user_{id}";
            cache.Remove(cacheKey);

            return Results.Ok(new UserResponseDto
            {
                Id = user.Id,
                Name = user.Name,
                Email = user.Email,
                Tickets = new List<TicketDto>()
            });
        });

        app.MapDelete("/api/users/{id:long}", async (ApplicationDbContext db, IMemoryCache cache, long id) =>
        {
            var user = await db.Users.FindAsync(id);
            if (user == null)
            {
                return Results.NotFound();
            }

            db.Users.Remove(user);
            await db.SaveChangesAsync();

            // Invalidate the cache for this user
            string cacheKey = $"user_{id}";
            cache.Remove(cacheKey);

            return Results.Ok(new { success = true });
        });

        app.MapGet("/api/users", async (ApplicationDbContext db, [AsParameters] ListUsersRequestDto request) =>
        {
            var query = db.Users.AsQueryable();
            var totalElements = await query.CountAsync();

            int page = request.Page > 0 ? request.Page : 1;
            int size = request.Size > 0 ? request.Size : 10;

            var users = await query
                .Skip((page - 1) * size)
                .Take(size)
                .Select(u => new UserResponseDto
                {
                    Id = u.Id,
                    Name = u.Name,
                    Email = u.Email,
                    Tickets = new List<TicketDto>()
                })
                .ToListAsync();

            int totalPages = (int)Math.Ceiling((double)totalElements / size);

            return Results.Ok(new ListUsersResponseDto
            {
                Users = users,
                CurrentPage = page,
                PageSize = size,
                TotalPages = totalPages,
                TotalElements = totalElements
            });
        });
    }
}