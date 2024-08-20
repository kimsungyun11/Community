package com.ProgrammerCommunity.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPageSearchResponse {
	private String title;
	private LocalDateTime createdAt;
	private String username;
}
