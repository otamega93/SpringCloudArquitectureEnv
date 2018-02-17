package com.example.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Authority implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String authority;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Authority(Long id, String username, String authority, Account account) {
		super();
		this.id = id;
		this.username = username;
		this.authority = authority;
		this.account = account;
	}

	public Authority(Long id) {
		super();
		this.id = id;
	}
	
	public Authority(String username) {
		super();
		this.username = username;
	}

	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Authorities [id=" + id + ", username=" + username + ", authority=" + authority + ", account=" + account
				+ "]";
	}
	
}
