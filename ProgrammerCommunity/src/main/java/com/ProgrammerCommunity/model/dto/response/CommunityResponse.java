package com.ProgrammerCommunity.model.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityResponse {
	private Integer postId;
	private String title;
    private String content;
    private String tags;
    private LocalDateTime updatedAt;
}
