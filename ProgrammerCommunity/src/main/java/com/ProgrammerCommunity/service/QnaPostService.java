package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.QnaDetailResponse;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.model.dto.response.QnaTagsSearchResponse;
import com.ProgrammerCommunity.model.entity.BoardType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;

	// 글 작성 기능
	public void createQnaPost(@Valid QnaCreateRequest dto) {
		// 현재 시각
        dto.setCreatedAt(LocalDateTime.now());
        dto.setBoardType(BoardType.QNA);
        mapper.createQna(dto);
    }
	
	// 게시판 페이지 이동 기능
	public List<QnaListResponse> qnaList(String boardType, int pageSize, int pageNum) {
		
		if ( !boardType.equals("QNA") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "QNA게시판이 아님");
		}
		
		// offSet 설정
		int offset = ( pageNum - 1 ) * pageSize;
		
		List<QnaListResponse> qna = mapper.qnaPageList( boardType, offset, pageSize );
		
		return qna;
	}

	// qna 게시물 갯수
	public int getTotalQnaCount(String boardType, int pageSize) {
		
		int totalItems = mapper.totalPage( boardType );
		
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		
		return totalPages;
	}

	// 글 상세 페이지 기능
	public QnaDetailResponse detail(Integer postId) {
		
		// null인지 확인
		if ( postId == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "게시글이 없음" ); // 에러코드
		}
		
		// 상세 페이지
		QnaDetailResponse qna = mapper.findByPostId( postId );
		
		return qna;
	}

	// 태그 검색 기능
	public List<QnaTagsSearchResponse> tagSearch(String tags, int pageSize, int pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return mapper.searchByTag(tags, pageSize, offset);
    }

    public int getTotalTaggedQnaCount(String tags, int pageSize) {
        int totalCount = mapper.countByTag(tags);
        return (int) Math.ceil((double) totalCount / pageSize);
    }
	
}
