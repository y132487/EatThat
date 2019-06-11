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
import com.member.dto.MemberVO;

public class JoinAction implements Action {

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
		int result=dao.insertMember(vo);
		
		if(result==1){
			HttpSession session=request.getSession();
			session.setAttribute("userid",vo.getUserid());
		}
		String url="Member.do?command=login_form";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
