package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

import com.ProgrammerCommunity.model.entity.Action;

@Service
public class PermissionService {
	
	public boolean hasPermission(String boardType, Action action, Boolean isAdmin) {
		// ������ ���ΰ� null�� ��� (�α������� ���� ���) ���� ����
		if (isAdmin == null) {
			return false;
		}

		// �����ڴ� ��� �Խ��ǰ� ��� �۾��� ���� ������ ����
		if (isAdmin) {
			return true;
		}

		// �Ϲ� ������� ���� üũ ����
		switch (boardType) {
		case "NOTICE":
			// ���������� �Ϲ� ����ڿ��� �б� ���Ѹ� �ο�
			return action == Action.READ;
		case "QNA":
		case "COMMUNITY":
			// QNA�� COMMUNITY �Խ����� �Ϲ� ����ڿ��� ��� ���� �ο�
			return true;
		default:
			// ���ǵ��� ���� �Խ��� ������ ���ؼ��� ���� ����
			return false;
		}
		
	}
}
