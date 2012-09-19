package com.qpeka.api;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class RequestHandlerServer {
	
	private int port = 8000;
	private AtomicBoolean started = new AtomicBoolean(false);
	
	private static RequestHandlerServer instance = null;
	
	public static RequestHandlerServer getInstance()
	{
		if(instance == null)
			instance = new RequestHandlerServer();
		
		return instance;
	}
	
	private  RequestHandlerServer()
	{
		
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void run()
	{
		if(!started.getAndSet(true))
		{
			// Configure the server.
			ServerBootstrap bootstrap = new ServerBootstrap(
					new NioServerSocketChannelFactory(
							Executors.newCachedThreadPool(),
							Executors.newCachedThreadPool()));
				  
			// Set up the pipeline factory.
			bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				public ChannelPipeline getPipeline() throws Exception {
					return Channels.pipeline(new UserRequestHandler());
				}
			});
				   
			// Bind and start to accept incoming connections.
			bootstrap.bind(new InetSocketAddress(port));
			
			System.out.println("Server started on port " + port);
		}
		else
		{
			System.out.println("Server already started");
		}
	}
	
	public static void main(String[] args) {
		
		RequestHandlerServer.getInstance().run();
	}
}
