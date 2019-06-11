package com.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Action;

import com.member.dao.MemberDAO;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		String userid=request.getParameter("userid");
		MemberDAO dao=MemberDAO.getInstance();
		dao.deleteMember(userid);
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		String url="index.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
