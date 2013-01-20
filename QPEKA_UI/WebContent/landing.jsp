<%@page import="com.qpeka.utils.SystemConfigHandler"%>
<%@page import="com.qpeka.db.book.store.WorksHandler"%>
<%@page import="com.qpeka.db.book.store.tuples.Work"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:fb="http://ogp.me/ns/fb#">
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="og:title" content="QPEKA" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://qpeka.com/" />
<meta property="og:image" content="" />
<meta property="og:site_name" content="QPEKA" />
<meta property="fb:admins" content="100000416610498" />
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
</head>

<%
	String uid = (String)request.getSession().getAttribute("uid");
String uname = (String)request.getSession().getAttribute("uname");
boolean loggedIn = true;
if(uid == null || uname == null)
	loggedIn = false;

if(loggedIn)
{
	request.getRequestDispatcher("/userHome.jsp").forward(request, response);	
	return;
}

List<Work> l = WorksHandler.getInstance().getFirstFiveBooks();
%>
<body>
<!-- FB Like -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<!-- FB Like -->
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
            <a href="<%=request.getContextPath()%>/aboutUs.jsp">About Us</a>|
            <a href="#">Contact Us</a>|
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
            </span>
        </div>
        
        <ul id="main-menu">        
        	<li><a href="<%=request.getContextPath()%>/landing.jsp">Home</a></li>
            <li><a href="#">Books</a></li>
            <li><a href="<%=request.getContextPath()%>/underConstruction.jsp">Short Stories</a></li>
            <li><a href="<%=request.getContextPath()%>/underConstruction.jsp">Poetry/Articles</a></li>
            <li><a href="<%=request.getContextPath()%>/underConstruction.jsp">Blogs</a></li>
            <li><a href="<%=request.getContextPath()%>/underConstruction.jsp">Request</a></li>
           
        </ul><!--main-menu-->
        </div><!--header-->
        <div class="middle-container">
        	<div class="lft-cont">
        	<br/><br/>
           	<div class="ad">
              <img src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=l.get(0).get_id()%>" width="187" height="265">
              <p>Book Of the Day</p>
              </div>
              <div class="ad">
              <img src="images/book2.jpg" width="205" height="267">
              <p>Author Of the Day</p>
              </div>
              </div>
          </div><!--lft-cont-->
			<div class="mdle-cont">
           	  
			<div id="newRead"  style="text-align:center;">
				<p class="title">Books</p>
				<ul id="gal">
					<%
						for(Work b : l){
					%>
						<li><a onmouseover="popup(<%=b.getTitle()%>)" onclick="javascript:alert('You need to login to read this book.');" href="#"><img src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=b.get_id()%>" width="83" height="133"></a></li>
					<%}%>
				</ul>
				<br/><br/>
				 <fb:like href="http://www.facebook.com/QPeka" send="true" width="450" show_faces="true"></fb:like>
			</div>
		
          
          </div><!--mdle-cont-->
            <div class="rght-cont">
           	<div class="ad">
              <img src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=l.get(0).get_id()%>" width="187" height="265">
              <p>Book Of the Day</p>
              </div>
              <div class="ad">
              <img src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=l.get(0).get_id()%>" width="187" height="265">
              <p>Most Read</p>
              </div>
              </div><!--rght-cont-->
        </div><!--middle-container-->
       <div id="footer">
       		<span><a href="#">BECOME A SELF PUBLISH WRITER FOR FREE</a></span>
            <span class="copyright">
            	Copyright @ 2012. QPEKA & ME meeting simplified. All rights reserved.
			</span>
       </div><!--footer-->
    </div><!--pagewrap-->
</div>  <!--main-container-->  
</body>
</html>