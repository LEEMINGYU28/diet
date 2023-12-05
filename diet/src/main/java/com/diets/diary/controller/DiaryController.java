package com.diets.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {

	
	@GetMapping("/diary")
	public String diary() {
		return "main/diary";
	}
}
