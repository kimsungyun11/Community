package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProgrammerCommunity.model.dto.request.CommunityUpdateRequest;
import com.ProgrammerCommunity.model.dto.response.CommunityCreateResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityDetailResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityResponse;
import com.ProgrammerCommunity.model.dto.response.EditResponse;
import com.ProgrammerCommunity.service.CommunityService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
	
	private final CommunityService service;
	
	// community 페이지 이동
	@GetMapping
	public String communityPage( Model model,
								@RequestParam( name = "pageNum", defaultValue = "1" ) int pageNum,
								@RequestParam( name = "pageSize", defaultValue = "10" ) int pageSize,
								@RequestParam( name = "boardType", defaultValue = "COMMUNITY" ) String boardType ) {
		
		// community 리스트
		List<CommunityResponse> list = service.communityPage( pageNum, pageSize, boardType );
		
		// total
		int totalPages = service.tatalPage( pageSize, boardType );
		
		// community 리스트
		model.addAttribute("posts", list);
		// totalPage수
		model.addAttribute("totalPages", totalPages);
		// boardType
		model.addAttribute("boardType", boardType);
		// pageSize
		model.addAttribute("pageSize", pageSize);
		// pageNum
		model.addAttribute("currentPage", pageNum);
		
		return "communityBoard";
	}
	
	// 글 작성 페이지 이동
	@GetMapping("write")
	public String communityWrite( HttpSession session, Model model ) {
		
		Integer user = (Integer) session.getAttribute("userId");
		
		if ( user == null ) {
			return "redirect:/login/loginpage";
		}
		
		model.addAttribute("dto", new CommunityCreateResponse());
		
		return "communityWrite";
	}
	
	// 글 작성
	@PostMapping("create")
	public String communityCreate( HttpSession session, @ModelAttribute("dto") CommunityCreateResponse dto, Model model ) {
		
		// 유저 확인
		Integer user = (Integer) session.getAttribute("userId");
		
		// 글 작성
		service.createCommunityPost( user, dto );
		
		// 완료시 community페이지 이동
		return "redirect:/community";
	}
	
	// 글 상세 페이지
	@GetMapping("detail/{postId}")
	public String communityDetail( @PathVariable("postId") Integer postId, Model model ) {
		
		CommunityDetailResponse detailPage = service.communityDetail( postId );
		
		model.addAttribute("detailPage", detailPage);
		model.addAttribute("comments", detailPage.getComments());
		return "detailPage";
	}
	
	// 글 삭세 기능
	@PostMapping("/delete/{postId}")
	public String communityDelete( @PathVariable("postId") Integer postId, HttpSession session ) {
		
		// 삭제 기능
		service.communityDelete( postId, session );
		
		// 로그인 안하면 로그인 페이지
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		
		// 삭제 완료 후 community 페이지로 리다이렉트
		return "redirect:/community";
	}
	
	// 글 수정 페이지 이동
	@GetMapping("/edit/{postId}")
	public String edit( @PathVariable("postId") Integer postId, Model model, HttpSession session ) {
		// 로그인 안하면 로그인 페이지로 이동
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		// 수정 해야 할 글 정보
		EditResponse update = service.edit( postId, session );
		
		model.addAttribute("update", update);
		// 수정 페이지 이동
		return "editForm";
	}
	
	// 글 수정 기능
	@PostMapping("/update/{postId}")
	public String update( @PathVariable("postId") Integer postId, HttpSession session, CommunityUpdateRequest dto ) {
		
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		
		service.update( postId, session, dto );
		
		return "redirect:/community";
	}
}
