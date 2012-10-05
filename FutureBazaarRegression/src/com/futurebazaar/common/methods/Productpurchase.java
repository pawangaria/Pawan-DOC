package com.futurebazaar.common.methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.futurebazaar.base.*;
import com.futurebazaar.common.methods.*;

public class Productpurchase extends TestBase{

	/*
	private static Productpurchase instance = new Productpurchase(); 
	
	public static Productpurchase getInstance(){
		return instance;
	}*/
          
	
	
	public static void purchaseItem(String username,String password,String item)
	{   //this method guide you to the payment details page
		//this method will take the user name and password for the user at the time purchasing the 
		//product.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//select the first product
		driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_LINK_XPATH"))).click();
	
		
		//Verifying the Product Description page.
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
		if(driver.getPageSource().contains("OUT OF STOCK"))
		{   		APP_LOGS.debug("NOTE: Product is out of Stock So selecting second item");

			driver.navigate().back();
			driver.findElement(By.xpath(OR.getProperty("HOME_SECOND_PRODUCT_LINK_XPATH"))).click();
		
			//Verifying the Product Description page.
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
			
		}
		
		APP_LOGS.debug("product details page for the selected Item");
		
		Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).isDisplayed(),"buy now button is not displayed");
		APP_LOGS.debug("Selecting Buy now option");
	
		driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).click();
		
	//	driver.findElement(By.xpath("//button[starts-with(@title,'Buy this product')]")).click();
		APP_LOGS.debug("opening Review your cart page");
		driver.findElement(By.name(OR.getProperty("PROCEED_TOSHIPPING_DETAILS_NAME"))).click();
		
			if(driver.getPageSource().contains("Proceed with your email or mobile"))
		{   APP_LOGS.debug("user loggin by the loginguestflow method");
			Loginlogout.loginGuestflow(username,password);
		}
		
		
		APP_LOGS.debug("Opening the shipping details page");
		APP_LOGS.debug("Selecting the Default shipping details option");
		driver.findElement(By.xpath(OR.getProperty("SELECT_DEFAULT_SHIPPING_XPATH"))).click();
		
			driver.findElement(By.id(OR.getProperty("PROCEED_TO_PAYAMENT_PAGE_ID"))).click();
		APP_LOGS.debug("processing to the payment details page");
			}

	 public static void purchaseItem(String item)
	 {
		 //this method is used to purchase product with out login , the user is already logged in the account.  
		 APP_LOGS.debug("searching the product from purchaseitem method");
			driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(item);
			APP_LOGS.debug("Searching the product");
			driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
			APP_LOGS.debug("selecting teh first product from the search");
			
			//select the first product
			driver.findElement(By.xpath(OR.getProperty("SELECT_FIRST_ITEM_XPATH"))).click();
			APP_LOGS.debug("product details page for the selected Item");
			driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).click();
			APP_LOGS.debug("Selected Buy now option");
			APP_LOGS.debug("opening Review your cart page");
			driver.findElement(By.name(OR.getProperty("PROCEED_TOSHIPPING_DETAILS_NAME"))).click();
					
					APP_LOGS.debug("Selecting the Default shipping details option");
					driver.findElement(By.xpath(OR.getProperty("SELECT_DEFAULT_SHIPPING_XPATH"))).click();
					APP_LOGS.debug("Processing to the payment details page");	
				    
					driver.findElement(By.id(OR.getProperty("PROCEED_TO_PAYAMENT_PAGE_ID"))).click();
					APP_LOGS.debug("To Payemnt page clicked");	
		    
	 }
	
	public static void toShippingPage(String username,String password,String item)
	{
		APP_LOGS.debug("searching the product from purchaseitem method");
		driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(item);
		APP_LOGS.debug("Searching the product");
		driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
		APP_LOGS.debug("selecting teh first product from the search");
		
		//select the first product
		driver.findElement(By.xpath(OR.getProperty("SELECT_FIRST_ITEM_XPATH"))).click();
		APP_LOGS.debug("product details page for the selected Item");
		driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).click();
		APP_LOGS.debug("Selected Buy now option");
		APP_LOGS.debug("opening Review your cart page");
		driver.findElement(By.name(OR.getProperty("PROCEED_TOSHIPPING_DETAILS_NAME"))).click();
		
		if(driver.getPageSource().contains("Already a Member"))
		{   APP_LOGS.debug("user loggin by the loginguestflow method");
			Loginlogout.loginGuestflow(username,password);
		}
		

		
	}
	
	
}
