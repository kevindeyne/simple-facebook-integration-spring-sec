package com.redleafbooks.task.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redleafbooks.core.dto.FacebookDTO;


public interface UserTaskService {

	void loginOrCreateFacebookUser(HttpServletRequest request, HttpServletResponse response, FacebookDTO dto);

}
