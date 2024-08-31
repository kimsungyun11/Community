package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.entity.Users;
import com.ProgrammerCommunity.service.QnaPostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaPostController {
	
	private final QnaPostService service;
	
	// 게시판 페이지 이동 기능
	@GetMapping("/qnapage")
	public String qnaPage() {
		return "qnaBoard";
	}
	
	// 글 작성 페이지 이동 기능
	@GetMapping("/write")
    public String write(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }
        model.addAttribute("dto", new QnaCreateRequest());
        return "qna/qnaWrite";
    }
	
	// 글 작성 기능
	@PostMapping("/create")
    public String create(@ModelAttribute("dto") @Valid QnaCreateRequest dto,
                         BindingResult bindingResult,
                         HttpSession session,
                         Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }

        if (bindingResult.hasErrors()) {
            return "qna/qnaWrite";
        }
        
        dto.setUserId(user.getUserId());  // Users 클래스에 getId() 메소드가 있다고 가정
        service.createQnaPost(dto);
        return "redirect:/qna/qnapage";
    }
	
}
