-- liquibase formatted sql

-- changeset dev:001-create-users
CREATE TABLE users (
  id            BIGSERIAL PRIMARY KEY,
  username      VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
-- rollback DROP TABLE users;