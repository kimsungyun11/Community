package com.ProgrammerCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ProgrammerCommunity.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class NoticeController {

	private static NoticeService service;
	
}
