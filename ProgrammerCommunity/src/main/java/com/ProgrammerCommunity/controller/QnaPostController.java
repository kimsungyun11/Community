package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.entity.Posts;
import com.ProgrammerCommunity.service.QnaPostService;

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
	@GetMapping("/qnaWrite")
	public String qnaWrite() {
		return "qnaWrite";
	}
	
	// 글 작성 기능
	@PostMapping("/create")
	public String create( @ModelAttribute("dto") @Valid QnaCreateRequest dto, Model model ) {
		
		service.create( dto );
		
		return "redirect:/qna/qnapage";
	}
	
}
