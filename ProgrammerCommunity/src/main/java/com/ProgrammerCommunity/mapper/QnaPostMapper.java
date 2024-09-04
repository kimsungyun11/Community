package com.ProgrammerCommunity.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.QnaDetailResponse;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.model.dto.response.QnaTagsSearchResponse;

import jakarta.validation.Valid;

@Mapper
public interface QnaPostMapper {
	
	// 글 작성 기능
	@Insert("INSERT INTO Posts (user_id, board_type, title, content, tags, created_at) " +
	        "VALUES (#{dto.userId}, #{dto.boardType}, #{dto.title}, #{dto.content}, #{dto.tags}, #{dto.createdAt})")
	void createQna(@Param("dto") @Valid QnaCreateRequest dto);

	// 글 목록 보는 기능
	@Select("SELECT post_id, title, content, tags, updated_at FROM posts WHERE board_type = #{boardType} ORDER BY updated_at DESC "
			+ "LIMIT #{pageSize} OFFSET #{offset}")
	List<QnaListResponse> qnaPageList(@Param("boardType") String boardType,@Param("offset") int offset,@Param("pageSize") int pageSize);

	// 글 갯수
	@Select("SELECT count(*) FROM Posts WHERE board_type = #{boardType}")
	int totalPage(String boardType);

	// 상세 페이지 
	@Select("SELECT a.title, a.content, a.updated_at, b.username "
			+ "FROM Posts a "
			+ "JOIN Users b ON a.user_id = b.user_id "
			+ "WHERE post_id = #{postId}")
	QnaDetailResponse findByPostId(@Param("postId") Integer postId);

	// 태그로 글 검색
	@Select("SELECT title, content, tags, updated_at FROM Posts WHERE tags = #{tags}")
	QnaTagsSearchResponse searchByTag(String tags);

}
