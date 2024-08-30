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
	
	// �Խ��� ������ �̵� ���
	@GetMapping("/qnapage")
	public String qnaPage() {
		return "qnaBoard";
	}
	
	// �� �ۼ� ������ �̵� ���
	@GetMapping("/qnaWrite")
	public String qnaWrite() {
		return "qnaWrite";
	}
	
	// �� �ۼ� ���
	@PostMapping("/create")
	public String create( @ModelAttribute("dto") @Valid QnaCreateRequest dto, Model model ) {
		
		service.create( dto );
		
		return "redirect:/qna/qnapage";
	}
	
}
