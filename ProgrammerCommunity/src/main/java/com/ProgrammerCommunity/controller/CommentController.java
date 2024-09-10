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

    @PostMapping("/create")
    public String createComment(@Valid @ModelAttribute CommentCreateRequest dto,
                                BindingResult bindingResult,
                                HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        if (bindingResult.hasErrors()) {
            // 에러 처리
            return "redirect:/qna/detail/" + dto.getPostId();
        }
        
        dto.setUserId(userId);
        commentService.createComment(dto);
        return "redirect:/qna/detail/" + dto.getPostId();
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        Integer postId = commentService.deleteComment(commentId, userId);
        return "redirect:/qna/detail/" + postId;
    }
}
