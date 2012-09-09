package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserComments {
	
	public static final String ID = "_id";
	public static final String COMMENT = "comment";
	public static final String USERINFO = "userInfo";
	
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
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(COMMENT, comment);
		dbObj.put(USERINFO, userInfo.toDBObject());
		
		return dbObj;
	}
	
	public static UserComments getUserCommentfromDBObject(BasicDBObject obj)
	{
		return new UserComments(obj.getString(COMMENT) ,UserInfoIdentifier.getUserInfoIdentifierfromDBObject((BasicDBObject)obj.get(USERINFO)));
	}
	
}
