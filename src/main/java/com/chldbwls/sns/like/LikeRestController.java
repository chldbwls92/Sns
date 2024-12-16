package com.chldbwls.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chldbwls.sns.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {

	private LikeService likeService;
	
	public LikeRestController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	
	// like 필요한 정보 전달
	@PostMapping("/post/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("id");
		
		Map<String, String> resultMap = new HashMap<>();
		if(likeService.addLike(postId, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 좋아요 취소
	// 로그인한 사용자가 한 특정 게시글 좋아요 삭제
	@DeleteMapping("/post/unlike")
	public Map<String, String> unlike(
			@RequestParam("postId") int postId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("id");
		
		Map<String, String> resultMap = new HashMap<>();
		if(likeService.deleteLike(postId, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");	
		}
		return resultMap;
	}
	
}
