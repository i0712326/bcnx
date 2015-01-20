package com.web.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.web.app.entity.Role;

public interface RoleDao {
	public void save(Role role) throws SQLException;
	public List<Role> getRole(int init,int max) throws SQLException;
	public void update(Role role) throws SQLException;
	public Role getRole(Role role) throws SQLException;
}
