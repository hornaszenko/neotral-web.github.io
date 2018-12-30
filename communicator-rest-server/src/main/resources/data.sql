INSERT INTO `persons` (`id`, `username`, `password`, `first_name`, `surname`, `active`)
            VALUES (1, 'kargass9', '$2a$04$tzZaS0wCxdyU7UWjLxaw7uVWbHhJ0duyGOGI/0eWfwUNtAYPqFvqa','Piotr', 'Krzymiński', false);

INSERT INTO `persons` (`id`, `username`, `password`, `first_name`, `surname`, `active`)
            VALUES (2, 'tomeczek123', '$2a$04$tzZaS0wCxdyU7UWjLxaw7uVWbHhJ0duyGOGI/0eWfwUNtAYPqFvqa','Tomasz', 'Maślak', false);

INSERT INTO `persons` (`id`, `username`, `password`, `first_name`, `surname`, `active`)
            VALUES (3, 'annaTamka', '$2a$04$tzZaS0wCxdyU7UWjLxaw7uVWbHhJ0duyGOGI/0eWfwUNtAYPqFvqa','Anna', 'Tamka', false);

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (1, 'Szlachta nie pracuje XD', false, 'https://www.ienglishstatus.com/wp-content/uploads/2018/04/Anonymous-Whatsapp-profile-picture.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (2, 'WMII', false, 'https://i.imgur.com/itElfV3.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (3, 'Niebieskie malinki', false, 'https://content-static.upwork.com/uploads/2014/10/02123010/profilephoto_goodcrop.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (4, 'Królowie', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRI4JuatGP6M5_Q0wYSkx2jAVzJff1FBaPYXV7zFbMngh5RV6J7');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (5, 'Prawy do lewego', false, 'https://pbs.twimg.com/profile_images/1029330268090449920/c0lN_8cL_400x400.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (6, 'FIFA Masters', false, 'http://www.twojaeuropa.pl/images/cache/nh82tf1i9lju.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (7, 'Sylwester z Polsatem', false, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Super_polsat_logo.png/240px-Super_polsat_logo.png');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (8, 'Rowerzyści Warszawa', false, 'http://www.kmart.com.au/wcsstore/Kmart/images/ncatalog/f/9/42476429-1-f.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (9, 'Spotted Łódź', false, 'http://spotted.us/wp-content/uploads/2018/05/spotted_fav.png');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (10, 'Dziewczyny maliny', false, 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Raspberries_%28Rubus_Idaeus%29.jpg/245px-Raspberries_%28Rubus_Idaeus%29.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (11, 'Programiści Java', false, 'http://sqool.forumszkoleniowe.com.pl/wp-content/uploads/2017/12/java_logo.png');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (12, 'Słodkie kotki', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTinG6yZjBsyPDaNlCMlICegEJZCbpTHF7KvjIDMzDWYGgUGkv8-w');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (13, 'ZOO Łódź', false, 'https://pbs.twimg.com/profile_images/1055935931902029824/zLiaGqLY_400x400.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (14, 'Angular', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkinbglh7BjO5I-xKCBdG4C2x7qeINPvXRajDoMjxTix8rP04baA');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (15, 'Pieskie życie', false, 'https://polki.pl/foto/4_3_SMALL/uwaga-tych-11-produktow-absolutnie-nie-moze-jesc-twoj-pies-2227140.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (16, 'Polaki Robaki', false, 'https://www.imperiumromanum.edu.pl/wp-content/uploads/2015/05/sds.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (17, 'Chemiki', false, 'https://cdn-images-1.medium.com/max/1600/1*4apcJgemYNhhihE6jm0JDw.png');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (18, 'Polscy kierowcy', false, 'https://images-na.ssl-images-amazon.com/images/I/51B3SqO6NFL._SX425_.jpg');

INSERT INTO `groups` (`id`, `name`, `is_private`, `image`)
VALUES (19, 'Madki', false, 'https://cdn.images.express.co.uk/img/dynamic/galleries/x701/389530.jpg');

INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,1);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,2);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,3);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,4);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,5);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (1,6);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (2,1);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (2,2);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (3,1);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (3,7);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (3,9);
INSERT INTO `users_groups` (`user_id`, `group_id`) VALUES (2,8);

INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (1, 1, 1, PARSEDATETIME('28/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Witam wszystkich szlachciców');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (2, 2, 1, PARSEDATETIME('28/12/2018 12:00:48', 'dd/MM/yyyy HH:mm:ss'), 'Cześć!');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (3, 3, 1, PARSEDATETIME('28/12/2018 12:00:52', 'dd/MM/yyyy HH:mm:ss'), 'Siemanko!');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (4, 1, 1, PARSEDATETIME('28/12/2018 12:02:34', 'dd/MM/yyyy HH:mm:ss'), 'Nazwa grupy jest nieprzypadkowa i liczę, że wszczcy członkowie tej grupy będą wiedzieli jak zachywać się w sieci. Jeżeli ktoś z członków group ma jakieś pytania to proszę zadawać je tutaj.');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (5, 3, 1, PARSEDATETIME('28/12/2018 12:02:01', 'dd/MM/yyyy HH:mm:ss'), 'Myślę, że wszystko jest jasne :)');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (6, 2, 1, PARSEDATETIME('28/12/2018 12:05:45', 'dd/MM/yyyy HH:mm:ss'), 'W jakim celu ta grupa została założona?');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (7, 1, 1, PARSEDATETIME('28/12/2018 12:07:21', 'dd/MM/yyyy HH:mm:ss'), 'Bo taki miałem kaprys. Jakieś mądrzejsze pytania?');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (8, 2, 1, PARSEDATETIME('28/12/2018 12:08:46', 'dd/MM/yyyy HH:mm:ss'), 'Panie nie obrażaj Pan dobre? Bo sprawa do admina poleci!');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (9, 1, 1, PARSEDATETIME('28/12/2018 12:10:00', 'dd/MM/yyyy HH:mm:ss'), 'Cieszcie się, że nie mam możliwości nikogo z tej grupy wyrzucić. Jak się nie podoba to se Pan ją opuść sam.');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (10, 3, 1, PARSEDATETIME('28/12/2018 12:10:00', 'dd/MM/yyyy HH:mm:ss'), '...');

INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (11, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (12, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (13, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (14, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (15, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (16, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (17, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (18, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (19, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (20, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (21, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (22, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (23, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (24, 1, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');
INSERT INTO `messages` (`id`, `author_id`, `group_id`, `creation_date`, `content`) VALUES (25, 2, 2, PARSEDATETIME('29/12/2018 12:00:00', 'dd/MM/yyyy HH:mm:ss'), 'Hello');