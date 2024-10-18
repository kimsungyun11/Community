package com.ProgrammerCommunity.mapper;

import com.ProgrammerCommunity.model.dto.request.NoticeUpdateRequest;
import com.ProgrammerCommunity.model.dto.response.NoticeCreateResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeDetailResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface NoticeMapper {

    // ��� ��ȸ
    @Select("SELECT post_id, title, content, updated_at FROM posts " +
            "WHERE board_type = 'NOTICE' ORDER BY updated_at DESC " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<NoticeResponse> getNoticeList(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // �������� ��ü ��
    @Select("SELECT COUNT(*) FROM posts WHERE board_type = 'NOTICE'")
    int getTotalNoticeCount();

    // �������� ����
    @Insert("INSERT INTO posts (user_id, board_type, title, content, created_at) " +
            "VALUES (#{dto.userId}, #{dto.boardType}, #{dto.title}, #{dto.content}, #{dto.createdAt})")
    void createNotice(@Param("dto") NoticeCreateResponse dto);

    // �� ������
    @Select("SELECT p.post_id, p.title, p.content, p.updated_at, u.username " +
            "FROM posts p JOIN users u ON p.user_id = u.user_id " +
            "WHERE p.post_id = #{postId} AND p.board_type = 'NOTICE'")
    NoticeDetailResponse getNoticeDetailByPostId(@Param("postId") Integer postId);

    // �������� ����
    @Delete("DELETE FROM posts WHERE post_id = #{postId} AND board_type = 'NOTICE'")
    void deleteNoticeByPostId(@Param("postId") Integer postId);

    // �������� ����
    @Update("UPDATE posts SET title = #{dto.title}, content = #{dto.content}, " +
            "updated_at = CURRENT_TIMESTAMP WHERE post_id = #{postId} AND board_type = 'NOTICE'")
    void updateNotice(@Param("postId") Integer postId, @Param("dto") NoticeUpdateRequest dto);
}