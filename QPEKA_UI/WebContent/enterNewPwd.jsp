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

function ajaxcheck()
{
	var pwd=document.forms["cre-prfle"]["password"].value;
	var pwdc=document.forms["cre-prfle"]["passwordconf"].value;
	
	if ((pwd != null && pwd != "") && (pwdc != null && pwdc != ""))
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
	
	var pwd=document.forms["cre-prfle"]["password"].value;
	var pwdc=document.forms["cre-prfle"]["passwordconf"].value;
	
		
	if ((pwd == null || pwd == "") || (pwdc == null || pwdc == "") )
			
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
                	
                	<form id="cre-prfle" method="post" action="<%=request.getContextPath()%>/auth" onsubmit="return validateForm()" >
                    
                     <table id="ed-pf">
                    	<tbody>
                    	<tr>
							<td>&nbsp;</td>
							<td><input name="rType" type="hidden" onblur="ajaxcheck()" value="setpwd"><%if(isErr){ %>
							Invalid Details
							<%}%></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
                        	<td width="105">&nbsp;</td>
                            <td width="142"><input name="username" type="hidden" value="<%=request.getParameter("username")%>"></td>
                            <td width="165"><div id="msg">&nbsp;</div></td>
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
            <div class="ad">
           	  <p>Our Recommendations</p>
              <img src="images/boo3.jpg" width="200" height="151">
              </div>
              <div class="ad">
			  <p>New Entry</p>
              <img src="images/boo3.jpg" width="200" height="151">
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
    </div><!--main-container-->
</body>
</html>