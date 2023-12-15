package com.diets.excel.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diets.excel.domain.Food;
import com.diets.excel.service.ExcelService;

@Controller
public class ExcelController {

	 @Autowired
	    private ExcelService foodService;

	    @GetMapping("/upload-local")
	    public String uploadLocalExcel() {
	        try {
	            foodService.saveDataFromLocalExcel("C:\\Users\\KGA\\git\\diet\\diet\\src\\main\\java\\com\\diets\\excell\\db.xlsx");
	            return "main/diary";
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "upload-failure";
	        }
	    }
	    
	    @GetMapping("/searchs")
	    public ResponseEntity<List<Food>> search(@RequestParam("keyword") String keyword,Model model) {
	    	
	        List<Food> searchResults = foodService.searchFoods(keyword);
	        model.addAttribute("searchResults", searchResults);
	        

	        return 	
	        		searchResults.isEmpty() ?
	                ResponseEntity.notFound().build() :
	                ResponseEntity.ok(searchResults);
	    }
	    
	    @GetMapping("/getFoodInfo")
	    @ResponseBody
	    public ResponseEntity<Object> getFoodInfo(@RequestParam("foodName") String foodName) {
	        try {
	            Food foodResult = foodService.getFoodInfoByName(foodName);

	            if (foodResult != null) {
	                return ResponseEntity.ok().body(foodResult);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Internal Server Error");
	        }
	    } 
}
