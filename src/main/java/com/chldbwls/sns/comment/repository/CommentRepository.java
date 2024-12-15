package com.chldbwls.sns.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
