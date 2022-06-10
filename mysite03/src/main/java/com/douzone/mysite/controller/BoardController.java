package com.douzone.mysite.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
	
	@Controller
	@RequestMapping("/board")
	public class BoardController {
		
		@Autowired
		private BoardService boardService;
		
		@RequestMapping("")
		public String index(@RequestParam(value = "p", required = true, defaultValue = "1") int pages, Model model) {
			model.addAttribute("p", pages);
			model.addAttribute("list", boardService.getContentsList(pages));
			return "board/index";
		}
		
		
		@RequestMapping("/view/{no}")
		public String view(@PathVariable("no") Long no, Model model) {
			BoardVo boardVo = boardService.getContent(no);
			model.addAttribute("boardVo", boardVo);
			return "board/view";
		}
		
		@RequestMapping(value = "/write", method = RequestMethod.GET)
		public String write() {
			return "board/write";
		}

		

}
