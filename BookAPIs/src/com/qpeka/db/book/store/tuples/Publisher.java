package com.qpeka.db.book.store.tuples;

public class Publisher {

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
	
	
}
