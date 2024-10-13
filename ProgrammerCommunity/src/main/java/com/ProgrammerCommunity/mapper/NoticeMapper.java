package com.ProgrammerCommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ProgrammerCommunity.model.dto.response.QnaListResponse;

@Mapper
public interface NoticeMapper {
	// �� ��� ���� ���
	@Select("SELECT post_id, title, content, tags, updated_at FROM posts WHERE board_type = #{boardType} ORDER BY updated_at DESC "
			+ "LIMIT #{pageSize} OFFSET #{offset}")
	List<QnaListResponse> qnaPageList(@Param("boardType") String boardType, @Param("offset") int offset,
			@Param("pageSize") int pageSize);

	// �� ����
	@Select("SELECT count(*) FROM Posts WHERE board_type = #{boardType}")
	int totalPage(String boardType);
}
