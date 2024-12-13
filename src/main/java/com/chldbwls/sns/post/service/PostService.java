package com.chldbwls.sns.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chldbwls.sns.common.FileManager;
import com.chldbwls.sns.post.domain.Post;
import com.chldbwls.sns.post.repository.PostRepository;
import com.chldbwls.sns.user.domain.User;
import com.chldbwls.sns.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	public boolean addPost(int userId, String contents, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			postRepository.save(post);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	// 모든 postList 가져오기
	public List<Post> getPostList() {
		
		// 조회된 게시글마다 작성자의 로그인 id 얻어오기
		// 매번 알아봐야함
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		for(Post post:postList) {
			int userId = post.getUserId();
			
			// user table 조회
			// loginId뿐 아니라 email부터 다 얻어올 수 있음
			User user = userService.getUserById(userId);
		}
		
		return postRepository.findAllByOrderByIdDesc();
	}
	

}
