package com.diets.food.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diets.food.domain.Food;
import com.diets.food.service.FoodService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FoodController {

	@Autowired
	FoodService foodService;
	@PostMapping("/addFoodToMeal")
	public String addFoodToMeal(@RequestParam Map<String,String> map,Model model, HttpSession session) {
		try {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			Food tempFood = new Food(userId);
					if(map.get("foodName") != null) {
						tempFood.setFoodName(map.get("foodName"));
					}
					if(map.get("brand") != null) {
						tempFood.setBrand(map.get("brand"));
					}
					if(map.get("calorie") != null) {
						tempFood.setCalorie(map.get("calorie"));
						model.addAttribute("calorie", map.get("calorie"));
					}
					if(map.get("carbohydrate") != null) {
						tempFood.setCarbohydrate(map.get("carbohydrate"));
						model.addAttribute("carbohydrate", map.get("carbohydrate"));
						
					}
					if(map.get("protein") != null) {
						tempFood.setProtein(map.get("protein"));
						model.addAttribute("protein", map.get("protein"));
					}
					if(map.get("fat") != null) {
						tempFood.setFat(map.get("fat"));
						 model.addAttribute("fat", map.get("fat"));
						
					}
					if(map.get("mealType") != null) {
						tempFood.setMealType(Integer.parseInt(map.get("mealType")));
					}
				       if (map.get("mealType") != null) {
				            int mealType = Integer.parseInt(map.get("mealType"));

				            List<Food> foods = foodService.getFoodsByUserIdAndMealType(userId, mealType);

				            double totalCalorie = 0.0;
				            double totalCarbohydrate = 0.0;
				            double totalProtein = 0.0;
				            double totalFat = 0.0;

				            for (Food food : foods) {
				                totalCalorie += Double.parseDouble(food.getCalorie());
				                totalCarbohydrate += Double.parseDouble(food.getCarbohydrate());
				                totalProtein += Double.parseDouble(food.getProtein());
				                totalFat += Double.parseDouble(food.getFat());
				            }

				            model.addAttribute("totalCalorie", totalCalorie);
				            System.out.println(totalCalorie);
				            model.addAttribute("totalCarbohydrate", totalCarbohydrate);
				            model.addAttribute("totalProtein", totalProtein);
				            model.addAttribute("totalFat", totalFat);
				        }
	
			foodService.add(tempFood);
			
			return  "redirect:/?mealType=";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("plusError","추가 실패");
			return  "redirect:/diary";
		}
	
	}

}
