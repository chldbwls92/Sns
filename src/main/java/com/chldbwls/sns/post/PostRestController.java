package com.chldbwls.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chldbwls.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("contents") String contents
			,@RequestParam("imagePath") MultipartFile file
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("id");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, contents, file)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(
			@RequestParam("id") int id) {
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.deletePost(id)) {
			resultMap.put("resultMap", "success");
		} else {
			resultMap.put("resultMap", "fail");
		}
		
		return resultMap;
	}
	

 
}
