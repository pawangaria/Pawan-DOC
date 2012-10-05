package com.futurebazaar.common.methods;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.futurebazaar.base.TestBase;



public class Netbanking extends TestBase{
	public int noOFbanks;
	public void netbankingverification() throws InterruptedException
	{ 
		APP_LOGS.debug("****verifying the cheque OPtion ******");
		//this method clicks on the every link on the payment page and verify each link.
		WebElement select = driver.findElement(By.className("payment_options"));
	    List<WebElement> allOptions = select.findElements(By.tagName("li"));
	    for (WebElement option : allOptions) {
	    	
	    	if(option.getText().equalsIgnoreCase("Net Banking"))
	    			{
	    		option.click();
	    		Thread.sleep(3000);
	    		System.out.println("in the net banking option");
	    		//taking the count of the Bank names
	    		
	    			WebElement banklisttake = driver.findElement(By.id("bank"));
	    			List<WebElement> bank = banklisttake.findElements(By.tagName("option"));
	    			for(WebElement banklist : bank)
	    			{
	    				System.out.println(banklist.getText());
	    				noOFbanks++;
	    			}
	    			// the total no of banks count in the netBanking list are  " 37 "
	    			// so checking the list with the default list
	    			APP_LOGS.debug("verifying the no of count of the banks in the list");
	    			Assert.assertEquals(37,noOFbanks);
		            
	    			//selecting the bank at the index of 4
	    			Select selectmonth = new Select(getIDobject("NETBANKING_NAME_ID"));
		            
		            selectmonth.selectByIndex(4);
		            Assert.assertTrue(driver.getPageSource().contains("We will redirect you to the bank or payment partner"));
		            APP_LOGS.debug("*****Net banking page is verified");
	    			}	
	}
	}	

}

