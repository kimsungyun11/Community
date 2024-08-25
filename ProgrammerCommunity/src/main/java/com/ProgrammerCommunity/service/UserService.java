package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.UserMapper;
import com.ProgrammerCommunity.model.dto.request.SignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	// 닉네임, 이메일 중복 확인
	public String check(@Valid SignupRequest dto) {

		if (userMapper.existsByUsername(dto.getUsername())) {
			return "중복 된 닉네임 입니다.";
		}
		
		if (userMapper.existsByEmail(dto.getEmail())) {
			return "중복 된 이메일 입니다.";
		}
		
		return null;
	}

	// 회원가입 기능
	public void signup(@Valid SignupRequest dto) {

		LocalDateTime createdAt = LocalDateTime.now();
		
		userMapper.userSingup(dto, createdAt);
	}

}
