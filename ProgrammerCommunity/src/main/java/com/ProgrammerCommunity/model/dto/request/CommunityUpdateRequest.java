package com.ProgrammerCommunity.model.dto.request;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUpdateRequest {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
}
