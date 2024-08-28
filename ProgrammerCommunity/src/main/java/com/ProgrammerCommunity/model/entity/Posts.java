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
public class Posts {
	private int postId;
    private int userId;
    private BoardType boardType;
    private String title;
    private String content;
    private String tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}