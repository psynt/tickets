package com.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.User;
import com.entity.UserType;

import lombok.val;

@Repository
public class Users {

	private Map<String, User> users = new HashMap<>();
	
	public String create(String username, String password, UserType type) {
		val t = new User(username, password, type);
		users.put(t.username(), t);
		return t.username();
	}
	
	public User get(String name) {
		return users.get(name);
	}
	
	public boolean validatePassword(User user, String pass) {
		return user.password().equals(pass);
	}
	
	{
		create("John", "123", UserType.ADMIN);
		create("Mary", "123", UserType.STANDARD);
	}
}
