package com.web.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.dao.UserDao;
import com.web.app.entity.User;

public class UserServiceImp implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImp.class);
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	public void save(User user){
		try{
			userDao.save(user);
		}
		catch(SQLException e){
			logger.debug("SQL Exception occure once save user service execute", e);
		}
	}
	
	public void update(User user) {
		try{
			userDao.update(user);
		}
		catch(SQLException e){
			logger.debug("SQL Exception occured once update user service execute",e);
		}
	}

	public User getUser(User user){
		try {
			return userDao.getUser(user);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to fetch user",e);
			return new User();
		}
	}
	public List<User> getUsers(int init, int max) {
		try {
			return userDao.getUsers(init, max);
		} catch (SQLException e) {
			logger.debug("SQL Exception occured once get users service execute", e);
			return new ArrayList<User>();
		}
	}
	
	public List<User> getUsersLike(User user, int init, int max) {
		try {
			return userDao.getUsersLike(user, init, max);
		} catch (SQLException e) {
			logger.debug("SQL Exception occured once get users service execute", e);
			return new ArrayList<User>();
		}
	}
}
