package com.coderscampus.assignment14.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.coderscampus.assignment14.domain.User;

@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//	public User findByUserId(Long userId);
//				
//	public User findByUsername(String user); 
//	
//}

public class UserRepository {
	private Map<Long, User> users = new HashMap<>();
	
	public void save (User user) {
		users.put(user.getUserId(), user);
	}
	
	public User findById (Long userId) {
		return users.get(userId);
	}
	
	public int size () {
		return users.size();
		
		
	}
	
}