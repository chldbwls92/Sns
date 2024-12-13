package com.chldbwls.sns.post.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chldbwls.sns.post.domain.Post;
import com.chldbwls.sns.post.service.PostService;

@Controller
public class MainController {
	
	private PostService postService;
	
	public MainController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/home/main-view")
	public String home(Model model) {
		
		List<Post> postList = postService.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "home/main";
	}
	

}
