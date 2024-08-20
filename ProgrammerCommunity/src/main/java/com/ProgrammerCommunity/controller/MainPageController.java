package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.service.MainPageService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPageController {
	
	private final MainPageService mainPageService;
	
	// 메인 페이지 이동
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// 검색 기능
	@PostMapping("/search")
	public String searchMain(@RequestBody String search, Model model ) {
		
		List<MainPageSearchResponse> list = mainPageService.searchMain( search );
		
		model.addAttribute("search", list);
		
		return search;
	}
	
	
}
