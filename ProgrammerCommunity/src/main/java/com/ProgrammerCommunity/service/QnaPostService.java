package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.entity.Posts;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;

	// 글 작성 기능
	public void create(@Valid QnaCreateRequest dto) {
		
		LocalDateTime time = LocalDateTime.now();
		
		mapper.createQna( dto, time );
	}
	
}
