package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.mapper.QnaPostMapper;
import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.entity.BoardType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
	
	private final QnaPostMapper mapper;

	// 글 작성 기능
	public void createQnaPost(@Valid QnaCreateRequest dto) {
        dto.setCreatedAt(LocalDateTime.now());
        dto.setBoardType(BoardType.QNA);
        mapper.createQna(dto);
    }
	
}
