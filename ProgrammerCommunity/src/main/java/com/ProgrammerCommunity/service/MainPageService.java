package com.ProgrammerCommunity.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.MainPageMapper;
import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.model.dto.response.RecentBoardResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainPageService {

	private final MainPageMapper mainPageMapper;

	// 검색기능
	public List<MainPageSearchResponse> searchMain(String search, int pageSize, int pageNum) {
		
		// 검색어가 없으면 에러
        if ( search == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "검색어가 없음");
        }
        
        // offset 설정
        int offset = (pageNum - 1) * pageSize;
        
        return mainPageMapper.mainPageSearch(search, pageSize, offset);
    }

	// 최신 글 5개
	public List<RecentBoardResponse> index() {
		
		// 최신 글 5개 넣기
		List<RecentBoardResponse> recentBoard = mainPageMapper.recent();
		
		return recentBoard;
	}
	
}