package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/loginPage")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	
	@GetMapping("")
    public String loginPage() {
        return "login";
    }
	
	
	
}
