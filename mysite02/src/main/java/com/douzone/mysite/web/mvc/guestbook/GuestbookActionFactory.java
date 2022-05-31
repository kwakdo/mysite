package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		System.out.println(actionName);

		if ("delete".equals(actionName)) {
			action = new DeleteAction();

		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();

		} else if ("insert".equals(actionName)) {
			action = new InsertAction();
			
		} else if ("index".equals(actionName)) {
			action = new IndexAction();

		} else {
			action = new DefaultAction();
		}

		return action;
	}
}