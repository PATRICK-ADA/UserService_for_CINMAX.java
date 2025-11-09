-- PostgreSQL table creation script
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE tickets (
    ticket_id VARCHAR(50) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE movies (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    s3_url VARCHAR(500) NOT NULL,
    file_name VARCHAR(255),
    content_type VARCHAR(100),
    file_size BIGINT,
    master_playlist_url VARCHAR(500),
    processed_for_streaming BOOLEAN DEFAULT FALSE,
    buy_price DECIMAL(10,2),
    rental_price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movie_qualities (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    resolution VARCHAR(10) NOT NULL,
    bandwidth BIGINT NOT NULL,
    playlist_url VARCHAR(500),
    width INTEGER,
    height INTEGER,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE user_movies (
    user_id BIGINT NOT NULL,
    ticket_id VARCHAR(50) NOT NULL,
    movie_id BIGINT NOT NULL,
    rental_type VARCHAR(10) NOT NULL, -- 'buy' or 'rent'
    rental_duration INTEGER,
    purchase_date TIMESTAMP NOT NULL,
    expiry_date TIMESTAMP,
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE payments (
    payment_id VARCHAR(50) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL, -- 'pending', 'completed', 'failed'
    payment_date TIMESTAMP NOT NULL,
    ticket_id VARCHAR(50),
    rental_type VARCHAR(10),
    rental_duration INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE INDEX idx_movies_processed ON movies(processed_for_streaming);
CREATE INDEX idx_movie_qualities_movie_id ON movie_qualities(movie_id);
CREATE INDEX idx_user_movies_user_id ON user_movies(user_id);
CREATE INDEX idx_payments_user_id ON payments(user_id);