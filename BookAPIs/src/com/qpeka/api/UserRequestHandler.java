package com.qpeka.api;


import org.jboss.netty.channel.Channel;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.managers.BookContentManager;

public class UserRequestHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(UserRequestHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		// Send back the received message to the remote peer.
		transferredBytes.addAndGet(((ChannelBuffer) e.getMessage())
				.readableBytes());
		System.out.println(Thread.currentThread().getId() + " RECEIVED MESSAGE " + e.getMessage());
		
		e.getChannel().write(e.getMessage());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
	
	private void invokeApi(String requestString, Channel channel)
	{
		JSONObject request = null;
		try 
		{
			request = new JSONObject(requestString);
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			channel.write("Invalid request syntax");
			return;
		}
		
		if(!request.has("method"))
		{
			channel.write("Request should contain method parameter");
			return;
		}
		
		try 
		{
			
			String method = request.getString("method");
			
			if(method.equals(APIConstants.GETBOOKGIVENID))
			{
				if(!request.has("bookid"))
				{
					channel.write("Request should contain bookid parameter");
					return;
				}
				
				BookContentManager.getInstance().getBookDetails(request.getString("bookid"));
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENCRITERIA))
			{
				if(!request.has("criteria"))
				{
					channel.write("Request should contain criteria parameter");
					return;
				}
				
				BookContentManager.getInstance().getBooksByCriteria(request.getString("criteria"));
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENCATEGORY))
			{
				if(!request.has("category"))
				{
					channel.write("Request should contain category parameter");
					return;
				}
				
				try
				{
					BookContentManager.getInstance().getBookDetailsByCategory(CATEGORY.valueOf(request.getString("category")));
				}
				catch (Exception e) {
					e.printStackTrace();
					channel.write("Invalid category parameter");
					return;
				}
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENAUTHORNAME))
			{
				if(!request.has("authorname"))
				{
					channel.write("Request should contain authorname parameter");
					return;
				}
				
				BookContentManager.getInstance().getBookDetailsByAuthorName(request.getString("authorname"));
			}
			else if(method.equals(APIConstants.GETAUTHORGIVENID))
			{
				if(!request.has("authorId"))
				{
					channel.write("Request should contain authorId parameter");
					return;
				}
				
				AuthorHandler.getInstance().getAuthor(request.getString("authorId"));
			}
			else if(method.equals(APIConstants.GETAUTHORSGIVENCATEGORY))
			{
				
			}
			else if(method.equals(APIConstants.GETAUTHORSGIVENNAME))
			{
				if(!request.has("authorName"))
				{
					channel.write("Request should contain authorName parameter");
					return;
				}
				
				AuthorHandler.getInstance().getAuthorsByLikelyName(request.getString("authorName"));
			}
			else
			{
				
			}
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
