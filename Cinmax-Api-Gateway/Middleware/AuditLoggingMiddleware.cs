using Cinmax.Api.Gateway.Data;
using Cinmax.Api.Gateway.Models;
using System.Diagnostics;
using System.Security.Claims;
using System.Text;

namespace Cinmax.Api.Gateway.Middleware
{
    public class AuditLoggingMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly IServiceScopeFactory _scopeFactory;
        private readonly ILogger<AuditLoggingMiddleware> _logger;

        public AuditLoggingMiddleware(RequestDelegate next, IServiceScopeFactory scopeFactory, ILogger<AuditLoggingMiddleware> logger)
        {
            _next = next;
            _scopeFactory = scopeFactory;
            _logger = logger;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            var stopwatch = Stopwatch.StartNew();
            
            // Skip audit logging for health checks and swagger
            if (ShouldSkipAuditLogging(context.Request.Path))
            {
                await _next(context);
                return;
            }

            // Capture request details
            var requestBody = await CaptureRequestBody(context.Request);
            var originalResponseBodyStream = context.Response.Body;

            using var responseBodyStream = new MemoryStream();
            context.Response.Body = responseBodyStream;

            try
            {
                await _next(context);
            }
            finally
            {
                stopwatch.Stop();
                
                // Capture response details
                var responseBody = await CaptureResponseBody(responseBodyStream);
                
                // Copy response back to original stream
                responseBodyStream.Seek(0, SeekOrigin.Begin);
                await responseBodyStream.CopyToAsync(originalResponseBodyStream);
                
                // Log the audit entry
                await LogAuditEntry(context, requestBody, responseBody, stopwatch.ElapsedMilliseconds);
            }
        }

        private static bool ShouldSkipAuditLogging(PathString path)
        {
            var skipPaths = new[] { "/health", "/swagger", "/favicon.ico", "/_framework" };
            return skipPaths.Any(skipPath => path.StartsWithSegments(skipPath));
        }

        private static async Task<string?> CaptureRequestBody(HttpRequest request)
        {
            if (request.ContentLength == null || request.ContentLength == 0)
                return null;

            request.EnableBuffering();
            var buffer = new byte[Convert.ToInt32(request.ContentLength)];
            await request.Body.ReadAsync(buffer, 0, buffer.Length);
            request.Body.Position = 0;
            
            return Encoding.UTF8.GetString(buffer);
        }

        private static async Task<string?> CaptureResponseBody(MemoryStream responseBodyStream)
        {
            responseBodyStream.Seek(0, SeekOrigin.Begin);
            var responseBody = await new StreamReader(responseBodyStream).ReadToEndAsync();
            responseBodyStream.Seek(0, SeekOrigin.Begin);
            
            return string.IsNullOrEmpty(responseBody) ? null : responseBody;
        }

        private async Task LogAuditEntry(HttpContext context, string? requestBody, string? responseBody, long duration)
        {
            try
            {
                using var scope = _scopeFactory.CreateScope();
                var dbContext = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();

                var auditLog = new AuditLog
                {
                    UserId = GetUserId(context),
                    UserEmail = GetUserEmail(context),
                    Action = DetermineAction(context),
                    Resource = DetermineResource(context.Request.Path),
                    Method = context.Request.Method,
                    Path = context.Request.Path + context.Request.QueryString,
                    StatusCode = context.Response.StatusCode,
                    RequestBody = TruncateIfNeeded(requestBody, 5000),
                    ResponseBody = TruncateIfNeeded(responseBody, 5000),
                    IpAddress = GetClientIpAddress(context),
                    UserAgent = context.Request.Headers.UserAgent.ToString(),
                    Duration = duration
                };

                dbContext.AuditLogs.Add(auditLog);
                await dbContext.SaveChangesAsync();
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Failed to log audit entry");
            }
        }

        private static long? GetUserId(HttpContext context)
        {
            var userIdClaim = context.User?.FindFirst(ClaimTypes.NameIdentifier)?.Value;
            return long.TryParse(userIdClaim, out var userId) ? userId : null;
        }

        private static string? GetUserEmail(HttpContext context)
        {
            return context.User?.FindFirst(ClaimTypes.Email)?.Value;
        }

        private static string DetermineAction(HttpContext context)
        {
            var method = context.Request.Method;
            var path = context.Request.Path.Value?.ToLower() ?? "";

            return method switch
            {
                "GET" => path.Contains("login") ? "LOGIN_ATTEMPT" : "VIEW",
                "POST" => path.Contains("register") ? "REGISTER" : 
                         path.Contains("login") ? "LOGIN" : 
                         path.Contains("tickets") ? "CREATE_TICKET" : "CREATE",
                "PUT" => "UPDATE",
                "DELETE" => "DELETE",
                _ => method
            };
        }

        private static string DetermineResource(PathString path)
        {
            var pathValue = path.Value?.ToLower() ?? "";
            
            if (pathValue.Contains("/users")) return "USER";
            if (pathValue.Contains("/tickets")) return "TICKET";
            if (pathValue.Contains("/movies")) return "MOVIE";
            if (pathValue.Contains("/auth")) return "AUTH";
            
            return "UNKNOWN";
        }

        private static string GetClientIpAddress(HttpContext context)
        {
            return context.Request.Headers["X-Forwarded-For"].FirstOrDefault() ??
                   context.Request.Headers["X-Real-IP"].FirstOrDefault() ??
                   context.Connection.RemoteIpAddress?.ToString() ?? "Unknown";
        }

        private static string? TruncateIfNeeded(string? text, int maxLength)
        {
            if (string.IsNullOrEmpty(text) || text.Length <= maxLength)
                return text;
                
            return text.Substring(0, maxLength) + "... [TRUNCATED]";
        }
    }
}