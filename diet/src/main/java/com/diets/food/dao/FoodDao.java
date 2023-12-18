package com.diets.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	private RowMapper<Food> mappers = new RowMapper<Food>() {
	    @Override
	    public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new Food(
	                0,  // id
	                "", // foodName
	                "", // brand
	                rs.getString("all_kcal"),  // calorie
	                rs.getString("all_carbs"), // carbohydrate
	                rs.getString("all_protein"), // protein
	                rs.getString("all_fat"), // fat
	                0, //mealType
	                0  //user_id
	        );
	    }
	};
	public void add(Food food) {
		jdbcTemplate.update(
				"insert into savefoods (food_name, brand, calorie, carbohydrate, protein, fat, mealType, user_id) values (?, ?, ?, ?, ?, ?, ?,?)",
				food.getFoodName(), food.getBrand(), food.getCalorie(), food.getCarbohydrate(),
				food.getProtein(), food.getFat(),food.getMealType(), food.getUserId());
	}
    public List<Food> getFoods(int userId) {
        String sql = "select sum(calorie) AS all_kcal,sum(carbohydrate) AS all_carbs, sum(protein) AS all_protein, sum(fat) AS all_fat from savefoods WHERE user_id = ?";
        return jdbcTemplate.query(sql, mappers, userId);
    }
    public Double getCaloriesSum(int userId, int mealType) {
        String sql = "select sum(calorie) AS all_kcal from savefoods WHERE user_id = ? AND mealType = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, userId, mealType);
    
    }
    public Map<String, Object> getMealTypeData(int userId, int mealType) {
        String sql = "select sum(calorie) AS all_kcal,sum(carbohydrate) AS all_carbs,sum(protein) AS all_protein,sum(fat) AS all_fat from savefoods WHERE user_id = ? AND mealType = ?";
        return jdbcTemplate.queryForMap(sql, userId, mealType);
    
    }

}
