
package com.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

public class JoinFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		String url = "/member/joinForm.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}


}
