package com.futurebazaar.common.methods;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.ErrorUtil;

public class Cash extends TestBase{
	
	
	public void cashVerification()
	{//this method is used to verify the cash Payment page
	
		
		try{
			//clicking on the cash option 
			  WebElement select = driver.findElement(By.className("payment_options"));
			    List<WebElement> allOptions = select.findElements(By.tagName("li"));
			    for (WebElement option : allOptions) {
			    	   	
			    	 if(option.getText().equalsIgnoreCase("Pay using Cash Drop"))
					    {
			    	
			        option.click();
				    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
				    
				    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("CASH_OPTION_DROPDOWN_ID"))));
			Assert.assertTrue(driver.getPageSource().contains("Cash Option"),"cash option is not displayed.");
	            
    	    //clicking on the option in the radio button.
    	   
    	    APP_LOGS.debug("Verifying the No of options in Chash Drop down");
    	    WebElement droopdown = driver.findElement(By.id(OR.getProperty("CASH_OPTION_DROPDOWN_ID")));
    	    List<WebElement> alloption2 = droopdown.findElements(By.tagName("option"));
    	    int no = alloption2.size();
    	    Assert.assertTrue(no==4, "One cash option is missing from the drop down");
    	    APP_LOGS.debug("Chash Drop down options verified");
    	    Select selectDROP = new Select(droopdown);
    	    selectDROP.selectByVisibleText("Suvidha");
    	   
    	    APP_LOGS.debug("Selected the Suvidha option from the drop down ");
    	    
    	    //clicking on the click here link for the options
    	    APP_LOGS.debug("clicking on the CLICK HERE link");
    		 Thread.sleep(3000);
    	   getlinktextobject("CASH_CLICK_HERE_LINK").click();
	        
    	    WebElement frmname1= driver.findElement(By.id("fancybox-frame"));
    	    String framename = frmname1.getAttribute("name");
    	    System.out.println(framename);
    	    
    	    driver.switchTo().frame(framename);
    	        	    
    	    instructionFrameVerif();
    	    
    	   // Assert.assertTrue(driver.getPageSource().contains("Safe & Secure Payment Options"));
    	    APP_LOGS.debug("payment instruction verified");
					    }
			    }
	}catch(Throwable t)
	{
		ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
	    return;
	}
		
	}
	
	private void instructionFrameVerif()
	{
		try{
			
				//this method is used to verify the payment option page opened from the payment page option.
		Thread.sleep(2000);
		APP_LOGS.debug("verifying the Payment instruction frame");
	    Assert.assertTrue(driver.getPageSource().contains("Payment Instructions"));
	   
	    //Checking on the Itz cash world and clicking on the link 
	    Assert.assertTrue(driver.getPageSource().contains("How to pay using ItzCash World/Outlet?"));
	   
	    //APP_LOGS.debug("clicking on the Itz cash locator");
	   // getlinktextobject("CASH_FRAME_LOCATOR_LINK").click();
	    
	    //driver.findElement(By.cssSelector("td.title_txt")).isDisplayed();
	    APP_LOGS.debug("closing the frame");
	    driver.switchTo().defaultContent();
	   getIDobject("CASH_FRAME_CLOSE_ID").click();
	    APP_LOGS.debug("Clicked on the Frame close");
	  
	    APP_LOGS.debug("frame successfully closed");
	    
		
		}catch(Throwable t)
		{
			ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			APP_LOGS.debug("failed at the Payment Instruction frame from the payment page");
			t.getMessage();
			return;
		}
	    
	}
	

}
