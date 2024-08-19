package com.ProgrammerCommunity.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
	private Integer commentId;
    private Integer postId;
    private Integer userId;
    private Integer parentCommentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
