package com.diets.food.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diets.food.domain.Food;
import com.diets.food.service.FoodService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FoodController {

	@Autowired
	FoodService foodService;

	@PostMapping("/addFoodToMeal")
	public String addFoodToMeal(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		try {
			Food tempFood = new Food(Integer.parseInt(session.getAttribute("userId").toString()));
			if (map.get("foodName") != null) {
				tempFood.setFoodName(map.get("foodName"));
			}
			if (map.get("brand") != null) {
				tempFood.setBrand(map.get("brand"));
			}
			if (map.get("calorie") != null) {
				tempFood.setCalorie(map.get("calorie"));
				model.addAttribute("calorie", map.get("calorie"));
			}
			if (map.get("carbohydrate") != null) {
				tempFood.setCarbohydrate(map.get("carbohydrate"));
				model.addAttribute("carbohydrate", map.get("carbohydrate"));

			}
			if (map.get("protein") != null) {
				tempFood.setProtein(map.get("protein"));
				model.addAttribute("protein", map.get("protein"));
			}
			if (map.get("fat") != null) {
				tempFood.setFat(map.get("fat"));
				model.addAttribute("fat", map.get("fat"));

			}
			if (map.get("mealType") != null) {
				tempFood.setMealType(Integer.parseInt(map.get("mealType")));
			}

			foodService.add(tempFood);

			return "redirect:/?mealType=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("plusError", "추가 실패");
			return "redirect:/diary";
		}

	}

	@GetMapping("/diarys")
	public String getFoods(Model model, HttpSession session) {
	    int userId = Integer.parseInt(session.getAttribute("userId").toString());

	    List<Food> foods = foodService.getFoods(userId);

	    if (!foods.isEmpty()) {
	        Food totalFood = foods.get(0); 


	        String allKcal = totalFood.getCalorie();
	        String allCarbs = totalFood.getCarbohydrate();
	        String allProtein = totalFood.getProtein();
	        String allFat = totalFood.getFat();

	        double allKcalDouble = Double.parseDouble(allKcal);
	        double allCarbsDouble = Double.parseDouble(allCarbs);
	        double allProteinDouble = Double.parseDouble(allProtein);
	        double allFatDouble = Double.parseDouble(allFat);


	        model.addAttribute("allKcal", (int) allKcalDouble);
	        model.addAttribute("allCarbs", (int) allCarbsDouble);
	        model.addAttribute("allProtein", (int) allProteinDouble);
	        model.addAttribute("allFat", (int) allFatDouble);
	  
	        
	        Double caloriesSumGroup1 = foodService.getCaloriesSum(userId, 1);
	        Double caloriesSumGroup2 = foodService.getCaloriesSum(userId, 2);
	        Double caloriesSumGroup3 = foodService.getCaloriesSum(userId, 3);
	        Double caloriesSumGroup4 = foodService.getCaloriesSum(userId, 4);


	        model.addAttribute("caloriesSumGroup1", caloriesSumGroup1);
	        model.addAttribute("caloriesSumGroup2", caloriesSumGroup2);
	        model.addAttribute("caloriesSumGroup3", caloriesSumGroup3);
	        model.addAttribute("caloriesSumGroup4", caloriesSumGroup4);
	    
	    }

	    return "main/diary";
	}
}
