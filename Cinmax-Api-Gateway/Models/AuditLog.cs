using System.ComponentModel.DataAnnotations;

namespace Cinmax.Api.Gateway.Models
{
    public class AuditLog
    {
        [Key]
        public long Id { get; set; }
        
        public long? UserId { get; set; }
        public string? UserEmail { get; set; }
        public string Action { get; set; } = string.Empty;
        public string Resource { get; set; } = string.Empty;
        public string Method { get; set; } = string.Empty;
        public string Path { get; set; } = string.Empty;
        public int StatusCode { get; set; }
        public string? RequestBody { get; set; }
        public string? ResponseBody { get; set; }
        public string IpAddress { get; set; } = string.Empty;
        public string UserAgent { get; set; } = string.Empty;
        public DateTime Timestamp { get; set; } = DateTime.UtcNow;
        public long Duration { get; set; } // in milliseconds
        
        // Navigation property
        public User? User { get; set; }
    }
}