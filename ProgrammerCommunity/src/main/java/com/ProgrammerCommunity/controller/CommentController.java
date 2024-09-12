package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.CommentCreateRequest;
import com.ProgrammerCommunity.service.CommentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;
    
 // �Ϲ� ��۰� ���� �ۼ��� ���� �޼���
    @PostMapping("/create")
    public String createComment(@Valid @ModelAttribute CommentCreateRequest dto,
                                BindingResult bindingResult,
                                HttpSession session) {
        // ���ǿ��� ����� ID�� ������
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            // �α������� ���� ��� �α��� �������� �����̷�Ʈ
            return "redirect:/login/loginpage";
        }
        
        if (bindingResult.hasErrors()) {
            // ��ȿ�� �˻� ���� �� ���� �������� �����̷�Ʈ
            return "redirect:/qna/detail/" + dto.getPostId();
        }
        
        // ��� �ۼ��� ID ����
        dto.setUserId(userId);
        // ��� ���� ���� ȣ��
        commentService.createComment(dto);
        // ��� �ۼ� �� �ش� �Խñ� �� �������� �����̷�Ʈ
        return "redirect:/qna/detail/" + dto.getPostId();
    }
    
    // ���� �ۼ��� ���� ������ ��������Ʈ
    // �����δ� createComment�� ������ ������ ���������, URL�� �����Ͽ� �ǹ̸� ��Ȯ�� ��
    @PostMapping("/reply")
    public String createReply(@Valid @ModelAttribute CommentCreateRequest dto,
                              BindingResult bindingResult,
                              HttpSession session) {
        // createComment �޼��带 ����
        return createComment(dto, bindingResult, session);
    }
    
    // ��� ���� �޼��� (������ ����)
    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId, HttpSession session) {
        // ���ǿ��� ����� ID�� ������
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        // ��� ���� ���� ȣ�� �� �ش� �Խñ��� ID ��ȯ
        Integer postId = commentService.deleteComment(commentId, userId);
        // ���� �� �ش� �Խñ� �� �������� �����̷�Ʈ
        return "redirect:/qna/detail/" + postId;
    }
}
