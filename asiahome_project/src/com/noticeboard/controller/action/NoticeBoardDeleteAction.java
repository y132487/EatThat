package com.noticeboard.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.noticeboard.dao.NoticeBoardDAO;

public class NoticeBoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws ServletException, IOException {
		String num=request.getParameter("num");
		NoticeBoardDAO dao=NoticeBoardDAO.getInstance();
		dao.deleteBoard(num);
		
		String url="NoticeBoard.do?command=notice_board_list?1";
		response.sendRedirect(url);
	}

}
