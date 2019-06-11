package com.noticeboard.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

import com.noticeboard.dao.NoticeBoardDAO;
import com.noticeboard.dto.NoticeBoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeBoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		context.getContextPath();
		String path=context.getRealPath("upload");
		int sizeLimit=20*1024*1024;
		String encType="utf-8";
		
		MultipartRequest multi=new MultipartRequest(request,path,sizeLimit,encType,new DefaultFileRenamePolicy());
		String num=multi.getParameter("num");
		String userid=multi.getParameter("userid");
		String pass=multi.getParameter("pass");
		String title=multi.getParameter("title");
		String content=multi.getParameter("content");
		String fileurl=multi.getFilesystemName("fileurl");
		
		NoticeBoardVO vo=new NoticeBoardVO();
		vo.setNum(Integer.parseInt(num));
		vo.setUserid(userid);
		vo.setPass(pass);
		vo.setTitle(title);
		vo.setContent(content);
		if(fileurl!=null){
			vo.setFileurl(fileurl);
		}
		
		
		NoticeBoardDAO dao=NoticeBoardDAO.getInstance();	
		dao.updateBoard(vo);
		
		String url="NoticeBoard.do?command=notice_board_view&num="+num+"&userid="+userid;
		
		response.sendRedirect(url);
	}

}