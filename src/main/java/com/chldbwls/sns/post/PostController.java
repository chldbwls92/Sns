package com.chldbwls.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

	@GetMapping("/home/main-view")
	public String home() {
		return "home/main";
	}
	
	@GetMapping("/post/create-view")
	public String createPost() {
		return "post/create";
	}
	
	@GetMapping("/post/list-view")
	public String postList() {
		return "post/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		
		return "redirect:/user/login-view";
	}
	
}
