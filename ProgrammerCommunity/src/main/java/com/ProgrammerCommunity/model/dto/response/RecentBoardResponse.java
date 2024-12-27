package com.ProgrammerCommunity.model.dto.response;

import java.time.LocalDateTime;

import com.ProgrammerCommunity.model.entity.BoardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentBoardResponse {
	private Integer postId;
	private Integer userId;
	private String title;
	private BoardType boardType;
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
