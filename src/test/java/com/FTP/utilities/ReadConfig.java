package com.FTP.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {

	
	Properties pro;
	
	
	public ReadConfig()
	{
		File src = new File("./config/Config.properties");
		
		try
		{
			FileInputStream fils = new FileInputStream(src);
			pro = new Properties();
			pro.load(fils);
			
			//logger.info("properties file Url Added");
			
		}
		catch (Exception ex)
		{
			//logger.info("Exception is " + ex.getMessage());
		}
	}
	
	public String geturl()
	{
		String url = pro.getProperty("Url");
		
		return url;
	}
	
	public String getusername()
	{
		String username = pro.getProperty("UserName");
		return username;
	}
	
	public String getpasswoard()
	{
		String passwoard = pro.getProperty("Passwoard");
		return passwoard;
	}
	
	public String getIEPath()
	{
		String iepath = pro.getProperty("iepath");
		return iepath;
	}
	
	public String getChromePath()
	{
		String ChromePath = pro.getProperty("chromepath");
		return ChromePath;
	}
}
