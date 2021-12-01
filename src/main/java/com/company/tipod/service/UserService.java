package com.company.tipod.service;

import com.company.tipod.model.SystemUser;
import com.company.tipod.repo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService
{
	final UserRepository userRepository;


	public Optional<SystemUser> findById(UUID id) {
		return userRepository.findById(id);
	}


	public Optional<SystemUser> findByEmail(String email) {
		Optional<SystemUser> op = userRepository.findByEmail(email);
		return op;
	}


	public Page<SystemUser> getPage(Pageable page) {
		return userRepository.findAll(page);
	}


	public SystemUser save(SystemUser user) {
		return userRepository.save(user);
	}


	public void delete(UUID id) {
		userRepository.deleteById(id);
	}

}


