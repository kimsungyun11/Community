package com.ProgrammerCommunity.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.entity.Users;

@Mapper
public interface UserMapper {
	
	// ȸ������ ���
	@Insert("INSERT INTO Users (username, email, password, created_at) " +
            "VALUES (#{username}, #{email}, #{password}, #{createdAt})")
    void insertUser(@Param("username") String username, 
                    @Param("email") String email, 
                    @Param("password") String hashedPassword, 
                    @Param("createdAt") LocalDateTime createdAt);

	// �г��� �ߺ� Ȯ��
	@Select("SELECT COUNT(*) > 0 FROM Users WHERE username = #{username}")
    boolean existsByUsername(String username);

	// �̸��� �ߺ� Ȯ��
    @Select("SELECT COUNT(*) > 0 FROM Users WHERE email = #{email}")
    boolean existsByEmail(String email);

    // �α��� ���
    @Select("SELECT * FROM users WHERE email = #{email}")
    Users findByEmail(@Param("email") String email);

    
    
}
