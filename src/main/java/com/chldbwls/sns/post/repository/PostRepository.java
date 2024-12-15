package com.chldbwls.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.post.domain.Post;
														// 프라이머리키 타입
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findAllByOrderByIdDesc();

	
}
