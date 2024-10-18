package com.ProgrammerCommunity.service;

import com.ProgrammerCommunity.mapper.NoticeMapper;
import com.ProgrammerCommunity.model.dto.request.NoticeUpdateRequest;
import com.ProgrammerCommunity.model.dto.response.NoticeCreateResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeDetailResponse;
import com.ProgrammerCommunity.model.dto.response.NoticeResponse;
import com.ProgrammerCommunity.model.entity.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    // 공지사항 목록 조회
    public List<NoticeResponse> getNoticeList(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return noticeMapper.getNoticeList(offset, pageSize);
    }

    // 전체 페이지수 계산
    public int getTotalPages(int pageSize) {
        int total = noticeMapper.getTotalNoticeCount();
        return (int) Math.ceil((double) total / pageSize);
    }

    // 새 공지사항 생성
    public void createNotice(Integer userId, NoticeCreateResponse dto) {
        dto.setBoardType(BoardType.NOTICE);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUserId(userId);
        noticeMapper.createNotice(dto);
    }

    // 공지사항 상세 페이지
    public NoticeDetailResponse getNoticeDetail(Integer postId) {
        if (postId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "공지사항 ID가 없습니다.");
        }
        return noticeMapper.getNoticeDetailByPostId(postId);
    }

    // 공지사항 삭제
    public void deleteNotice(Integer postId) {
        noticeMapper.deleteNoticeByPostId(postId);
    }

    // 공지사항 수정
    public void updateNotice(Integer postId, NoticeUpdateRequest dto) {
        noticeMapper.updateNotice(postId, dto);
    }
}