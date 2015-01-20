package com.web.app.access.point;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.web.app.entity.Member;

@Path("/member")
public class MemberAccessPoint {
	@POST
	@Path("/save")
	public Response save(@FormParam("IIN") String iin,
			@FormParam("MEMID") String memId, @FormParam("ENTRY") String entry,
			@FormParam("ADDRESS") String address, @FormParam("TEL") String tel,
			@FormParam("FAX") String fax) {
		Member member = new Member();
		member.setIin(iin);
		member.setMemId(memId);
		
		return null;
	}
}
