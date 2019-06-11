package com.member.controller;

import util.*;
import com.member.controller.action.DeleteAction;
import com.member.controller.action.EditFormAction;
import com.member.controller.action.IdCheckAction;
import com.member.controller.action.JoinAction;
import com.member.controller.action.JoinFormAction;
import com.member.controller.action.LoginAction;
import com.member.controller.action.LoginFormAction;
import com.member.controller.action.LogoutAction;
import com.member.controller.action.UpdateAction;

public class ActionFactory {
	private static ActionFactory instance=new ActionFactory();
	
	public static ActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){
		Action action=null;
		System.out.println("ActionFactory : "+command);
		
		if(command.equals("login_form")){
			action=new LoginFormAction();
		}else if(command.equals("login_action")){
			action=new LoginAction();
		}else if(command.equals("join_form")){
			action=new JoinFormAction();
		}else if(command.equals("join_action")){
			action=new JoinAction();
		}else if(command.equals("idcheck")){
			action=new IdCheckAction();
		}else if(command.equals("logout_action")){
			action=new LogoutAction();
		}else if(command.equals("edit_form")){
			action=new EditFormAction();
		}else if(command.equals("update_action")){
			action=new UpdateAction();
		}else if(command.equals("delete_action")){
			action=new DeleteAction();
		}
		return action;
	}
}
