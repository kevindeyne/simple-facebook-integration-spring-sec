package com.kevindeyne.web.task.impl;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.kevindeyne.web.domain.User;
import com.kevindeyne.web.repository.UserRepository;
import com.kevindeyne.web.task.client.UserTaskService;
import com.kevindeyne.web.task.helper.FacebookDTO;
import com.kevindeyne.web.task.helper.SocialUserDetails;

@Service
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionRepository connectionRepository;
			
	@Override
	public void loginOrCreateFacebookUser(FacebookDTO dto) {

		User user = this.userRepository.findByFacebookId(dto.getId());

		if(null == user){
			user = new User();

			String facebookUserId = dto.getId();

			user.setUsername(dto.getDisplayName());
			user.setPassword(UUID.randomUUID().toString());
			user.setEmail(dto.getEmail());

			user.setFacebookId(facebookUserId);
			user.setFacebookImage(dto.getImageURL());

			user = this.userRepository.save(user);
		}

		SocialUserDetails userDetails = new SocialUserDetails(user.getId(), Arrays.asList(new SimpleGrantedAuthority("USER")));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		//not neccesary any more
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);		
		connectionRepository.removeConnection(connection.getKey());		
		
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
