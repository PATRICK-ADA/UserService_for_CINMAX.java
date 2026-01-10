using Cinmax.Api.Gateway.Data;
using Cinmax.Api.Gateway.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Cinmax.Api.Gateway.Endpoints;

public static class AuditEndpoints
{
    public static void MapAuditEndpoints(this WebApplication app)
    {
        app.MapGet("/api/audit-logs", async (ApplicationDbContext db, [AsParameters] ListAuditLogsRequestDto request) =>
        {
            var query = db.AuditLogs.Include(a => a.User).AsQueryable();

            if (request.UserId.HasValue)
                query = query.Where(a => a.UserId == request.UserId);
            
            if (!string.IsNullOrEmpty(request.Action))
                query = query.Where(a => a.Action == request.Action);
            
            if (!string.IsNullOrEmpty(request.Resource))
                query = query.Where(a => a.Resource == request.Resource);
            
            if (request.StartDate.HasValue)
                query = query.Where(a => a.Timestamp >= request.StartDate);
            
            if (request.EndDate.HasValue)
                query = query.Where(a => a.Timestamp <= request.EndDate);

            var totalElements = await query.CountAsync();

            int page = request.Page > 0 ? request.Page : 1;
            int size = request.Size > 0 ? request.Size : 10;

            var auditLogs = await query
                .OrderByDescending(a => a.Timestamp)
                .Skip((page - 1) * size)
                .Take(size)
                .Select(a => new AuditLogDto
                {
                    Id = a.Id,
                    UserId = a.UserId,
                    UserEmail = a.UserEmail,
                    Action = a.Action,
                    Resource = a.Resource,
                    Method = a.Method,
                    Path = a.Path,
                    StatusCode = a.StatusCode,
                    IpAddress = a.IpAddress,
                    UserAgent = a.UserAgent,
                    Timestamp = a.Timestamp,
                    Duration = a.Duration
                })
                .ToListAsync();

            int totalPages = (int)Math.Ceiling((double)totalElements / size);

            return Results.Ok(new ListAuditLogsResponseDto
            {
                AuditLogs = auditLogs,
                CurrentPage = page,
                PageSize = size,
                TotalPages = totalPages,
                TotalElements = totalElements
            });
        }).RequireAuthorization(policy => policy.RequireRole("SUPER_ADMIN", "ADMIN"));

        app.MapGet("/api/audit-logs/user/{userId:long}", async (ApplicationDbContext db, long userId, [AsParameters] ListAuditLogsRequestDto request) =>
        {
            var query = db.AuditLogs
                .Where(a => a.UserId == userId)
                .Include(a => a.User)
                .AsQueryable();

            var totalElements = await query.CountAsync();

            int page = request.Page > 0 ? request.Page : 1;
            int size = request.Size > 0 ? request.Size : 10;

            var auditLogs = await query
                .OrderByDescending(a => a.Timestamp)
                .Skip((page - 1) * size)
                .Take(size)
                .Select(a => new AuditLogDto
                {
                    Id = a.Id,
                    UserId = a.UserId,
                    UserEmail = a.UserEmail,
                    Action = a.Action,
                    Resource = a.Resource,
                    Method = a.Method,
                    Path = a.Path,
                    StatusCode = a.StatusCode,
                    IpAddress = a.IpAddress,
                    UserAgent = a.UserAgent,
                    Timestamp = a.Timestamp,
                    Duration = a.Duration
                })
                .ToListAsync();

            int totalPages = (int)Math.Ceiling((double)totalElements / size);

            return Results.Ok(new ListAuditLogsResponseDto
            {
                AuditLogs = auditLogs,
                CurrentPage = page,
                PageSize = size,
                TotalPages = totalPages,
                TotalElements = totalElements
            });
        }).RequireAuthorization(policy => policy.RequireRole("SUPER_ADMIN", "ADMIN"));
    }
}