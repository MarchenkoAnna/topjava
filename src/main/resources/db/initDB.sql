DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS user_roles;
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
CREATE UNIQUE INDEX users_unique_id_idx ON users (id);

CREATE TABLE user_roles
(
  user_id INT UNSIGNED NOT NULL,
  role    VARCHAR(30),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE TABLE meals
(
  id                INT UNSIGNED NOT NULL AUTO_INCREMENT,
  description       VARCHAR(255) NOT NULL,
  calories          INT UNSIGNED NOT NULL,
  user_id           INT UNSIGNED NOT NULL,
  dateTime              TIMESTAMP NOT NULL,
  PRIMARY KEY ( id ),
  CONSTRAINT user_meals_idx UNIQUE (user_id, id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
ALTER TABLE meals AUTO_INCREMENT = 100000;
CREATE UNIQUE INDEX meals_unique_id_idx ON meals (id);
