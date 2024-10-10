package com.ProgrammerCommunity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPermissions {
	private Integer permissionId;  // ������ ���� �ĺ���
    private BoardType boardType;   // �Խ��� ���� (QNA, COMMUNITY, NOTICE)
    private Action action;         // ������ �� �ִ� ���� (CREATE, READ, UPDATE, DELETE)
    private Boolean isAdminOnly;   // ������ ���� ���� ����
}
