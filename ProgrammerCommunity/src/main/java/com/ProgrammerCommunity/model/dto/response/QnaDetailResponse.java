package com.ProgrammerCommunity.model.dto.response;



import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaDetailResponse {
	private Integer postId;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private String username;
    private List<CommentResponse> comments;
}
