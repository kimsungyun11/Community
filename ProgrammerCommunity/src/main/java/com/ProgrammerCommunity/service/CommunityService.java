package com.ProgrammerCommunity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.CommunityMapper;
import com.ProgrammerCommunity.model.dto.response.CommunityCreateResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityDetailResponse;
import com.ProgrammerCommunity.model.dto.response.CommunityResponse;
import com.ProgrammerCommunity.model.entity.BoardType;
import com.ProgrammerCommunity.model.entity.Posts;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

	private final CommunityMapper mapper;

	// communityList 
	public List<CommunityResponse> communityPage(int pageNum, int pageSize, String boardType) {
		
		// boardType 확인
		if ( !boardType.equals("COMMUNITY") ) {
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

	// 글 생성
	public void createCommunityPost(Integer user, CommunityCreateResponse dto) {
		
		if ( user == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "로그인 안했습니다");
		}
		
		dto.setBoardType(BoardType.COMMUNITY);
		dto.setCreatedAt(LocalDateTime.now());
		dto.setUserId(user);
		mapper.createCommunity( dto );
		
	}

	// 글 상세 페이지 
	public CommunityDetailResponse communityDetail(Integer postId) {
		
		// postid 확인
		if ( postId == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "글 정보가 없습니다");
		}
		
		CommunityDetailResponse communityDetail =  mapper.communityDetailByPostid( postId );
		
		return communityDetail;
	}
	
}
