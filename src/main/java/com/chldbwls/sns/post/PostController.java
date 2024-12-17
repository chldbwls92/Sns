package com.chldbwls.sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chldbwls.sns.post.domain.Post;
import com.chldbwls.sns.post.dto.CardDTO;
import com.chldbwls.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	

	@GetMapping("/create-view")
	public String createPost() {
		return "post/create";
	}
	
	@GetMapping("/list-view")
	public String postList(
			Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("id");
		
		List<CardDTO> cardList = postService.getUserPostList(userId);
		model.addAttribute("cardList", cardList);
		
		return "post/list";
	}
	

	
}
