package com.qpeka.db.book.store;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.qpeka.db.book.store.tuples.Book;


public class BookHandler {
	
	private static BookHandler instance = new BookHandler();
	private DB db = null;
	private DBCollection books = null;
	
	private BookHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		books = db.getCollection("books");
	}
	
	public static BookHandler getInstance()
	{
		return instance;
	}
	
	public void addBook(Book book)
	{
		
	}
	
	public void updateBook(Book book)
	{
		
	}
	
	public Book getBook(long id)
	{
		return null;
	}
	
	
}
