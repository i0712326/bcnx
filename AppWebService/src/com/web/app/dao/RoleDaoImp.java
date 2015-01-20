package com.web.app.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.entity.Role;

public class RoleDaoImp implements RoleDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional
	public void save(Role role) throws SQLException {
		hibernateTemplate.save(role);
	}
	@Transactional
	public void update(Role role) throws SQLException {
		hibernateTemplate.update(role);
	}
	public Role getRole(Role role) throws SQLException {
		return hibernateTemplate.execute(new GetRoleUnique(role));
	}
	private class GetRoleUnique implements HibernateCallback<Role>{
		private Role role;
		public GetRoleUnique(Role role){
			this.role = role;
		}
		public Role doInHibernate(Session session) throws HibernateException {
			String hql = "from Role r where r.roleId = :roleId";
			Query query = session.createQuery(hql);
			query.setString("roleId", role.getName());
			return (Role) query.uniqueResult();
		}
	}
	public List<Role> getRole(int init, int max) throws SQLException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		return toList(hibernateTemplate.findByCriteria(criteria, init, max));
	}
	private List<Role> toList(List<?> beans){
		if(beans == null)
			return null;
		if(beans.isEmpty())
			return null;
		int size = beans.size();
		Role[] list = new Role[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
	
}
