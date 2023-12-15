package com.diets.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diets.food.dao.FoodDao;
import com.diets.food.domain.Food;

@Service
public class FoodService {

	
	@Autowired
	private FoodDao foodDao;
	
	public void add(Food food) {
		foodDao.add(food);
	}
	  public List<Food> getFoodsByUserIdAndMealType(int userId, int mealType) {
	        return foodDao.getFoodsByUserIdAndMealType(userId, mealType);
	    }
}
