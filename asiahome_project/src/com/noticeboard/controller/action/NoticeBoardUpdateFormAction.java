package com.noticeboard.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.noticeboard.dao.NoticeBoardDAO;
import com.noticeboard.dto.NoticeBoardVO;

public class NoticeBoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws ServletException, IOException {
		String url="commu_00/commu_01.jsp";
		String num=request.getParameter("num");
		
		NoticeBoardDAO dao=NoticeBoardDAO.getInstance();
		NoticeBoardVO vo=dao.selectOneBoardByNum(num);
		
		request.setAttribute("board", vo);
		request.setAttribute("formType","update");
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
		
	}

}
