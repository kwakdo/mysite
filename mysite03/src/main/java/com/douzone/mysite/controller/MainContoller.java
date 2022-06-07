package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContoller {
	@RequestMapping({"/", "/main"})
	public String index() {
		return "/WEB-INF/views/main/index.jsp";
	}

}
