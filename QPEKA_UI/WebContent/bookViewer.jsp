<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.qpeka.utils.SystemConfigHandler"%>
<%@page import="com.qpeka.managers.BookContentManager"%>
<%@page import="com.qpeka.db.book.store.tuples.Book"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.FileReader"%>
<html>
<script language=JavaScript> 
var message="Sorry but you cannot right click.."; 
function clickIE4(){ if (event.button==2){ alert(message); return false; } } 
function clickNS4(e){ if (document.layers||document.getElementById&&!document.all){ if (e.which==2||e.which==3){ alert(message); return false; } } }
if (document.layers){ document.captureEvents(Event.MOUSEDOWN); document.onmousedown=clickNS4; }
else if (document.all&&!document.getElementById){ document.onmousedown=clickIE4; } document.oncontextmenu=new Function("alert(message);return false") 
</script>

<head>
<title>Qpeka</title>
<link href="css/STYLE2_1.css" rel="stylesheet" type="text/css">
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
<%!
String dir = "";
String filePrefix = "";
int pageNo = 0;
int numPagesPerFile = 100;
int totalPages = 0;
%>
<%
String done = request.getParameter("done");
String bookId = request.getParameter("book");
Book bk = BookContentManager.getInstance().getBookDetails(bookId);
pageNo = Integer.parseInt(request.getParameter("pageNo"));
totalPages = bk.getNumPages();
//dir = request.getParameter("fileDir");
dir = SystemConfigHandler.getInstance().getBookContentFolder();
//filePrefix = request.getParameter("filePrefix");
filePrefix = bk.getTitle();

int fileIndex = 0;
if(pageNo/numPagesPerFile < 1)
	fileIndex = 1 ;
else if(pageNo%numPagesPerFile == 0)
	fileIndex = pageNo/numPagesPerFile;
else
	fileIndex = ((int)pageNo/numPagesPerFile) + 1;

String fileName = dir + filePrefix + "-" + fileIndex;
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
<script type="text/javascript">
//cookie format <bookId>:<page>
function getCookie(c_name)
{
var i,x,y,ARRcookies=document.cookie.split(";");
for (i=0;i<ARRcookies.length;i++)
  {
	  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
	  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
	  x=x.replace(/^\s+|\s+$/g,"");
	  if (x==c_name)
	  {
	  	 return unescape(y);
	  }
  }
}

function setCookie(c_name,value,exdays)
{
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
	document.cookie=c_name + "=" + c_value;
	
}

function checkCookie(book,dvr)
{
	if(getCookie(book) &&  dvr != '1')
	{
		window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book='+book+'&pageNo='+getCookie(book)+'&done=1';
	}	
}

var currentPage = <%=Integer.parseInt(request.getParameter("pageNo"))%>;
function insertContent(pageNum,base,flag){
	
	if(flag == 'n')
	{
		pageNum = currentPage +1;
	}
	else if(flag == 'p')
		pageNum = currentPage -1;
	else if(flag == 'f')
		pageNum = 1;
	else if(flag == 'l')
		pageNum = <%=totalPages%>;
	
	var num = pageNum;
	setCookie('<%=bookId%>', num, 100);
	if(num == 0)
	{
		document.getElementById('pageNum').innerHTML = '';
		document.getElementById('pageContent').innerHTML = '<img style="height:100%; width:60%;" align="middle" id="cover" alt="" class="coverpage" src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=bk.get_id()%>">';
	}
	if(num >= base + 10)
	{
		window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+num;
	}
	else if(num < base - 1)
	{
		if(num > 1)
			window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+(base-1);
		else
			window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+1;
	}
	else
	{
		currentPage = pageNum;
		
		document.getElementById('pageNum').innerHTML = 'Page ' +(pageNum) +'/<%=totalPages%>';
		
		var text = document.getElementById('content-'+pageNum).innerHTML;
		document.getElementById('pageContent').innerHTML = text;
		
	}
}

</script>
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

<body onload="checkCookie('<%=bookId%>','<%=done%>');" style="-webkit-touch-callout: none;-webkit-user-select: none;-khtml-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;">
	<div class="pagewrap" >
    	<div class="maincont">
        
        	<div class="lft-cont">
        		<!--<div class="ad-top"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div>
           		<div class="ad-bottom"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
            </div><!--lft-cont-->
            <div class="middl-cont">
            	<div id="disply">
            		<div id="inner-disply" bgcolor="#FFFFFF">
            		<p id="pageNum" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-bottom: 10px;">
            			<%if(pageNo > 0) {%>
            			Page  <%=(pageNo)%>/<%=totalPages%>
            			<%}%>
            		</p> 
            		<p id="pageContent" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-bottom: 10px;font-size:18px;line-height:28px;font-family:Arial">
            		
            		<%if(pageNo == 0) {%>
            			
            			<img style="height:100%; width:60%;" id="cover" alt="" align="middle" src="<%=SystemConfigHandler.getInstance().getImageServerURL()%>?book=<%=bk.get_id()%>">
            		
            		<%} else {%>
            		<%=j.get(pageNo+"")%>	
            		
            		<%} %>
            		</p>
            		</div>
                </div><!--disply-->
                <%for(int i = pageNo ; i < pageNo+10 ; i++ )
                {
                	if(j.has(i+"")){%>
                
                	<div id="content-<%=i%>" style="display: none;">
                		<%=j.get(i+"")%>
                	</div>
               		<%}
                } %>
               <a href="javascript:insertContent(0,<%=pageNo%>,'f')" style="font-size:16px;line-height:26px;font-family:Arial">First</a>&nbsp;|&nbsp;
               <a href="javascript:insertContent(0,<%=pageNo%>,'p')" style="font-size:16px;line-height:26px;font-family:Arial">Prev</a>&nbsp;|&nbsp;
               <a href="javascript:insertContent(0,<%=pageNo%>,'n')" style="font-size:16px;line-height:26px;font-family:Arial">Next</a>&nbsp;|&nbsp;
               <a href="javascript:insertContent(0,<%=pageNo%>,'l')" style="font-size:16px;line-height:26px;font-family:Arial">Last</a>&nbsp; 
               <br>
               <a href="http://qpeka-qpeka.rhcloud.com/QPEKA/landing.jsp" style="font-size:16px;line-height:26px;font-family:Arial">Book List</a>&nbsp; 
               
                <div id="thumb">
               	  <div id="motioncontainer" style="position:relative; overflow:hidden;">
                    <div id="motiongallery" style="position:absolute;left:0;top:0;white-space: nowrap;">
                    <nobr id="trueContainer">
                    <%for(int i = pageNo ; i < pageNo+10 ; i++ )
                	{%>
                    <a href="javascript:insertContent(<%=i%>,<%=pageNo%>)"><img src="images/dynamicbook1.jpg" border=1></a> 
                    <%} %>
                    </nobr>
                    
                    </div>
                    </div>
                </div>
            	
            </div>
            <div class="rht-cont">
            	<!--<div class="ad-top"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div>
           		<div class="ad-bottom"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
            </div><!--rht-cont-->
        
        </div><!--maincont-->
        
    </div><!--pagewrap-->
</body>
</html>
