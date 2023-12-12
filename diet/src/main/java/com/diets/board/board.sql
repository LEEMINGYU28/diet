create table boards(
  id int auto_increment primary key,
  title VARCHAR(100),
  content longtext,
  views int DEFAULT 0,
  created_at TIMESTAMP default current_timestamp,
  is_withdrew tinyint,
  user_id int,
 image_path VARCHAR(255),
  foreign key (user_id) references users(id)
);