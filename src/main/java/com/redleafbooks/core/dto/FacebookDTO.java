package com.redleafbooks.core.dto;

import java.io.Serializable;

public class FacebookDTO implements Serializable {

	private static final long serialVersionUID = -6214606890253226746L;

	public static final String GRAPH_PIC_URL = "//graph.facebook.com/_PIC_URL_/picture?height=285&width=285";
	public static final String GRAPH_PIC_URL_REPLACE = "_PIC_URL_";

	private String accessToken;
	private Integer expiresIn;
	private String signedRequest;
	private String connected;

	private String email;
	private String firstName;
	private String gender;
	private String id;
	private String lastName;
	private String name;
	private Integer timezone;

	private Boolean verified;

	public FacebookDTO() {
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getSignedRequest() {
		return this.signedRequest;
	}

	public void setSignedRequest(String signedRequest) {
		this.signedRequest = signedRequest;
	}

	public String getConnected() {
		return this.connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimezone() {
		return this.timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public Boolean getVerified() {
		return this.verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}