package com.qpeka.db.book.store;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.qpeka.db.book.store.tuples.User;

public class UserHandler {

	private static UserHandler instance = new UserHandler();
	private DB db = null;
	private DBCollection users = null;
	
	private UserHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		users = db.getCollection("users");
	}
	
	public static UserHandler getInstance()
	{
		return instance;
	}
	
	public String addUser(User user)
	{
		return null;
	}
	
	public void updateUser(User user)
	{
	
	}
	
	public User getUser(String userId)
	{
		return null;
	}
	
	
}
