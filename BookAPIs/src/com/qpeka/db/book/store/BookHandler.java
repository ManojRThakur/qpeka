package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.Publisher;
import com.qpeka.db.book.store.tuples.UserComments;
import com.qpeka.db.book.store.tuples.UserInfoIdentifier;
import com.qpeka.db.book.store.tuples.UserRating;

/*
 * 1) Primary key testing
 * 2) Index testing 
 * 3) Author testing
 * 4) Testing dependencies
 *
 */

public class BookHandler {
	
	private static BookHandler instance = new BookHandler();
	private DB db = null;
	private DBCollection books = null;
	
	private BookHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		books = db.getCollection("books");
		
		books.createIndex(new BasicDBObject(Book.CATEGORY, 1));
		books.createIndex(new BasicDBObject(Book.AUTHORID, 1));
		books.createIndex(new BasicDBObject(Book.TITLE, 1));
	}
	
	public static BookHandler getInstance()
	{
		return instance;
	}
	
	public String addBook(Book book)
	{
		BasicDBObject dObj = (BasicDBObject)book.toDBObject(true);
		WriteResult result = books.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updateBook(Book book)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, new ObjectId(book.get_id()));
		
		books.update(q, new BasicDBObject("$set" , book.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public void addBookComment(String id, UserComments comment)
	{
		BasicDBObject bdObj = (BasicDBObject)comment.toDBObject();
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		books.update(q, new BasicDBObject("$push", new BasicDBObject("comments", bdObj) ), true, false, WriteConcern.SAFE);
	}
	
	public void addBookRating(String id, UserRating rating)
	{
		BasicDBObject bdObj = (BasicDBObject)rating.toDBObject();
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		if(!doesUserRatingForBookExist(id,rating.getUserInfo().get_id()))
			books.update(q, new BasicDBObject("$push", new BasicDBObject("ratings", bdObj) ), true, false, WriteConcern.SAFE);
		else
		{
			q.put("ratings.userInfo._id", rating.getUserInfo().get_id());
			books.update(q, new BasicDBObject( "$set" , new BasicDBObject("ratings.$.rating", rating.getRating())), true, false, WriteConcern.SAFE);
		}
	}
	
	private boolean doesUserRatingForBookExist(String id , long userId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		q.put("ratings.userInfo._id", userId);
		
		DBCursor cursor = books.find(q);
		
		try 
        {
            if(cursor.hasNext()) 
            {
            	BasicDBObject dObj = (BasicDBObject)cursor.next();
            	return true;
            }
            else
            	return false;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return false;
		}
        finally {
            cursor.close();
        }
	}
	
	public Book getBook(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, new ObjectId(id));
		
		
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
	
	public List<Book> getBooksByCategory(CATEGORY category)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.CATEGORY, category.toString());
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByAuthorId(String authorId)
	{
		List<Book> listToReturn = new ArrayList<Book>();
	
		BasicDBObject q = new BasicDBObject();
		q.put(Book.AUTHORID, new ObjectId(authorId));
		
		DBCursor cursor = books.find(q);
		
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getAllBooks()
	{
		List<Book> listToReturn = new ArrayList<Book>();
		DBCursor cursor = books.find();
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
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
		Book book = new Book();
		book.set_id("");
		book.setTitle("Harry Potter");
		book.setAuthorId("504b7a67e0c1b0da3bcafc16");
		book.setEdition(1);
		book.setCoverPageFile("/tmp/img.jpg");
		book.setNumPages(200);
		book.setPublisher(new Publisher("TMH" , 12312321));
		book.setAvgRating(3.6f);
		book.setCategory(CATEGORY.FICTION);
		
		List<UserRating> ratings = new ArrayList<UserRating>();
		ratings.add(new UserRating(3, new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
		ratings.add(new UserRating(4, new UserInfoIdentifier(513, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
		ratings.add(new UserRating(6, new UserInfoIdentifier(514, "danger anna3", new Name("manoj3", "rameshchandra", "Thakur"))));
		ratings.add(new UserRating(2, new UserInfoIdentifier(515, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
		ratings.add(new UserRating(1, new UserInfoIdentifier(516, "danger anna5", new Name("manoj5", "rameshchandra", "Thakur"))));
		ratings.add(new UserRating(0, new UserInfoIdentifier(517, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
		
		List<UserComments> comments = new ArrayList<UserComments>();
		comments.add(new UserComments("Hello Comment1", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
		comments.add(new UserComments("Hello Comment2", new UserInfoIdentifier(512, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123457, "Hello Comment3", new UserInfoIdentifier(512, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123458, "Hello Comment4", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123459, "Hello Comment5", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		
		book.setRatings(ratings);
		book.setComments(comments);
		
		//String x = BookHandler.getInstance().addBook(book);
		BookHandler.getInstance().addBookRating("504c83b6364509aed091c801", new UserRating(300, new UserInfoIdentifier(516, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
		
		
		//System.out.println(BookHandler.getInstance().getBookByAuthorId("504b7a67e0c1b0da3bcafc16"));
	}
	
}
