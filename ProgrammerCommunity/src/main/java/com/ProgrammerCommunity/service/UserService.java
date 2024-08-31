package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.UserMapper;
import com.ProgrammerCommunity.model.dto.request.LoginRequest;
import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	// �г���, �̸��� �ߺ� Ȯ��
	public String check(@Valid SignupRequest dto) {

		if (userMapper.existsByUsername(dto.getUsername())) {
			return "�ߺ� �� �г��� �Դϴ�.";
		}
		
		if (userMapper.existsByEmail(dto.getEmail())) {
			return "�ߺ� �� �̸��� �Դϴ�.";
		}
		
		return null;
	}

	// ȸ������ ���
	public void signup(@Valid SignupRequest dto) {

		LocalDateTime createdAt = LocalDateTime.now();
		
		userMapper.userSingup(dto, createdAt);
	}

	// �α��� ���
	public Users login(LoginRequest dto) {
	    Users user = userMapper.findByUsernameAndPassword(dto.getEmail(), dto.getPassword());
	    if (user == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�̸��� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
	    }
	    return user;
	}

	

}
