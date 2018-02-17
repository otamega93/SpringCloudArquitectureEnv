package com.example.auth.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.auth.entities.Client;

@RepositoryRestController
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

	public Client findByClientId (@Param(value = "clientId") String clientId);
	
}
