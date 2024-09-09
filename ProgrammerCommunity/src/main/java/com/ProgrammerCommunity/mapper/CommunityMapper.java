package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
