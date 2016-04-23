package com.kevindeyne.web.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kevindeyne.web.domain.User;
import com.kevindeyne.web.repository.UserRepository;

@Controller
public class HomeController {

	private UserRepository userRepository;
	private RememberMeServices rememberMeServices;

	@Inject
	public HomeController(UserRepository userRepository, RememberMeServices rememberMeServices) {
		this.userRepository = userRepository;
		this.rememberMeServices = rememberMeServices;
	}

	@RequestMapping(value = { "", "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {

		if (null != principal) {
			User user = this.userRepository.findOne(Long.valueOf(principal.getName()));
			model.addAttribute("user", user);

			//You don't need to call this on every page, just the one you get redirected to should suffice
			//Mostly moved here because of the interceptor layout of a ConnectInterceptor, which does not support HttpServletResponses
			//Move where-ever you want this! (though check for a principal)
			rememberMeServices.loginSuccess(request, response, SecurityContextHolder.getContext().getAuthentication());
		}

		return "home";
	}

	
	@RequestMapping(value = { "/userlogout" }, method = RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model, Principal principal) throws ServletException {
		if (null != principal) {
			SecurityContextHolder.clearContext();

			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			request.logout();
		}

		return "redirect:/";
	}

}
