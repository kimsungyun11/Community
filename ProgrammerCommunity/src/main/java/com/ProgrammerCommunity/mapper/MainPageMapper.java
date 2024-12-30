package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.model.dto.response.RecentBoardResponse;
import com.ProgrammerCommunity.model.entity.BoardType;

@Mapper
public interface MainPageMapper {
	// 검색 기능
	@Select("SELECT p.title, p.created_at, u.username " +
            "FROM posts p " +
            "JOIN users u ON p.user_id = u.user_id " +
            "WHERE p.title LIKE CONCAT('%', #{search}, '%') " +
            "OR p.content LIKE CONCAT('%', #{search}, '%') " +
            "ORDER BY p.created_at DESC " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<MainPageSearchResponse> mainPageSearch(@Param("search") String search, @Param("pageSize") int pageSize,
    											@Param("offset") int offset);

	// 게시판 글 5개 최신순
	@Select("SELECT post_id, user_id, title, board_type, created_at, updated_at " +
	        "FROM posts " +
	        "WHERE board_type = #{boardType} " +
	        "ORDER BY created_at DESC " +
	        "LIMIT 5")
	List<RecentBoardResponse> getRecentPosts(@Param("boardType") BoardType boardType);


}
