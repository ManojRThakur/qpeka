package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserRating {
	
	public static final String RATING = "rating";
	public static final String USERINFO = "userInfo";
	
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
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(RATING, rating);
		dbObj.put(USERINFO, userInfo.toDBObject());
		
		return dbObj;
	}
	
	public static UserRating getUserRatingfromDBObject(BasicDBObject obj)
	{
		return new UserRating(obj.getInt(RATING), UserInfoIdentifier.getUserInfoIdentifierfromDBObject((BasicDBObject)obj.get(USERINFO)));
	}
}
