package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ProgrammerCommunity.service.UserPageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserPageController {
	
	private final UserPageService service;
	
	// 내가 쓴 글 페이지 이동
	@GetMapping("/mypost")
	public String myPost( Model model ) {
		return "";
	}
	
	// 내가 쓴 글 상세 페이지
}
