package com.authentication.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.data.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
