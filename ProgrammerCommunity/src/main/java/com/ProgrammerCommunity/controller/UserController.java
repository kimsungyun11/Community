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
	
	// 로그인 페이지 이동
	@GetMapping("/loginpage")
    public String loginPage() {
        return "login";
    }
	
	// 회원가입 페이지 이동
	@GetMapping("/singuppage")
	public String singupPage() {
		return "singup";
	}
	
	// 회원가입 기능
	@PostMapping("/singup")
	public String singup( @ModelAttribute("SingupRequest") @Valid SingupRequest request, Model model ) {
		
		
		
		return "";
	}
	
	// 로그인 기능
	@PostMapping("/login")
	public String login(  ) {
		return "";
	}
	
}
