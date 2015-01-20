package com.web.app.service;

import java.util.List;

import com.web.app.entity.Role;

public interface RoleService {
	public void save(Role role);
	public void update(Role role);
	public List<Role> getRoles(int init, int max);
	public Role getRoles(Role role);
}
