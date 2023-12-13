package com.diets.excel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diets.excel.domain.Food;

@Repository
public interface FoodDAO extends JpaRepository<Food, Long>{

	List<Food> findByFoodNameContainingIgnoreCase(String keyword);
}
