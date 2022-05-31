package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		int groupNo = Integer.parseInt(request.getParameter("groupno"));
		// int orderNo = Integer.parseInt(request.getParameter("orderno"));
		// int depth = Integer.parseInt(request.getParameter("depth"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("content"));
		vo.setHit((long)0);
		vo.setGroupNo((long)groupNo);
		vo.setOrderNo((long)1);
		vo.setDepth((long)0);
		vo.setUserNo(authUser.getNo());
		
		
		new BoardRepository().insert(vo);

		System.out.println(authUser.getName());
		WebUtil.redirect(request, response, request.getContextPath() + "/board?a=index");
	}

}