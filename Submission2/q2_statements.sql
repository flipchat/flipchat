--
-- COMP 421 - Submission 02
-- Question 02
-- Group 40
--

---
--- Clean DB
---
DROP TABLE IF EXISTS cs421g40.user_achievements;
DROP TABLE IF EXISTS cs421g40.user_categories;
DROP TABLE IF EXISTS cs421g40.notification;
DROP TABLE IF EXISTS cs421g40.achievement;
DROP TABLE IF EXISTS cs421g40.comment;
DROP TABLE IF EXISTS cs421g40.transaction;
DROP TABLE IF EXISTS cs421g40.bid;
DROP TABLE IF EXISTS cs421g40.product;
DROP TABLE IF EXISTS cs421g40.category;
DROP TABLE IF EXISTS cs421g40.users;

---
--- Table structure for FlipChat
---
CREATE TABLE cs421g40.users (
  u_id        SERIAL       NOT NULL,
  email       VARCHAR(50)  NOT NULL,
  password    VARCHAR(255) NOT NULL,
  full_name   VARCHAR(100) NOT NULL,
  address     VARCHAR(100),
  admin_since TIMESTAMP,
  PRIMARY KEY (u_id)
);

CREATE TABLE cs421g40.category (
  cat_id        SERIAL      NOT NULL,
  name          VARCHAR(50) NOT NULL,
  description   TEXT,
  product_count INT         NOT NULL DEFAULT 0,
  user_count    INT         NOT NULL DEFAULT 0,
  created_by    INT         NOT NULL,
  PRIMARY KEY (cat_id),
  FOREIGN KEY (created_by) REFERENCES cs421g40.users (u_id)
);

CREATE TABLE cs421g40.product (
  p_id        SERIAL      NOT NULL,
  title       VARCHAR(50) NOT NULL,
  description TEXT,
  price       MONEY       NOT NULL,
  image       VARCHAR(50),
  is_sold     BOOLEAN     NOT NULL DEFAULT FALSE,
  datetime    TIMESTAMP   NOT NULL,
  expiry      TIMESTAMP   NOT NULL,
  u_id        INT         NOT NULL,
  cat_id      INT         NOT NULL,
  bid_id      INT,
  PRIMARY KEY (p_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id),
  FOREIGN KEY (cat_id) REFERENCES cs421g40.category (cat_id)
);

CREATE TABLE cs421g40.bid (
  bid_id   SERIAL    NOT NULL,
  datetime TIMESTAMP NOT NULL,
  price    MONEY     NOT NULL,
  u_id     INT       NOT NULL,
  p_id     INT       NOT NULL,
  t_id     INT,
  PRIMARY KEY (bid_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id),
  FOREIGN KEY (p_id) REFERENCES cs421g40.product (p_id)
);

CREATE TABLE cs421g40.transaction (
  t_id     SERIAL    NOT NULL,
  paid     BOOLEAN   NOT NULL DEFAULT FALSE,
  datetime TIMESTAMP NOT NULL,
  u_id     INT       NOT NULL,
  bid_id   INT       NOT NULL,
  PRIMARY KEY (t_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id),
  FOREIGN KEY (bid_id) REFERENCES cs421g40.bid (bid_id)
);

CREATE TABLE cs421g40.notification (
  n_id    SERIAL NOT NULL,
  content TEXT   NOT NULL,
  type    INT, -- 0-transaction, 1-new product, 2-comment
  p_id    INT,
  t_id    INT,
  PRIMARY KEY (n_id),
  FOREIGN KEY (p_id) REFERENCES cs421g40.product (p_id),
  FOREIGN KEY (t_id) REFERENCES cs421g40.transaction (t_id)
);

CREATE TABLE cs421g40.comment (
  c_id     SERIAL    NOT NULL,
  content  TEXT      NOT NULL,
  datetime TIMESTAMP NOT NULL,
  u_id     INT       NOT NULL,
  p_id     INT       NOT NULL,
  PRIMARY KEY (c_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id),
  FOREIGN KEY (p_id) REFERENCES cs421g40.product (p_id)
);

CREATE TABLE cs421g40.achievement (
  a_id        SERIAL      NOT NULL,
  a_type      INT, -- 1 - seller, 2 - buyer, 3 - general
  label       VARCHAR(50) NOT NULL,
  condition   VARCHAR(50) NOT NULL,
  description TEXT        NOT NULL,
  PRIMARY KEY (a_id)
);

CREATE TABLE cs421g40.user_achievements (
  a_id INT NOT NULL,
  u_id INT NOT NULL,
  FOREIGN KEY (a_id) REFERENCES cs421g40.achievement (a_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id)
);

CREATE TABLE cs421g40.user_categories (
  cat_id INT NOT NULL,
  u_id   INT NOT NULL,
  FOREIGN KEY (cat_id) REFERENCES cs421g40.category (cat_id),
  FOREIGN KEY (u_id) REFERENCES cs421g40.users (u_id)
);