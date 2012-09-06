package com.qpeka.db.book.store.tuples;

import java.util.List;

import org.json.JSONObject;

import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

/*
 * 
 * books
	{
	"_id" : "12345677",
	"title" : "Harry Potter",
	"authorId" : "13254",
	"coverPageFile" : "/tmp/file/img.jpg",
	"edition" : "1",
	"category" : "Fiction",
	"numPages" : "200",
	"publisherId" : "1345",
	"rating" : "3",
	"metaData" : "",
	"comments" : [{
				commentText : "bla bla...",
				commenter : "321",
				timeOfComment : "123123124124"
				}.......],
	"ratings" : [{
				userId : "123",
				rating : "3"
				}....]
	}
 */

public class Book {
	
	private long _id;
	private String title;
	private Author authorId;
	private String coverPageFile;
	private int edition;
	private CATEGORY category;
	private int numPages;
	private Publisher publisher;
	private float avgRating;
	private JSONObject metaData;
	private List<UserRating> ratings;
	private List<UserComments> comments;
	
	public Book()
	{
		
	}
	
	public Book(long _id, String title, Author authorId, String coverPageFile,
			int edition, CATEGORY category, int numPages, Publisher publisher,
			float avgRating, JSONObject metaData, List<UserRating> ratings,
			List<UserComments> comments) {
		super();
		this._id = _id;
		this.title = title;
		this.authorId = authorId;
		this.coverPageFile = coverPageFile;
		this.edition = edition;
		this.category = category;
		this.numPages = numPages;
		this.publisher = publisher;
		this.avgRating = avgRating;
		this.metaData = metaData;
		this.ratings = ratings;
		this.comments = comments;
	}
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}

	public String getCoverPageFile() {
		return coverPageFile;
	}

	public void setCoverPageFile(String coverPageFile) {
		this.coverPageFile = coverPageFile;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public CATEGORY getCategory() {
		return category;
	}

	public void setCategory(CATEGORY category) {
		this.category = category;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public JSONObject getMetaData() {
		return metaData;
	}

	public void setMetaData(JSONObject metaData) {
		this.metaData = metaData;
	}

	public List<UserRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<UserRating> ratings) {
		this.ratings = ratings;
	}

	public List<UserComments> getComments() {
		return comments;
	}

	public void setComments(List<UserComments> comments) {
		this.comments = comments;
	}
	
	
}
