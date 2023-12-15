create table savefoods(
  id int auto_increment primary key,
  food_name longtext,
  brand VARCHAR(255),
  calorie longtext,
  carbohydrate longtext,
  protein longtext,
  fat longtext,
  mealType int,
  user_id int,
  foreign key (user_id) references users(id)
);