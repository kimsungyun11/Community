package com.ProgrammerCommunity.service;

import org.springframework.stereotype.Service;

/**
 * ����� ������ Ȯ���ϴ� ���� Ŭ�����Դϴ�.
 * �� �Խ��� ������ �۾��� ���� ������ �˻��մϴ�.
 */
@Service
public class PermissionService {
	
	public boolean hasPermission(String boardType, String action, Boolean isAdmin) {
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
			return "READ".equals(action);
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
