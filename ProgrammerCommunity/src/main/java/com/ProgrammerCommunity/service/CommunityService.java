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
		
		// boardType Ȯ��
		if ( !boardType.equals("COMMUNITY") ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "community�ƴ�" );
		}
		
		// offset
		int offSet = ( pageNum - 1 ) * pageSize;
		
		// communityList
		List<CommunityResponse> community = mapper.ListByBoardType( boardType, offSet, pageSize );
		
		return community;
	}

	// ������ ��
	public int tatalPage(int pageNum, String boardType) {
		
		// �Խù� ��
		int total = mapper.total( boardType );
		
		// ������ ��
		int totalPage = ( int ) Math.ceil( (double) total / pageNum );
		
		return totalPage;
	}

	// �� ����
	public void createCommunityPost(Integer user, CommunityCreateResponse dto) {
		
		if ( user == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "�α��� ���߽��ϴ�");
		}
		
		dto.setBoardType(BoardType.COMMUNITY);
		dto.setCreatedAt(LocalDateTime.now());
		dto.setUserId(user);
		mapper.createCommunity( dto );
		
	}

	// �� �� ������ 
	public CommunityDetailResponse communityDetail(Integer postId) {
		
		// postid Ȯ��
		if ( postId == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "�� ������ �����ϴ�");
		}
		
		CommunityDetailResponse communityDetail =  mapper.communityDetailByPostid( postId );
		
		return communityDetail;
	}
	
}
