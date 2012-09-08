package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Publisher {

	public static final String NAME = "name";
	public static final String ID = "_id";
	
	private String name;
	private long _id;
	
	public Publisher()
	{
		
	}
	
	public Publisher(String name, long _id) {
		super();
		this.name = name;
		this._id = _id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(NAME, name);
		dbObj.put(ID, _id);
		
		return dbObj;
	}
	
	public static Publisher getPublisherfromDBObject(BasicDBObject obj)
	{
		return new Publisher(obj.getString(NAME), obj.getLong(ID));
	}
}
