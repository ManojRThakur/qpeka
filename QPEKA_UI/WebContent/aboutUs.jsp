<%@page import="com.qpeka.db.book.store.tuples.Work"%>
<%@page import="java.util.List"%>
<%@page import="com.qpeka.db.book.store.WorksHandler"%>
<%@page import="com.qpeka.db.book.store.UserHandler"%>
<%@page import="com.qpeka.db.book.store.tuples.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QPEKA</title>
<!-- Google Analytics -->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37156701-1']);
  _gaq.push(['_setDomainName', 'qpeka.com']);
  _gaq.push(['_setAllowLinker', true]);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- Google Analytics -->
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<%
	boolean loggedIn = true;
String uid = (String)request.getSession().getAttribute("uid");
String uname = (String)request.getSession().getAttribute("uname");
User u = null;
if(uid == null || uname == null)
	loggedIn = false;
else
	u = UserHandler.getInstance().getUser(uid);

List<Work> l = WorksHandler.getInstance().getFirstFiveBooks();
%>
<body>
<div class="main-container">
	<div class="pagewrap">
        <div class="header">
        	<div class="logo">
   		    <img src="images/logo.png" width="207" height="104"> 
            </div>
            <div class="social-media"> </div>
        <div id="login">
       	  	<span>
          	<a href="#" style="margin:0 0 0 20px !important;"><img src="images/fc.png" width="20" height="20"></a> 
            <a href="#" ><img src="images/tw.png" width="20" height="20"></a>
            <a href="#">About Us</a>|
            <a href="#">Contact Us</a>|<%if(loggedIn) {%>
            <a href="#">Logged in As <%=uname%></a>|<a href="<%=request.getContextPath()%>/register?rType=logout">Log out</a><%} else {%><a href="#">Login</a><%}%>           
			</span>
        </div>
        <ul id="main-menu">        
        	<li><a href="<%=request.getContextPath()%>/landing.jsp">Home</a></li>
            <li><a href="#">Books</a></li>
            <li><a href="#">Short Stories</a></li>
            <li><a href="#">Poetry/Articles</a></li>
            <li><a href="#">Blogs</a></li>
            <li><a href="#">Request</a></li>
        </ul><!--main-menu-->
        </div><!--header-->
        <div class="main-container">
        	<div class="lft-cont">
            	
          </div><!--lft-cont-->
            <div class="mdle-cont">
        	
              <p>What happens when there is a group of young guys with a mad dream to make a world where everything from books, blogs, poems, short stories, articles and a lot more is free to read?</p>

<p>Well, Qpeka is born!</p>

<p>We plan to establish a unique platform where every written word gets a reader.</p>

<p>Before you start wondering, please know that we don’t plan to just hand out free e-books to the readers. We don’t want to be just another blogging platform. We don’t want to be just another platform for budding writers to showcase their talent. We are a notch above the rest. We would love to reward those who write on our site.</p>

<p>YES WE PAY YOU!</p>

<p>So it is legal and it is free.</p> 

<p>If you love to read, you may find whatever you are looking for. If you don’t find it, you can always request it. </p>

<p>And if you love to write, tell us and we will showcase it to the world. And as we just mentioned, we will pay you for what you contribute to our site.</p>

<p>We can write volumes about our project but we would want you to discover more about us. If you need us to help you, info@qpeka.com is where you can find us.</p>
        		
              
             
          </div><!--mdle-cont-->
            <div class="rght-cont">
           	  
              </div><!--rght-cont-->
      
        </div><!--middle-container-->
         <div id="footer">
       		<span><a href="#">BECOME A SELF PUBLISH WRITER FOR FREE</a></span>
            <span class="copyright">
            	Copyright @ 2012. QPEKA & ME meeting simplified. All rights reserved.
			</span>
       </div><!--footer-->
    </div><!--pagewrap-->
    </div><!--main-container-->
</body>
</html>