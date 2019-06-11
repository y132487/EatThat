package com.noticeboard.controller.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Action;

public class NoticeBoardDownloadAction implements Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String fileName=(String)session.getAttribute("fileName");
		String fileFolder="upload";
		String serverFolder=context.getRealPath(fileFolder);
		String filePath=serverFolder+"//"+fileName;

		
		try{
			//File file=new File(filePath);
			byte b[]=new byte[20971520];
			
			 response.reset();
			 response.setContentType("application/octet-stream");
			   
			 String Encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
			 
			 response.setHeader("Content-Disposition", "attachment; filename=\""+Encoding+"\"");
			FileInputStream in = new FileInputStream(filePath);
			ServletOutputStream out2 = response.getOutputStream();
			
			int numRead;
			while((numRead = in.read(b, 0, b.length)) != -1){
			    out2.write(b, 0, numRead);
			}
			
			
			out2.flush();
			out2.close();
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		

}
