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

	// �� �ۼ� ���
	public void createQnaPost(@Valid QnaCreateRequest dto) {
		// ���� �ð�
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

	// qna ������ ����
	public int getTotalQnaCount(String boardType, int pageSize) {
		
		int totalItems = mapper.totalPage( boardType );
		
		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		
		return totalPages;
	}

	// �� �� ������ ���
	public QnaDetailResponse getQnaDetail(Integer postId) {
        if (postId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�Խñ��� ����");
        }
        QnaDetailResponse qna = mapper.findByPostId(postId);
        if (qna == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "�Խñ��� ã�� �� �����ϴ�.");
        }
        
        // ��� ���� �߰�
        List<CommentResponse> comments = commentService.getCommentsByPostId(postId);
        qna.setComments(comments);
        
        return qna;
    }

	// �±� �˻� ���
	public List<QnaTagsSearchResponse> tagSearch(String tags, int pageSize, int pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return mapper.searchByTag(tags, pageSize, offset);
    }

    public int getTotalTaggedQnaCount(String tags, int pageSize) {
        int totalCount = mapper.countByTag(tags);
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    // �� ���� ���
	public void delete(Integer user, Integer postId) {
		
		// user null���� Ȯ��
		if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "�α����� �ʿ��մϴ�.");
        }
		
		// ����
        mapper.deleteQna(user, postId);

	}
	
	// ���� ������ �̵�
	public QnaEditResponse getQnaForEdit(Integer postId, Integer userId) {
		
		// ���� �ؾ� �� ����
        QnaEditResponse qna = mapper.findByPostIdForEdit(postId);
        
        // �� ������ ������ ����
        if (qna == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "�Խñ��� ã�� �� �����ϴ�.");
        }
        
        // �� �ۼ��ڰ� �ƴϸ� ����
        if (!qna.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "���� ������ �����ϴ�.");
        }
        
        return qna;
    }

	// �� ���� 
    public void updateQna(Integer postId, Integer userId, QnaUpdateRequest updateRequest) {
    	
    	// �� ����
        QnaDetailResponse existingQna = mapper.findByPostId(postId);
        
        // �� ������ ���ų� �� �ۼ��ڰ� �ƴϸ� ����
        if (existingQna == null || !existingQna.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "���� ������ �����ϴ�.");
        }
        
        // �� ����
        mapper.updateQna(postId, updateRequest.getTitle(), updateRequest.getContent(), updateRequest.getTags());
    }
	
}
