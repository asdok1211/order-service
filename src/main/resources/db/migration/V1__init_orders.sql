-- Enable UUID generation extension
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the orders table
CREATE TABLE orders
(
    id           UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    status       VARCHAR(32)    NOT NULL,
    total_amount NUMERIC(10, 2) NOT NULL,
    idempotency_key UUID NOT NULL UNIQUE,

    created_at   TIMESTAMPTZ NOT NULL,
    modified_at  TIMESTAMPTZ,
    created_by   VARCHAR(100) NOT NULL,
    modified_by  VARCHAR(100)
);