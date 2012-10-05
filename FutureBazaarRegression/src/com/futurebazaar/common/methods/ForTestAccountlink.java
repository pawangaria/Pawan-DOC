package com.futurebazaar.common.methods;

import java.util.List;

import junit.framework.Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.ErrorUtil;

public class ForTestAccountlink extends TestBase{
	
	public void alllinksTest() throws InterruptedException
	{
	
	Loginlogout.loginByMyAccount("pawan1@gmail.com","p0513231068");
	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
	
	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))).click();	
	try{
		
	   Assert.assertTrue(driver.getPageSource().contains("Track your packages and see the history of your orders placed"));
		}catch(Throwable t)
		{
			t.getCause();
			ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
		}	 
	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_SUBSCRIPTION_LINK"))).click();
	
		try{
				
			   Assert.assertTrue(driver.getPageSource().contains("Send me daily notifications for deals"));
				}catch(Throwable t)
				{
					t.getCause();
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				}
				
		driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
		driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_PROFILE_LINK"))).click();
			
			try{
				
			   Assert.assertTrue(driver.getPageSource().contains("Name"));
				}catch(Throwable t)
				{
					t.getCause();
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				}
		
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ADDRESSBOOK_LINK"))).click();
			

			try{
		
			   Assert.assertTrue(driver.getPageSource().contains("Manage your Shipping Addresses here"));
				}catch(Throwable t)
				{
					t.getCause();
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				}
				
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_MANAGEPASSWORD_LINK"))).click();
		

                        try{		
			   Assert.assertTrue(driver.getPageSource().contains("Use the form below to change your password for"));
				}catch(Throwable t)
				{
					t.getCause();
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				}
		
			
	
	 
	 
	}
}
