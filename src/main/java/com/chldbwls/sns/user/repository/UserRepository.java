package com.chldbwls.sns.user.repository;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chldbwls.sns.user.domain.User;

@Mapper
public interface UserRepository extends JpaRepository<User, Integer>{

	// 유저 추가하기
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("birthday")LocalDate birthday);
	
}
