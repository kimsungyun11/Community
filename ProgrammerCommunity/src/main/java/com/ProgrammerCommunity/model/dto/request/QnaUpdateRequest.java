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

    @NotBlank(message = "������ �ʼ� �Է� �׸��Դϴ�.")
    @Size(min = 2, max = 200, message = "������ 2�� �̻� 200�� ���Ͽ��� �մϴ�.")
    private String title;

    @NotBlank(message = "������ �ʼ� �Է� �׸��Դϴ�.")
    private String content;

    private String tags;
}
