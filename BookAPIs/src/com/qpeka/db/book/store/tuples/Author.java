package com.qpeka.db.book.store.tuples;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.book.store.tuples.Constants.GENDER;

/*
 * 
 * authors
	{
	_id : "",
	name : {
				firstName : "manoj",
				middleName : "R"
				lastName : "thakur"
			},
	gender : M,
	dob : 12321312,
	nationality : "Indian",
	imageFile : "/tmp/img3.jpg",
	shortBio : "jrcbkuhgr grlfgruflrgflrgfk, gfkjsdgf,sjdg flksjagf",
	infoLink : "http://google.com",
	genre : "Fiction"
	
	}

 */

public class Author {

	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String NATIONALITY = "nationality";
	public static final String IMAGEFILE = "imageFile";
	public static final String SHORTBIO = "shortBio";
	public static final String INFOLINK = "infoLink";
	public static final String GENRE = "genre";
	
	private long _id ;
	private Name name;
	private GENDER gender;
	private Date dob;
	private String nationality;
	private String imageFile;
	private String shortBio;
	private String infoLink;
	private JSONArray genre;
	
	public Author() {
		super();
	}
	
	public Author(long _id, Name name, GENDER gender, Date dob,
			String nationality, String imageFile, String shortBio,
			String infoLink, JSONArray genre) {
		super();
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.shortBio = shortBio;
		this.infoLink = infoLink;
		this.genre = genre;
	}
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public GENDER getGender() {
		return gender;
	}
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getShortBio() {
		return shortBio;
	}
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
	public String getInfoLink() {
		return infoLink;
	}
	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}
	public JSONArray getGenre() {
		return genre;
	}
	public void setGenre(JSONArray genre) {
		this.genre = genre;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(ID, _id);
		dbObj.put(NAME, name.toDBObject());
		dbObj.put(GENDER, gender);
		dbObj.put(DOB, dob.getTime());
		dbObj.put(NATIONALITY, nationality);
		dbObj.put(IMAGEFILE, imageFile);
		dbObj.put(SHORTBIO, shortBio);
		dbObj.put(INFOLINK, infoLink);
		dbObj.put(GENRE, genre.toString());
		
		return dbObj;
	}
	
	public static Author getAuthorfromDBObject(BasicDBObject obj)
	{
		JSONArray genre = new JSONArray();
		try 
		{
			genre = new JSONArray(obj.getString(GENRE));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Author(obj.getLong(ID), Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)), com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)),
				new Date(obj.getLong(DOB)), obj.getString(NATIONALITY) , obj.getString(IMAGEFILE), obj.getString(SHORTBIO),obj.getString(INFOLINK),
				genre);
	}
	
}
