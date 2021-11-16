package com.osdb.octipod.service;

import com.osdb.octipod.model.SystemUser;
import com.osdb.octipod.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class UserService //implements MyService<SystemUser>
{

	@Autowired
	private UserRepository userRepository;

	public Optional<SystemUser> show(UUID id) {
		return userRepository.findById(id);
	}


	public Optional<SystemUser> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


//	public SystemUser save(SystemUser user) {
//		return userRepository.save(user);
//	}
//
//	public void delete(UUID id) {
//		userRepository.deleteById(id);
//	}

}


