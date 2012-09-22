package com.qpeka.managers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.BookHandler;
import com.qpeka.db.book.store.UserCommentHandler;
import com.qpeka.db.book.store.UserRatingHandler;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.UserComments;
import com.qpeka.db.book.store.tuples.UserRating;
import com.qpeka.utils.SystemConfigHandler;

public class BookContentManager {
	
	private static BookContentManager instance = null;
	
	private BookContentManager()
	{
		
	}
	
	public static BookContentManager getInstance()
	{
		if(instance == null)
		{	
			synchronized (BookContentManager.class) {
			
				instance = new BookContentManager();
			}
			
		}
		
		return instance;
	}
	
	//BookDetail Methods
	
	public Book getBookDetails(String bookId)
	{
		return BookHandler.getInstance().getBook(bookId);
	}
	
	public List<Book> getBookDetailsByCategory(CATEGORY category)
	{
		return BookHandler.getInstance().getBooksByCategory(category);
	}
	
	public List<Book> getBookDetailsByAuthor(String authorId)
	{
		return BookHandler.getInstance().getBooksByAuthorId(authorId);
	}
	
	public List<Book> getBookDetailsByAuthorName(String name)
	{
		List<String> authors = new ArrayList<String>();
		
		for(Author author : AuthorHandler.getInstance().getAuthorsByLikelyName(name))
		{
			authors.add(author.get_id());
		}
		
		return BookHandler.getInstance().getBooksByAuthorIds(authors);
	}
	
	public List<Book> getBooksByCriteria(String criteria)
	{
		/*
		 * the hierarcy is as follows
		 *  1) bookTitle
		 *  2) Category 
		 *  3) Description
		 *  4) Metakey -> searchKey 
		 */
		
		return BookHandler.getInstance().getBooksSpecificCriteriaHierarchy(criteria);
	}
	
	public JSONObject getContentGivenBookPageId(String bookId , int page) 
	{
		Book b  = BookHandler.getInstance().getBook(bookId);
		int numPagesPerFile = 50;
		
		if(b.getMetaData().has(Book.NUMPAGESPERFILE))
		{
			try
			{
				numPagesPerFile = b.getMetaData().has(Book.NUMPAGESPERFILE) ? Integer.parseInt(b.getMetaData().getString(Book.NUMPAGESPERFILE)) : 50;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int fileIndex = 0;
		if(page/numPagesPerFile < 1)
			fileIndex = 1 ;
		else if(page%numPagesPerFile == 0)
			fileIndex = page/numPagesPerFile;
		else
			fileIndex = ((int)page/numPagesPerFile) + 1;
		
		String fileName = SystemConfigHandler.getInstance().getBookContentFolder() + "/" + b.get_id() + "/" + b.getTitle() + "-" + fileIndex;
		FileReader fr = null;
		JSONObject returnObj = new JSONObject();
		
		try
		{
			fr = new FileReader(new File(fileName));
			
			char[] cbuf = new char[1000000];
			fr.read(cbuf);
			String s = new String(cbuf);
			s = s.trim();
			
			JSONObject j = new JSONObject(s);
			
			returnObj.put("bookId", bookId);
			returnObj.put("pageId", page);
			returnObj.put("content", j.get(page+""));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnObj;
	}
	
	public List<UserComments> getBookComments(String bookId)
	{
		return UserCommentHandler.getInstance().getCommentsGivenBook(bookId);
	}
	
	public List<UserRating> getBookRatings(String bookId)
	{
		return UserRatingHandler.getInstance().getRatingGivenBook(bookId);
	}
}
