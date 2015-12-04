package com.redleafbooks.page.home;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redleafbooks.core.domain.User;
import com.redleafbooks.core.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value={"", "/", "/home"})
	public String home(Model model, Principal principal) {

		if(null != principal){
			User user = this.userRepository.findOne(Long.valueOf(principal.getName()));
			model.addAttribute("user", user);
		}

		return "home/index";
	}

}
