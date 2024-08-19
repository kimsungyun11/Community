package com.ProgrammerCommunity.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	private Integer userId;
	private String username;
	private String email;
	private String passwordHash;
	private LocalDateTime createdAt;
}
