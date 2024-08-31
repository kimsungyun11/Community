package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.entity.Users;
import com.ProgrammerCommunity.service.QnaPostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaPostController {
	
	private final QnaPostService service;
	
	// �Խ��� ������ �̵� ���
	@GetMapping("/qnapage")
	public String qnaPage() {
		return "qnaBoard";
	}
	
	// �� �ۼ� ������ �̵� ���
	@GetMapping("/write")
    public String write(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";  // �α��� �������� �����̷�Ʈ
        }
        model.addAttribute("dto", new QnaCreateRequest());
        return "qna/qnaWrite";
    }
	
	// �� �ۼ� ���
	@PostMapping("/create")
    public String create(@ModelAttribute("dto") @Valid QnaCreateRequest dto,
                         BindingResult bindingResult,
                         HttpSession session,
                         Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";  // �α��� �������� �����̷�Ʈ
        }

        if (bindingResult.hasErrors()) {
            return "qna/qnaWrite";
        }
        
        dto.setUserId(user.getUserId());  // Users Ŭ������ getId() �޼ҵ尡 �ִٰ� ����
        service.createQnaPost(dto);
        return "redirect:/qna/qnapage";
    }
	
}
