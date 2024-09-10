package com.ProgrammerCommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ProgrammerCommunity.model.dto.request.QnaCreateRequest;
import com.ProgrammerCommunity.model.dto.response.QnaDetailResponse;
import com.ProgrammerCommunity.model.dto.response.QnaListResponse;
import com.ProgrammerCommunity.model.dto.response.QnaTagsSearchResponse;
import com.ProgrammerCommunity.service.QnaPostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaPostController {
	
	private final QnaPostService service;
	
	// 게시판 페이지 이동 기능
	@GetMapping("/qnapage")
	public String qnaPage( Model model, 
						@RequestParam( value = "pageSize", defaultValue = "10" ) int pageSize,
						@RequestParam( value = "pageNum", defaultValue = "1" ) int pageNum,
						@RequestParam( value = "boardType", defaultValue = "QNA") String boardType) {
		
		int totalPages = service.getTotalQnaCount( boardType, pageSize );
		
		List<QnaListResponse> qnaList = service.qnaList( boardType, pageSize, pageNum );
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", totalPages);
		
		return "qnaBoard";
	}
	
	// 글 작성 페이지 이동 기능
	@GetMapping("/write")
	public String write(Model model, HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");

	    if (userId == null) {
	        return "redirect:/login/loginpage";
	    }
	    model.addAttribute("dto", new QnaCreateRequest());
	    return "qnaWrite";
	}
	
	// 글 작성 기능
	@PostMapping("/create")
    public String create(@ModelAttribute("dto") @Valid QnaCreateRequest dto,
                         BindingResult bindingResult,
                         HttpSession session,
                         Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

	    if (userId == null) {
	        return "redirect:/login/loginpage";
	    }
        
        if (bindingResult.hasErrors()) {
            return "qna/qnaWrite";
        }
        
        dto.setUserId(userId);
        service.createQnaPost(dto);
        return "redirect:/qna/qnapage";
    }
	
	// 글 상세 페이지 기능
	@GetMapping("/detail/{postId}")
	public String detail( @PathVariable("postId") Integer postId, Model model ) {
		
		// 글 상세 페이지 
		QnaDetailResponse qnaDetail = service.detail( postId );
		
		model.addAttribute("qnaDetail", qnaDetail); // 상세 페이지 뷰로
		
		return "qnaDetail";
	}
	
	// 태그 클릭 시 검색
	@GetMapping("/tag/{tags}")
	public String tagSearch(@PathVariable("tags") String tags,
	                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
	                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
	                        Model model) {

	    int totalPages = service.getTotalTaggedQnaCount(tags, pageSize);

	    List<QnaTagsSearchResponse> qnaList = service.tagSearch(tags, pageSize, pageNum);

	    model.addAttribute("tags", tags);
	    model.addAttribute("qnaList", qnaList);
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPages", totalPages);

	    return "qnaBoard";
	}
	
	// 글 삭제 기능
	@DeleteMapping("/delete/{postId}")
	public String delete( @PathVariable("postId") Integer postId, HttpSession session ) {
		
		// userId 
		Integer user = (Integer) session.getAttribute("userId");
		
		// 글 삭제
		service.delete( user, postId );
		
		return "redirect:/qna/qnapage";
	}
	
	
}
