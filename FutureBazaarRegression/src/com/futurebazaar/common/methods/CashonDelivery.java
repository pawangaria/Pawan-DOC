package com.futurebazaar.common.methods;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.futurebazaar.base.TestBase;



public class CashonDelivery extends TestBase{
	
	public void cashondeliveryTest() throws InterruptedException
	{
		//this method clicks on the every link on the payment page and verify each link.
				WebElement select = driver.findElement(By.className("payment_options"));
			    List<WebElement> allOptions = select.findElements(By.tagName("li"));
			    for (WebElement option : allOptions) {
			    	
			    	if(option.getText().equalsIgnoreCase("Cash On Delivery"))
			    			{
			    		option.click();
			    		Thread.sleep(3000);
			    	
			      
				    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
				    APP_LOGS.debug("verifying the given option and clicking :"+option.getText());
				    
				    
				    Assert.assertTrue(driver.getPageSource().contains("You need to make a cash payment for any order placed only"));
			    			}
			    	
			    	//clicking on the click here link for the options
		    	    APP_LOGS.debug("clicking on the CLICK HERE link");
		    	 getlinktextobject("CASH_CLICK_HERE_LINK").click();
			        
		    	    WebElement frmname1= driver.findElement(By.id("fancybox-frame"));
		    	    String framename = frmname1.getAttribute("name");
		    	    System.out.println(framename);
		    	    
		    	    driver.switchTo().frame(framename);
		    	    Assert.assertTrue(driver.getPageSource().contains("How do I place a Cash-on-Delivery Order?"));
		    	    
			    }

}
}