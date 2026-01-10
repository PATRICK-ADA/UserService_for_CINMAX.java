using Cinmax.Api.Gateway.Models;
using Microsoft.AspNetCore.Mvc;

namespace Cinmax.Api.Gateway.Endpoints;

public static class TicketEndpoints
{
    public static void MapTicketEndpoints(this WebApplication app)
    {
        app.MapPost("/api/tickets", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, CreateTicketRequestDto request) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.CreateTicketRequest { UserId = request.UserId, MovieName = request.MovieName };
            var response = await userService.CreateTicketAsync(grpcRequest);
            return Results.Ok(new TicketResponseDto
            {
                Ticket = new TicketDto
                {
                    TicketId = response.Ticket.TicketId,
                    UserId = response.Ticket.UserId,
                    MovieName = response.Ticket.MovieName
                }
            });
        });

        app.MapGet("/api/tickets", async (Cinmax.Api.Gateway.Protos.UserService.UserServiceClient userService, [AsParameters] ListTicketsRequestDto request) =>
        {
            var grpcRequest = new Cinmax.Api.Gateway.Protos.ListTicketsRequest { UserId = request.UserId };
            var response = await userService.ListTicketsAsync(grpcRequest);

            var allTickets = response.Tickets.Select(t => new TicketDto
            {
                TicketId = t.TicketId,
                UserId = t.UserId,
                MovieName = t.MovieName
            }).ToList();

            int page = request.Page > 0 ? request.Page : 1;
            int size = request.Size > 0 ? request.Size : 10;
            var paginatedTickets = allTickets.Skip((page - 1) * size).Take(size).ToList();
            int totalPages = (int)Math.Ceiling((double)allTickets.Count / size);

            return Results.Ok(new ListTicketsResponseDto
            {
                Tickets = paginatedTickets,
                CurrentPage = page,
                PageSize = size,
                TotalPages = totalPages,
                TotalElements = allTickets.Count
            });
        });
    }
}