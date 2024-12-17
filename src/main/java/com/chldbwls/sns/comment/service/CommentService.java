package com.chldbwls.sns.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chldbwls.sns.comment.domain.Comment;
import com.chldbwls.sns.comment.dto.CommentDTO;
import com.chldbwls.sns.comment.repository.CommentRepository;
import com.chldbwls.sns.user.domain.User;
import com.chldbwls.sns.user.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(
			CommentRepository commentRepository
			, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
		
		Comment comment = Comment.builder()
				.postId(postId)
				.userId(userId)
				.contents(contents)
				.build();
		
		try {
			commentRepository.save(comment);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	// 게시글별로 댓글 조회
	public List<CommentDTO> getCommentList(int postId) {
		// 일치하는 행들 조회
		List<Comment> commentList =  commentRepository.findByPostId(postId);
		
		List<CommentDTO> commentDTOList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			int userId = comment.getUserId();
			
			User user = userService.getUserById(userId);
			
			CommentDTO commentDTO = CommentDTO.builder()
			.commentId(comment.getId())
			.userId(userId)
			.loginId(user.getLoginId())
			.contents(comment.getContents())
			.build();
			
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	}
	
	
	
	// 댓글 삭제 service(해당 게시글이 삭제되었을때)
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}

}
