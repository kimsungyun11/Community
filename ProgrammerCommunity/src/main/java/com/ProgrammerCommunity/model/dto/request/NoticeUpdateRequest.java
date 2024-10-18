package com.ProgrammerCommunity.model.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeUpdateRequest {
	private String title;
    private String content;
}
