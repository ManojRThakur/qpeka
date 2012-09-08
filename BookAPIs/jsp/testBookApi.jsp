<%@page import="com.qpeka.db.book.store.BookHandler"%>
<%@page import="com.qpeka.db.book.store.tuples.UserComments"%>
<%@page import="com.qpeka.db.book.store.tuples.UserRating"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.qpeka.db.book.store.tuples.Constants.CATEGORY"%>
<%@page import="com.qpeka.db.book.store.tuples.Publisher"%>
<%@page import="com.qpeka.db.book.store.tuples.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

Book book = new Book();
book.set_id(0);
book.setTitle("Harry Potter");
book.setAuthorId(123455);
book.setEdition(1);
book.setCoverPageFile("/tmp/img.jpg");
book.setNumPages(200);
book.setPublisher(new Publisher("TMH" , 12312321));
book.setAvgRating(3.6f);
book.setCategory(CATEGORY.FICTION);
book.setRatings(new ArrayList<UserRating>());
book.setComments(new ArrayList<UserComments>());

BookHandler.getInstance().addBook(book);
%>
</body>
</html>