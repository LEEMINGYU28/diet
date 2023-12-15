package com.diets.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.diets.user.domain.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class DiaryController {

	@GetMapping("/diary")
	public String diary(HttpSession session, Model model) {

	    return "main/diary";
	}
	@GetMapping("/details")
	public String viewDetails() {
		return "main/viewDetails";
	}
	@GetMapping("/morning")
	public String morning() {
		return "main/morning";
	}
	
}
