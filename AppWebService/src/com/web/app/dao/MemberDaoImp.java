package com.web.app.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.entity.Member;

public class MemberDaoImp implements MemberDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional
	public void save(Member member) throws SQLException {
		hibernateTemplate.save(member);
	}
	@Transactional
	public void update(Member member) throws SQLException{
		hibernateTemplate.execute(new UpdateMember(member));
	}
	private class UpdateMember implements HibernateCallback<Void>{
		private Member member;
		public UpdateMember(Member member){
			this.member = member;
		}
		public Void doInHibernate(Session session) throws HibernateException {
			String hql = "update Member m set m.name = :name, m.memId = :id, m.tel = :tel, m.fax = :fax, m.address = :address where m.iin = :iin";
			Query query = session.createQuery(hql);
			query.setString("name", member.getName());
			query.setString("id", member.getMemId());
			query.setString("tel", member.getTel());
			query.setString("fax", member.getFax());
			query.setString("address", member.getAddress());
			query.setString("iin", member.getIin());
			query.executeUpdate();
			return null;
		}
		
	}
	public List<Member> getMembers(int first, int max) throws SQLException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
		return toList(hibernateTemplate.findByCriteria(criteria, first, max));
	}
	public List<Member> getMembers(Member member, int first, int max) throws SQLException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
		criteria.add(Restrictions.like("memId", member.getMemId()));
		return toList(hibernateTemplate.findByCriteria(criteria, first, max));
	}
	public Member getMember(Member member) throws SQLException {
		return hibernateTemplate.execute(new GetUniqueMember(member));
	}
	private class GetUniqueMember implements HibernateCallback<Member>{
		private Member member;
		public GetUniqueMember(Member member){
			this.member = member;
		}
		public Member doInHibernate(Session session) throws HibernateException {
			String hql = "from Member m where m.memId = :membId";
			Query query = session.createQuery(hql);
			query.setString("memId", member.getMemId());
			return (Member) query.uniqueResult();
		}
		
	}
	private List<Member> toList(final List<?> beans){
		if(beans==null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		Member[] list = new Member[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
}
