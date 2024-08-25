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
	
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(max = 20, message = "닉네임은 최대 20자까지 허용됩니다.")
	private String username;
	
	@Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
	private String password;
}
