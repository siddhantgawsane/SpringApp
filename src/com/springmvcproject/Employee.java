package com.springmvcproject;

import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Employee {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String blobKeyString;
	private int logins;
	private Date loginDate;
	
/*	public UserDB(String username, String password, String fname, String lname, String eid, String bks)
	{
		this.username = username;
		this.password = password;
		this.firstName = fname;
		this.lastName = lname;
		this.emailId = eid;
		this.logins = 0;
		this.blobKeyString = bks;
	}
*/	public void setFirstName(String fname)
	{
		this.firstName = fname;
	}
	
	public void setLastName(String lname)
	{
		this.lastName = lname;
	}
	
	public void setEmailId(String email)
	{
		this.emailId = email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setBlobKey(String blobkey)
	{
		this.blobKeyString = blobkey;
	}
	
	public void setLoginDate(Date login)
	{
		this.loginDate = login;
	}	

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}

	public void incrementLogins()
	{
		logins = logins + 1;
	}
	
	public String getUserName()
	{
		return this.username;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public String getEmailId()
	{
		return this.emailId;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public Integer getLogins()
	{
		return this.logins;
	}
	
	public String getBlobKeyString()
	{
		return this.blobKeyString;
	}
	
	public Date getLoginDate()
	{
		return this.loginDate;
	}
}
