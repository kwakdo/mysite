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
		//-----------------------------------------------------
				// 접근 제어
				HttpSession session = request.getSession();
				if(session == null) {
					WebUtil.redirect(request, response, request.getContextPath());
					return;
				}
				
				UserVo authUser = (UserVo)session.getAttribute("authUser");
				// 접근 제어
				if(authUser == null) {
					WebUtil.redirect(request, response, request.getContextPath());
					return;
				}		
		//-----------------------------------------------------
				
				String no = request.getParameter("no");
				String groupNo = request.getParameter("groupNo");
				String orderNo = request.getParameter("orderNo");
				String depth = request.getParameter("depth");	
				
				String title = request.getParameter("title");
				String contents = request.getParameter("content");
				
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);	
				vo.setUserNo(authUser.getNo());
				System.out.println(groupNo + "," + orderNo + "," + depth);
				if("".equals(groupNo) || "".equals(orderNo) || "".equals(depth)) {
					new BoardRepository().insert(vo);
					System.out.println("INSERT");
					WebUtil.redirect(request, response, request.getContextPath() + "/board");
					return;
				}
				if(groupNo == null || orderNo == null || depth == null) {
					new BoardRepository().insert(vo);
					System.out.println("INSERT");
					WebUtil.redirect(request, response, request.getContextPath() + "/board");
					return;
				}
				
				vo.setNo(Long.parseLong(no));
				vo.setGroupNo(Long.parseLong(groupNo));
				vo.setOrderNo(Long.parseLong(orderNo));
				vo.setDepth(Long.parseLong(depth));
				
				new BoardRepository().reply(vo);
				
				System.out.println("REPLY");
				WebUtil.redirect(request, response, request.getContextPath() + "/board");
				
					
			}

		}