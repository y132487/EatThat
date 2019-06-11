package com.noticeboard.controller;
import util.Action;

import com.noticeboard.controller.action.NoticeBoardDeleteAction;
import com.noticeboard.controller.action.NoticeBoardDownloadAction;
import com.noticeboard.controller.action.NoticeBoardListAction;
import com.noticeboard.controller.action.NoticeBoardSearchAction;
import com.noticeboard.controller.action.NoticeBoardUpdateAction;
import com.noticeboard.controller.action.NoticeBoardUpdateFormAction;
import com.noticeboard.controller.action.NoticeBoardViewAction;
import com.noticeboard.controller.action.NoticeBoardWriteAction;
import com.noticeboard.controller.action.NoticeBoardWriteFormAction;


public class ActionFactory {
	
	private static ActionFactory instance=new ActionFactory();
	
	public static ActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){
		Action action=null;
		System.out.println("ActionFactory : "+command);
		
		if(command.equals("notice_board_list")){
			action=new NoticeBoardListAction();
		}else if(command.equals("notice_board_write_form")){
			action=new NoticeBoardWriteFormAction();
		}else if(command.equals("notice_board_write")){
			action=new NoticeBoardWriteAction();
		}else if(command.equals("notice_board_view")){
			action=new NoticeBoardViewAction();
		}else if(command.equals("notice_board_update_form")){
			action=new NoticeBoardUpdateFormAction();
		}else if(command.equals("notice_board_update")){
			action=new NoticeBoardUpdateAction();
		}else if(command.equals("notice_board_delete")){
			action=new NoticeBoardDeleteAction();
		}else if(command.equals("notice_board_search")){
			action=new NoticeBoardSearchAction();
		}else if(command.equals("notice_board_download")){
			action=new NoticeBoardDownloadAction();
		}
		return action;
	}
}
