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

    // ��� ���� �޼���
    public void createComment(CommentCreateRequest dto, Integer postId, Integer userId) {
    	dto.setPostId(postId);
        dto.setUserId(userId);
        // ���� �ð��� ��� �ۼ� �ð����� ����
        dto.setCreatedAt(LocalDateTime.now());
        // ���۸� ���� ����� �����ͺ��̽��� ����
        commentMapper.insertComment(dto);

        if (dto.getParentCommentId() == null) {
            dto.setParentCommentId(0);
        }
        System.err.println( "dto : " + dto.getContent() );
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
        // ��� ID�� Ű�� ����ϴ� Map ����
        Map<Integer, CommentResponse> commentMap = new HashMap<>();
        // �ֻ��� ����� ������ ����Ʈ
        List<CommentResponse> rootComments = new ArrayList<>();

        for (CommentResponse comment : allComments) {
            // ��� ����� Map�� ����
            commentMap.put(comment.getCommentId(), comment);
            if (comment.getParentCommentId() == null) {
                // �θ� ����� ������ �ֻ��� ��۷� ó��
                rootComments.add(comment);
            } else {
                // �θ� ����� ������ �ش� �θ��� ���۷� �߰�
                CommentResponse parentComment = commentMap.get(comment.getParentCommentId());
                if (parentComment != null) {
                    if (parentComment.getReplies() == null) {
                        parentComment.setReplies(new ArrayList<>());
                    }
                    parentComment.getReplies().add(comment);
                }
            }
        }
        // �ֻ��� ��� ����Ʈ ��ȯ (������ �� ����� replies�� ���Ե�)
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
