package com.coderscampus.assignment14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public User createUser(String username, Channel channel) {
		User newUser = new User();
		newUser.getChannel().add(channel);
		newUser.setUsername(username);
		return userRepo.save(newUser);
	}

	public User findByUserId(Long userId) {
		return userRepo.findByUserId(userId);
	}
}
