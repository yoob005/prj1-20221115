package com.study.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.study.domain.board.TestBoardDto;
import com.study.domain.board.testPageInfo;
import com.study.service.board.TestBoardService;

@Controller
@RequestMapping("board")
public class TestBoardController {
	
	@Autowired
	private TestBoardService service;
	
	@GetMapping("testRegister")
	public void register() {
	}
	
	@PostMapping("testRegister")
	public String register(TestBoardDto board, RedirectAttributes rttr) {
		int cnt = service.register(board);

		if(cnt==1) {
			rttr.addFlashAttribute("message", "게시물이 작성되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물이 작성되지 않았습니다.");
		}
		
		
		return "redirect:/board/testList";
	}
	
	@GetMapping("testList")
	public void	list(@RequestParam(name = "page", defaultValue = "1") int page, 
			testPageInfo pageInfo,
			Model model
			){
		
		List<TestBoardDto> list = service.listBoard(page, pageInfo);
		
		model.addAttribute("boardList",list);
	}
	
	@GetMapping("testGet")
	public void get(int id, Model model) {
		TestBoardDto board = service.get(id);
		
		model.addAttribute("board", board);
	}
	
	@GetMapping("testModify")
	public void modify(int id, Model model) {
		TestBoardDto board = service.get(id);
		model.addAttribute("board", board);
	}
	
	@PostMapping("testModify")
	public String modify(TestBoardDto board, RedirectAttributes rttr) {
		
		int cnt = service.update(board);

		if(cnt==1) {
			rttr.addFlashAttribute("message", "게시물이 수정되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물이 수정되지 않았습니다.");
		}
		
		return "redirect:/board/testList";
	}
	
	@PostMapping("testRemove")
	public String remove(TestBoardDto board, RedirectAttributes rttr) {

		int cnt = service.remove(board);

		if(cnt==1) {
			rttr.addFlashAttribute("message", "게시물이 삭제되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물이 삭제되지 않았습니다.");
		}
		
		return "redirect:/board/testList";
	}
}












