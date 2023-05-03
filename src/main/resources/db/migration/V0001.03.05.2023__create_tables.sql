create table brands
(
    id    bigserial
        primary key,
    brand varchar(255)
);

create table categories
(
    id       bigserial
        primary key,
    category varchar(255)
);

create table products
(
    id          serial primary key,
    name        text    not null,
    count       integer not null,
    description text,
    price       integer not null,
    brand_id    bigint not null,
    foreign key (brand_id) references brands (id) on delete cascade,
    category_id bigint not null,
    foreign key (category_id) references categories (id) on delete cascade,
    image_link  text    not null
);