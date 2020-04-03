package com.FTP.TestCases;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.FTP.utilities.ReadConfig;
import org.testng.annotations.Parameters;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseClass {

	ReadConfig objReadConfig = new ReadConfig();
	
	
	
	public String Url = objReadConfig.geturl();
	public String UserName = objReadConfig.getusername();
	public String Passwoard = objReadConfig.getpasswoard();
	
	public static WebDriver driver;
	public static Logger logger;

	
	@Parameters("browser")
	@BeforeClass
	public void Setup(String br)
	{
		logger = Logger.getLogger("FTP");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			//driver = new   ChromeDriver();
			ChromeOptions chromeOptions= new ChromeOptions(); 
			chromeOptions.setBinary("C:\\Users\\Muhammad Raheel\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.driver",objReadConfig.getChromePath());
			driver=new ChromeDriver(chromeOptions);
		} else if(br.equals("firefoxdriver"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new   FirefoxDriver();
		} else if (br.equals("ie"))
		{
			//WebDriverManager.iedriver().setup();
			//driver = new InternetExplorerDriver();
			System.setProperty("webdriver.ie.driver",objReadConfig.getIEPath());
			driver = new InternetExplorerDriver();
			
		}
		
		driver.get(Url);
		
		
	}
	
	
	@AfterClass
	public void tearDown() {
		if(driver!=null)
			driver.close();
	}
	
	@BeforeSuite
	public void testBeforeSuite() {
		System.out.println("testBeforeSuite()");
	}

	@AfterSuite
	public void testAfterSuite() {
		System.out.println("testAfterSuite()");
	}
}
