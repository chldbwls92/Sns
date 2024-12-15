package com.chldbwls.sns.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	// loginId 중복확인
	public boolean existsByLoginId(String loginId);
	
	// user 정보 찾기
	public User findByLoginIdAndPassword(String loginId, String password);
	
	public User findById(int id);
}
