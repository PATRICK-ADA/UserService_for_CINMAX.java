# Use the official .NET 8 SDK image to build the app
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src

# Copy csproj and restore dependencies
COPY ["Cinmax-Api-Gateway.csproj", "./"]
RUN dotnet restore "Cinmax-Api-Gateway.csproj"

# Copy everything else and build
COPY . .
RUN dotnet build "Cinmax-Api-Gateway.csproj" -c Release -o /app/build

# Publish the app
FROM build AS publish
RUN dotnet publish "Cinmax-Api-Gateway.csproj" -c Release -o /app/publish /p:UseAppHost=false

# Use the official ASP.NET Core runtime image
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS final
WORKDIR /app
COPY --from=publish /app/publish .

# Expose the port (Cloud Run will set PORT environment variable)
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["dotnet", "Cinmax-Api-Gateway.dll"]