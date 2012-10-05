package com.futurebazaar.common.methods;

import static org.testng.AssertJUnit.fail;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.futurebazaar.base.TestBase;



public class PaymentPage extends TestBase{
	
	public static void paymentPageOptionsVerification()
	{ //this method clicks on the every link on the payment page and verify each link.
		WebElement select = driver.findElement(By.className("payment_options"));
	    List<WebElement> allOptions = select.findElements(By.tagName("li"));
	    for (WebElement option : allOptions) {
	    	   	
	    	
	    	//System.out.println("verifying the given option and clicking :"+option.getText());
	        option.click();
		    System.out.println("Clicked on the given payment option :"+ option.getText());
		    try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(option.getText().equalsIgnoreCase("Pay by Net Banking"))
		    {
		    	try{
		    	Thread.sleep(3000);
		    	APP_LOGS.debug("Verifying the Payment option : "+option.getText());
		       Assert.assertTrue(driver.getPageSource().contains("We will redirect you to the bank or payment partner site for your payment."));
		    	APP_LOGS.debug(option.getText()+"Option verified");
		    	}catch(Throwable t)
				{
					fail(t.getMessage());
				}
		    }else if(option.getText().equalsIgnoreCase("Pay by Cheque/DD"))
		    {
		    	try{
		    	System.out.println("Verifying the Payment option : "+option.getText());
		    	
		       Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("You can make a Cheque/DD payment for any order placed."));
		    	System.out.println(option.getText()+"Option verified");
		    	
		    	}catch(Throwable t)
				{
					fail(t.getMessage());
				}
		    	}else if(option.getText().equalsIgnoreCase("Pay by Credit Card"))
		    {
		    	try{
			    	System.out.println("Verifying the Payment option : "+option.getText());
			       Assert.assertTrue(driver.getPageSource().contains("Card Type"));
			       Assert.assertTrue(driver.getPageSource().contains("Card Number"));
			       Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
			       Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
			       Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card"));
			       System.out.println(option.getText()+"Option verified");
			    	
		    }
		    catch(Throwable t)
			{
				fail(t.getMessage());
			}	
	}else if(option.getText().equalsIgnoreCase("Pay using Cash Drop"))
	{
		try{
			System.out.println("Verifying the Payment option :"+option.getText());
			//Assert.assertTrue(driver.getPageSource().contains("Select a partner that has a location near you."));
			System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText());
			Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("learn How to Pay by Cash & to find a nearest Cash payment location"));	
		
			System.out.println(option.getText()+"Option verified");
			
			
		}catch(Throwable t)
		{
			fail(t.getMessage());
		}

	}else if(option.getText().equalsIgnoreCase("Pay by Debit Card"))
	{
		try{
			System.out.println("Verifying the Payment option :"+option.getText());
			Assert.assertTrue(driver.getPageSource().contains("Issuing Bank"));
			Assert.assertTrue(driver.getPageSource().contains("Card Number"));
			Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
			Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
			Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card "));
			System.out.println(option.getText()+"Option verified");
			
			
		}catch(Throwable t)
		{
			fail(t.getMessage());
		}
	}else if(option.getText().equalsIgnoreCase("Credit Card EMI"))
	{
		try{
			System.out.println("Verifying the Payment option :"+option.getText());
			Assert.assertTrue(driver.getPageSource().contains("Credit Card Bank"));
			Assert.assertTrue(driver.getPageSource().contains("EMI Plan"));
			Assert.assertTrue(driver.getPageSource().contains("Card Type"));
			Assert.assertTrue(driver.getPageSource().contains("Card Number"));
			Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
			Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
			Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card"));
			System.out.println(option.getText()+"Option verified");
			
			
		}catch(Throwable t)
		{
			fail(t.getMessage());
		}
	}else if(option.getText().equalsIgnoreCase("Pay by COD"))
	{
		try{
			System.out.println("Verifying the Payment option :"+option.getText());
		
			//Assert.assertTrue(driver.getPageSource().contains("In order to verify the Cash on Delivery Payment, please enter a valid contact number."));
			
			System.out.println(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText());
			Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText().contains("We need your mobile number to verify your COD order."));	
		
			System.out.println(option.getText()+"Option verified");
			
			
		}catch(Throwable t)
		{
			fail(t.getMessage());
		}
	}else if(option.getText().equalsIgnoreCase("Pay by Payback"))
	{
		try{
			System.out.println("Verifying the Payment option :"+option.getText());
			//Assert.assertTrue(driver.getPageSource().contains("Redeem your i-mint point."));
			System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/ul/li[1]")).getText());
			Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/ul/li[1]")).getText().contains("PAYBACK (imint) is a loyalty program"));
			System.out.println(option.getText()+"Option verified");
			
			
		}catch(Throwable t)
		{
			fail(t.getMessage());
		}
	      }
	          }
	    
	    
}
}

