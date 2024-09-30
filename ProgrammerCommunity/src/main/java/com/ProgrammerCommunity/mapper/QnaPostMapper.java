package com.ProgrammerCommunity.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.QnaDetailResponse;
import com.ProgrammerCommunity.model.dto.response.QnaEditResponse;
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
	@Select("SELECT a.post_id, a.title, a.content, a.updated_at, b.username, a.user_id " +
            "FROM Posts a " +
            "JOIN Users b ON a.user_id = b.user_id " +
            "WHERE a.post_id = #{postId}")
    QnaDetailResponse findByPostId(@Param("postId") Integer postId);

	// 태그로 글 검색
	@Select("SELECT post_id, title, content, tags, updated_at FROM Posts WHERE tags LIKE CONCAT('%', #{tags}, '%')")
	List<QnaTagsSearchResponse> searchByTag(@Param("tags") String tags, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM Posts WHERE tags LIKE CONCAT('%', #{tags}, '%')")
    int countByTag(@Param("tags") String tags);

    // qna글 삭제
    @Delete("DELETE FROM posts WHERE user_id = #{user} AND post_id = #{postId}")
    void deleteQna(@Param("user") Integer user, @Param("postId") Integer postId);
    
    // 글 수정 기능
    @Update("UPDATE Posts SET title = #{title}, content = #{content}, tags = #{tags}, updated_at = CURRENT_TIMESTAMP " +
            "WHERE post_id = #{postId}")
    void updateQna(@Param("postId") Integer postId, @Param("title") String title, @Param("content") String content, @Param("tags") String tags);
    
    // 수정 해야 할 글 정보
    @Select("SELECT post_id, user_id, title, content, tags, updated_at "
            + "FROM Posts WHERE post_id = #{postId}")
    QnaEditResponse findByPostIdForEdit(@Param("postId") Integer postId);
    
}
