create table brand(
    id serial primary key,
    brand_name text not null
);

create table category(
    id serial primary key,
    category_name text not null
);

create table product(
    id serial primary key,
    name text not null,
    description text,
    price integer not null,
    count integer not null,
    brand_fk integer not null,
    foreign key (brand_fk) references brand(id) on delete cascade,
    category_fk integer not null,
    foreign key (category_fk) references category(id) on delete cascade,
    image_link text not null
);