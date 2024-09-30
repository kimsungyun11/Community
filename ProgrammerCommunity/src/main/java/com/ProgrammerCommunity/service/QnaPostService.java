package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.request.QnaUpdateRequest;
import com.ProgrammerCommunity.model.dto.response.CommentResponse;
import com.ProgrammerCommunity.model.dto.response.QnaDetailResponse;
import com.ProgrammerCommunity.model.dto.response.QnaEditResponse;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.model.dto.response.QnaTagsSearchResponse;
import com.ProgrammerCommunity.model.entity.BoardType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;
	private final CommentService commentService;

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

	// qna 페이지 갯수
	public int getTotalQnaCount(String boardType, int pageSize) {
		
		int totalItems = mapper.totalPage( boardType );
		
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		
		return totalPages;
	}

	// 글 상세 페이지 기능
	public QnaDetailResponse getQnaDetail(Integer postId) {
        if (postId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이 없음");
        }
        QnaDetailResponse qna = mapper.findByPostId(postId);
        if (qna == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
        
        // 댓글 정보 추가
        List<CommentResponse> comments = commentService.getCommentsByPostId(postId);
        qna.setComments(comments);
        
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

    // 글 삭제 기능
	public void delete(Integer user, Integer postId) {
		
		// user null인지 확인
		if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
		
		// 삭제
        mapper.deleteQna(user, postId);

	}
	
	// 수정 페이지 이동
	public QnaEditResponse getQnaForEdit(Integer postId, Integer userId) {
		
		// 수정 해야 할 정보
        QnaEditResponse qna = mapper.findByPostIdForEdit(postId);
        
        // 글 정보가 없으면 에러
        if (qna == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
        
        // 글 작성자가 아니면 에러
        if (!qna.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }
        
        return qna;
    }

	// 글 수정 
    public void updateQna(Integer postId, Integer userId, QnaUpdateRequest updateRequest) {
    	
    	// 글 정보
        QnaDetailResponse existingQna = mapper.findByPostId(postId);
        
        // 글 정보가 없거나 글 작성자가 아니면 에러
        if (existingQna == null || !existingQna.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }
        
        // 글 수정
        mapper.updateQna(postId, updateRequest.getTitle(), updateRequest.getContent(), updateRequest.getTags());
    }
	
}
