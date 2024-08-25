package com.ProgrammerCommunity.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	
	@NotBlank(message = "�г����� �ʼ� �Է� ���Դϴ�.")
    @Size(max = 20, message = "�г����� �ִ� 20�ڱ��� ���˴ϴ�.")
	private String username;
	
	@Email(message = "��ȿ�� �̸��� �ּҸ� �Է����ּ���.")
    @NotBlank(message = "�̸����� �ʼ� �Է� ���Դϴ�.")
	private String email;
	
	@NotBlank(message = "��й�ȣ�� �ʼ� �Է� ���Դϴ�.")
	@Size(min = 6, message = "��й�ȣ�� �ּ� 6�� �̻��̾�� �մϴ�.")
	private String password;
}
