package com.web.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.dao.MemberDao;
import com.web.app.entity.Member;

public class MemberServiceImp implements MemberService {
	private static final Logger logger = Logger.getLogger(MemberServiceImp.class);
	private MemberDao memberDao;
	public void setMemberDao(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	public void save(Member member) {
		try {
			memberDao.save(member);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to save member",e);
		}
	}
	public void update(Member member){
		try {
			memberDao.update(member);
		} catch (SQLException e) {
			logger.debug(member.toString());
			logger.debug("Exception occured while try to update member",e);
		}
	}
	
	public List<Member> getMembers(Member member, int first, int max) {
		try {
			return memberDao.getMembers(member, first, max);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to get member list", e);
			return new ArrayList<Member>();
		}
	}

	public List<Member> getMembers(int first, int max) {
		try {
			return memberDao.getMembers(first, max);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to get member list",e);
			return new ArrayList<Member>();
		}
	}
	public Member getMembers(Member member) {
		try {
			return memberDao.getMember(member);
		} catch (SQLException e) {
			logger.debug("Exception occured while try to exception get member data",e);
			return null;
		}
	}

}
