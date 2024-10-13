package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.model.entity.Action;

@Service
public class PermissionService {
	
	public boolean hasPermission(String boardType, Action action, Boolean isAdmin) {
		// 관리자 여부가 null인 경우 (로그인하지 않은 경우) 권한 없음
		if (isAdmin == null) {
			return false;
		}

		// 관리자는 모든 게시판과 모든 작업에 대해 권한을 가짐
		if (isAdmin) {
			return true;
		}

		// 일반 사용자의 권한 체크 로직
		switch (boardType) {
		case "NOTICE":
			// 공지사항은 일반 사용자에게 읽기 권한만 부여
			return action == Action.READ;
		case "QNA":
		case "COMMUNITY":
			// QNA와 COMMUNITY 게시판은 일반 사용자에게 모든 권한 부여
			return true;
		default:
			// 정의되지 않은 게시판 유형에 대해서는 권한 없음
			return false;
		}
		
	}
}
