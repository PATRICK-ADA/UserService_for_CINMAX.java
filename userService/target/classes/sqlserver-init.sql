-- SQL Server table creation script
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL
);

CREATE TABLE tickets (
    ticket_id NVARCHAR(50) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_name NVARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
