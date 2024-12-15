package com.chldbwls.sns.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	
	// SELECT count(*) FROM `post` WHERE `postId` = #{}
	public int countByPostId(int postId);
}
 