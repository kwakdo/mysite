package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WritetFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근제어 ---------------------------------------------
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.forward(request, response, "user/loginform");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtil.forward(request, response, "user/loginform");
			return;
		}
		// ----------------------------------------------------
		
		WebUtil.forward(request, response, "board/write");

	}

}
