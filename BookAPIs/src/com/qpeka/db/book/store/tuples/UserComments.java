package com.qpeka.db.book.store.tuples;

public class UserComments {
	
	private String comment;
	private UserInfoIdentifier userInfo;
	
	public UserComments()
	{
		
	}
	
	public UserComments(String comment, UserInfoIdentifier userInfo) {
		super();
		this.comment = comment;
		this.userInfo = userInfo;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public UserInfoIdentifier getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoIdentifier userInfo) {
		this.userInfo = userInfo;
	}
	
}
