insert into restaurant (id, name, restaurant_type, seats) values (nextval('restaurant_seq_id'), 'Le Pizzo', 'ITALIEN', 50);
insert into restaurant (id, name, restaurant_type, seats) values (nextval('restaurant_seq_id'), 'Gypse', 'FRANCAIS', 40);
insert into restaurant (id, name, restaurant_type, seats) values (nextval('restaurant_seq_id'), 'Class Croute', 'FRANCAIS', 30);

insert into client (id, email, first_name, last_name) values (nextval('client_seq_id'), 'jgrand@simplon.co', 'Jules', 'Grand');

insert into review (id, comment, note, client_id, restaurant_id) values (nextval('review_seq_id'), 'De la bombe', 5, 1, 2);
