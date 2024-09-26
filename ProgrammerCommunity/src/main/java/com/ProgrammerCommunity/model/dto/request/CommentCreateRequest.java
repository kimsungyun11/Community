package com.ProgrammerCommunity.model.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequest {

    private Integer postId;

    private Integer userId;

    private Integer parentCommentId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    private LocalDateTime createdAt;
}
