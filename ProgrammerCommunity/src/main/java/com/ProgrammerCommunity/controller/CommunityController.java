package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProgrammerCommunity.model.dto.response.CommunityCreateResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityResponse;
import com.ProgrammerCommunity.service.CommunityService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
	
	private final CommunityService service;
	
	// community ������ �̵�
	@GetMapping
	public String communityPage( Model model,
								@RequestParam( name = "pageNum", defaultValue = "1" ) int pageNum,
								@RequestParam( name = "pageSize", defaultValue = "10" ) int pageSize,
								@RequestParam( name = "boardType", defaultValue = "COMMUNITY" ) String boardType ) {
		
		// community ����Ʈ
		List<CommunityResponse> list = service.communityPage( pageNum, pageSize, boardType );
		
		// total
		int total = service.tatalPage( pageSize, boardType );
		
		// community ����Ʈ
		model.addAttribute("posts", list);
		// totalPage��
		model.addAttribute("total", total);
		// boardType
		model.addAttribute("boardType", boardType);
		// pageSize
		model.addAttribute("pageSize", pageSize);
		// pageNum
		model.addAttribute("pageNum", pageNum);
		
		return "communityBoard";
	}
	
	// �� �ۼ� ������ �̵�
	@GetMapping("write")
	public String communityWrite( HttpSession session, Model model ) {
		
		Integer user = (Integer) session.getAttribute("userId");
		
		if ( user == null ) {
			return "redirect:/login/loginpage";
		}
		
		model.addAttribute("dto", new CommunityCreateResponse());
		
		return "communityWrite";
	}
	
	// �� �ۼ�
	@PostMapping("create")
	public String communityCreate( HttpSession session ) {
		return "";
	}
	
}
