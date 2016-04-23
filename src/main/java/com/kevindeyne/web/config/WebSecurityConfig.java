package com.kevindeyne.web.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.kevindeyne.web.interceptor.CustomConnectInterceptor;
import com.kevindeyne.web.task.client.UserTaskService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;
	
	@Inject
	private UserTaskService userTaskService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest()
			.permitAll()
			.and()
			.rememberMe()
			.rememberMeServices(rememberMeServices());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication();
	}

	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userDetailsService());
		rememberMeServices.setCookieName(environment.getProperty("local.cookieName"));
		rememberMeServices.setParameter("rememberMe");
		return rememberMeServices;
	}

	@Bean
	public ConnectController connectController() {
		ConnectController controller = new ConnectController(connectionFactoryLocator(), connectionRepository());
		controller.addInterceptor(new CustomConnectInterceptor(userTaskService));
		return controller;
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(
				environment.getProperty("facebook.clientId"),
				environment.getProperty("facebook.clientSecret")));
		return registry;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), textEncryptor());
	}

	@Bean
	public TextEncryptor textEncryptor() {
		String localEncryptionPassword = environment.getProperty("local.encryption.password");
		String localEncryptionSalt = environment.getProperty("local.encryption.hexSalt");
		return Encryptors.delux(localEncryptionPassword, localEncryptionSalt);
	}
}
