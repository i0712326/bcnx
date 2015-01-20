package com.web.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.web.app.entity.User;

public interface UserDao {
	public void save(User user) throws SQLException;
	public boolean checkUser(User user) throws SQLException;
	public void update(User user) throws SQLException;
	public String updateUser(User usr) throws SQLException;
	public User getUser(User user) throws SQLException;
	public List<User> getUsers(int init, int max) throws SQLException;
	public List<User> getUsersLike(User user, int first, int max) throws SQLException;
	public String updatePassword(User usr, String nPass, String cPass) throws SQLException;
}
