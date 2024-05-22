
INSERT INTO member values (1, '우희민', 'publisher', 'publisher', 'publisher', '12345678910', 'dddd@gmail.com');

INSERT INTO publisher (name, email, phone_number, business_number) values ('흼출판사', 'admin@heem.com','01012345678', '1234567891');

INSERT INTO publisher (name, email, phone_number, business_number) values ('신세계출판사', 'admin@ssg.com','01012245678', '1233456789');

INSERT INTO publisher_manager (member_id, publisher_id) values (1, 1);

INSERT INTO `genre` (name) values ('소설');

INSERT INTO `book` (title, ISBN, publisher_id, genre_id, price, pub_date, author, pages, size) values ('희민이의 책', '12345678910', 1, 1, 10000, '2021-06-01', '희민', 100, 'A4');

INSERT INTO `book` (title, ISBN, publisher_id, genre_id, price, pub_date, author, pages, size) values ('신세계 책', '12345678911', 1, 1, 10000, '2021-06-010', '신세계', 24, 'A4');

insert into in_warehousing (date, status, publisher_manager_id) values
    ('2023-06-01', 'PENDING', 1);

insert into in_warehousing (date, status, publisher_manager_id) values
    ('2023-06-03', 'PENDING', 1);

insert into in_warehousing (date, status, publisher_manager_id) values
    ('2023-06-05', 'PENDING', 1);

INSERT INTO `order` (quantity, book_id, in_warehousing_id) values (10, 1, 1);

INSERT INTO `order` (quantity, book_id, in_warehousing_id) values (25, 2, 1);

INSERT INTO `order` (quantity, book_id, in_warehousing_id) values (10, 1, 2);

INSERT INTO `order` (quantity, book_id, in_warehousing_id) values (25, 2, 2);

INSERT INTO `order` (quantity, book_id, in_warehousing_id) values (10, 1, 3);


INSERT INTO member values (2, 'inven', 'inven', 'inven', 'INVENTORY', '12345678911', 'ddddd@gmail.com');

INSERT INTO inventory_manager (entry_date, emp_number, inventory_id, member_id) values ('2023-06-01', '1234578911', 1, 2);