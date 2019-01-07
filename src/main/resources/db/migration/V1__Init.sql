CREATE TABLE post (
  post_id     int AUTO_INCREMENT PRIMARY KEY,
  title       varchar(200)  NOT NULL,
  body        varchar(2000) NULL,
  created     datetime      NOT NULL,
  author_name varchar(50)   NULL
);
