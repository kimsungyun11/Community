package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.UserPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPageService {
	private final UserPageMapper mapper;
}
