DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL,
  PRIMARY KEY ( id )
);
ALTER TABLE users AUTO_INCREMENT = 100000;
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INT UNSIGNED NOT NULL,
  role    VARCHAR(30),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals (
  id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id     INT UNSIGNED NOT NULL,
  date_time    TIMESTAMP NOT NULL,
  description TEXT NOT NULL,
  calories    INT NOT NULL,
  PRIMARY KEY ( id ),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
ALTER TABLE meals AUTO_INCREMENT = 100002;
CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time);
