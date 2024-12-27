package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.UserMapper;
import com.ProgrammerCommunity.model.dto.request.LoginRequest;
import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;
import com.ProgrammerCommunity.util.PasswordHasher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	// 닉네임, 이메일 중복 확인
	private void check(SignupRequest dto) {
        if (userMapper.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다.");
        }
        if (userMapper.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이메일입니다.");
        }
    }

	// 회원가입 기능
	public void signup(@Valid SignupRequest dto) {
        check(dto);
        
        String hashedPassword = PasswordHasher.hashPassword(dto.getPassword());
        LocalDateTime createdAt = LocalDateTime.now();
        Boolean isAdmin = false; // 기본값으로 false 설정
        
        userMapper.insertUser(dto.getUsername(), dto.getEmail(), hashedPassword, createdAt, isAdmin);
    }

	// 로그인 기능
	public Users login(LoginRequest dto) {
        Users user = userMapper.findByEmail(dto.getEmail());
        if (user == null || !user.getPassword().equals(PasswordHasher.hashPassword(dto.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        
        // isAdmin이 null인 경우 false로 설정
        if (user.getIsAdmin() == null) {
            user.setIsAdmin(false);
        }
        
        return user;
    }

	

}