package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.CommentMapper;
import com.ProgrammerCommunity.model.dto.request.CommentCreateRequest;
import com.ProgrammerCommunity.model.dto.response.CommentResponse;
import com.ProgrammerCommunity.model.entity.Comments;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentMapper commentMapper;

 // 댓글 생성 메서드
    public void createComment(CommentCreateRequest dto) {
        // 현재 시간을 댓글 작성 시간으로 설정
        dto.setCreatedAt(LocalDateTime.now());
        // 매퍼를 통해 댓글을 데이터베이스에 삽입
        commentMapper.insertComment(dto);
    }
    
    // 특정 게시글의 모든 댓글을 조회하고 구조화하는 메서드
    public List<CommentResponse> getCommentsByPostId(Integer postId) {
        // 데이터베이스에서 모든 댓글을 가져옴
        List<CommentResponse> allComments = commentMapper.findByPostId(postId);
        // 댓글을 구조화하여 반환
        return organizeComments(allComments);
    }
    
    // 댓글을 계층 구조로 정리하는 private 메서드
    private List<CommentResponse> organizeComments(List<CommentResponse> allComments) {
        // 댓글 ID를 키로 사용하는 Map 생성
        Map<Integer, CommentResponse> commentMap = new HashMap<>();
        // 최상위 댓글을 저장할 리스트
        List<CommentResponse> rootComments = new ArrayList<>();

        for (CommentResponse comment : allComments) {
            // 모든 댓글을 Map에 저장
            commentMap.put(comment.getCommentId(), comment);
            if (comment.getParentCommentId() == null) {
                // 부모 댓글이 없으면 최상위 댓글로 처리
                rootComments.add(comment);
            } else {
                // 부모 댓글이 있으면 해당 부모의 대댓글로 추가
                CommentResponse parentComment = commentMap.get(comment.getParentCommentId());
                if (parentComment != null) {
                    if (parentComment.getReplies() == null) {
                        parentComment.setReplies(new ArrayList<>());
                    }
                    parentComment.getReplies().add(comment);
                }
            }
        }
        // 최상위 댓글 리스트 반환 (대댓글은 각 댓글의 replies에 포함됨)
        return rootComments;
    }

    // 댓글 삭제 메서드
    public Integer deleteComment(Integer commentId, Integer userId) {
        Comments comment = commentMapper.findById(commentId);
        if (comment == null || !comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }
        commentMapper.deleteComment(commentId);
        return comment.getPostId();
    }
}
