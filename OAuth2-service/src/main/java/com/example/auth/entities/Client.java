package com.example.auth.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = -5853805749682736079L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String clientId;
	
	private String clientSecret;
	
	private String resourceId;
	
	private String authorizedGrantTypes;
	
	private String scopes;
	
	private String authorities;
	
	private Integer accessTokenValiditySeconds;
	
	private Integer refreshTokenValiditySeconds;
	
	private Boolean isAutoApprove;
	
	private String redirectUri;


	public Client(String clientId, String clientSecret, String resourceId, String authorizedGrantTypes,
			String scopes, String authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,
			Boolean isAutoApprove, String redirectUri) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.resourceId = resourceId;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.scopes = scopes;
		this.authorities = authorities;
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
		this.isAutoApprove = isAutoApprove;
		this.redirectUri = redirectUri;
	}

	public Client() {
		super();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Boolean getIsAutoApprove() {
		return isAutoApprove;
	}

	public void setIsAutoApprove(Boolean isAutoApprove) {
		this.isAutoApprove = isAutoApprove;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientId=" + clientId + ", clientSecret=" + clientSecret + ", resourceId="
				+ resourceId + ", authorizedGrantTypes=" + authorizedGrantTypes + ", scopes=" + scopes
				+ ", authorities=" + authorities + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
				+ ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds + ", isAutoApprove=" + isAutoApprove
				+ ", redirectUri=" + redirectUri + "]";
	}

}
