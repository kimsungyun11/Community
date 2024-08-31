package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.model.dto.request.LoginRequest;
import com.ProgrammerCommunity.model.dto.request.SignupRequest;
import com.ProgrammerCommunity.model.entity.Users;
import com.ProgrammerCommunity.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// �α��� ������ �̵�
	@GetMapping("/loginpage")
	public String loginpage(Model model) {
        model.addAttribute("LoginRequest", new LoginRequest());
        return "login";  // ���⼭ "login"�� ���ø� �̸��Դϴ�.
    }

	// ȸ������ ������ �̵�
	@GetMapping("/signuppage")
	public String signupPage() {
		return "signup";
	}

	
	// ȸ������ ���
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

	// �α��� ���
	@PostMapping("/login")
	public String login(@ModelAttribute("LoginRequest") LoginRequest dto, Model model, HttpSession session) {
	    try {
	        Users user = userService.login(dto);
	        session.setAttribute("user", user);
	        return "redirect:/main";
	    } catch (ResponseStatusException e) {
	        model.addAttribute("error", "�α��� ����: " + e.getReason());
	        return "login";  // "redirect:/login" ��� "login"�� ��ȯ
	    }
	}
	
	// �α׾ƿ� ���
	@GetMapping("/logout")
    public String logout(HttpSession session) {
		// ���� ��ȿȭ
        session.invalidate();
        
        return "redirect:/login/loginpage";
    }

}
