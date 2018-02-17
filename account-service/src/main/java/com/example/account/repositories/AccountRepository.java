package com.example.account.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.account.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

	@PreAuthorize("hasAuthority('FOO_READ_USER')")
	//@PreAuthorize("#oauth2.hasScope('DOO')")
	public Account findByUsername (@Param(value = "username") String username);
	
	//@PreAuthorize("#oauth2.clientHasRole('FOO_READ')")
	@PreAuthorize("#oauth2.hasScope('FOO')")
	public Account findByName (@Param(value = "name") String name);
	
}