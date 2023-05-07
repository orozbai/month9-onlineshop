insert into users (name, email, username)
values ('John Doe', 'johndoe@example.com', 'johndoe'),
       ('Jane Smith', 'janesmith@example.com', 'janesmith'),
       ('Bob Johnson', 'bobjohnson@example.com', 'bobjohnson');

insert into reviews (description, description_time, user_id, product_id)
values ('Great product, I highly recommend it!', '2023-05-01 12:00:00', 1, 1),
       ('Not impressed, I would not buy again', '2023-05-02 14:00:00', 2, 1),
       ('Decent product, but not worth the price', '2023-05-03 10:00:00', 3, 2);

insert into orders (delivery, price, order_time, user_id, product_id)
values ('Bishkek', 12400, '2023-05-01 12:00:00', 1, 1),
       ('Osh', 12400, '2023-05-02 14:00:00', 2, 1),
       ('Sokuluk', 9900, '2023-05-03 10:00:00', 3, 2);