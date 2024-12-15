package com.chldbwls.sns.like.service;

import org.springframework.stereotype.Service;

import com.chldbwls.sns.like.domain.Like;
import com.chldbwls.sns.like.repository.LikeRepository;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	// like를 다른 곳에서 사용할 수 있겠다는 전제로 like 따로 만듬
	public boolean addLike(int postId, int userId) {
		
		Like like = Like.builder()
				.postId(postId)
				.userId(userId)
				.build();
		
		try {
			likeRepository.save(like);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	

}
