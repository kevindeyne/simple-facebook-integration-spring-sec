package com.redleafbooks.page.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redleafbooks.core.dto.FacebookDTO;
import com.redleafbooks.task.client.UserTaskService;

@Controller
public class AccountController {

	@Autowired
	private UserTaskService userTaskService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/account/facebook-login", method = RequestMethod.POST)
	public String loginFacebook(@Valid @ModelAttribute FacebookDTO dto, Model model, HttpServletRequest request, HttpServletResponse response) {

		this.userTaskService.loginOrCreateFacebookUser(request, response, dto);

		return "redirect:/";

	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/account/facebook-logout", method = RequestMethod.GET)
	public String logoutFacebook(HttpServletRequest request, Model model) {

		SecurityContextHolder.clearContext();

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/";
	}

}
