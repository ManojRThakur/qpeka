<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QPEKA</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
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
            <a href="<%=request.getContextPath()%>/aboutUs.jsp">About Us</a>|
            <a href="#">Contact Us</a>|
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
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
        	<img alt="" src="images/under-construction-logo.gif">
             
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