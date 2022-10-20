package com.coderscampus.assignment14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.assignment14.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserId(Long userId);

	public User findByUsername(String user);

}
