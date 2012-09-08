package com.qpeka.db.book.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
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
		authors.insert(author.toDBObject(), WriteConcern.SAFE);
	}
	
	public void updateBook(Author author)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, author.get_id());
		
		authors.update(q, new BasicDBObject("$set" , author.toDBObject()), true, false, WriteConcern.SAFE);
	}
	
	public Author getAuthor(long id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, id);
		
		
		DBCursor cursor = authors.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Author author = Author.getAuthorfromDBObject(dObj);
                
                return author;
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
