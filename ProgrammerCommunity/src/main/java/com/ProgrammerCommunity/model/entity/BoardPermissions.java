package com.ProgrammerCommunity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPermissions {
	private Integer permissionId;  // 권한의 고유 식별자
    private BoardType boardType;   // 게시판 유형 (QNA, COMMUNITY, NOTICE)
    private Action action;         // 수행할 수 있는 동작 (CREATE, READ, UPDATE, DELETE)
    private Boolean isAdminOnly;   // 관리자 전용 권한 여부
}