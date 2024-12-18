DROP TABLE orders IF EXISTS;

CREATE TABLE orders (
                        order_id BIGINT IDENTITY PRIMARY KEY,
                        customer_name VARCHAR(100) NOT NULL,
                        amount DOUBLE NOT NULL
);