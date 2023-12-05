package com.diets.user.controiier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

	@GetMapping("/login")
	public String logins() {
			return "user/login";
		}
	@GetMapping("/join")
	public String joins() {
			return "user/join";
		}
}

