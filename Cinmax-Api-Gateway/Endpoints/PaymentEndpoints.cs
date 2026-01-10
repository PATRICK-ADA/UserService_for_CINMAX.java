using Cinmax.Api.Gateway.Models;

namespace Cinmax.Api.Gateway.Endpoints;

public static class PaymentEndpoints
{
    public static void MapPaymentEndpoints(this WebApplication app)
    {
        app.MapPost("/api/payments/initialize", async (IHttpClientFactory httpClientFactory, InitializePaymentRequestDto request) =>
        {
            var client = httpClientFactory.CreateClient("PaymentService");
            var response = await client.PostAsJsonAsync("api/payment/initialize", request);
            if (response.IsSuccessStatusCode)
            {
                var result = await response.Content.ReadFromJsonAsync<InitializePaymentResponseDto>();
                return Results.Ok(result);
            }
            return Results.BadRequest(await response.Content.ReadAsStringAsync());
        });

        app.MapPost("/api/payments/verify", async (IHttpClientFactory httpClientFactory, VerifyPaymentRequestDto request) =>
        {
            var client = httpClientFactory.CreateClient("PaymentService");
            var response = await client.PostAsJsonAsync("api/payment/verify", request);
            if (response.IsSuccessStatusCode)
            {
                var result = await response.Content.ReadFromJsonAsync<VerifyPaymentResponseDto>();
                return Results.Ok(result);
            }
            return Results.BadRequest(await response.Content.ReadAsStringAsync());
        });
    }
}