package com.web.app.access.point;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.web.app.context.ServiceApplicationContext;
import com.web.app.entity.Member;
import com.web.app.entity.Role;
import com.web.app.entity.User;
import com.web.app.passwd.builder.PasswdBuilder;
import com.web.app.service.MemberService;
import com.web.app.service.RoleService;
import com.web.app.service.UserService;
@Path("/user")
public class UserAccessPoint { 
	@POST
	@Path("/save")
	public Response save(@FormParam("USRID") String id, @FormParam("NAME") String name,
			@FormParam("TEL") String tel, @FormParam("EMAIL") String email, 
			@FormParam("ROLENAME")String roleName, @FormParam("MEMID")String memId) {
		UserService userService = (UserService) ServiceApplicationContext
				.getBean("userService");
		MemberService memberService = (MemberService) ServiceApplicationContext.getBean("memberService");
		RoleService roleService = (RoleService) ServiceApplicationContext.getBean("roleService");
		PasswdBuilder passwdBuilder = (PasswdBuilder) ServiceApplicationContext.getBean("passwdBuilder");
		User user = new User();
		user.setUserId(id);
		user.setName(name);
		user.setEmail(email);
		user.setTel(tel);
		user.setPasswd(passwdBuilder.buildPasswd());
		Member member = new Member();
		member.setMemId(memId);
		member = memberService.getMembers(member);
		Role role = new Role();
		role.setName(roleName);
		role = roleService.getRoles(role);
		userService.save(user);
		return Response.status(200).entity("0000").build();
	}
}
