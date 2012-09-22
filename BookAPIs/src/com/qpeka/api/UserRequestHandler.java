package com.qpeka.api;


import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.jboss.netty.util.CharsetUtil;
import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.managers.BookContentManager;

public class UserRequestHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(UserRequestHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	 @Override
     public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
   	  
   	  Map<String, String> requestParams = new HashMap<String, String>();
   	  
   	  HttpRequest request =  (HttpRequest) e.getMessage();
   	        
   	  QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
   	  Map<String, List<String>> params = queryStringDecoder.getParameters();
   	  if (!params.isEmpty()) {
   		  for (Entry<String, List<String>> p: params.entrySet()) {
   			  String key = p.getKey();
   			  requestParams.put(key, p.getValue().get(0));
   		  }
   	  }	  
   	  
   	  logger.log(Level.INFO, "[UserRequestHandler-"+Thread.currentThread().getId()+"] Handling request with params " + requestParams.toString());
   	  
   	  invokeApi(requestParams , e);
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
	
	private void invokeApi(Map<String, String> requestParams, MessageEvent e)
	{
		
		
		if(!requestParams.containsKey("method"))
		{
			writeResponse(e, "{ error : Request should contain method parameter }");
			return;
		}
		
		
		String method = requestParams.get("method");
			
		if(method.equals(APIConstants.GETBOOKGIVENID))
		{
			if(!requestParams.containsKey("bookid"))
			{
				writeResponse(e, "{ error : Request should contain bookid parameter }");
				return;
			}
				
			Book b = BookContentManager.getInstance().getBookDetails(requestParams.get("bookid"));
			writeResponse(e, b.toDBObject(false).toString());
			
		}
		else if(method.equals(APIConstants.GETBOOKSGIVENCRITERIA))
		{
			if(!requestParams.containsKey("criteria"))
			{
				writeResponse(e, "{ error : Request should contain criteria parameter }");
				return;
			}
				
			List<Book> books = BookContentManager.getInstance().getBooksByCriteria(requestParams.get("criteria"));
			List<String> resp = new ArrayList<String>();
			
			for(Book b : books)
				resp.add(b.toDBObject(false).toString());
			
			writeResponse(e, resp.toString());
			
		}
		else if(method.equals(APIConstants.GETBOOKSGIVENCATEGORY))
		{
			if(!requestParams.containsKey("category"))
			{
				writeResponse(e, "{ error : Request should contain category parameter }");
				return;
			}
				
			try
			{
				List<Book> books = BookContentManager.getInstance().getBookDetailsByCategory(CATEGORY.valueOf(requestParams.get("category")));
				List<String> resp = new ArrayList<String>();
				
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
				
				writeResponse(e, resp.toString());
			}
			catch (Exception ex) {
				ex.printStackTrace();
				writeResponse(e, "{ error : Invalid category parameter } ");
				return;
			}
		}
		else if(method.equals(APIConstants.GETBOOKSGIVENAUTHORNAME))
		{
			if(!requestParams.containsKey("authorname"))
			{
				
				writeResponse(e, "{ error : Request should contain authorname parameter }");
				return;
			}
				
			List<Book> books = BookContentManager.getInstance().getBookDetailsByAuthorName(requestParams.get("authorname"));
			List<String> resp = new ArrayList<String>();
			
			for(Book b : books)
				resp.add(b.toDBObject(false).toString());
			
			writeResponse(e, resp.toString());
		}
		else if(method.equals(APIConstants.GETAUTHORGIVENID))
		{
			if(!requestParams.containsKey("authorId"))
			{
				writeResponse(e, "{ error : Request should contain authorId parameter }");
				return;
			}
				
			Author author = AuthorHandler.getInstance().getAuthor(requestParams.get("authorId"));
			writeResponse(e, author.toDBObject(false).toString());
			
		}
		else if(method.equals(APIConstants.GETAUTHORSGIVENCATEGORY))
		{
			
		}
		else if(method.equals(APIConstants.GETAUTHORSGIVENNAME))
		{
			if(!requestParams.containsKey("authorName"))
			{
				writeResponse(e, "{ error : Request should contain authorName parameter }");
				return;
			}
			
			List<Author> authors = AuthorHandler.getInstance().getAuthorsByLikelyName(requestParams.get("authorName"));
			List<String> resp = new ArrayList<String>();
			
			for(Author a : authors)
				resp.add(a.toString());
			
			writeResponse(e, resp.toString());
		}
		else if(method.equals(APIConstants.GETBOOKPAGECONTENT))
		{
			if(!requestParams.containsKey("bookId") || !requestParams.containsKey("pageId"))
			{
				writeResponse(e, "{ error : invalid request }");
				return;
			}
			
			try {
				JSONObject jObj = BookContentManager.getInstance().getContentGivenBookPageId(requestParams.get("bookId"), Integer.parseInt(requestParams.get("pageId")));
				writeResponse(e, jObj.getString("content"));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	private void writeResponse(MessageEvent e, String resp) {
        
		logger.log(Level.INFO, "[UserRequestHandler-"+Thread.currentThread().getId()+"] Writing Response:  " + resp);
	   	  
        // Build the response object.
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        response.setContent(ChannelBuffers.copiedBuffer(resp, CharsetUtil.UTF_8));
        response.setHeader(CONTENT_TYPE, "text/html; charset=UTF-8");

        // Write the response.
        ChannelFuture future = e.getChannel().write(response);

        // Close the non-keep-alive connection after the write operation is done.
        future.addListener(ChannelFutureListener.CLOSE);
        
    }

}
