package com.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.member.dao.MemberDAO;

public class IdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		String userid=request.getParameter("userid");
		MemberDAO dao=MemberDAO.getInstance();
		int result=dao.confirmID(userid);
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("member/idcheck.jsp"); //idcheck.jsp로 request 객체 보냄
		dispatcher.forward(request, response);
	}

}
