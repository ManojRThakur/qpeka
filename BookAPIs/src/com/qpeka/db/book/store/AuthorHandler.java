package com.qpeka.db.book.store;

import java.util.Date;

import org.bson.types.ObjectId;
import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.Constants.GENDER;


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
	
	public String addAuthor(Author author)
	{
		BasicDBObject dObj = (BasicDBObject)author.toDBObject(true);
		WriteResult result = authors.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updateBook(Author author)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, new ObjectId(author.get_id()));
		
		authors.update(q, new BasicDBObject("$set" , author.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public Author getAuthor(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, new ObjectId(id));
		
		
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
	
	public static void main(String[] args) {
		
		Author a = new Author();
		a.setName(new Name("manoj", "ramesh", "thakur"));
		a.setDob(new Date());
		a.setGender(GENDER.MALE);
		a.setInfoLink("http://google.com");
		a.setImageFile("/tmp/img.jpg");
		JSONArray j = new JSONArray();
		j.put(CATEGORY.FICTION);
		j.put(CATEGORY.SUSPENSE);
		a.setGenre(j);
		a.setShortBio("ehgwkgdedhgwljkdghe");
		
		AuthorHandler.getInstance().getAuthor("504b8430e0c11d217f13a39a");
		
		
	}
	
}
