package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;

@Mapper
public interface MainPageMapper {

	// 검색 및 페이징
	@Select("SELECT p.title, p.created_at, u.username " +
            "FROM posts p " +
            "JOIN users u ON p.user_id = u.user_id " +
            "WHERE p.title LIKE CONCAT('%', #{search}, '%') " +
            "OR p.content LIKE CONCAT('%', #{search}, '%') " +
            "ORDER BY p.created_at DESC " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<MainPageSearchResponse> mainPageSearch(@Param("search") String search, @Param("pageSize") int pageSize,
    											@Param("offset") int offset);


}
