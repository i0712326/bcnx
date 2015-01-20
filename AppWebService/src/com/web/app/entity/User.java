package com.web.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="USRDATA")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name="USRID",unique=true, nullable=false,length=6)
	private String userId;
	@Column(name="PASSWD",nullable=false)
	private String passwd;
	@Column(name="NAME")
	private String name;
	@Column(name="EMAIL")
	private String email;
	@Column(name="TEL")
	private String tel;
	@Column(name="STATUS")
	private String status = "A";
	@Column(name="STATE")
	private String state = "0";
	@Column(name="COUNT")
	private int count = 0;
	@ManyToOne
	@JoinColumn(name="ROLEDATA_ID")
	private Role role;
	@ManyToOne
	@JoinColumn(name="MEMDATA_ID")
	private Member member;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", passwd=" + passwd
				+ ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", status=" + status + ", state=" + state + ", count="
				+ count + ", role=" + role + ", member=" + member + "]";
	}
}
