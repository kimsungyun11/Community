package com.ProgrammerCommunity.model.dto.request;

import java.time.LocalDateTime;

import com.ProgrammerCommunity.model.entity.BoardType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityCreateRequest {
	private Integer userId;
	private BoardType boardType;

    @NotBlank(message = "������ �ʼ� �Է� �׸��Դϴ�.")
    private String title;

    @NotBlank(message = "������ �ʼ� �Է� �׸��Դϴ�.")
    private String content;

    @NotBlank(message = "�±״� �ʼ� �Է� �׸��Դϴ�.")
    private String tags;

    private LocalDateTime createdAt;
}
