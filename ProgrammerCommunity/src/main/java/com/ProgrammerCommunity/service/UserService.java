package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper UserMapper;
	
}
