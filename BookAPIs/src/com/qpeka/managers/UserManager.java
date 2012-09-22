package com.qpeka.managers;

public class UserManager {

private static UserManager instance = null;
	
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		if(instance == null)
		{	
			synchronized (UserManager.class) {
			
				instance = new UserManager();
			}
			
		}
		
		return instance;
	}
}
