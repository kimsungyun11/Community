package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginMapper loginMapper;
	
}
