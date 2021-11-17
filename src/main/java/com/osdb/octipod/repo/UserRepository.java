package com.osdb.octipod.repo;

import com.osdb.octipod.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<SystemUser, UUID> {
	Optional<SystemUser> findByEmail(String email);
}