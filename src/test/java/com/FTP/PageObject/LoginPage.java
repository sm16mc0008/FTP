package com.FTP.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver lwebdrive;
	
	public LoginPage(WebDriver rdriver, String Url)
	{
		lwebdrive = rdriver;
		lwebdrive.get(Url);
		PageFactory.initElements(rdriver, this);
	}

		
	@FindBy(name="uid")
	WebElement txtusername;
	
	@FindBy(name="password")
	WebElement txtpassword;
	
	@FindBy(name="btnLogin")
	WebElement btnLogin;
	
	public void setUserName(String username)
	{
		txtusername.sendKeys(username);
	}
	
	public void setPasswoard(String userPass)
	{
		txtpassword.sendKeys(userPass);
	}
	
	public void clickBtn()
	{
		btnLogin.click();
	}

}
