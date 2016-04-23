package com.kevindeyne.web.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 7501066482020512291L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = USERNAME, length = 80)
	private String username;
	public static final String USERNAME = "username";

	@Column(name = PASSWORD, length = 500)
	private String password;
	public static final String PASSWORD = "password";

	@Column(name = EMAIL, length = 80)
	private String email;
	public static final String EMAIL = "email";


	/** FACEBOOK ELEMENTS **/
	@Column(name = FACEBOOK_ID, length = 500)
	private String facebookId;
	public static final String FACEBOOK_ID = "facebook_id";

	@Column(name = FACEBOOK_IMAGE, length = 500)
	private String facebookImage;
	public static final String FACEBOOK_IMAGE = "facebook_image";

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFacebookImage() {
		return this.facebookImage;
	}

	public void setFacebookImage(String facebookImage) {
		this.facebookImage = facebookImage;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s']", this.id, this.username);
	}
}
