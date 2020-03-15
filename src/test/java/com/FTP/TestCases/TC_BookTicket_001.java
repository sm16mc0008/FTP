package com.FTP.TestCases;



import org.testng.annotations.Test;

import com.FTP.PageObject.PakrailwayDashboard;
import com.FTP.PageObject.Pakrailwaylogin;

import net.sourceforge.tess4j.TesseractException;

public class TC_BookTicket_001 extends BaseClass{

	@Test (priority = 2)
	public void PR_Test_BookTicket() throws TesseractException
	{
		Pakrailwaylogin paklogin = new Pakrailwaylogin(driver);
		
		paklogin.inputMobile("923029232918");
		paklogin.inputtxtPassword("R@heel123");
		String StrCaptcha = paklogin.imgCaptcha();
		paklogin.SetCaptcha(StrCaptcha);
		System.out.println("Captcha From BookTicket Test :" + StrCaptcha);
		
		
		
		PakrailwayDashboard objpkdashboard = new PakrailwayDashboard(driver);
		
		objpkdashboard.ItemSelect();
		
	}
}
