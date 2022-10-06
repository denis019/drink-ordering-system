CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PLACED', 'CANCELLED', 'FINISHED');

DROP TYPE IF EXISTS drink_type;
CREATE TYPE drink_type AS ENUM ('COFFEE', 'TEA', 'CHOCOLATE');

DROP TABLE IF EXISTS "order".orders CASCADE;

CREATE TABLE "order".orders
(
    id               uuid         NOT NULL,
    order_status     order_status NOT NULL,
    drink_type       drink_type   NOT NULL,
    drink_additions  character varying COLLATE pg_catalog."default",
    created_at       timestamp,
    updated_at       timestamp
);