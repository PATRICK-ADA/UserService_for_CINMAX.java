namespace Cinmax.Api.Gateway.Models
{
    public class AuditLogDto
    {
        public long Id { get; set; }
        public long? UserId { get; set; }
        public string? UserEmail { get; set; }
        public string Action { get; set; } = string.Empty;
        public string Resource { get; set; } = string.Empty;
        public string Method { get; set; } = string.Empty;
        public string Path { get; set; } = string.Empty;
        public int StatusCode { get; set; }
        public string IpAddress { get; set; } = string.Empty;
        public string UserAgent { get; set; } = string.Empty;
        public DateTime Timestamp { get; set; }
        public long Duration { get; set; }
    }

    public class ListAuditLogsRequestDto
    {
        public int Page { get; set; } = 1;
        public int Size { get; set; } = 10;
        public long? UserId { get; set; }
        public string? Action { get; set; }
        public string? Resource { get; set; }
        public DateTime? StartDate { get; set; }
        public DateTime? EndDate { get; set; }
    }

    public class ListAuditLogsResponseDto
    {
        public List<AuditLogDto> AuditLogs { get; set; } = new();
        public int CurrentPage { get; set; }
        public int PageSize { get; set; }
        public int TotalPages { get; set; }
        public int TotalElements { get; set; }
    }
}