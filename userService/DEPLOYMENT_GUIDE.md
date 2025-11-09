# CINMAX User Service - Deployment Guide

## Prerequisites

1. **AWS Account** with S3 bucket created
2. **Google Cloud Platform (GCP) Account** with Cloud Run and Cloud SQL enabled
3. **Domain name** (optional)

## AWS S3 Setup

1. Create S3 bucket:
```bash
aws s3 mb s3://your-cinmax-movies-bucket
```

2. Create IAM user with S3 permissions:
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:PutObject",
                "s3:GetObject",
                "s3:DeleteObject"
            ],
            "Resource": "arn:aws:s3:::your-cinmax-movies-bucket/*"
        }
    ]
}
```

## GCP Cloud Run Deployment

### 1. Set up Cloud SQL PostgreSQL Instance

```bash
# Create Cloud SQL instance
gcloud sql instances create cinmax-db \
  --database-version=POSTGRES_15 \
  --tier=db-f1-micro \
  --region=us-central1 \
  --root-password=your_secure_password

# Create database
gcloud sql databases create cinmax_db --instance=cinmax-db

# Create user (optional, or use default postgres user)
gcloud sql users create cinmax_user --instance=cinmax-db --password=your_user_password
```

### 2. Build and Deploy to Cloud Run

```bash
# Clone repository
git clone <your-repo-url>
cd userService

# Build application
./mvnw clean package -DskipTests

# Build Docker image
docker build -t gcr.io/your-gcp-project/cinmax-userservice .

# Push to Google Container Registry
docker push gcr.io/your-gcp-project/cinmax-userservice

# Deploy to Cloud Run
gcloud run deploy cinmax-userservice \
  --image gcr.io/your-gcp-project/cinmax-userservice \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated \
  --set-env-vars AWS_ACCESS_KEY_ID=your_access_key \
  --set-env-vars AWS_SECRET_ACCESS_KEY=your_secret_key \
  --set-env-vars S3_BUCKET_NAME=your-cinmax-movies-bucket \
  --set-env-vars DB_HOST=/cloudsql/your-gcp-project:us-central1:cinmax-db \
  --set-env-vars DB_NAME=cinmax_db \
  --set-env-vars DB_USER=postgres \
  --set-env-vars DB_PASSWORD=your_secure_password \
  --add-cloudsql-instances your-gcp-project:us-central1:cinmax-db
```

### 3. Environment Configuration

Environment variables are set during Cloud Run deployment. For local development, create `.env` file:
```env
AWS_ACCESS_KEY_ID=your_access_key
AWS_SECRET_ACCESS_KEY=your_secret_key
S3_BUCKET_NAME=your-cinmax-movies-bucket
DB_HOST=localhost
DB_NAME=cinmax_db
DB_USER=postgres
DB_PASSWORD=secure_password_here
```

### 4. Production Deployment

The deployment above is for production. To update:

```bash
# Build and push new image
docker build -t gcr.io/your-gcp-project/cinmax-userservice .
docker push gcr.io/your-gcp-project/cinmax-userservice

# Deploy update
gcloud run deploy cinmax-userservice \
  --image gcr.io/your-gcp-project/cinmax-userservice \
  --platform managed \
  --region us-central1
```

## Database Migration

```bash
# Connect to Cloud SQL PostgreSQL
gcloud sql connect cinmax-db --user=postgres --quiet

# Or use psql directly if you have it installed
psql "host=/cloudsql/your-gcp-project:us-central1:cinmax-db user=postgres dbname=cinmax_db"

# Verify tables
\dt
```

## Testing the Service

```bash
# Get Cloud Run service URL
SERVICE_URL=$(gcloud run services describe cinmax-userservice --region=us-central1 --format="value(status.url)")

# Test gRPC service
grpcurl -plaintext $SERVICE_URL:443 list

# Upload movie test
grpcurl -plaintext -d '{
  "title": "Test Movie",
  "description": "Test Description",
  "file_data": "base64_encoded_video_data",
  "file_name": "test.mp4",
  "content_type": "video/mp4"
}' $SERVICE_URL:443 UserService/UploadMovie
```

## Monitoring

```bash
# View Cloud Run service logs
gcloud logging read "resource.type=cloud_run_revision AND resource.labels.service_name=cinmax-userservice" --limit=50

# Monitor resource usage via GCP Console
# Go to Cloud Run > cinmax-userservice > Metrics tab

# Check Cloud SQL metrics
gcloud sql instances describe cinmax-db --format="value(settings.tier)"
```

## Security Considerations

1. Use Google Cloud Secret Manager for secrets instead of environment variables
2. Cloud Run automatically provides HTTPS
3. Configure VPC for Cloud SQL private IP access
4. Enable Cloud SQL IAM authentication
5. Set up Cloud Armor for additional security
6. Monitor S3 bucket access logs
7. Enable Cloud Run service accounts with minimal permissions

## Scaling

- Cloud Run automatically scales based on traffic (0 to N instances)
- Configure maximum instances and concurrency in Cloud Run settings
- Use Cloud SQL connection pooling (built-in with Cloud SQL Proxy)
- Consider Cloud CDN for S3 content delivery
- Monitor and optimize Cloud SQL and Cloud Run costs
- Set up alerts for budget and performance metrics