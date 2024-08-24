package com.ProgrammerCommunity.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private Integer userId;
	private String username;
	private String email;
	private String passwordHash;
	private LocalDateTime createdAt;
}
