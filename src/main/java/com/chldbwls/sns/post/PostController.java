package com.chldbwls.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/home/main-view")
	public String home() {
		return "home/main";
	}
}
