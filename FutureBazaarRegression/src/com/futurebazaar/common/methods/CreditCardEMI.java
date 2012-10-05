package com.futurebazaar.common.methods;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.futurebazaar.base.TestBase;

import net.sourceforge.htmlunit.corejs.javascript.ast.TryStatement;



public class CreditCardEMI extends TestBase{
	
	// this class is to verify the credit card EMI option from the payment page.
	
	public void creditCardEMIVerification(String cardno,String EXmonth,String EXyear,String CVV,String name)
	{   
		//this is the method for the CREDIT Card EMI option verification
		try
		{
			
		
		getxpathobject("PAYMENT_TYPE_CREDITCARDEMI_XPATH").click();
		Thread.sleep(2000);
		
		//clicking on the ICICI option 
		getxpathobject("CREDIT_CARD_EMI_ICIC_XPATH").click();
		icicibank();//calling the icici method for the verification.
		
		//clicking on the HDFC bank option  
		getnameobject("CREDIT_CARD_EMI_HDFC_NAME").click();
		hdfcbank(cardno,EXmonth,EXyear,CVV,name);//calling the hdfc verification method.
		}
		
	 catch(Throwable t)
	 {
		 t.getMessage();
	 }
			
	}
	
	private void hdfcbank(String cardno,String EXmonth,String EXyear,String CVV,String name)
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getxpathobject("CREDIT_CARD_EMI_EMI_PLANS_6MONTH_XPATH").click();
		
		getCSSobject("CREDIT_CARD_EMI_EMI_PLANS_9MONTH_CSS").click();
		
		getnameobject("CREDIT_CARD_EMI_EMI_PLANS_3MONTH_NAME").click();
		
		//Assert.assertEquals(getCSSobject("DEBIT_CARD_ISSUING_BANK_CSS").getText(),"Issuing Bank");
	   	/*
		//printing the bank names  values from the drop down list.
		List<WebElement> allBanks = driver.findElements(By.id("id_issuingBank"));
		
		for (WebElement option : allBanks) {
			APP_LOGS.debug("Bank name values from the drop down list"+option.getText()+",");
			System.out.print(option.getText()+",");
			
		   	}
        */
		
		//card type
		getxpathobject("CREDIT_CARD_EMI_HDFC_CARD_MASTER_XPATH").click();
		
		getnameobject("CREDIT_CARD_EMI_HDFC_CARD_VISA_NAME").click();
		
				
		//enter Debit card no.
		getIDobject("CREDIT_CARD_EMI_HDFC_CARDNO_ID").sendKeys(cardno);
		
					
		//printing the month values from the drop down list.
		/*
		List<WebElement> allOptions = driver.findElements(By.id("id_exp_month"));
		
		for (WebElement option : allOptions) {
			APP_LOGS.debug("month values from the drop down list"+option.getText()+",");
			System.out.print(option.getText()+",");
			
		   	}
        */
		
		//Selecting the month from the drop down. 
		Select selectmonth = new Select(getIDobject("CREDIT_CARD_EMI_HDFC_EXPIRATION_MONTH_ID"));
        selectmonth.selectByVisibleText(EXmonth);
      
      /* 			
       //printing the values from the drop down list 
       List<WebElement> allyear = driver.findElements(By.id("id_exp_year"));
		
		for (WebElement option : allyear) {
			APP_LOGS.debug("year values from the drop down list"+option.getText()+",");
			System.out.print(option.getText()+",");
						}
        */
		//selecting an year from the drop down
        Select selectyear = new Select(getIDobject("CREDIT_CARD_EMI_HDFC_EXPIRATION_YEAR_ID"));
        selectyear.selectByVisibleText(EXyear);
    
        
		//entering the CVV no.
		getIDobject("CREDIT_CARD_EMI_HDFC_CVV_ID").sendKeys(CVV);
	
		
		
	    //Entering the name on the card
		getIDobject("CREDIT_CARD_EMI_HDFC_NAME_ID").sendKeys(name);
	   
		//verifying the Note on the Debit card page.
		APP_LOGS.debug("Verifiying the Note: on the debit card page");
		Assert.assertTrue(driver.getPageSource().contains("Please note that currently we are unable"));
	
		//check and uncheck the checkbox for the shipping details. 
		APP_LOGS.debug("clicking the checkbox");
		getIDobject("shipping_address_checkbox").click();
		APP_LOGS.debug("unchecking the checkbox");
		getIDobject("shipping_address_checkbox").click();
		
		
		
	}
	
	private void icicibank()
	{
        getxpathobject("CREDIT_CARD_EMI_EMI_PLANS_6MONTH_XPATH").click();
		
		getCSSobject("CREDIT_CARD_EMI_EMI_PLANS_9MONTH_CSS").click();
		
		getnameobject("CREDIT_CARD_EMI_EMI_PLANS_3MONTH_NAME").click();
		
	}
	
	

}
