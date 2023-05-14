insert into brands(brand)
values ('Nvidia'),
       ('AMD'),
       ('HyperX');

insert into products(name, description, price, count, brand_id, category_id, image_link)
values ('Intel Core i9-11900K', '11th Gen desktop processor with 8 cores and 16 threads', 599, 5, 5, 1, 'cpu.jpg'),
       ('AMD Ryzen 7 5800X', 'Desktop processor with 8 cores and 16 threads', 449, 7, 9, 1, 'cpu.jpg'),
       ('Nvidia GeForce RTX 3080', 'High-end graphics card with 10GB GDDR6X memory and ray tracing', 799, 3, 10, 5,
        'video-card.jpg'),
       ('Corsair Vengeance RGB Pro', '32GB (2 x 16GB) DDR4 RAM kit with 3200MHz speed and RGB lighting', 199, 11, 10, 4,
        'ram.jpg'),
       ('Samsung 970 EVO Plus', 'Internal SSD with 2TB storage capacity and NVMe interface', 449, 2, 2, 6, 'hdd.jpg'),
       ('Asus ROG Strix Z590-E', 'ATX motherboard with PCIe 4.0 support and WiFi 6E', 349, 4, 1, 2, 'motherboard.jpg'),
       ('Noctua NH-D15 chromax.black', 'Dual-tower CPU cooler with two 140mm fans and black color scheme', 109, 12, 11,
        3, 'cpu-cooler.jpg'),
       ('EVGA Supernova 850W', '80 Plus Gold certified power supply with fully modular cables', 149, 8, 8, 8,
        'power-unit.jpg'),
       ('Fractal Design Meshify C', 'Mid-tower PC case with tempered glass side panel and mesh front panel', 99, 15, 7,
        7, 'frame.jpg'),
       ('Nvidia GeForce RTX 3070', 'Wired gaming keyboard with user-swappable switches and RGB lighting', 349, 6,
        10, 4, 'video-card.jpg');