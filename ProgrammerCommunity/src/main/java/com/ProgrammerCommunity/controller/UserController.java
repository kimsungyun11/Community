package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;
import com.ProgrammerCommunity.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// 로그인 페이지 이동
	@GetMapping("/loginpage")
	public String loginPage() {
		return "login";
	}

	// 회원가입 페이지 이동
	@GetMapping("/signuppage")
	public String signupPage() {
		return "signup";
	}

	// 회원가입 기능
	@PostMapping("/signup")
	public String signup(@ModelAttribute("SignupRequest") @Valid SignupRequest dto, Model model) {
		String errorMessage = userService.check(dto);
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			return "signup";
		}
		userService.signup(dto);
		return "redirect:/login/loginpage";
	}

	// 로그인 기능
	@PostMapping("/login")
	public String login() {
		return "";
	}

}
