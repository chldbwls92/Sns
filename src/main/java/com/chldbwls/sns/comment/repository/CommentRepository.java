package com.chldbwls.sns.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.comment.domain.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	// 게시글 삭제될경우 댓글 다 삭제하기 repository
	// DELET FROM `comment` WHERE `postId` = #{}
	// delete는 기본적으로 return type void
	@Transactional
	public void deleteByPostId(int postId);
}
