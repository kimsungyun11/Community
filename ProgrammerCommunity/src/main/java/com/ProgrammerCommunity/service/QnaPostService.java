package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.QnaPostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;
	
}
