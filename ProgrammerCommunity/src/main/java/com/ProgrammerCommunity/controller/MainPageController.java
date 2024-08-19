package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.service.MainPageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPageController {
	
	private final MainPageService mainPageService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
