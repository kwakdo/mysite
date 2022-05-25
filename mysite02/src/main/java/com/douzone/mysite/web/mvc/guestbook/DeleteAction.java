package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String pass = request.getParameter("password");
		System.out.println(pass);
		GuestbookVo vo = new GuestbookVo();
		vo.setNo((long)no);
		vo.setPassword(pass);
		
		new GuestbookRepository().delete(vo);
		WebUtil.redirect(request, response, request.getContextPath() + "/guestbook?a=index");
	}

}
