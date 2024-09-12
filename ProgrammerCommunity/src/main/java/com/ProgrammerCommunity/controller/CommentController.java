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
    
 // 일반 댓글과 대댓글 작성을 위한 메서드
    @PostMapping("/create")
    public String createComment(@Valid @ModelAttribute CommentCreateRequest dto,
                                BindingResult bindingResult,
                                HttpSession session) {
        // 세션에서 사용자 ID를 가져옴
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login/loginpage";
        }
        
        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패 시 원래 페이지로 리다이렉트
            return "redirect:/qna/detail/" + dto.getPostId();
        }
        
        // 댓글 작성자 ID 설정
        dto.setUserId(userId);
        // 댓글 생성 서비스 호출
        commentService.createComment(dto);
        // 댓글 작성 후 해당 게시글 상세 페이지로 리다이렉트
        return "redirect:/qna/detail/" + dto.getPostId();
    }
    
    // 대댓글 작성을 위한 별도의 엔드포인트
    // 실제로는 createComment와 동일한 로직을 사용하지만, URL을 구분하여 의미를 명확히 함
    @PostMapping("/reply")
    public String createReply(@Valid @ModelAttribute CommentCreateRequest dto,
                              BindingResult bindingResult,
                              HttpSession session) {
        // createComment 메서드를 재사용
        return createComment(dto, bindingResult, session);
    }
    
    // 댓글 삭제 메서드 (기존과 동일)
    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId, HttpSession session) {
        // 세션에서 사용자 ID를 가져옴
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/loginpage";
        }
        
        // 댓글 삭제 서비스 호출 및 해당 게시글의 ID 반환
        Integer postId = commentService.deleteComment(commentId, userId);
        // 삭제 후 해당 게시글 상세 페이지로 리다이렉트
        return "redirect:/qna/detail/" + postId;
    }
}
