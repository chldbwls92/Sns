package com.chldbwls.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@GetMapping("/create-view")
	public String createPost() {
		return "post/create";
	}
	
	@GetMapping("/list-view")
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
