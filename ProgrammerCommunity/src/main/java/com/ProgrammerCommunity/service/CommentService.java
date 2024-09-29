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

    // ��� ���� �޼���
    public void createComment(CommentCreateRequest dto, Integer postId, Integer userId) {
        log.info("Entering createComment method");
        log.info("Received postId: {}, userId: {}", postId, userId);
        log.info("Original DTO: {}", dto);

        dto.setPostId(postId);
        dto.setUserId(userId);
        dto.setCreatedAt(LocalDateTime.now());

        if (dto.getParentCommentId() == null || dto.getParentCommentId() == 0) {
            dto.setParentCommentId(null);  // null�� ����
        }

        log.info("Modified DTO before insertion: {}", dto);

        commentMapper.insertComment(dto);

        log.info("Comment inserted successfully");
    }
    
    // Ư�� �Խñ��� ��� ����� ��ȸ�ϰ� ����ȭ�ϴ� �޼���
    public List<CommentResponse> getCommentsByPostId(Integer postId) {
        // �����ͺ��̽����� ��� ����� ������
        List<CommentResponse> allComments = commentMapper.findByPostId(postId);
        // ����� ����ȭ�Ͽ� ��ȯ
        return organizeComments(allComments);
    }
    
    // ����� ���� ������ �����ϴ� private �޼���
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

    // ��� ���� �޼���
    public Integer deleteComment(Integer commentId, Integer userId) {
        Comments comment = commentMapper.findById(commentId);
        if (comment == null || !comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "���� ������ �����ϴ�.");
        }
        commentMapper.deleteComment(commentId);
        return comment.getPostId();
    }
}
