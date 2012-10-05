package com.Fb.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Fb.Base.Base;
import com.Fb.PDPpage.PDPpage;

public class HomePage extends Base{
	
	public PDPpage clickOnFirstProduct()
	{
		
		
		driver.findElement(By.xpath(OR.getProperty("SELECT_PRODUCT"))).click();
		return new PDPpage();
	}
	
    public boolean isHomePageDisplayed()
    {
    	if(driver.findElement(By.id(OR.getProperty("HOME_PAGE_SLIDES"))).isDisplayed())
    	{
    		return true;
    	}else
    	{
    		return false;
    	}
    	
    }
}
