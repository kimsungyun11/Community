package com.ProgrammerCommunity.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ProgrammerCommunity.model.dto.request.SignupRequest;

import jakarta.validation.Valid;

@Mapper
public interface UserMapper {
	
	// ȸ������ ���
	@Insert("INSERT INTO Users (username, email, password_hash, created_at) "
			+ "VALUES (#{dto.username}, #{dto.email}, #{dto.passwordHash}, #{createdAt})")
	void userSingup(@Param("dto") @Valid SignupRequest dto, @Param("createdAt") LocalDateTime createdAt);

}
