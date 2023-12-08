package com.diets.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/board")
	public String board() {
		return "board/list";
	}
	@GetMapping("/add")
		public String addBoard() {
			return "board/add";
		}
	@GetMapping("/view")
	public String boardView() {
		return "board/view";
	}
}
