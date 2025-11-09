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

CREATE TABLE movies (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(1000),
    file_data VARBINARY(MAX),
    file_name NVARCHAR(255),
    content_type NVARCHAR(100),
    master_playlist_url NVARCHAR(500),
    processed_for_streaming BIT DEFAULT 0,
    buy_price DECIMAL(10,2),
    rental_price DECIMAL(10,2)
);

CREATE TABLE movie_qualities (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    resolution NVARCHAR(10) NOT NULL,
    bandwidth BIGINT NOT NULL,
    playlist_url NVARCHAR(500),
    width INT,
    height INT,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE user_movies (
    user_id BIGINT NOT NULL,
    ticket_id NVARCHAR(50) NOT NULL,
    movie_id BIGINT NOT NULL,
    rental_type NVARCHAR(10) NOT NULL, -- 'buy' or 'rent'
    rental_duration INT,
    purchase_date DATETIME2 NOT NULL,
    expiry_date DATETIME2,
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE payments (
    payment_id NVARCHAR(50) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    status NVARCHAR(20) NOT NULL, -- 'pending', 'completed', 'failed'
    payment_date DATETIME2 NOT NULL,
    ticket_id NVARCHAR(50),
    rental_type NVARCHAR(10),
    rental_duration INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);
