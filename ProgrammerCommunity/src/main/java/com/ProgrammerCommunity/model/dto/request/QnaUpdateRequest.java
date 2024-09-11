package com.ProgrammerCommunity.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaUpdateRequest {
	private Integer postId;

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(min = 2, max = 200, message = "제목은 2자 이상 200자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    private String tags;
}
