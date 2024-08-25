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

	private final MainPageMapper mainPageMapper;

	// �˻����
	public List<MainPageSearchResponse> searchMain(String search, int pageSize, int pageNum) {
		
		// �˻�� ������ ����
        if ( search == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�˻�� ����");
        }
        
        // offset ����
        int offset = (pageNum - 1) * pageSize;
        
        return mainPageMapper.mainPageSearch(search, pageSize, offset);
    }
	
}
