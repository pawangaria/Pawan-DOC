package com.futurebazaar.common.methods;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.futurebazaar.base.TestBase;



public class CreditCard extends TestBase {
    public int count=0;
	public void creditcardVerification(String cardno,String EXmonth,String EXyear,String CVV,String name)
	{   
		
       //this method is used to verify the Credit card page option 
		APP_LOGS.debug("opening the credit card option");
	    getxpathobject("PAYMENT_TYPE_CREDITCARD_XPATH").click();
	    
	    driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	    try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    APP_LOGS.debug("verifying the Credit card option");
		try
		{   APP_LOGS.debug("verifying the Credit card Txt");
			Assert.assertEquals(getCSSobject("CREDIT_CARD_CSS").getText(),"Card Type");
		    APP_LOGS.debug("Clicking on the Master card option ");
			getxpathobject("CREDIT_CARD_CARDTYPE_MASTERCARD_XPATH").click();
			APP_LOGS.debug("Clicking on the VISA card option ");
			getxpathobject("CREDIT_CARD_CARDTYPE_VISA_XPATH").click();
			
			getIDobject("CREDIT_CARD_NUMBER_ID").clear();
			getIDobject("CREDIT_CARD_NUMBER_ID").sendKeys(cardno);
			
						
			//printing the values from the drop down list.
			/*
			List<WebElement> allOptions = driver.findElements(By.id("id_exp_month"));
			
			for (WebElement option : allOptions) {
				APP_LOGS.debug("month values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			    this.count++;
			    System.out.println(this.count);
			}*/
            
			
			//Selecting the month from the drop down. 
			Select selectmonth = new Select(getIDobject("CREDIT_CARD_EXPIRATION_MONTH_ID"));
            selectmonth.selectByVisibleText(EXmonth);
          
           			
           //printing the values from the drop down list 
         /*
            List<WebElement> allyear = driver.findElements(By.id("id_exp_month"));
			
			for (WebElement option : allyear) {
				APP_LOGS.debug("year values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			    this.count++;
			    System.out.println(this.count);
			}*/
            
			//selecting an year from the drop down
            Select selectyear = new Select(getIDobject("CREDIT_CARD_EXPIRATION_YEAR_ID"));
            selectyear.selectByVisibleText(EXyear);
        
            
			//entering the CVV no.
            getIDobject("CREDIT_CARD_CVV_ID").clear();
            getIDobject("CREDIT_CARD_CVV_ID").sendKeys(CVV);
		
			
			
		    //Entering the name on the card
            getIDobject("CREDIT_CARD_NAME_ID").clear();
            getIDobject("CREDIT_CARD_NAME_ID").sendKeys(name);
		   
			//verifying the shipping details page.
			Assert.assertEquals(getCSSobject("PAYMENT_TYPE_BILLINGINFO_CSS").getText(),"Please Enter Billing Information");
			APP_LOGS.debug("Please Enter Billing Information : is present on shipping details");
			
			//check and uncheck the checkbox for the shipping details. 
			APP_LOGS.debug("clicking the checkbox");
			driver.findElement(By.className("padt5 padb10")).click();
			
			//getIDobject("shipping_address_checkbox").click();
			APP_LOGS.debug("unchecking the checkbox");
			//getIDobject("shipping_address_checkbox").click();
			
			
            		
			
			
		}catch(Throwable t)
		{
			APP_LOGS.debug(t.getMessage());
		}
	    
	}
	
	public void getShippingDetails(String username,String password,String item)
	{    
		Productpurchase.toShippingPage(username, password, item);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		WebElement alloptions = driver.findElement(By.id("delivery_address_block"));
		List<WebElement> select =alloptions.findElements(By.tagName("input"));
		for(WebElement option : select)
		{
			System.out.println(option.getAttribute("value"));
		}*/
		
		
	    WebElement firstname1 =driver.findElement(By.id("id_delivery_first_name"));
		String firstname=firstname1.getAttribute("value");
		
		WebElement lastname1 =driver.findElement(By.id("id_delivery_last_name"));
		String lastname=lastname1.getAttribute("value");
		

		WebElement address1 =driver.findElement(By.id("id_delivery_address"));
		String address=address1.getAttribute("value");
		
		WebElement city1 =driver.findElement(By.id("id_delivery_city"));
		String city=city1.getAttribute("value");
		
		WebElement pincode1 =driver.findElement(By.id("id_delivery_pincode"));
		String pincode=pincode1.getAttribute("value");
		
		
		WebElement state1 =driver.findElement(By.id("id_delivery_state"));
		String state=state1.getAttribute("value");
		
		
	}

	public void getShippingDetails()
	{
		WebElement firstname1 =driver.findElement(By.id("id_delivery_first_name"));
		String firstname=firstname1.getAttribute("value");
		
		WebElement lastname1 =driver.findElement(By.id("id_delivery_last_name"));
		String lastname=lastname1.getAttribute("value");
		

		WebElement address1 =driver.findElement(By.id("id_delivery_address"));
		String address=address1.getAttribute("value");
		
		WebElement city1 =driver.findElement(By.id("id_delivery_city"));
		String city=city1.getAttribute("value");
		
		WebElement pincode1 =driver.findElement(By.id("id_delivery_pincode"));
		String pincode=pincode1.getAttribute("value");
		
		
		WebElement state1 =driver.findElement(By.id("id_delivery_state"));
		String state=state1.getAttribute("value");
		

		
	}

public void verifyshippingdetails()
	{
	}


}
