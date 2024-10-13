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

	// �������� �Խ��� �̵�
	@GetMapping
	public String noticeList(Model model, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageSize", defaultValue = "1") int pageNum,
			@RequestParam(value = "boardType", defaultValue = "NOTICE") String boardType) {
		// �� ��
		int totalPages = service.getTotalnoticeCount(boardType, pageSize);
		// �� ��� ���
		List<QnaListResponse> noticeList = service.noticeList(boardType, pageSize, pageNum);
		// �Խ��� Ÿ��
		model.addAttribute("boardType", boardType);
		// �� ��� ������
		model.addAttribute("qnaList", noticeList);
		// ������ ��
		model.addAttribute("currentPage", pageNum);
		// ������ ������
		model.addAttribute("pageSize", pageSize);
		// �� ������ 
		model.addAttribute("totalPages", totalPages);

		return "";
	}

	// �� �ۼ�

	// �� ����

	// �� ����

}
