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
	
	// community ������ �̵�
	@GetMapping
	public String communityPage( Model model,
								@RequestParam( name = "pageNum", defaultValue = "1" ) int pageNum,
								@RequestParam( name = "pageSize", defaultValue = "10" ) int pageSize,
								@RequestParam( name = "boardType", defaultValue = "COMMUNITY" ) String boardType ) {
		
		// community ����Ʈ
		List<CommunityResponse> list = service.communityPage( pageNum, pageSize, boardType );
		
		// total
		int totalPages = service.tatalPage( pageSize, boardType );
		
		// community ����Ʈ
		model.addAttribute("posts", list);
		// totalPage��
		model.addAttribute("totalPages", totalPages);
		// boardType
		model.addAttribute("boardType", boardType);
		// pageSize
		model.addAttribute("pageSize", pageSize);
		// pageNum
		model.addAttribute("currentPage", pageNum);
		
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
	public String communityCreate( HttpSession session, @ModelAttribute("dto") CommunityCreateResponse dto, Model model ) {
		
		// ���� Ȯ��
		Integer user = (Integer) session.getAttribute("userId");
		
		// �� �ۼ�
		service.createCommunityPost( user, dto );
		
		// �Ϸ�� community������ �̵�
		return "redirect:/community";
	}
	
	// �� �� ������
	@GetMapping("detail/{postId}")
	public String communityDetail( @PathVariable("postId") Integer postId, Model model ) {
		
		CommunityDetailResponse detailPage = service.communityDetail( postId );
		
		model.addAttribute("detailPage", detailPage);
		model.addAttribute("comments", detailPage.getComments());
		return "detailPage";
	}
	
	// �� �輼 ���
	@PostMapping("/delete/{postId}")
	public String communityDelete( @PathVariable("postId") Integer postId, HttpSession session ) {
		
		// ���� ���
		service.communityDelete( postId, session );
		
		// �α��� ���ϸ� �α��� ������
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		
		// ���� �Ϸ� �� community �������� �����̷�Ʈ
		return "redirect:/community";
	}
	
	// �� ���� ������ �̵�
	@GetMapping("/edit/{postId}")
	public String edit( @PathVariable("postId") Integer postId, Model model, HttpSession session ) {
		// �α��� ���ϸ� �α��� �������� �̵�
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		// ���� �ؾ� �� �� ����
		EditResponse update = service.edit( postId, session );
		
		model.addAttribute("update", update);
		// ���� ������ �̵�
		return "editForm";
	}
	
	// �� ���� ���
	@PostMapping("/update/{postId}")
	public String update( @PathVariable("postId") Integer postId, HttpSession session, CommunityUpdateRequest dto ) {
		
		if ( session == null ) {
			return "redirect:/login/loginpage";
		}
		
		service.update( postId, session, dto );
		
		return "redirect:/community";
	}
}
