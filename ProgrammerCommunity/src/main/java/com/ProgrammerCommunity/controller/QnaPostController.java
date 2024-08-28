package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.service.QnaPostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaPostController {
	
	private final QnaPostService service;
	
	@GetMapping("/qnapage")
	public String qnaPage() {
		return "qnaBoard";
	}
	
}
