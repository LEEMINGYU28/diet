package com.diets.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diets.user.domain.User;


@Repository
public class UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<User> mapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(
					rs.getInt("id"),
					rs.getString("user_id"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("age"),
					rs.getString("height"),
					rs.getString("weight"),
					rs.getInt("gender"),
					rs.getString("kcal"),
					rs.getString("fat"),
					rs.getString("protein"),
					rs.getString("carbs"),
					rs.getTimestamp("created_at"));
		}
	};

	public void add(User user) {
		jdbcTemplate.update(
				"insert into users (user_id, password, name, age, height, weight, gender, kcal, fat, protein, carbs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				user.getUserId(), user.getPassword(), user.getName(), user.getAge(), user.getHeight(),
				user.getWeight(), user.getGender(),user.getKcal(),user.getFat(),user.getProtein(),user.getCarbs());
	}
	public User get(String userId) {
		return jdbcTemplate.queryForObject("select * from users where user_id=?", mapper, userId);
	}
}
