create table users(
  id int auto_increment primary key,
  user_id VARCHAR(50) unique,
  password VARCHAR(64),
  name VARCHAR(15),
  age varchar(100),  
  height varchar(100),
  weight varchar(100),
  gender tinyint,
  kcal varchar(100),
  fat varchar(100),
  protein varchar(100),
  carbs varchar(100),
  created_at TIMESTAMP default current_timestamp
) charset=utf8;