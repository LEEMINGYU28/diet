package com.diets.board.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.diets.board.domain.Board;
import com.diets.board.sevice.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	
	@Autowired
	BoardService boardService;
	int count = 5;
//	@GetMapping("/board")
//	public String board() {
//		return "board/list";
//	}
	@GetMapping("/add")
		public String addBoard() {
			return "board/add";
		}
	@GetMapping("/view")
	public String boardView() {
		return "board/view";
	}
	@GetMapping("/calendar")
	public String calendar() {
		return "layout/calendar";
		
	}
	@GetMapping("/board")
	public String boardMainPage(Model model, @RequestParam Map<String, String> data) {
		int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
		model.addAttribute("title", "게시판");
		model.addAttribute("path", "/board/index");
		model.addAttribute("content", "boardFragment");
		model.addAttribute("contentHead", "boardFragmentHead");
		model.addAttribute("list", boardService.getAll(page, count));
//		model.addAttribute("pageCount", boardService.getPageCount(count));
		return "board/list";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam Map<String, String> data, HttpSession session) {
		if(session.getAttribute("userName") != null) {
			String tempContent = data.get("content");
			tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
			tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
			boardService.add(new Board(
					data.get("title"), 
					tempContent, 
					Integer.parseInt(session.getAttribute("userId").toString())
					));
		}
		
		return "redirect:/board";
	}
	@PostMapping("/upload")
	@ResponseBody
	public ModelMap uploadImage(MultipartHttpServletRequest req) {
		ModelMap model = new ModelMap();
		try {
			MultipartFile uploadFile = req.getFile("upload");
			System.out.println(uploadFile.getOriginalFilename());
			String originName = uploadFile.getOriginalFilename();
			String[] tempNames = originName.split(".");
			String ext = originName.substring(originName.indexOf("."));
			String randomName = UUID.randomUUID() + ext;
			String savePath = "C:\\Users\\KGA\\git\\diet\\diet\\src\\main\\resources\\static\\imgs\\" + randomName;
			String uploadUrl = "/imgs/" + randomName;
 			
			File file = new File(savePath);
			uploadFile.transferTo(file);

			model.addAttribute("uploaded",true);
			model.addAttribute("url",uploadUrl);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
					return model;
	}
}


