package com.web.app.service;

import java.util.List;

import com.web.app.entity.Member;

public interface MemberService {
	public void save(Member member);
	public void update(Member member);
	public List<Member> getMembers(Member member, int first, int max);
	public List<Member> getMembers(int first, int max);
	public Member getMembers(Member member);
}
