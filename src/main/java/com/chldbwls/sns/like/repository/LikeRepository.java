package com.chldbwls.sns.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	
	// SELECT count(*) FROM `post` WHERE `postId` = #{}
	public int countByPostId(int postId);
	
	
	// like 취소 repository
	public Optional<Like> findByPostIdAndUserId(int postId, int userId);
	
	// SELECT count(*) FROM `like` WHERE `postId` = #{} AND `userId` =#{}
	public int countByPostIdAndUserId(int postId, int userId);
	
	// post 삭제되면 관련 like 정보 다 삭제
	// delete 는 select 가 실행이 되는데 이 두개를 같이 한꺼번에 실행시켜야돼
	/// SELECT * FROM `like` WHERE `postId` = #{}
	// DELETE FROM `like` WHERE `postId` = #{}
	// transcation = "여러 쿼리를 하나로 묶어서 실행"하  고 중간에 다른 쿼리가 들어오지 않게 수행시켜주는 것
	// 진짜 강력한 어노테이션
	// 중간에 에러가 나면 실행시켰던 쿼리 다 원상복구(ex. delete 취소)
	@Transactional // 트레디셔널? import
	public void deleteByPostId(int postId);
	
}
 