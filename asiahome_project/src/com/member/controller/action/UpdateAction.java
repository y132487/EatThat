package com.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.member.dao.MemberDAO;
import com.member.dto.MemberVO;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		MemberVO vo=new MemberVO();
		vo.setName(request.getParameter("name"));
		vo.setUserid(request.getParameter("userid"));
		vo.setPass(request.getParameter("pass"));
		vo.setEmail(request.getParameter("email"));
		vo.setPhone(request.getParameter("phone"));
		MemberDAO dao=MemberDAO.getInstance();
		dao.updateMember(vo);
		
		String url="Member.do?command=login_action";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
