package com.example.auth.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.auth.entities.Authority;

@RepositoryRestResource
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long>{

}
