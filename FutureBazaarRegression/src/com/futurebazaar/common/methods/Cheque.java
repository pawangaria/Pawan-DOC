package com.futurebazaar.common.methods;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.futurebazaar.base.TestBase;



public class Cheque extends TestBase {
	
	public void chequeverification() throws InterruptedException
	{
		APP_LOGS.debug("****verifying the cheque OPtion ******");
	//this method clicks on the every link on the payment page and verify each link.
	WebElement select = driver.findElement(By.className("payment_options"));
    List<WebElement> allOptions = select.findElements(By.tagName("li"));
    for (WebElement option : allOptions) {
    	
    	if(option.getText().equalsIgnoreCase("Cheque"))
    			{
    		option.click();
    		Thread.sleep(3000);
    	
      
	    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
	    APP_LOGS.debug("verifying the given option and clicking :"+option.getText());
	    
	    
	    Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed"));
    			}
    	
    	//clicking on the click here link for the options
	    APP_LOGS.debug("clicking on the CLICK HERE link");
	getlinktextobject("CASH_CLICK_HERE_LINK").click();
        
	    WebElement frmname1= driver.findElement(By.id("fancybox-frame"));
	    String framename = frmname1.getAttribute("name");
	    System.out.println(framename);
	    APP_LOGS.debug("clicking to the Frame opened");
	    driver.switchTo().frame(framename);
	    APP_LOGS.debug("verifying the Cheque Frame");
	    Assert.assertTrue(driver.getPageSource().contains("Paying by Cheque or Demand Draft"));
    }   
    }

}
