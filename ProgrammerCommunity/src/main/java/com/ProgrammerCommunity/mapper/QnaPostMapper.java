package com.ProgrammerCommunity.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;

import jakarta.validation.Valid;

@Mapper
public interface QnaPostMapper {
	
	// 글 작성 기능
	@Insert("INSERT INTO Posts (board_type, title, content, tags, created_at) "
			+ "VALUES (#{dto.boardType}, #{dto.title}, #{dto.content}, #{dto.tags}, time)")
	void createQna(@Param("dto") @Valid QnaCreateRequest dto, LocalDateTime time);

}
