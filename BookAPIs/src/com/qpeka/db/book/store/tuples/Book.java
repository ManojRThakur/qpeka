package com.qpeka.db.book.store.tuples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
	
	public static final String ID ="_id";
	public static final String TITLE ="title";
	public static final String AUTHORID ="authorId";
	public static final String COVERPAGEFILE ="coverPageFile";
	public static final String EDITION ="edition";
	public static final String CATEGORY ="category";
	public static final String NUMPAGES ="numPages,";
	public static final String PUBLISHER ="publisher";
	public static final String RATING ="avgRating";
	public static final String METADATA ="metaData";
	public static final String COMMENTS ="comments";
	public static final String RATINGS ="ratings"; 
	
	private String _id = "";
	private String title = "";
	private String authorId = "";
	private String coverPageFile = "";
	private int edition = 0;
	private CATEGORY category = com.qpeka.db.book.store.tuples.Constants.CATEGORY.FICTION;
	private int numPages = 0;
	private Publisher publisher = new Publisher();
	private float avgRating = 0.0f;
	private JSONObject metaData = new JSONObject();
	private List<UserRating> ratings = null;
	private List<UserComments> comments = null;
	
	public Book()
	{
		
	}
	
	public Book(String _id, String title, String authorId, String coverPageFile,
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
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
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
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put(ID, new ObjectId(_id));
		
		dbObj.put(TITLE, title);
		dbObj.put(AUTHORID, new ObjectId(authorId));
		dbObj.put(COVERPAGEFILE, coverPageFile);
		dbObj.put(EDITION, edition);
		dbObj.put(CATEGORY, category.toString());
		dbObj.put(NUMPAGES, numPages);
		dbObj.put(PUBLISHER, publisher.toDBObject());
		dbObj.put(RATING, avgRating);
		dbObj.put(METADATA, metaData.toString());
		Set<BasicDBObject> commentsSet = new HashSet<BasicDBObject>();
		for(UserComments comment : comments)
		{
			commentsSet.add((BasicDBObject)comment.toDBObject());
		}
		dbObj.put(COMMENTS, commentsSet);
		
		Set<BasicDBObject> ratingSet = new HashSet<BasicDBObject>();
		for(UserRating rating : ratings)
		{
			ratingSet.add((BasicDBObject)rating.toDBObject());
		}
		dbObj.put(RATINGS, ratingSet);
		
		return dbObj;
	}
	
	public static Book getBookfromDBObject(BasicDBObject obj)
	{
		BasicDBList commentsList = (BasicDBList)obj.get(COMMENTS);
		BasicDBList ratingsList = (BasicDBList)obj.get(RATINGS);
		
		List<UserComments> userComments = new ArrayList<UserComments>();
		List<UserRating> userRatings = new ArrayList<UserRating>();
		
		for(int i = 0 ; i < commentsList.size(); i++ )
		{
			userComments.add(UserComments.getUserCommentfromDBObject((BasicDBObject)commentsList.get(i)));
		}
		
		for(int i = 0 ; i < ratingsList.size(); i++ )
		{
			userRatings.add(UserRating.getUserRatingfromDBObject((BasicDBObject)ratingsList.get(i)));
		}
		
		try {
			return new  Book(obj.getString(ID), obj.getString(TITLE), obj.getString(AUTHORID), obj.getString(COVERPAGEFILE), obj.getInt(EDITION), com.qpeka.db.book.store.tuples.Constants.CATEGORY.valueOf(obj.getString(CATEGORY)),
					obj.getInt(NUMPAGES), Publisher.getPublisherfromDBObject((BasicDBObject)obj.get(PUBLISHER)), (float)obj.getDouble(RATING), new JSONObject(obj.getString(METADATA)), userRatings, userComments);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
