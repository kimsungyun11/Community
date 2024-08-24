package com.ProgrammerCommunity.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
	private Integer commentId;
    private Integer postId;
    private Integer userId;
    private Integer parentCommentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
