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

	// 로그인 페이지 이동
	@GetMapping("/loginpage")
	public String loginpage(Model model) {
        model.addAttribute("LoginRequest", new LoginRequest());
        return "login";  // 여기서 "login"은 템플릿 이름입니다.
    }

	// 회원가입 페이지 이동
	@GetMapping("/signuppage")
	public String signupPage() {
		return "signup";
	}

	
	// 회원가입 기능
	@PostMapping("/signup")
    public String signup(@ModelAttribute("SignupRequest") @Valid SignupRequest dto, Model model) {
        try {
            userService.signup(dto);
            return "redirect:/login/loginpage";
        } catch (ResponseStatusException e) {
            model.addAttribute("errorMessage", e.getReason());
            return "signup";
        }
    }

	// 로그인 기능
	@PostMapping("/login")
    public String login(@ModelAttribute LoginRequest dto, HttpSession session) {
        try {
            Users user = userService.login(dto);
            if (user != null && user.getUserId() != null) {
                session.setAttribute("userId", user.getUserId());
                return "redirect:/main";
            } else {
                return "redirect:/login?error";
            }
        } catch (ResponseStatusException e) {
            return "redirect:/login?error";
        }
    }
	
	// 로그아웃 기능
	@GetMapping("/logout")
    public String logout(HttpSession session) {
		// 세션 무효화
        session.invalidate();
        
        return "redirect:/login/loginpage";
    }

}
