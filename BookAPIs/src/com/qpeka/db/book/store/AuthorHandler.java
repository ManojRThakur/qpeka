package com.qpeka.db.book.store;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.qpeka.db.book.store.tuples.Author;


public class AuthorHandler {
	
	private static AuthorHandler instance = new AuthorHandler();
	private DB db = null;
	private DBCollection authors = null;
	
	private AuthorHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		authors = db.getCollection("authors");
	}
	
	public static AuthorHandler getInstance()
	{
		return instance;
	}
	
	public void addAuthor(Author author)
	{
		
	}
	
	public void updateBook(Author author)
	{
		
	}
	
	public Author getAuthor(long id)
	{
		return null;
	}
	
	
}
