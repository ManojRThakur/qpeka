package com.qpeka.db.book.store.tuples;

public class UserRating {
	
	private int rating; //out of ten
	private UserInfoIdentifier userInfo;
	
	public UserRating()
	{
		
	}
	
	public UserRating(int rating, UserInfoIdentifier userInfo) {
		super();
		this.rating = rating;
		this.userInfo = userInfo;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public UserInfoIdentifier getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoIdentifier userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
