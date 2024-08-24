package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.SingupRequest;
import com.ProgrammerCommunity.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

	private final UserService UserService;
	
	// �α��� ������ �̵�
	@GetMapping("/loginpage")
    public String loginPage() {
        return "login";
    }
	
	// ȸ������ ������ �̵�
	@GetMapping("/singuppage")
	public String singupPage() {
		return "singup";
	}
	
	// ȸ������ ���
	@PostMapping("/singup")
	public String singup( @ModelAttribute("SingupRequest") @Valid SingupRequest request, Model model ) {
		
		
		
		return "";
	}
	
	// �α��� ���
	@PostMapping("/login")
	public String login(  ) {
		return "";
	}
	
}
