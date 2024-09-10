package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.CommentResponse;
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
		
		// ���� �� �� �ִ� �� Ȯ��
        int deletedCount = mapper.deleteQna(user, postId);
        
        // ������ ����
        if (deletedCount == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "������ �Խñ��� ã�� �� ���ų� ������ �����ϴ�.");
        }
		
	}
	
}
