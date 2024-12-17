package com.chldbwls.sns.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chldbwls.sns.comment.dto.CommentDTO;
import com.chldbwls.sns.comment.service.CommentService;
import com.chldbwls.sns.common.FileManager;
import com.chldbwls.sns.like.service.LikeService;
import com.chldbwls.sns.post.domain.Post;
import com.chldbwls.sns.post.dto.CardDTO;
import com.chldbwls.sns.post.repository.PostRepository;
import com.chldbwls.sns.user.domain.User;
import com.chldbwls.sns.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	public PostService(
			PostRepository postRepository
			, UserService userService
			, LikeService likeService
			, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.commentService = commentService;
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
	
	// user의 postList 가져오기
	public List<CardDTO> getUserPostList(int userId) {
		
		// user의 list 가져오기
		List<Post> postList =  postRepository.findByUserIdOrderByIdDesc(userId);
	
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			// user table 조회
			// loginId뿐 아니라 email부터 다 얻어올 수 있음
			
			User user = userService.getUserById(userId);
						
			likeService.isLike(post.getId(), userId);
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.loginId(user.getLoginId())
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
	}
	
	
	// 모든 postList 가져오기
	public List<CardDTO> getPostList(int loginUserId) {
		
		// 조회된 게시글마다 작성자의 로그인 id 얻어오기
		// 매번 알아봐야함
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		// 반복되며 생성된 것을 list에 저장
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			// user table 조회
			// loginId뿐 아니라 email부터 다 얻어올 수 있음
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			int likeCount = likeService.getLikeCount(post.getId());

			boolean isLike = likeService.isLike(post.getId(), loginUserId);
			
			List<CommentDTO> commentList = commentService.getCommentList(post.getId());
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.loginId(user.getLoginId())
			.likeCount(likeCount) // postId를 가진 행이 몇개인지 확인
			.isLike(isLike)
			.commentList(commentList)
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
	}
	

	// post delete service
	public boolean deletePost(int id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			// 파일 url path 가져와서 삭제하기 (file remove method 가져오기)
//			FileManager.removeFile(post.getImagePath());
			
			// 댓글과 좋아요도 삭제하기(대응되는 postId 다 삭제)
			likeService.deleteLikeByPostId(id);
			commentService.deleteCommentByPostId(id);
			
			postRepository.delete(post);
			
			return true;
		} else {
			return false;
		}
	}
	

}
