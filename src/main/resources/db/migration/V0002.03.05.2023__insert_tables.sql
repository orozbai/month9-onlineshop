insert into brand(brand_name)
values ('Asus'),
       ('Samsung'),
       ('Apple'),
       ('Acer'),
       ('Intel'),
       ('Lenovo'),
       ('Dell'),
       ('Hp');

insert into category(category_name)
values ('CPU'),
       ('Motherboard'),
       ('Processor cooling system'),
       ('RAM'),
       ('Video card'),
       ('Storage device'),
       ('Frame'),
       ('Power unit');

insert into product(name, description, price, count, brand_fk, category_fk, image_link)
values ('Intel Core i9-13900k', 'best cpu overall', 12400, 67, 5, 1, 'intel_core_i9_cpu.jpg'),
       ('Asus Prime Z490-A',
        'Asus Prime series motherboards are a reinforced power system, in the ability to organize full fledged of components',
        9900, 24, 1, 2, 'motherboard_prime.jpg'),
       ('Cooler x4-4', 'deep cool', 400, 107, 2, 3, 'cooler.jpg'),
       ('Apple Ram s32', '32 gb Ram', 8000, 87, 3, 4, 'Apple_ram_s32.jpg'),
       ('Acer video card zn-4000', 'very fast 16 gb video card', 42200, 23, 4, 5, 'zn_4000_video_card.jpg'),
       ('Lenovo sr-2048', 'hdd 2tb lenovo', 4000, 37, 6, 6, 'lenovo_sr2048_hdd.jpg'),
       ('jump tower', 'big frame tower version', 2000, 37, 7, 7, 'big_tower_frame.jpg'),
       ('vx-700 Hp', '700w power Hp brand', 6000, 20, 8, 8, 'power_unit_vx700.jpg');