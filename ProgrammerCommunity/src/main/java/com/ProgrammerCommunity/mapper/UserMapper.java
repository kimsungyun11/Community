package com.ProgrammerCommunity.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.entity.Users;

@Mapper
public interface UserMapper {
	
	// 회원가입 기능
	@Insert("INSERT INTO Users (username, email, password, created_at, is_admin) " +
            "VALUES (#{username}, #{email}, #{password}, #{createdAt}, #{isAdmin})")
    void insertUser(@Param("username") String username, 
                    @Param("email") String email, 
                    @Param("password") String hashedPassword, 
                    @Param("createdAt") LocalDateTime createdAt,
                    @Param("isAdmin") Boolean isAdmin);

	// 닉네임 중복 확인
	@Select("SELECT COUNT(*) > 0 FROM Users WHERE username = #{username}")
    boolean existsByUsername(String username);

	// 이메일 중복 확인
    @Select("SELECT COUNT(*) > 0 FROM Users WHERE email = #{email}")
    boolean existsByEmail(String email);

    // 로그인 기능
    @Select("SELECT user_id, username, email, password, created_at, is_admin " +
            "FROM users WHERE email = #{email}")
    Users findByEmail(@Param("email") String email);

    
    
}