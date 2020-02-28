insert into restaurant (id, name, restaurant_type, seats) values (restaurant_seq_id.nextval, 'Le Pizzo', 'ITALIEN', 50);
insert into restaurant (id, name, restaurant_type, seats) values (restaurant_seq_id.nextval, 'Gypse', 'FRANCAIS', 40);
insert into restaurant (id, name, restaurant_type, seats) values (restaurant_seq_id.nextval, 'Class Croute', 'FRANCAIS', 30);

insert into client (id, email, first_name, last_name) values (client_seq_id.nextval, 'jgrand@simplon.co', 'Jules', 'Grand');

insert into review (id, comment, note, client_id, restaurant_id) values (review_seq_id.nextval, 'De la bombe', 5, 1, 2);
