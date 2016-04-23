package com.kevindeyne.web.task.client;

import com.kevindeyne.web.task.helper.FacebookDTO;


public interface UserTaskService {

	void loginOrCreateFacebookUser(FacebookDTO dto);
	
}
