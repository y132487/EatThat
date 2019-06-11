package com.noticeboard.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.noticeboard.dao.NoticeBoardDAO;
import com.noticeboard.dto.NoticeBoardVO;

public class NoticeBoardListAction implements Action{

	private static final int COUNT_PER_PAGE=10;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,ServletContext context)
			throws ServletException, IOException {
		
		String pageNumParam=(String) request.getAttribute("pageNum");
		System.out.println(pageNumParam);
		int pageNum=1;
		if(pageNumParam!=null&&pageNumParam.length()>0){
			pageNum=Integer.parseInt(pageNumParam);
		}
		
		NoticeBoardDAO dao=NoticeBoardDAO.getInstance();
		
		int totalArticleCount=dao.getAllCount();
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
				
		List<NoticeBoardVO> boardList=dao.selectAllBoards(firstRow,endRow);
		request.setAttribute("boardList", boardList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("settedPageNum", pageNum);
		request.setAttribute("formType","list");
		
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
