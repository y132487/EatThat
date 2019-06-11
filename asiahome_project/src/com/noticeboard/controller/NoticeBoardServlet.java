package com.noticeboard.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Action;


/**
 * Servlet implementation class NoticeBoardServlet
 */
//@WebServlet("/NoticeBoard.do")
public class NoticeBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=null;
		String board_command=request.getParameter("command");
		StringTokenizer token=new StringTokenizer(board_command,"?");
		String isListView=token.nextToken();
		if(isListView.equals("notice_board_list")){
			command=isListView;
			request.setAttribute("pageNum", token.nextToken());
		}else if(isListView.equals("notice_board_search")){
			command=isListView;
			request.setAttribute("pageNum", token.nextToken());
			request.setAttribute("boolean",token.nextToken());
		}else{
			command=request.getParameter("command");
		}
		ActionFactory af=ActionFactory.getInstance();
		Action action=af.getAction(command);
		ServletContext context=getServletContext();
		if(action!=null){
			action.execute(request,response,context);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request,response);
	}

}
