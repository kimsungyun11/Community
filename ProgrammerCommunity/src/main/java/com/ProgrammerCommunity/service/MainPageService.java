package com.ProgrammerCommunity.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.MainPageMapper;
import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.model.entity.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainPageService {

	private MainPageMapper mainPageMapper;

	// 검색 기능
	public List<MainPageSearchResponse> searchMain(String search, int pageSize, int pageNum) {
		
		// 검색어가 없으면 예외발생
        if ( search == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "검색어가 없음");
        }
        
        // 페이징을 위한 offset 계산
        int offset = (pageNum - 1) * pageSize;
        
        return mainPageMapper.mainPageSearch(search, pageSize, offset);
    }
	
}
