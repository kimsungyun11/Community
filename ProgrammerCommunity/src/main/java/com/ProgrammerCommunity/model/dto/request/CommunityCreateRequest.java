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

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    @NotBlank(message = "태그는 필수 입력 항목입니다.")
    private String tags;

    private LocalDateTime createdAt;
}
