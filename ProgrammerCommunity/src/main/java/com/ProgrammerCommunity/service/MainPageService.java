package com.ProgrammerCommunity.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.mapper.MainPageMapper;
import com.ProgrammerCommunity.model.dto.response.MainPageSearchResponse;
import com.ProgrammerCommunity.model.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainPageService {

	private MainPageMapper mainPageMapper;

	public List<MainPageSearchResponse> searchMain(String search) {
		
		if ( search == null ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "검색어가 없음");
		}
		
		
		return null;
	}
	
}
