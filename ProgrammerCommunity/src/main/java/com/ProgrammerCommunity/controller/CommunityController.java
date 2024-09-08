package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProgrammerCommunity.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
	
	private static CommunityService service;
	
	@GetMapping
	public String communityPage( Model model,
								@RequestParam( name = "pageNum", defaultValue = "1" ) int pageNum,
								@RequestParam( name = "pageSize", defaultValue = "10" ) int pageSize ) {
		return "communityBoard";
	}
	
}
