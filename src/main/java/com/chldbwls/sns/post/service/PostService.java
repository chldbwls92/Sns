package com.chldbwls.sns.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chldbwls.sns.common.FileManager;

@Service
public class PostService {
	
//	private PostRepository postRepository;
//	
//	public PostService(PostRepository postRepository) {
//		this.postRepsotiroy = postRepository;
//	}
//	
//	public boolean addPost(int userId, String contents, MultipartFile file) {
//		
//		String imagePath = FileManager.saveFile(userId, file);
//		
//		Post post = Post.builder()
//		.userId(userId)
//		.contents(contents)
//		.imagePath(imagePath)
//		.buile();
//		
//		try {
//			postRepository.save(post);
//			return true;
//		} catch(Exception e) {
//			return false;
//		}
//	}

}
