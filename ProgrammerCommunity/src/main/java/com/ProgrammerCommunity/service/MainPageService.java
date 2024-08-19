package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.MainPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainPageService {

	private MainPageMapper mainPageMapper;
	
}
