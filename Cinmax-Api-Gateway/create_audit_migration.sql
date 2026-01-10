-- Migration script to add AuditLogs table
CREATE TABLE IF NOT EXISTS "AuditLogs" (
    "Id" BIGSERIAL PRIMARY KEY,
    "UserId" BIGINT NULL,
    "UserEmail" VARCHAR(255) NULL,
    "Action" VARCHAR(50) NOT NULL,
    "Resource" VARCHAR(50) NOT NULL,
    "Method" VARCHAR(10) NOT NULL,
    "Path" VARCHAR(500) NOT NULL,
    "StatusCode" INTEGER NOT NULL,
    "RequestBody" TEXT NULL,
    "ResponseBody" TEXT NULL,
    "IpAddress" VARCHAR(45) NOT NULL,
    "UserAgent" VARCHAR(1000) NULL,
    "Timestamp" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    "Duration" BIGINT NOT NULL DEFAULT 0,
    
    CONSTRAINT "FK_AuditLogs_Users_UserId" 
        FOREIGN KEY ("UserId") REFERENCES "Users" ("Id") 
        ON DELETE SET NULL
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS "IX_AuditLogs_UserId" ON "AuditLogs" ("UserId");
CREATE INDEX IF NOT EXISTS "IX_AuditLogs_Timestamp" ON "AuditLogs" ("Timestamp");
CREATE INDEX IF NOT EXISTS "IX_AuditLogs_UserId_Timestamp" ON "AuditLogs" ("UserId", "Timestamp");
CREATE INDEX IF NOT EXISTS "IX_AuditLogs_Action" ON "AuditLogs" ("Action");
CREATE INDEX IF NOT EXISTS "IX_AuditLogs_Resource" ON "AuditLogs" ("Resource");