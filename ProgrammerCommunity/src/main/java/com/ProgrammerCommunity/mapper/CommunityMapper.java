package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.response.CommunityCreateResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityDetailResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityResponse;

@Mapper
public interface CommunityMapper {

	// 페이지 list
	@Select("SELECT post_id, title, content, tags, updated_at FROM posts WHERE board_type = #{boardType} ORDER BY updated_at DESC "
			+ "LIMIT #{pageSize} OFFSET #{offSet}")
	List<CommunityResponse> ListByBoardType( @Param("boardType") String boardType, @Param("offSet") int offSet, @Param("pageSize") int pageSize);

	// 게시물 수
	@Select("SELECT COUNT(*) FROM posts WHERE board_type = #{boardType}")
	int total(String boardType);

	// 글 생성
	@Insert("INSERT INTO POSTS (user_id, board_type, title, content, created_at) "
			+ "VALUES (#{dto.userId}, #{dto.boardType}, #{dto.title}, #{dto.content}, #{dto.createdAt})")
	void createCommunity( @Param("dto") CommunityCreateResponse dto);

	// 글 상세 페이지
	@Select("SELECT a.post_id, a.title, a.content, a.updated_at, b.username, a.user_id "
			+ "FROM Posts a "
			+ "JOIN Users b ON a.user_id = b.user_id "
			+ "WHERE a.post_id = #{postId}")
	CommunityDetailResponse communityDetailByPostid( @Param("postId") Integer postId);

	// 삭제 기능
	@Delete("DELETE FROM posts WHERE user_id = #{user} AND post_id = #{postId}")
	void deleteBypostId( @Param("postId") Integer postId, @Param("user") Integer user);

	// 게시글 유저 아이디
	@Select("SELECE user_id FROM POSTS WHERE post_id = #{postId}")
	Integer findUserByPostId(@Param("postId") Integer postId);

}
