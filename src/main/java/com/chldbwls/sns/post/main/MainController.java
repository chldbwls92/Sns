package com.chldbwls.sns.post.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chldbwls.sns.post.dto.CardDTO;
import com.chldbwls.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	private PostService postService;
	
	public MainController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/home/main-view")
	public String home(
			Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("id");
		
		List<CardDTO> cardList = postService.getPostList(userId);
		
		model.addAttribute("cardList", cardList);
		
		return "home/main";
	}
	

}
