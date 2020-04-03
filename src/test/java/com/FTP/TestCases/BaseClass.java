package com.FTP.TestCases;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.FTP.utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
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

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	 
	@BeforeSuite
	public void testBeforeSuite() {
		System.out.println("BeforeSuite()");
	}
	
	@BeforeTest
	public void setExtent() 
	{
		System.out.println("BeforeTest()");
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		  // specify location of the report
		  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport" + dateName +  ".html");

		  htmlReporter.config().setDocumentTitle("Automation Report"); // Title of report
		  htmlReporter.config().setReportName("Functional Testing"); // Name of the report
		  htmlReporter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports();
		  extent.attachReporter(htmlReporter);
		  
		  // Passing General information
		  extent.setSystemInfo("Host name", "localhost");
		  extent.setSystemInfo("Environemnt", "QA");
		  extent.setSystemInfo("user", "Raheel");
	}
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		System.out.println("BeforeClass()");
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new   ChromeDriver();
			//ChromeOptions chromeOptions= new ChromeOptions(); 
			//chromeOptions.setBinary("C:\\Users\\Muhammad Raheel\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			//System.setProperty("webdriver.chrome.driver",objReadConfig.getChromePath());
			//driver=new ChromeDriver(chromeOptions);
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
	
	
	@BeforeMethod
	public void Setup()
	{
		logger = Logger.getLogger("FTP");
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("BeforeMethod()");
				
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		System.out.println("AfterMethod()");
		
		if (result.getStatus() == ITestResult.FAILURE) {
			   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
			   String screenshotPath = getScreenshot(driver, result.getName());
			   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
			  } 
		else if (result.getStatus() == ITestResult.SKIP) {
			   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
			  }
		else if (result.getStatus() == ITestResult.SUCCESS) {
			   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			  }
		//driver.quit();
		
	}
	
	@AfterClass
	public void tearDown()
	{
		System.out.println("AfterClass()");
		if(driver!=null)
			driver.close();
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  
		  // after execution, you could see a folder "FailedTestsScreenshots" under src folder
		  String destination = System.getProperty("user.dir") + "/ScreenShorts/" + screenshotName + dateName + ".png";
		  File finalDestination = new File(destination);
		  FileUtils.copyFile(source, finalDestination);
		  return destination;
		 }
	
	@AfterTest
	public void AfterTest()
	{
		System.out.println("AfterTest()");
		extent.flush();
	}

	@AfterSuite
	public void testAfterSuite() {
		System.out.println("AfterSuite()");
	}
}
