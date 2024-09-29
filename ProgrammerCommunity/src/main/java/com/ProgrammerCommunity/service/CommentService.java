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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    
    private final CommentMapper commentMapper;

    // 댓글 생성 메서드
    public void createComment(CommentCreateRequest dto, Integer postId, Integer userId) {
        log.info("Entering createComment method");
        log.info("Received postId: {}, userId: {}", postId, userId);
        log.info("Original DTO: {}", dto);

        dto.setPostId(postId);
        dto.setUserId(userId);
        dto.setCreatedAt(LocalDateTime.now());

        if (dto.getParentCommentId() == null || dto.getParentCommentId() == 0) {
            dto.setParentCommentId(null);  // null로 설정
        }

        log.info("Modified DTO before insertion: {}", dto);

        commentMapper.insertComment(dto);

        log.info("Comment inserted successfully");
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
        Map<Integer, CommentResponse> commentMap = new HashMap<>();
        List<CommentResponse> rootComments = new ArrayList<>();

        for (CommentResponse comment : allComments) {
            commentMap.put(comment.getCommentId(), comment);
            if (comment.getParentCommentId() == null) {
                rootComments.add(comment);
            } else {
                CommentResponse parentComment = commentMap.get(comment.getParentCommentId());
                if (parentComment != null) {
                    if (parentComment.getReplies() == null) {
                        parentComment.setReplies(new ArrayList<>());
                    }
                    parentComment.getReplies().add(comment);
                }
            }
        }
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
