<%@page import="com.qpeka.utils.SystemConfigHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.qpeka.db.book.store.tuples.Constants.GENDER"%>
<%@page import="com.qpeka.db.book.store.tuples.Constants.LANGUAGES"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" src="md5.js"></script>
<script type="text/javascript">
function passResponse() {
	
	document.hlogin.user.value = document.login.username.value;
	document.hlogin.pass.value = MD5(document.login.password.value);
	
	document.hlogin.submit();
}
</script>
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
	
boolean isErr = true;
if(request.getParameter("error") == null || request.getParameter("error") == "")
	isErr = false;
%>
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
            <a href="#">Contact Us</a>
			</span>
        </div>
        <ul id="main-menu">        
        	
        </ul><!--main-menu-->
        </div><!--header-->
        <div class="main-container">
        	<div class="lft-cont">
            	
          </div><!--lft-cont-->
            <div class="mdle-cont">
            	  
        		<div id="edt-prfle">
                	<form id="hlogin" method="post" action="<%=request.getContextPath()%>/auth" >
                		<input type="hidden" name="pass">
                		<input type="hidden" name="user">
                		<input name="rType" type="hidden" onblur="ajaxcheck()" value="login">
                    </form>
                	<form id="login" method="post" action="<%=request.getContextPath()%>/auth">
                    
                     <table id="ed-pf">
                    	<tbody>
                    	<tr>
							<td>&nbsp;</td>
							<td><input name="rType" type="hidden" onblur="ajaxcheck()" value="login"><%if(isErr){ %>
							Invalid Credentials
							<%}%></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
                        	<td width="105"><label>UserName</label></td>
                            <td width="142"><input name="username" type="text" onblur="ajaxcheck()" value="<%=request.getParameter("username")==null ? "":request.getParameter("username")%>"></td>
                            <td width="165">&nbsp;</td>
                        </tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
                        	<td width="105"><label>Password</label></td>
                            <td width="142"><input name="password" type="password"  onblur="ajaxcheck()"></td>
                            <td width="165"><div id="msg">&nbsp;</div></td>
                        </tr>
                        <tr>
							<td>&nbsp;</td>
							<td><a href="<%=request.getContextPath()%>/userRegister.jsp">Register now..!</a></td>
							<td>&nbsp;</td>
						</tr>
						 <tr>
							<td>&nbsp;</td>
							<td><a href="<%=request.getContextPath()%>/forgotpassword.jsp">Forgot password ?</a></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" onClick="passResponse(); return false;" value="Submit" /></td>
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