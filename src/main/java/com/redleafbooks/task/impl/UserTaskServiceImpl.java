package com.redleafbooks.task.impl;

import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

import com.redleafbooks.core.domain.User;
import com.redleafbooks.core.dto.FacebookDTO;
import com.redleafbooks.core.repository.UserRepository;
import com.redleafbooks.task.client.UserTaskService;
import com.redleafbooks.task.helper.SocialUserDetails;

@Service
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RememberMeServices rememberMeServices;

	@Override
	public void loginOrCreateFacebookUser(HttpServletRequest request, HttpServletResponse response, FacebookDTO dto) {
		User user = this.userRepository.findByFacebookId(dto.getId());

		if(null == user){
			user = new User();

			String facebookUserId = dto.getId();

			user.setUsername(dto.getName());
			user.setPassword(UUID.randomUUID().toString());
			user.setEmail(dto.getEmail());

			user.setFacebookId(facebookUserId);
			user.setFacebookImage(FacebookDTO.GRAPH_PIC_URL.replace(FacebookDTO.GRAPH_PIC_URL_REPLACE, facebookUserId));

			user = this.userRepository.save(user);
		}

		SocialUserDetails userDetails = new SocialUserDetails(user.getId(), Arrays.asList(new SimpleGrantedAuthority("USER")));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		this.rememberMeServices.loginSuccess(request, response, authentication);
	}

}
