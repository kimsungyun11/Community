package com.ProgrammerCommunity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ProgrammerCommunity.model.dto.request.CommentCreateRequest;
import com.ProgrammerCommunity.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    
    private final CommentService commentService;

    @PostMapping("/create/{postId}")
    public String createComment(@PathVariable("postId") Integer postId,
                                @Valid @ModelAttribute CommentCreateRequest commentForm,
                                BindingResult bindingResult,
                                HttpSession session,
                                Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "��� ������ Ȯ�����ּ���.");
            return "redirect:/qna/detail/" + postId;
        }
        
        try {
            commentService.createComment(commentForm, postId, userId);
            model.addAttribute("message", "����� ���������� �ۼ��Ǿ����ϴ�.");
        } catch (Exception e) {
            model.addAttribute("error", "��� �ۼ� �� ������ �߻��߽��ϴ�.");
        }
        
        return "redirect:/qna/detail/" + postId;
    }

    @PostMapping("/reply/{postId}")
    public String createReply(@PathVariable("postId") Integer postId,
                              @Valid @ModelAttribute CommentCreateRequest commentForm,
                              BindingResult bindingResult,
                              HttpSession session,
                              Model model) {
        return createComment(postId, commentForm, bindingResult, session, model);
    }

    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId, 
                                HttpSession session, 
                                Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        try {
            Integer postId = commentService.deleteComment(commentId, userId);
            model.addAttribute("message", "����� ���������� �����Ǿ����ϴ�.");
            return "redirect:/qna/detail/" + postId;
        } catch (Exception e) {
            model.addAttribute("error", "��� ���� �� ������ �߻��߽��ϴ�.");
            return "redirect:/error";
        }
    }
}