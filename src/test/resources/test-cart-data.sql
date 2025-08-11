TRUNCATE TABLE cart RESTART IDENTITY CASCADE;
TRUNCATE TABLE cart_item RESTART IDENTITY CASCADE;

INSERT INTO cart (user_id, last_modified)
VALUES (1, CURRENT_TIMESTAMP),
       (2, CURRENT_TIMESTAMP),
       (3, CURRENT_TIMESTAMP);

INSERT INTO cart_item (cart_id, product_name, product_id, sort_order, quantity, price, image_url, price_type,
                       total_price, currency)
VALUES (1, 'Product A', 101, 1, 2, 19.99, 'http://example.com/images/product_a.jpg', 'PAID', 39.98, 'USD'),
       (1, 'Product B', 102, 2, 1, 29.99, 'http://example.com/images/product_b.jpg', 'PAID', 29.99,  'USD'),
       (2, 'Product C', 103, 1, 3, 15.50, 'http://example.com/images/product_c.jpg', 'PAID', 46.50, 'USD'),
       (3, 'Product D', 104, 1, 1, 49.99, 'http://example.com/images/product_d.jpg', 'PAID', 49.99, 'USD'),
       (3, 'Product E', .105, 2, 5, 9.99, 'http://example.com/images/product_e.jpg', 'PAID', 49.95, 'USD');