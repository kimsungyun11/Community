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

    // ���� ���� ��� �̵�
    @GetMapping
    public String noticeList(Model model,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam( name = "boardType", defaultValue = "NOTICE" ) String boardType) {
    	// �� ���
        List<NoticeResponse> noticeList = noticeService.getNoticeList(pageNum, pageSize);
        // ������ ��
        int totalPages = noticeService.getTotalPages(pageSize);
        // �� ���
        model.addAttribute("posts", noticeList);
        // ������ ��ȣ
        model.addAttribute("currentPage", pageNum);
        // ������ ũ��
        model.addAttribute("pageSize", pageSize);
        // �� ������ ��
        model.addAttribute("totalPages", totalPages);
        // �Խ��� Ÿ��
        model.addAttribute("boardType", boardType);

        return "noticeBoard";
    }

    // �������� �ۼ� ������ �̵�
    @GetMapping("/write")
    public String writePage(Model model, HttpSession session) {
    	// ������ Ȯ��
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // ������ �ƴϸ� ����
        if (!permissionService.hasPermission("NOTICE", Action.CREATE, isAdmin)) {
            return "redirect:/error";
        }
        model.addAttribute("dto", new NoticeCreateResponse());
        return "noticeWrite";
    }
    

    // �������� ����
    @PostMapping("/create")
    public String createNotice(@ModelAttribute("dto") NoticeCreateResponse dto, HttpSession session) {
    	// ������ Ȯ��
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // ������ �ƴϸ� ����
        if (!permissionService.hasPermission("NOTICE", Action.CREATE, isAdmin)) {
            return "redirect:/error";
        }
        // ���� ����
        Integer userId = (Integer) session.getAttribute("userId");
        noticeService.createNotice(userId, dto);
        return "redirect:/notice";
    }

    // �������� �� ����
    @GetMapping("/detail/{postId}")
    public String noticeDetail(@PathVariable("postId") Integer postId, Model model) {
    	// �� ����
        NoticeDetailResponse detailPage = noticeService.getNoticeDetail(postId);
        model.addAttribute("detailPage", detailPage);
        return "noticeDetailPage";
    }

    // �������� ����
    @PostMapping("/delete/{postId}")
    public String deleteNotice(@PathVariable("postId") Integer postId, HttpSession session) {
    	// ������ Ȯ��
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // ������ �ƴϸ� ����
        if (!permissionService.hasPermission("NOTICE", Action.DELETE, isAdmin)) {
            return "redirect:/error";
        }
        noticeService.deleteNotice(postId);
        return "redirect:/notice";
    }

    // �������� ���� ������ �̵�
    @GetMapping("/edit/{postId}")
    public String editNoticePage(@PathVariable("postId") Integer postId, Model model, HttpSession session) {
    	// ������ Ȯ�� 
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // ������ �ƴϸ� ����
        if (!permissionService.hasPermission("NOTICE", Action.UPDATE, isAdmin)) {
            return "redirect:/error";
        }
        // ���� ������ ������
        NoticeDetailResponse noticeData = noticeService.getNoticeDetail(postId);
        model.addAttribute("notice", noticeData);
        return "editForm";
    }

    // �������� ����
    @PostMapping("/update/{postId}")
    public String updateNotice(@PathVariable("postId") Integer postId, 
                               @ModelAttribute NoticeUpdateRequest dto, 
                               HttpSession session) {
    	// ������ Ȯ��
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        // ������ �ƴϸ� ����
        if (!permissionService.hasPermission("NOTICE", Action.UPDATE, isAdmin)) {
            return "redirect:/error";
        }
        noticeService.updateNotice(postId, dto);
        return "redirect:/notice/detail/" + postId;
    }
}