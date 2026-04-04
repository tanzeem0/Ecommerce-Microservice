-- Insert Orders
INSERT INTO orders (order_status, total_price) VALUES ('PENDING', 1299.98);
INSERT INTO orders (order_status, total_price) VALUES ('CONFIRMED', 2149.97);
INSERT INTO orders (order_status, total_price) VALUES ('DELIVERED', 449.98);
INSERT INTO orders (order_status, total_price) VALUES ('CANCELLED', 899.99);
INSERT INTO orders (order_status, total_price) VALUES ('PENDING', 239.98);

-- Insert OrderItems (order_id references the orders above)
-- Order 1 items
INSERT INTO order_item (product_id, quantity, order_id) VALUES (1, 1, 1);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (3, 1, 1);

-- Order 2 items
INSERT INTO order_item (product_id, quantity, order_id) VALUES (4, 1, 2);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (6, 3, 2);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (8, 2, 2);

-- Order 3 items
INSERT INTO order_item (product_id, quantity, order_id) VALUES (3, 2, 3);

-- Order 4 items
INSERT INTO order_item (product_id, quantity, order_id) VALUES (2, 1, 4);

-- Order 5 items
INSERT INTO order_item (product_id, quantity, order_id) VALUES (8, 1, 5);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (10, 2, 5);