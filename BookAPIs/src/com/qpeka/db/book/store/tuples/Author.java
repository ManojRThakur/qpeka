package com.qpeka.db.book.store.tuples;

import java.util.Date;
import java.util.List;

import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
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
	
	private long _id ;
	private Name name;
	private GENDER gender;
	private Date dob;
	private String nationality;
	private String imageFile;
	private String shortBio;
	private String infoLink;
	private List<CATEGORY> genre;
	
	public Author() {
		super();
	}
	
	public Author(long _id, Name name, GENDER gender, Date dob,
			String nationality, String imageFile, String shortBio,
			String infoLink, List<CATEGORY> genre) {
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
	public List<CATEGORY> getGenre() {
		return genre;
	}
	public void setGenre(List<CATEGORY> genre) {
		this.genre = genre;
	}
	
	
}
