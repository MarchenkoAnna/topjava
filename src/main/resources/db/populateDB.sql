DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER TABLE users AUTO_INCREMENT = 100000;
ALTER TABLE meals AUTO_INCREMENT = 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
  select 'ROLE_USER', id from users where name = 'User';

INSERT INTO user_roles (role, user_id)
  select 'ROLE_ADMIN', id from users where name = 'Admin';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-30 10:00:00','Завтрак', 500, id from users where name = 'Admin';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-31 14:00:00','Обед', 1500, id from users where name = 'Admin';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-30 10:00:00', 'Завтрак', 550, id from users where name = 'User';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-30 14:00:00', 'Обед', 1600, id from users where name = 'User';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-30 21:00:00','Ужин', 850, id from users where name = 'User';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-31 10:00:00','Завтрак', 550, id from users where name = 'User';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-31 13:00:00','Обед', 1100, id from users where name = 'User';

INSERT INTO meals (dateTime, description, calories, user_id)
  select '2015-05-31 20:00:00','Ужин', 400, id from users where name = 'User';

#DATE_FORMAT('2015-05-31 20:00:00', '%Y-%m-%d %H:%i')