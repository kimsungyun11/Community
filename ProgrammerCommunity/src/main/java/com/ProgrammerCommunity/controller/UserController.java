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
	
	// �α��� ������ �̵�
	@GetMapping("/loginpage")
    public String loginPage() {
        return "login";
    }
	
	// ȸ������ ������ �̵�
	
	@GetMapping("/signuppage") 
	public String signupPage() {
		return "signup"; 
	}
	 
	/*
	 * @GetMapping("/signuppage") public String showSignupForm(Model model) {
	 * model.addAttribute("SignupRequest", new SignupRequest()); return "signup"; }
	 */
	
	// ȸ������ ���
	@PostMapping("/signup")
	public String signup(@ModelAttribute("SignupRequest") @Valid SignupRequest dto, Model model) {
	  
	    userService.signup(dto);

	    return "redirect:/login/loginpage";  // ���� �� �α��� �������� �����̷�Ʈ
	}
	
	// �α��� ���
	@PostMapping("/login")
	public String login(  ) {
		return "";
	}
	
}
