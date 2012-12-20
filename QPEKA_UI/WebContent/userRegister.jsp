<%@page import="com.qpeka.db.book.store.BookHandler"%>
<%@page import="com.qpeka.db.book.store.tuples.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.qpeka.db.book.store.tuples.Constants.GENDER"%>
<%@page import="com.qpeka.db.book.store.tuples.Constants.LANGUAGES"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function ajaxcheck()
{
	var uname=document.forms["cre-prfle"]["username"].value;
	var pwd=document.forms["cre-prfle"]["password"].value;
	var pwdc=document.forms["cre-prfle"]["passwordconf"].value;
	
	if ((uname != null && uname != "") && (pwd != null && pwd != "") && (pwdc != null && pwdc != ""))
	{
		if(pwd == pwdc)
			xmlhttpPost('http://localhost:8080/QPEKA/register?rType=authAvail&uname='+uname+'&pwd='+pwd);
	}
		
}
function xmlhttpPost(strURL) {
    var xmlHttpReq = false;
    var self = this;
    // Mozilla/Safari
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    self.xmlHttpReq.open('GET', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function() 
    {
        if (self.xmlHttpReq.readyState == 4) {
            updatepage(self.xmlHttpReq.responseText);
        }
    }
    self.xmlHttpReq.send(getquerystring());
}

function updatepage(str){
    document.getElementById("msg").innerHTML = str;
}

function validateForm()
{
	var fname=document.forms["cre-prfle"]["firstName"].value;
	var lname=document.forms["cre-prfle"]["lastName"].value;
	var uname=document.forms["cre-prfle"]["username"].value;
	var pwd=document.forms["cre-prfle"]["password"].value;
	var pwdc=document.forms["cre-prfle"]["passwordconf"].value;
	var email=document.forms["cre-prfle"]["email"].value;
	
		
	if ((fname == null || fname == "") || (lname == null || lname == "") ||
			(uname == null || uname == "") || (pwd == null || pwd == "") ||
			(pwdc == null || pwdc == "") || (email == null || email == ""))
			
	{
		  document.getElementById("errmsg").innerHTML = 'Some of the mandatory fields are empty';
		  return false;
	}
	
	if(pwd != pwdc)
	{
		 document.getElementById("errmsg").innerHTML = 'Passwords do not match';
		  return false;
	}		
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QPEKA</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
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

List<Book> l = BookHandler.getInstance().getFirstFiveBooks();
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
            <a href="#">Contact Us</a>|
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
			</span>
        </div>
        <ul id="main-menu">        
        	<li><a href="#">Home</a></li>
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
            	  
        		<div id="edt-prfle">
                	
                    <form id="cre-prfle" method="post" action="<%=request.getContextPath()%>/register" enctype="multipart/form-data" onsubmit="return validateForm()" >
                    <table id="ed-pf">
                    	<tbody>
                    	<tr>
							<td>&nbsp;</td>
							<td><input name="rType" type="hidden" value="register"><div id="errmsg"></div></td>
							<td>&nbsp;</td>
						</tr>
                    	<tr>
                        	<td width="105"><label>UserName</label></td>
                            <td width="142"><input name="username" type="text" onblur="ajaxcheck()"></td>
                            <td width="165">&nbsp;</td>
                        </tr>
                        <tr>
                        	<td width="105"><label>Password</label></td>
                            <td width="142"><input name="password" type="password"  onblur="ajaxcheck()"></td>
                            <td width="165"><div id="msg">&nbsp;</div></td>
                        </tr>
                        <tr>
                        	<td width="105"><label>Confirm Password</label></td>
                            <td width="142"><input name="passwordconf" type="password"  onblur="ajaxcheck()"></td>
                            <td width="165">&nbsp;</td>
                        </tr>
                        <tr>
                        	<td width="105"><label>First Name</label></td>
                            <td width="142"><input name="firstName" type="text"></td>
                            <td width="165">&nbsp;</td>
                        </tr>
                         <tr>
                        	<td width="200"><label>Middle Name</label></td>
                            <td><input name="middleName" type="text"></td>
                            <td>&nbsp;</td>
                         </tr>
                         <tr>
                        	<td><label>Last Name</label></td>
                            <td><input name="lastName" type="text"></td>
                            <td>&nbsp;</td>
                         </tr>
                         <tr>
                        	<td><label>Pen Name</label></td>
                            <td><input name="penName" type="text"></td>
                            <td>
                              <label></label>
                            </td>
                         </tr>
                         <tr>
                        	<td><label>Email Id</label></td>
                            <td><input name="email"  type="text"></td>
                            <td></td>
                         </tr>
                         <tr>
                        	<td><label>Phone</label></td>
                            <td><input name="phone"  type="text"></td>
                            <td>&nbsp;</td>
                         </tr>
                         <tr>
                        	<td><label>DOB</label></td>
                            <td>Day   : <select name="day">
							 <%for(int i = 1 ; i <= 31 ; i ++){ %>
							 
							  <option value="<%=i%>"><%=i%></option>
							 
							 <%} %>
							</select>
                                Month :<select name="month">
							 <%for(int i = 1 ; i <= 12 ; i ++){ %>
							 
							  <option value="<%=i%>"><%=i%></option>
							 
							 <%} %>
							</select>
							
                                Year  : <select name="year">
							 <%for(int i = 1960 ; i <= 2012 ; i ++){ %>
							 
							  <option value="<%=i%>"><%=i%></option>
							 
							 <%} %>
							</select></td>
                            <td></td>
                         </tr>
                          <tr>
                        	<td><label>Gender</label></td>
                            <td><select name="gender">
							 <%for(GENDER g : GENDER.values()){ %>
							 
							  <option value="<%=g.toString()%>"><%=g.toString()%></option>
							 
							 <%} %>
							</select> </td>
                            <td>&nbsp;</td>
                         </tr>
                          <tr>
                        	<td><label>About You</label></td>
                            <td><textarea rows="10" cols="1000" name="desc"></textarea></td>
                            <td>&nbsp;</td>
                         </tr>
                          <tr>
                        	<td><label>Preferred Languages for Reading</label></td>
                             <td>
                               <select name="rLang">
							 <%for(LANGUAGES g : LANGUAGES.values()){ %>
							 
							  <option value="<%=g.toString()%>"><%=g.toString()%></option>
							 
							 <%} %>
							</select> 
                                </td>
                            <td>&nbsp;</td>
                         </tr>
                    	  <tr>
                        	<td><label>Preferred Languages for Writing</label></td>
                              <td>
                                <select name="wLang">
								 <%for(LANGUAGES g : LANGUAGES.values()){ %>
								 
								  <option value="<%=g.toString()%>"><%=g.toString()%></option>
								 
								 <%} %>
							</select> 
                                </td>
                            <td>&nbsp;</td>
                         </tr>
                         <tr>
							<td>Profile Image:</td>
							<td><input type="file" name="file" value=""></input></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit"  value="Submit" /></td>
							<td>&nbsp;</td>
						</tr>
                    </tbody>
                    </table>
                    
                    
                    </form>
                </div><!--edt-prfle-->

              
           
          </div><!--mdle-cont-->
            <div class="rght-cont border-left">
            <!--  <div class="ad">
           	  <p>Our Recommendations</p>
              <img src="images/boo3.jpg" width="200" height="151">
              </div>
              <div class="ad">
			  <p>New Entry</p>
              <img src="images/boo3.jpg" width="200" height="151">
              </div>-->
              
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