package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.request.CommentCreateRequest;
import com.ProgrammerCommunity.model.dto.response.CommentResponse;
import com.ProgrammerCommunity.model.entity.Comments;

@Mapper
public interface CommentMapper {
	
	// ¥Ò±€ ¿€º∫
	@Insert("INSERT INTO Comments (post_id, user_id, parent_comment_id, content, created_at) " +
	        "VALUES (#{dto.postId}, #{dto.userId}, #{dto.parentCommentId,jdbcType=INTEGER}, #{dto.content}, #{dto.createdAt})")
	void insertComment(@Param("dto") CommentCreateRequest dto);

    // ¥Ò±€ ªË¡¶
    @Delete("DELETE FROM Comments WHERE comment_id = #{commentId}")
    void deleteComment(@Param("commentId") Integer commentId);
    
    @Select("SELECT * FROM Comments WHERE comment_id = #{commentId}")
    Comments findById(@Param("commentId") Integer commentId);

    @Select("SELECT c.*, u.username FROM Comments c " +
            "JOIN Users u ON c.user_id = u.user_id " +
            "WHERE c.post_id = #{postId} " +
            "ORDER BY c.created_at")
    List<CommentResponse> findByPostId(@Param("postId") Integer postId);
}
