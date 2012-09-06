package com.qpeka.db.book.store.tuples;

public class UserInfoIdentifier 
{
	private long _id;
	private String userName;
	private Name name;
	
	public UserInfoIdentifier()
	{
		super();
	}
	
	public UserInfoIdentifier(long _id, String userName, Name name) {
		super();
		this._id = _id;
		this.userName = userName;
		this.name = name;
	}
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	
}
