package com.ProgrammerCommunity.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class QnaTagsSearchResponse {
	private String title;
    private String content;
    private String tags;
    private LocalDateTime updatedAt;
}
