create table boards(
  id int auto_increment primary key,
  title VARCHAR(100),
  content longtext,
  views int DEFAULT 0,
  created_at TIMESTAMP default current_timestamp,
  is_withdrew tinyint,
  user_id int,
  foreign key (user_id) references users(id)
);