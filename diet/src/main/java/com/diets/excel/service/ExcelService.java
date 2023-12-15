package com.diets.excel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diets.excel.ExcelUtils;
import com.diets.excel.dao.FoodDAO;
import com.diets.excel.domain.Food;

@Service
public class ExcelService {

    @Autowired
    private FoodDAO foodRepository;

    public void saveDataFromLocalExcel(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            List<Food> foods = ExcelUtils.readFromExcel(inputStream);
            foodRepository.saveAll(foods);
        }
    }
    public List<Food> searchFoods(String keyword) {
        // 특정 키워드를 사용하여 데이터베이스에서 검색
        return foodRepository.findByFoodNameContainingIgnoreCase(keyword);
    }
    
    public Food getFoodInfoByName(String foodName) {
        try {
            List<Food> searchResults = foodRepository.findByFoodNameContainingIgnoreCase(foodName);

            if (!searchResults.isEmpty()) {
                return searchResults.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 어떤 예외가 발생했는지 로깅 또는 반환을 조정할 수 있습니다.
            throw new RuntimeException("Error retrieving food information", e);
        }
    }
}
