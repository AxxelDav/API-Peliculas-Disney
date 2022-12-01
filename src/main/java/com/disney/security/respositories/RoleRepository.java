package com.disney.security.respositories;

import com.disney.security.entities.Role;
import com.disney.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Integer> {

    Optional<Role> findByRoleName(RoleList roleName);

}
