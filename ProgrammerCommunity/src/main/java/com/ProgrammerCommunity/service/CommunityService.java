package com.ProgrammerCommunity.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.CommunityMapper;
import com.ProgrammerCommunity.model.dto.response.CommunityResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

	private static CommunityMapper mapper;

	// communityList 
	public List<CommunityResponse> communityPage(int pageNum, int pageSize, String boardType) {
		
		// boardType 확인
		if ( !boardType.equals("community") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "community아님" );
		}
		
		// offset
		int offSet = ( pageNum - 1 ) * pageSize;
		
		// communityList
		List<CommunityResponse> community = mapper.ListByBoardType( boardType, offSet, pageSize );
		
		return community;
	}

	// 페이지 수
	public int tatalPage(int pageNum, String boardType) {
		
		// 게시물 수
		int total = mapper.total( boardType );
		
		// 페이지 수
		int totalPage = ( int ) Math.ceil( (double) total / pageNum );
		
		return totalPage;
	}
	
}
