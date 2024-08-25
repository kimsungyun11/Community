package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.UserMapper;
import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;

	// 회원가입 기능
	public void signup(@Valid SignupRequest dto) {
		LocalDateTime createdAt = LocalDateTime.now();
		userMapper.userSingup( dto, createdAt );
	}
	
}
