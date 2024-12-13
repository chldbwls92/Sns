package com.chldbwls.sns.user;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chldbwls.sns.user.domain.User;
import com.chldbwls.sns.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
//api controller
	
	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("birthday") LocalDate birthday) {
		
		

		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, birthday)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 중복확인
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicatedId(@RequestParam("loginId") String loginId) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		// 있으면 true 없으면 false
		resultMap.put("isDuplicate", userService.isDuplicateId(loginId));
		
		return resultMap;
	}
	
	
	// login api
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		// 실제로 이 사람이 있는지 확인하기
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			// 성공했을 경우
			// 서블릿 기반
			HttpSession session = request.getSession();
			
			// user id, user memo
			// 어디서든 session값 가져와서 쓸 수 있음
			session.setAttribute("id", user.getId());
			session.setAttribute("password", user.getPassword());
			// 사용자정보의 이름, id 정보 저장(업캐스팅 되어서)
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
	}
	
	
}
