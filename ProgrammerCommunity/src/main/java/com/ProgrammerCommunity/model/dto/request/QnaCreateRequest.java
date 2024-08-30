package com.ProgrammerCommunity.model.dto.request;

import java.time.LocalDateTime;

import com.ProgrammerCommunity.model.entity.BoardType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaCreateRequest {
	private BoardType boardType;
	
	@NotBlank
    private String title;
	
	@NotBlank
    private String content;
	
	@NotBlank
    private String tags;
    private LocalDateTime createdAt;
}
