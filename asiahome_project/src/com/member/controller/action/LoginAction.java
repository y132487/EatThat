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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		String url="Member.do?command=login_form";
		String userid=request.getParameter("userid");
		String pass=request.getParameter("pass");
		
		MemberDAO dao=MemberDAO.getInstance();
		int result=dao.userCheck(userid,pass);
		
		if(result==1){
			MemberVO vo=dao.getMember(userid);
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", vo);
			url="index.jsp";
		}else if(result==0){
			request.setAttribute("message","비밀번호가 일치하지 않습니다");
		}else if(result==-1){
			request.setAttribute("message", "존재하지 않는 회원입니다");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
	}

}
