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
	
	// �г���, �̸��� �ߺ� Ȯ��
	private void check(SignupRequest dto) {
        if (userMapper.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�ߺ��� �г����Դϴ�.");
        }
        if (userMapper.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�ߺ��� �̸����Դϴ�.");
        }
    }

	// ȸ������ ���
	public void signup(@Valid SignupRequest dto) {
        check(dto);
        
        String hashedPassword = PasswordHasher.hashPassword(dto.getPassword());
        LocalDateTime createdAt = LocalDateTime.now();
        Boolean isAdmin = false; // �⺻������ false ����
        
        userMapper.insertUser(dto.getUsername(), dto.getEmail(), hashedPassword, createdAt, isAdmin);
    }

	// �α��� ���
	public Users login(LoginRequest dto) {
        Users user = userMapper.findByEmail(dto.getEmail());
        if (user == null || !user.getPassword().equals(PasswordHasher.hashPassword(dto.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�̸��� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
        }
        
        // isAdmin�� null�� ��� false�� ����
        if (user.getIsAdmin() == null) {
            user.setIsAdmin(false);
        }
        
        return user;
    }

	

}
