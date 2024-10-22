package com.ProgrammerCommunity.controller;

import com.ProgrammerCommunity.model.dto.request.NoticeUpdateRequest;
import com.ProgrammerCommunity.model.dto.response.NoticeCreateResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeDetailResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeResponse;
import com.ProgrammerCommunity.model.entity.Action;
import com.ProgrammerCommunity.service.NoticeService;
import com.ProgrammerCommunity.service.PermissionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final PermissionService permissionService;

    // 공지 사항 목록 이동
    @GetMapping
    public String noticeList(Model model,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam( name = "boardType", defaultValue = "NOTICE" ) String boardType) {
    	// 글 목록
        List<NoticeResponse> noticeList = noticeService.getNoticeList(pageNum, pageSize);
        // 페이지 수
        int totalPages = noticeService.getTotalPages(pageSize);
        // 글 목록
        model.addAttribute("posts", noticeList);
        // 페이지 번호
        model.addAttribute("currentPage", pageNum);
        // 페이지 크기
        model.addAttribute("pageSize", pageSize);
        // 총 페이지 수
        model.addAttribute("totalPages", totalPages);
        // 게시판 타입
        model.addAttribute("boardType", boardType);

        return "noticeBoard";
    }

    // 공지사항 작성 페이지 이동
    @GetMapping("/write")
    public String writePage(Model model, HttpSession session) {
    	// 관리자 확인
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // 관리자 아니면 에러
        if (!permissionService.hasPermission("NOTICE", Action.CREATE, isAdmin)) {
            return "redirect:/error";
        }
        model.addAttribute("dto", new NoticeCreateResponse());
        return "noticeWrite";
    }
    

    // 공지사항 생성
    @PostMapping("/create")
    public String createNotice(@ModelAttribute("dto") NoticeCreateResponse dto, HttpSession session) {
    	// 관리자 확인
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // 관리자 아니면 에러
        if (!permissionService.hasPermission("NOTICE", Action.CREATE, isAdmin)) {
            return "redirect:/error";
        }
        // 유저 정보
        Integer userId = (Integer) session.getAttribute("userId");
        noticeService.createNotice(userId, dto);
        return "redirect:/notice";
    }

    // 공지사항 상세 내용
    @GetMapping("/detail/{postId}")
    public String noticeDetail(@PathVariable("postId") Integer postId, Model model) {
    	// 상세 내용
        NoticeDetailResponse detailPage = noticeService.getNoticeDetail(postId);
        model.addAttribute("detailPage", detailPage);
        return "noticeDetailPage";
    }

    // 공지사항 삭제
    @PostMapping("/delete/{postId}")
    public String deleteNotice(@PathVariable("postId") Integer postId, HttpSession session) {
    	// 관리자 확인
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // 관리자 아니면 에러
        if (!permissionService.hasPermission("NOTICE", Action.DELETE, isAdmin)) {
            return "redirect:/error";
        }
        noticeService.deleteNotice(postId);
        return "redirect:/notice";
    }

    // 공지사항 수정 페이지 이동
    @GetMapping("/edit/{postId}")
    public String editNoticePage(@PathVariable("postId") Integer postId, Model model, HttpSession session) {
    	// 관리자 확인 
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // 관리자 아니면 에러
        if (!permissionService.hasPermission("NOTICE", Action.UPDATE, isAdmin)) {
            return "redirect:/error";
        }
        // 수정 페이지 데이터
        NoticeDetailResponse noticeData = noticeService.getNoticeDetail(postId);
        model.addAttribute("notice", noticeData);
        return "editForm";
    }

    // 공지사항 수정
    @PostMapping("/update/{postId}")
    public String updateNotice(@PathVariable("postId") Integer postId, 
                               @ModelAttribute NoticeUpdateRequest dto, 
                               HttpSession session) {
    	// 관리자 확인
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // 관리자 아니면 에러
        if (!permissionService.hasPermission("NOTICE", Action.UPDATE, isAdmin)) {
            return "redirect:/error";
        }
        noticeService.updateNotice(postId, dto);
        return "redirect:/notice/detail/" + postId;
    }
}