package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.model.entity.BoardType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;

	// �� �ۼ� ���
	public void createQnaPost(@Valid QnaCreateRequest dto) {
        dto.setCreatedAt(LocalDateTime.now());
        dto.setBoardType(BoardType.QNA);
        mapper.createQna(dto);
    }
	
	// �Խ��� ������ �̵� ���
	public List<QnaListResponse> qnaList(String boardType, int pageSize, int pageNum) {
		
		if ( !boardType.equals("QNA") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "QNA�Խ����� �ƴ�");
		}
		
		// offSet ����
		int offset = ( pageNum - 1 ) * pageSize;
		
		List<QnaListResponse> qna = mapper.qnaPageList( boardType, offset, pageSize );
		
		return qna;
	}

	// qna �Խù� ����
	public int getTotalQnaCount(String boardType, int pageSize) {
		
		int totalItems = mapper.totalPage( boardType );
		
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		
		return totalPages;
	}
	
}
