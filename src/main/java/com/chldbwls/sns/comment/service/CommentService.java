package com.chldbwls.sns.comment.service;

import org.springframework.stereotype.Service;

import com.chldbwls.sns.comment.domain.Comment;
import com.chldbwls.sns.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
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
	
	
	// 댓글 삭제 service(해당 게시글이 삭제되었을때)
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}

}
