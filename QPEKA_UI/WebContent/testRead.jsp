<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.FileReader"%>
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
String fileName = "/tmp/" + "converted/pnp" + "--" + "1";
FileReader fr = null;
JSONObject returnObj = new JSONObject();
JSONObject j = null;
try
{
	fr = new FileReader(new File(fileName));
	
	char[] cbuf = new char[1000000];
	fr.read(cbuf);
	String s = new String(cbuf);
	s = s.trim();
	
	j = new JSONObject(s);
	
}
catch (Exception e) {
	e.printStackTrace();
}
finally{
	try {
		fr.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
%>
<div style="font-size:18px;line-height:28px"><%=j.get("1")%></div>
</body>
</html>