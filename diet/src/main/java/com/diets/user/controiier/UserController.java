package com.diets.user.controiier;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diets.user.domain.User;
import com.diets.user.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String logins() {
		return "user/login";
	}

	@GetMapping("/join")
	public String regist(Model model) {
//		model.addAttribute("title","회원가입");
//		model.addAttribute("path","/user/join");
//		model.addAttribute("content","registFragment");
//		model.addAttribute("contentHead","registFragmentHead");
		return "user/join";
	}

	@GetMapping("/mypage")
	public String mypage() {
		return "user/mypage";
	}
	
	@PostMapping("user/regist")
	public String registPost(@RequestParam Map<String,String> map,Model model) {
		try {
			User tempUser = new User(
					map.get("userId"),
					map.get("password"),
					map.get("name"),
					map.get("age"),
					Integer.parseInt(map.get("height")),
					Integer.parseInt(map.get("weight")),
					Integer.parseInt(map.get("gender")));
			
			userService.add(tempUser);
			
			return  "redirect:/login";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("requestError","회원가입 실패");
			return  "redirect:/user/regist";
		}
	
	}
	@PostMapping("user/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		User tempUser = new User();
		tempUser.setUserId(map.get("userId"));
		tempUser.setPassword(map.get("password"));
		tempUser = userService.login(tempUser);

		if (tempUser != null) {
			session.setAttribute("userName", tempUser.getName());
			session.setAttribute("userId", tempUser.getId());
		}
		return "redirect:/diary";
	}
}
