package com.FTP.PageObject;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Pakrailwaylogin {
	
WebDriver lwebdrive;
WebDriverWait wait = null;


	public Pakrailwaylogin(WebDriver rdriver)
	{
		lwebdrive = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new WebDriverWait(lwebdrive, 10);
	}

	
	
	public void inputMobile(String username)
	{
		//txtMobile.sendKeys(username);
		
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtMobile")));
		element.sendKeys(username);
	}
	
	public void inputtxtPassword(String Password)
	{
		//txtPassword.sendKeys(Password);
		
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPassword")));
		element.sendKeys(Password);
	}
	
	public String imgCaptcha() throws TesseractException 
	{
		String imageText = null;
		try
		{
		
			lwebdrive.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			File src = lwebdrive.findElement(By.id("imgCaptcha")).getScreenshotAs(OutputType.FILE);
			
			String path = System.getProperty("user.dir") + "/ScreenShorts/" + GetCurrentTimeStamp().replace(":","_").replace(".","_") + "captchascreen.png";	

			FileUtils.copyFile(src,new File(path));
			ITesseract image = new Tesseract();
			imageText =  image.doOCR(new File(path));
			//lwebdrive.findElement(By.id("CaptchaCodeTextBox")).sendKeys(imageText);
			
			System.out.println(imageText);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return imageText;
	}
	
		
	public static String GetCurrentTimeStamp() {
	        SimpleDateFormat sdfDate = new SimpleDateFormat(
	                "yyyy-MM-dd HH:mm:ss.SSS");// dd/MM/yyyy
	        Date now = new Date();
	        String strDate = sdfDate.format(now);
	        return strDate;
	    }
	
	public void SetCaptcha(String StrCaptcha)
	{
		//txtPassword.sendKeys(Password);
		
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CaptchaCodeTextBox")));
		element.sendKeys(StrCaptcha);
	}
	
	public void btn_Submit()
	{
		//btn_Submit.click();
		WebDriverWait wait = new WebDriverWait(lwebdrive, 0);
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Submit")));
		element.click();
	}
	
}
