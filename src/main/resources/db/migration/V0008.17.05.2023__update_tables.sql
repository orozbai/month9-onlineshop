alter table users
    add column role text not null default 'FULL';
alter table users
    add column enabled bool not null default true;