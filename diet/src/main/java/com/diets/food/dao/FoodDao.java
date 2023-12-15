package com.diets.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diets.food.domain.Food;
import com.diets.user.domain.User;

@Repository
public class FoodDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Food> mapper = new RowMapper<Food>() {
		@Override
		public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Food(
					rs.getInt("id"),
					rs.getString("food_name"),	
					rs.getString("brand"),
					rs.getString("calorie"),
					rs.getString("carbohydrate"),
					rs.getString("protein"),
					rs.getString("fat"),
					rs.getInt("mealType"),
					rs.getInt("user_id"));
		}
	};
	public void add(Food food) {
		jdbcTemplate.update(
				"insert into savefoods (food_name, brand, calorie, carbohydrate, protein, fat, mealType, user_id) values (?, ?, ?, ?, ?, ?, ?,?)",
				food.getFoodName(), food.getBrand(), food.getCalorie(), food.getCarbohydrate(),
				food.getProtein(), food.getFat(),food.getMealType(), food.getUserId());
	}
    public List<Food> getFoodsByUserIdAndMealType(int userId, int mealType) {
        String sql = "select * from savefoods where user_id = ? and mealType = ?";
        return jdbcTemplate.query(sql, mapper, userId, mealType);
    }
}
