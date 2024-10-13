package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

	private static NoticeService service;

	// 공지사항 게시판 이동
	@GetMapping
	public String noticeList(Model model, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageSize", defaultValue = "1") int pageNum,
			@RequestParam(value = "boardType", defaultValue = "NOTICE") String boardType) {
		// 굴 수
		int totalPages = service.getTotalnoticeCount(boardType, pageSize);
		// 글 목록 기능
		List<QnaListResponse> noticeList = service.noticeList(boardType, pageSize, pageNum);
		// 게시판 타입
		model.addAttribute("boardType", boardType);
		// 글 목록 데이터
		model.addAttribute("qnaList", noticeList);
		// 페이지 수
		model.addAttribute("currentPage", pageNum);
		// 페이지 사이즈
		model.addAttribute("pageSize", pageSize);
		// 총 페이지 
		model.addAttribute("totalPages", totalPages);

		return "";
	}

	// 글 작성

	// 글 수정

	// 글 삭제

}
