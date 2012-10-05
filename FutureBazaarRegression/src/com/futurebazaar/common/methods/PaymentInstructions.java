package com.futurebazaar.common.methods;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.futurebazaar.base.TestBase;



public class PaymentInstructions extends TestBase {

	
	
	public static void instructionFrameVerif()
	{
		try{
			
				//this method is used to verify the payment option page opened from the payment page option.
		Thread.sleep(2000);
		APP_LOGS.debug("verifying the Payment instruction frame");
	    Assert.assertTrue(driver.getPageSource().contains("Payment Instructions"));
	   
	    //Checking on the Itz cash world and clicking on the link 
	    Assert.assertTrue(driver.getPageSource().contains("How to pay using ItzCash World/Outlet?"));
	   
	    APP_LOGS.debug("clicking on the Itz cash locator");
	    getlinktextobject("CASH_FRAME_LOCATOR_LINK").click();
	    
	    driver.findElement(By.cssSelector("td.title_txt")).isDisplayed();
	    APP_LOGS.debug("closing the frame");
	    driver.switchTo().defaultContent();
	    getIDobject("CASH_FRAME_CLOSE_ID").click();
	    APP_LOGS.debug("Clicked on the Frame close");
	    Assert.assertTrue(driver.getPageSource().contains("Safe & Secure Payment Options"));
	    APP_LOGS.debug("frame successfully closed");
	    
		
		}catch(Throwable t)
		{
			APP_LOGS.debug("failed at the Payment Instruction frame from the payment page");
			t.getMessage();
			
		}
	    
	}
	
	
	
	
}
