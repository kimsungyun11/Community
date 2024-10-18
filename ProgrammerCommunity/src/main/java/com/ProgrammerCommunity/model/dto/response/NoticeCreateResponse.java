package com.ProgrammerCommunity.model.dto.response;

import java.time.LocalDateTime;

import com.ProgrammerCommunity.model.entity.BoardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateResponse {
    private Integer userId;
    private BoardType boardType;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}