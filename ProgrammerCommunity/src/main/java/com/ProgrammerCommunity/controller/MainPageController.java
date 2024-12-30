package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.model.dto.response.RecentBoardResponse;
import com.ProgrammerCommunity.service.MainPageService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPageController {
	
	private final MainPageService mainPageService;
	
	// 메인페이지 이동 기능
	// 게시판 별로 최신순 5개씩 나오는 기능
	@GetMapping
	public String index( Model model ) {
		
		// 게시판 최신순 5개
		List<RecentBoardResponse> recentPosts = mainPageService.getRecentPosts();
		
	    model.addAttribute("recentPosts", recentPosts);
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
