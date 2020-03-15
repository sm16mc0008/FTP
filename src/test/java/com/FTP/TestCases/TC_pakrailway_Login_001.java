package com.FTP.TestCases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.FTP.PageObject.Pakrailwaylogin;
import net.sourceforge.tess4j.TesseractException;

public class TC_pakrailway_Login_001 extends BaseClass {
	
	
	@Test
	public void PR_Test_Login() throws TesseractException
	{
		logger.info("Test Paistan Railway URL is hit");
		
		// url afterlogin website
	
		Pakrailwaylogin paklogin = new Pakrailwaylogin(driver);
		
		paklogin.inputMobile("923029232918");
		paklogin.inputtxtPassword("R@heel123");
		String StrCaptcha = paklogin.imgCaptcha();
		paklogin.SetCaptcha(StrCaptcha);
		System.out.println("Captcha From Login Test :" + StrCaptcha);
		
	}
	
	

}
