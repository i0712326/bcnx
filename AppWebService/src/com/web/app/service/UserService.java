package com.web.app.service;

import java.util.List;

import com.web.app.entity.User;

public interface UserService {
	public void save(User user);
	public void update(User user);
	public User getUser(User user);
	public List<User> getUsers(int init, int max);
	public List<User> getUsersLike(User user, int init, int max);
}
