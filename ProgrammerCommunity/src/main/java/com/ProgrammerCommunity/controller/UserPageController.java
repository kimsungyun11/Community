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
	
	// ���� �� �� ������ �̵�
	@GetMapping("/mypost")
	public String myPost( Model model ) {
		return "";
	}
	
	// ���� �� �� �� ������
}
