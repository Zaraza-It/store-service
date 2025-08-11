--liquibase formatted sql
--changeset zaraza:3
CREATE TABLE cart
(
    id            SERIAL PRIMARY KEY,
    user_id       BIGINT,
    last_modified TIMESTAMP
);

--liquibase formatted sql
--changeset zaraza:4
CREATE TABLE cart_item
(
    id           SERIAL PRIMARY KEY,
    cart_id      BIGINT,
    product_name VARCHAR(255),
    product_id   BIGINT,
    sort_order   INT,
    quantity     INT,
    price        DECIMAL(10, 2),
    image_url    VARCHAR(255),
    price_type   VARCHAR(50),
    total_price  DECIMAL(10, 2),
    currency     VARCHAR(20),
    FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE
);