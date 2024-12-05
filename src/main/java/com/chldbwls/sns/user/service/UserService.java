package com.chldbwls.sns.user.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.chldbwls.sns.common.MD5HashingEncoder;
import com.chldbwls.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private UserService(UserRepository userRepsitory) {
		this.userRepository = userRepository;
	}
	
	// user 추가하기
	public boolean addUser(
			String loginId
			, String password
			, String name
			, LocalDate birthday) {
		
		String encodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodingPassword, name, birthday);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
