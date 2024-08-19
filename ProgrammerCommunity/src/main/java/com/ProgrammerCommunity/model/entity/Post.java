package com.ProgrammerCommunity.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Post {
	private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private String tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
