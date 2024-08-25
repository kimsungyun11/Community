package com.ProgrammerCommunity.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;

import jakarta.validation.Valid;

@Mapper
public interface UserMapper {
	
	// ȸ������ ���
	@Insert("INSERT INTO Users (username, email, password, created_at) "
			+ "VALUES (#{dto.username}, #{dto.email}, #{dto.password}, #{createdAt})")
	void userSingup(@Param("dto") @Valid SignupRequest dto, @Param("createdAt") LocalDateTime createdAt);

	// �г��� �ߺ� Ȯ��
	@Select("SELECT COUNT(*) > 0 FROM Users WHERE username = #{username}")
    boolean existsByUsername(String username);

	// �̸��� �ߺ� Ȯ��
    @Select("SELECT COUNT(*) > 0 FROM Users WHERE email = #{email}")
    boolean existsByEmail(String email);

    // �α��� ���
    @Select("SELECT * FROM users WHERE email = #{email} AND password_hash = #{password}")
	Users findByUsernameAndPassword(String email, String password);

    
    
}
