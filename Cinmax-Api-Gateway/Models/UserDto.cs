using Microsoft.EntityFrameworkCore;

namespace Cinmax.Api.Gateway.Models;

public static class Role
{
    public const string SUPER_ADMIN = "SUPER_ADMIN";
    public const string ADMIN = "ADMIN";
    public const string PARTNER = "PARTNER";
    public const string EXCO = "EXCO";
    public const string STAFFMEMBER = "STAFFMEMBER";
    public const string USER = "USER";
}

public class User
{
    public long Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public string PasswordHash { get; set; } = string.Empty;
    public string Role { get; set; } = string.Empty;
}

public record RegisterRequestDto
{
    public string Name { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public string Password { get; set; } = string.Empty;
}

public record LoginRequestDto
{
    public string Email { get; set; } = string.Empty;
    public string Password { get; set; } = string.Empty;
}

public record LoginResponseDto
{
    public string Token { get; set; } = string.Empty;
    public UserDto User { get; set; } = new();
}

public record UserDto
{
    public long Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public string Role { get; set; } = string.Empty;
}

public record UpdateUserRequestDto
{
    public string Name { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
}

public record UserResponseDto
{
    public long Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public List<TicketDto> Tickets { get; set; } = new();
}

public record TicketDto
{
    public string TicketId { get; set; } = string.Empty;
    public long UserId { get; set; }
    public string MovieName { get; set; } = string.Empty;
}

public record CreateTicketRequestDto
{
    public long UserId { get; set; }
    public string MovieName { get; set; } = string.Empty;
}

public record TicketResponseDto
{
    public TicketDto Ticket { get; set; } = new();
}

public record ListTicketsRequestDto
{
    public long UserId { get; set; }
    public int Page { get; set; } = 1;
    public int Size { get; set; } = 10;
}

public record ListTicketsResponseDto
{
    public List<TicketDto> Tickets { get; set; } = new();
    public int CurrentPage { get; set; }
    public int PageSize { get; set; }
    public int TotalPages { get; set; }
    public long TotalElements { get; set; }
}

public record ListUsersRequestDto
{
    public int Page { get; set; } = 1;
    public int Size { get; set; } = 10;
}

public record ListUsersResponseDto
{
    public List<UserResponseDto> Users { get; set; } = new();
    public int CurrentPage { get; set; }
    public int PageSize { get; set; }
    public int TotalPages { get; set; }
    public long TotalElements { get; set; }
}

public record MovieDto
{
    public long Id { get; set; }
    public string Title { get; set; } = string.Empty;
    public string Description { get; set; } = string.Empty;
    public string Category { get; set; } = string.Empty;
    public double BuyPrice { get; set; }
    public double RentalPrice { get; set; }
    public List<int> RentalDurations { get; set; } = new();
}

public record ListMoviesRequestDto
{
    public int Page { get; set; } = 1;
    public int Size { get; set; } = 10;
}

public record ListMoviesResponseDto
{
    public List<MovieDto> Movies { get; set; } = new();
    public int CurrentPage { get; set; }
    public int PageSize { get; set; }
    public int TotalPages { get; set; }
    public long TotalElements { get; set; }
}

public record GetMovieStreamRequestDto
{
    public long MovieId { get; set; }
}

public record MovieStreamResponseDto
{
    public string MasterPlaylistUrl { get; set; } = string.Empty;
    public List<StreamQualityDto> Qualities { get; set; } = new();
    public bool ProcessedForStreaming { get; set; }
}

public record StreamQualityDto
{
    public string Resolution { get; set; } = string.Empty;
    public long Bandwidth { get; set; }
    public string PlaylistUrl { get; set; } = string.Empty;
    public int Width { get; set; }
    public int Height { get; set; }
}

public record UploadMovieRequestDto
{
    public string Title { get; set; } = string.Empty;
    public string Description { get; set; } = string.Empty;
    public string Category { get; set; } = string.Empty;
    public byte[] FileData { get; set; } = Array.Empty<byte>();
    public string FileName { get; set; } = string.Empty;
    public string ContentType { get; set; } = string.Empty;
}

public record UploadMoviesRequestDto
{
    public List<UploadMovieRequestDto> Movies { get; set; } = new();
}

public record UploadMoviesResponseDto
{
    public List<MovieDto> Movies { get; set; } = new();
    public int UploadedCount { get; set; }
}

public record MovieResponseDto
{
    public MovieDto Movie { get; set; } = new();
}

// Payment DTOs
public record InitializePaymentRequestDto
{
    public long UserId { get; set; }
    public long MovieId { get; set; }
    public string MovieName { get; set; } = string.Empty;
    public decimal Amount { get; set; }
    public string? Email { get; set; }
    public string? CallbackUrl { get; set; }
}

public record InitializePaymentResponseDto
{
    public bool Success { get; set; }
    public string? AuthorizationUrl { get; set; }
    public string? AccessCode { get; set; }
    public string? Reference { get; set; }
    public string? Message { get; set; }
}

public record VerifyPaymentRequestDto
{
    public string Reference { get; set; } = string.Empty;
}

public record VerifyPaymentResponseDto
{
    public bool Success { get; set; }
    public string? Status { get; set; }
    public string? Message { get; set; }
    public long? UserId { get; set; }
    public long? MovieId { get; set; }
    public decimal? Amount { get; set; }
}

public record FastTransferRequestDto
{
    public long UserId { get; set; }
    public long MovieId { get; set; }
}

public record FastTransferResponseDto
{
    public bool Success { get; set; }
    public string FirstSegmentUrl { get; set; } = string.Empty;
    public int TotalSegments { get; set; }
    public string Message { get; set; } = string.Empty;
}

public record CachedSegmentRequestDto
{
    public long UserId { get; set; }
    public int SegmentIndex { get; set; }
}

public record CachedSegmentResponseDto
{
    public string SegmentUrl { get; set; } = string.Empty;
    public bool Available { get; set; }
}