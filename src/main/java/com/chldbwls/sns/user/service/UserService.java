package com.chldbwls.sns.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chldbwls.sns.common.MD5HashingEncoder;
import com.chldbwls.sns.user.domain.User;
import com.chldbwls.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	// jpa 활용
	private UserRepository userRepository;
	
	private UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// user 추가하기
	public boolean addUser(
			String loginId
			, String password
			, String name
			, LocalDate birthday) {
		
		// 비밀번호 암호화
		String encodingPassword = MD5HashingEncoder.encode(password);
		
		User user = User.builder()
		.loginId(loginId)
		.password(encodingPassword)
		.name(name)
		.birthday(birthday)
		.build();
		
		try {
			userRepository.save(user);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	// 중복확인
	public boolean isDuplicateId(String loginId) {
		
		return userRepository.existsByLoginId(loginId);
	}
	
	// 특성 사용자 있는지 여부
	public User getUser(String loginId, String password) {
		
		String encodingPassword = MD5HashingEncoder.encode(password);
		return userRepository.findByLoginIdAndPassword(loginId, encodingPassword);
	}

}
