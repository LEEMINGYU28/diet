package com.diets.food.domain;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Controller
public class Food {

	private int id;
	
	private String foodName;
	
	private String brand;
	
	private String calorie;
	
	private String carbohydrate;
	
	private String protein;
	
	private String fat;
	
	private int mealType;
	
	private int userId;
	
	public Food(int userId){
		this.userId = userId;
	}

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
