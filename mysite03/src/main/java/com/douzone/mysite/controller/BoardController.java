package com.douzone.mysite.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
	
	@Controller
	@RequestMapping("/board")
	public class BoardController {
		
		@Autowired
		private BoardService boardService;
		int limit = 5;
		@RequestMapping("")
		public String index(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page, Model model) {
			model.addAttribute("p", page);
			return "board/index";
		}
		

		
		@RequestMapping("/view/{no}")
		public String view(@PathVariable("no") Long no, Model model) {
			BoardVo boardVo = boardService.getContent(no);
			model.addAttribute("boardVo", boardVo);
			return "board/view";
		}
		

}
