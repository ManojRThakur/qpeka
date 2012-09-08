package com.qpeka.db.book.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
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
		books.insert(book.toDBObject(), WriteConcern.SAFE);
	}
	
	public void updateBook(Book book)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, book.get_id());
		
		books.update(q, new BasicDBObject("$set" , book.toDBObject()), true, false, WriteConcern.SAFE);
	}
	
	public Book getBook(long id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, id);
		
		
		DBCursor cursor = books.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                return book;
            }
            else
            	return null;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	
	
}
