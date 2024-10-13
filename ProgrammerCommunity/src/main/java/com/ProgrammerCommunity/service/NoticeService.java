package com.ProgrammerCommunity.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.NoticeMapper;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NoticeService {

	private static NoticeMapper noticeMapper;

	private static PermissionService permissionService;
	// 글 수
	public int getTotalnoticeCount(String boardType, int pageSize) {
		// 글 갯수
		int totalItems = noticeMapper.totalPage(boardType);
		// 페이지 수
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);

		return totalPages;
	}
	// 글 목록
	public List<QnaListResponse> noticeList(String boardType, int pageSize, int pageNum) {
		// 게시판 확인
		if ( !boardType.equals("NOTICE") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "NOTICE게시판이 아님");
		}
		
		// offSet 설정
		int offset = ( pageNum - 1 ) * pageSize;
		// 글 목록
		List<QnaListResponse> notice = noticeMapper.qnaPageList( boardType, offset, pageSize );
		
		return notice;
	}

}
