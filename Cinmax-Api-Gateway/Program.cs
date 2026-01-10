using Cinmax.Api.Gateway.Data;
using Cinmax.Api.Gateway.Models;
using Cinmax.Api.Gateway.Middleware;
using Cinmax.Api.Gateway.Endpoints;
using Cinmax.Api.Gateway.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.ResponseCompression;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.IO.Compression;
using System.Security.Claims;
using System.Text;
using BCrypt.Net;

var builder = WebApplication.CreateBuilder(args);

// Configure port for Cloud Run
var port = Environment.GetEnvironmentVariable("PORT") ?? "8080";
builder.WebHost.UseUrls($"http://+:{port}");

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Add JWT authentication
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = true,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,
            ValidIssuer = builder.Configuration["Jwt:Issuer"] ?? "Cinmax",
            ValidAudience = builder.Configuration["Jwt:Audience"] ?? "Cinmax",
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"] ?? "supersecretkey"))
        };
    });

builder.Services.AddAuthorization();

// Add DbContext
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

// Add in-memory cache
builder.Services.AddMemoryCache();

// Add cache key tracker
builder.Services.AddSingleton<CacheKeyTracker>();

// Add response compression
builder.Services.AddResponseCompression(options =>
{
    options.EnableForHttps = true;
    options.Providers.Add<GzipCompressionProvider>();
});

// Add gRPC client for UserService
builder.Services.AddGrpcClient<Cinmax.Api.Gateway.Protos.UserService.UserServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090"); // Adjust port as needed
});

// Add HttpClient for PaymentService
builder.Services.AddHttpClient("PaymentService", client =>
{
    client.BaseAddress = new Uri("http://localhost:5284"); // Adjust port as needed
});

var app = builder.Build();

// Add exception handling middleware
app.UseMiddleware<ExceptionHandlingMiddleware>();

// Add audit logging middleware (after authentication)
app.UseMiddleware<AuditLoggingMiddleware>();

// Configure the HTTP request pipeline.
app.UseSwagger();
app.UseSwaggerUI();

app.UseHttpsRedirection();

app.UseResponseCompression();

app.UseAuthentication();
app.UseAuthorization();

// Map all endpoints
app.MapAuthEndpoints();
app.MapUserEndpoints();
app.MapTicketEndpoints();
app.MapMovieEndpoints();
app.MapPaymentEndpoints();
app.MapAuditEndpoints();

app.Run();