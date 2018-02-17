package com.example.auth.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.auth.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

	public Account findByUsername (@Param("username") String username);
}
