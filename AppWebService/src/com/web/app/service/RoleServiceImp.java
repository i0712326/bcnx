package com.web.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.dao.RoleDao;
import com.web.app.entity.Role;

public class RoleServiceImp implements RoleService {
	private static final Logger logger = Logger.getLogger(RoleServiceImp.class);
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao){
		this.roleDao = roleDao;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(Role role) {
		try {
			roleDao.save(role);
		} catch (SQLException e) {
			logger.debug(role.toString());
			logger.debug("Exception occured while try to save role",e);
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update(Role role) {
		try {
			roleDao.update(role);
		} catch (SQLException e) {
			logger.debug(role.toString());
			logger.debug("Exception occured while try update role", e);
		}
	}
	
	public Role getRoles(Role role) {
		try {
			return roleDao.getRole(role);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to get unique role", e);
			return null;
		}
	}
	public List<Role> getRoles(int init, int max) {
		try {
			return roleDao.getRole(init, max);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to get roles",e);
			return new ArrayList<Role>();
		}
	}

}
