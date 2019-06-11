package com.noticeboard.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Action;

import com.noticeboard.dao.NoticeBoardDAO;
import com.noticeboard.dto.NoticeBoardVO;

public class NoticeBoardSearchAction implements Action {

	private static final int COUNT_PER_PAGE=10;
	
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws ServletException, IOException {
		String keyword=null;
		String type=null;
		HttpSession session=request.getSession();
		if(request.getAttribute("boolean").equals("true")){
			session.setAttribute("search_bar", request.getParameter("search_bar"));
			session.setAttribute("search_option", request.getParameter("search_option"));
		}
		String pageNumParam=(String) request.getAttribute("pageNum");
		keyword=(String) session.getAttribute("search_bar");
		type=(String) session.getAttribute("search_option");
		System.out.println(pageNumParam);
		int pageNum=1;
		if(pageNumParam!=null&&pageNumParam.length()>0){
			pageNum=Integer.parseInt(pageNumParam);
		}
		
		NoticeBoardDAO dao=NoticeBoardDAO.getInstance();
		
		int totalArticleCount=dao.getSearchCount(type,keyword);
		System.out.println("서치결과갯수 - "+totalArticleCount);
		int totalPageCount=getTotalPageCount(totalArticleCount);
		
		int firstRow=(pageNum-1)*COUNT_PER_PAGE+1;
		int endRow=firstRow+COUNT_PER_PAGE-1;
		if(endRow>totalArticleCount){
			endRow=totalArticleCount;
		}
		
		if(totalPageCount>0){
			int beginPageNum=(pageNum-1)/10*10+1;
			int endPageNum=beginPageNum+9;
			if(endPageNum>totalPageCount){
				endPageNum=totalPageCount;
			}
			request.setAttribute("beginPage", beginPageNum);
			request.setAttribute("endPage", endPageNum);
		}
				
		List<NoticeBoardVO> boardList=dao.searchBoards(type, keyword, firstRow, endRow);
		request.setAttribute("boardList", boardList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("settedPageNum", pageNum);
		request.setAttribute("formType","search");
		request.setAttribute("search_bar",keyword);
		
		String url="/commu_00/commu_01.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
		
	}
	
	private int getTotalPageCount(int totalArticleCount){
		if(totalArticleCount==0){
			return 0;
		}
		int pageCount=totalArticleCount/COUNT_PER_PAGE;
		if(totalArticleCount%COUNT_PER_PAGE>0){
			pageCount++;
		}
		return pageCount;
	}
	

}
