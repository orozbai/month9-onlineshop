create table users
(
    id       serial
        primary key,
    name     text not null,
    email    text not null,
    username text not null
);

create table reviews
(
    id               serial primary key,
    description      text,
    description_time timestamp,
    user_id          integer not null,
    foreign key (user_id) references users (id) on delete cascade,
    product_id       integer not null,
    foreign key (product_id) references products (id) on delete cascade
);

create table orders
(
    id         serial primary key,
    delivery   text      not null,
    price      integer   not null,
    order_time timestamp not null,
    user_id    integer   not null,
    foreign key (user_id) references users (id) on delete cascade,
    product_id integer   not null,
    foreign key (product_id) references products (id) on delete cascade
);