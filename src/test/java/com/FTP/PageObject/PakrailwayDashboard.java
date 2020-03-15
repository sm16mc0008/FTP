package com.FTP.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PakrailwayDashboard {
	
WebDriver lwebdrive;
	
	public PakrailwayDashboard(WebDriver rdriver)
	{
		lwebdrive = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	public void ItemSelect()
	{
		WebElement titles = lwebdrive.findElement(By.xpath("//*[@id=\"tilesId\"]"));
		List<WebElement> links = titles.findElements(By.tagName("li"));
		
		for (WebElement li : links) {
			if(li .getText().equals("Book E-Ticket"))
			{
				
				System.out.println(li.getText());
				li.click();
				break;
			}
			
		}
			
		
	}


}
