package com.osdb.octipod.repo;

import com.osdb.octipod.model.SystemUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<SystemUser, UUID> {
	Optional<SystemUser> findByEmail(String email);
}