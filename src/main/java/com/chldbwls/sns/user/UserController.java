package com.chldbwls.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
	
	@PostMapping("/login-view")
	public String login() {
		return "user/login";
	}
	
	
}