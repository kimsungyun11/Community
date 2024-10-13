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
	// �� ��
	public int getTotalnoticeCount(String boardType, int pageSize) {
		// �� ����
		int totalItems = noticeMapper.totalPage(boardType);
		// ������ ��
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);

		return totalPages;
	}
	// �� ���
	public List<QnaListResponse> noticeList(String boardType, int pageSize, int pageNum) {
		// �Խ��� Ȯ��
		if ( !boardType.equals("NOTICE") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "NOTICE�Խ����� �ƴ�");
		}
		
		// offSet ����
		int offset = ( pageNum - 1 ) * pageSize;
		// �� ���
		List<QnaListResponse> notice = noticeMapper.qnaPageList( boardType, offset, pageSize );
		
		return notice;
	}

}
