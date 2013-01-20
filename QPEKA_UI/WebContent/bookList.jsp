<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.qpeka.db.book.store.WorksHandler"%>
<%@page import="com.qpeka.managers.WorkContentManager"%>
<%@page import="com.qpeka.db.book.store.tuples.Work"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.FileReader"%>
<html>
<head>
<meta charset="utf-8">
<title>Qpeka</title>
<link href="css/STYLE2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/gallerystyle.css" />
      
<!-- Do not edit IE conditional style below -->
<!--[if gte IE 5.5]>
<style type="text/css">
#motioncontainer {
width:expression(Math.min(this.offsetWidth, maxwidth)+'px');
}
</style>
<![endif]-->
<!-- End Conditional Style -->

<script type="text/javascript"  src="js/motiongallery.js">
/***********************************************
* CMotion Image Gallery- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
* Modified by Jscheuer1 for autowidth and optional starting positions
***********************************************/
</script>
<%
	List<Work> list = WorksHandler.getInstance().getAllBooks();
%>
<!-- Google Analytics -->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37156701-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- Google Analytics -->
</head>

<body >
	<div class="pagewrap" >
    	<div class="maincont">
    	<br><br>
    	<h2>Available Books</h2> <br><br><br>
    	<%
    		int i = 1;
    	    	for(Work b : list)
    	    	{
    	%>
    	<h3><%=i++%>. &nbsp;<a href="http://qpeka-qpeka.rhcloud.com/QPEKA/bookViewer.jsp?book=<%=b.get_id()%>&pageNo=0"><%=b.getTitle() %></h3></a> <br>
    	<%} %>
        </div>
        	
    </div><!--pagewrap-->
</body>
</html>
