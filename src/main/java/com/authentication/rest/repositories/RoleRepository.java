package com.authentication.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.data.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
