package com.qpeka.db.book.store;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.qpeka.db.book.store.tuples.Publisher;


public class PublisherHandler {
	
	private static PublisherHandler instance = new PublisherHandler();
	private DB db = null;
	private DBCollection authors = null;
	
	private PublisherHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		authors = db.getCollection("publishers");
	}
	
	public static PublisherHandler getInstance()
	{
		return instance;
	}
	
	public void addAuthor(Publisher publisher)
	{
		
	}
	
	public void updatePublisher(Publisher publisher)
	{
		
	}
	
	public Publisher getPublisher(long id)
	{
		return null;
	}
	
	
}
