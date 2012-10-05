package com.futurebazaar.common.methods;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import com.futurebazaar.base.TestBase;
import com.sun.enterprise.util.Print;
public class DebitCard extends TestBase{
	
	/*
	private boolean verifyTestvalues(String bank,String cardno,String EXmonth,String EXyear,String CVV,String name)
	{
		System.out.println("in cerify method");
	    try{
		WebElement bankname1 =driver.findElement(By.id("DEBIT_CARD_BANK_ID"));
		String getbankname =bankname1.getAttribute("value");
		System.out.println(getbankname);
		Assert.assertEquals(getbankname,bank);
		
		//getting Debit card no.
		WebElement cardno1 =driver.findElement(By.id("DEBIT_CARD_CARDNO_ID"));
		String getcardno=cardno1.getAttribute("value");
		System.out.println(getcardno);
		Assert.assertEquals(getcardno,cardno);
        
		//getting Expiry month.
		WebElement month1 =driver.findElement(By.id("DEBIT_CARD_EXPIRATION_MONTH_ID"));
		String getmonth = month1.getAttribute("value");
	System.out.println(getmonth);
		Assert.assertEquals(getmonth,EXmonth);
		
		// getting expiry year.
		WebElement year1 =driver.findElement(By.id("DEBIT_CARD_EXPIRATION_YEAR_ID"));
		String getyear=year1.getAttribute("value");
		Assert.assertEquals(getyear,EXyear);
		
		//getting the no entered by the text. 
		WebElement cvv1 =driver.findElement(By.id("DEBIT_CARD_CVV_ID"));
		String getcvv=cvv1.getAttribute("value");
		Assert.assertEquals(getcvv,CVV);
		
		//getting the name on the card.
		WebElement cardname1 =driver.findElement(By.id("DEBIT_CARD_NAME_ID"));
		String getcardname=cardname1.getAttribute("value");
	    }catch(Throwable t)
	    {
	    	
	    	t.getMessage();
	    	return false;
	    }
		
		return true;		
		
	}
	
	*/
	public void debitCardVerification(String bank,String cardno,String EXmonth,String EXyear,String CVV,String name)
	{   
		
       //this method is used to verify the Debit card page option 
		APP_LOGS.debug("opening the Debit Card option");
	    getxpathobject("PAYMENT_TYPE_DEBITCARD_XPATH").click();
	    
	    driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	    try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    APP_LOGS.debug("verifying the Debit card option");
		try
		{   APP_LOGS.debug("verifying the Debit card Txt");
			Assert.assertEquals(getCSSobject("DEBIT_CARD_ISSUING_BANK_CSS").getText(),"Issuing Bank");
		   	/*
			//printing the bank names  values from the drop down list.
			List<WebElement> allBanks = driver.findElements(By.id("id_issuingBank"));
			
			for (WebElement option : allBanks) {
				APP_LOGS.debug("Bank name values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			   	}
            */
			//Selecting the BANK from the drop down. 
			Select selectbank = new Select(getIDobject("DEBIT_CARD_BANK_ID"));
            selectbank.selectByVisibleText(bank);
			
			//enter Debit card no.
			getIDobject("DEBIT_CARD_CARDNO_ID").sendKeys(cardno);
			
						
			//printing the month values from the drop down list.
			/*
			List<WebElement> allOptions = driver.findElements(By.id("id_exp_month"));
			
			for (WebElement option : allOptions) {
				APP_LOGS.debug("month values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			   	}
            */
			
			//Selecting the month from the drop down. 
			Select selectmonth = new Select(getIDobject("DEBIT_CARD_EXPIRATION_MONTH_ID"));
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
            Select selectyear = new Select(getIDobject("DEBIT_CARD_EXPIRATION_YEAR_ID"));
            selectyear.selectByVisibleText(EXyear);
        
            
			//entering the CVV no.
			getIDobject("DEBIT_CARD_CVV_ID").sendKeys(CVV);
		
			
			
		    //Entering the name on the card
			getIDobject("DEBIT_CARD_NAME_ID").sendKeys(name);
		   
			//verifying the Note on the Debit card page.
			APP_LOGS.debug("Verifiying the Note: on the debit card page");
			Assert.assertTrue(driver.getPageSource().contains("Please note that currently we are unable"));
		
			/*
			//verifying the details entered by the test.
			APP_LOGS.debug("verifying the values entered in the fields ");
			if(verifyTestvalues(bank,cardno,EXmonth,EXyear,CVV,name))
			{
				APP_LOGS.debug("the given values verified ");
			}else
			{
				APP_LOGS.debug("the given values provided are failed ");
			}
			
			*/
			//
			
            		
			
			
		}catch(Throwable t)
		{   APP_LOGS.debug("Script failed at the time of entering the details");
			APP_LOGS.debug(t.getMessage());
			APP_LOGS.debug(new Exception(t.getMessage()));
		}
	    
	}
	

}
