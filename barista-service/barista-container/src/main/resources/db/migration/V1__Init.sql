CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

DROP
TYPE IF EXISTS order_preparation_status;
CREATE
TYPE order_preparation_status AS ENUM ('ORDER_ACCEPTED', 'ORDER_CANCELED', 'ORDER_FINISHED');

DROP TABLE IF EXISTS "barista".orders CASCADE;

CREATE TABLE "barista".orders
(
    id uuid NOT NULL,
    order_preparation_status order_preparation_status NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);