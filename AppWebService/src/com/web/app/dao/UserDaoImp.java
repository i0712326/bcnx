package com.web.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.entity.User;

public class UserDaoImp implements UserDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(User user) throws SQLException {
		hibernateTemplate.save(user);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update(User user) throws SQLException {
		hibernateTemplate.update(user);
	}
	public User getUser(User user) throws SQLException{
		return hibernateTemplate.execute(new GetUserCallback(user));
	}
	private class GetUserCallback implements HibernateCallback<User>{
		private User user;
		public GetUserCallback(User user){
			this.user = user;
		}
		public User doInHibernate(Session session) throws HibernateException {
			String hql = "from User u where u.userId = :userId";
			Query query = session.createQuery(hql);
			query.setString("userId", user.getUserId());
			return (User)query.uniqueResult();
		}	
	}
	public boolean checkUser(User user) throws SQLException {
		return hibernateTemplate.execute(new CheckUserCallback(user));
	}
	private class CheckUserCallback implements HibernateCallback<Boolean>{
		private User user;
		public CheckUserCallback(User user){
			this.user = user;
		}
		public Boolean doInHibernate(Session session) throws HibernateException {
			return session.doReturningWork(new ReturningWork<Boolean>(){
				public Boolean execute(Connection conn) throws SQLException {
					String sql = "{? = call checkUser(?, ?)}";
					CallableStatement stat = conn.prepareCall(sql);
					stat.setString(2, user.getUserId());
					stat.setString(3, user.getPasswd());
					ResultSet rs =stat.executeQuery();
					int ret = 0;
					while(rs.next()){
						ret = rs.getInt(1);
					}
					return new Boolean(ret>0?true:false);
				}
			});
		}
	}
	public List<User> getUsers(int init, int max) throws SQLException {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		return toList(hibernateTemplate.findByCriteria(criteria, init, max));
	}
	public List<User> getUsersLike(User user, int first, int max)
			throws SQLException {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.like("userId",
				"%"+user.getUserId() + "%"));
		return toList(hibernateTemplate.findByCriteria(criteria, first, max));
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateUser(User user) throws SQLException{
		return hibernateTemplate.execute(new UserUpdate(user));
	}
	private class UserUpdate implements HibernateCallback<String>{
		private User user;
		public UserUpdate(User user) {
			super();
			this.user = user;
		}
		public String doInHibernate(Session session) throws HibernateException {
			String hql = "update User u set u.name = :name, u.email = :mail, u.tel = :tel, u.status = :stat where u.userId = :id";
			Query query = session.createQuery(hql);
			query.setString("name", user.getName());
			query.setString("email", user.getEmail());
			query.setString("tel", user.getTel());
			query.setString("stat", user.getStatus());
			query.setString("id", user.getUserId());
			query.executeUpdate();
			return null;
		}
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updatePassword(User usr, String nPass,String cPass) throws SQLException{
		return hibernateTemplate.execute(new UpdatePasswd(usr,nPass,cPass));
	}
	private class UpdatePasswd implements HibernateCallback<String>{
		private User usr;
		private String nPass;
		private String cPass;
		public UpdatePasswd(User usr, String nPass, String cPass) {
			super();
			this.usr = usr;
			this.nPass = nPass;
			this.cPass = cPass;
		}
		public String doInHibernate(Session session) throws HibernateException {
			return session.doReturningWork(new ReturningWork<String>(){
				public String execute(Connection conn) throws SQLException {
					String sql = "{? = call UPDATEPASSWD(?, ?, ?, ?)}";
					CallableStatement call = conn.prepareCall(sql);
					call.setString(2, usr.getUserId());
					call.setString(3, usr.getPasswd());
					call.setString(4, nPass);
					call.setString(5, cPass);
					ResultSet rs = call.executeQuery();
					String ret = null;
					while(rs.next()){
						ret = rs.getString(1);
					}
					return ret;
				}
				
			});
		}
	}
	private List<User> toList(final List<?> beans){
		if(beans==null)
			return null;
		if(beans.isEmpty())
			return null;
		int size = beans.size();
		User[] list = new User[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
}
