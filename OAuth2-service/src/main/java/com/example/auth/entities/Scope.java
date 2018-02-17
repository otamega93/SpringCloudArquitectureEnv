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
public class Scope implements Serializable {
	
	private static final long serialVersionUID = 7193203925684367689L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Scope(Long id, String name, Client client) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
	}

	public Scope(Long id) {
		super();
		this.id = id;
	}

	public Scope() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Scope [id=" + id + ", name=" + name + ", client=" + client + "]";
	}

}
