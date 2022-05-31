package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
	
		
		if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("view".equals(actionName)) {
			action = new ViewFormAction();
		} else if("writeform".equals(actionName)) {
			action = new WritetFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else action = new IndexAction();
		
		return action;
	}

}