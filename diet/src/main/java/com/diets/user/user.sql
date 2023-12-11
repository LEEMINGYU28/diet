create table users(
  id int auto_increment primary key,
  user_id VARCHAR(50) not null,
  password VARCHAR(64) not null,
  name VARCHAR(15) not null,
  age int not null,  
  height int not null,
  weight int not null,
  gender tinyint,
  created_at TIMESTAMP default current_timestamp
) charset=utf8;
