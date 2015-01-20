package com.web.app.passwd.builder;

public interface PasswdBuilder {
	public int LOWERCASE = 2;
	public int UPPERCASE = 3;
	public int SPECAIL	 = 2;
	public int DIGIT	 = 2;
	public String build();
	public String buildPasswd();
}
