package com.qpeka.managers;

import java.util.ArrayList;
import java.util.List;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.BookHandler;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

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
}
