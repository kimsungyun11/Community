package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.service.MainPageService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPageController {
	
	private final MainPageService mainPageService;
	
	// 메인페이지 이동 기능
	@GetMapping("")
	public String index() {
		return "index";
	}
	
	// 검색 기능
	@GetMapping("/search")
    public String searchMain(@RequestParam("search") String search, 
                             Model model,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        List<MainPageSearchResponse> results = mainPageService.searchMain(search, pageSize, pageNum);
        
        model.addAttribute("searchResults", results);
        
        model.addAttribute("searchKeyword", search);
        
        return "searchResults";
    }
	
	
}
