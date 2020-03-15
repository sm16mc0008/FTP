package com.FTP.TestCases;



import org.testng.Assert;
import org.testng.annotations.Test;
import com.FTP.PageObject.LoginPage;
import org.testng.annotations.Parameters;


public class TC_LoginTest_001 extends BaseClass{
	
	@Parameters({ "url1" })
	@Test (priority = 0)
	public void loginTest(String url)
	{
		
		
		logger.info("URL is hit");
		
		LoginPage lp = new LoginPage(driver,url);
		
		lp.setUserName(UserName);
		lp.setPasswoard(Passwoard);
		lp.clickBtn();
		
		logger.info("Click on Submit Button");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("inside true condition");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("inside false condition");
		}
			
	}
	
	

}
