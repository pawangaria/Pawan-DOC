package com.futurebazaar.suites.PaymentPageTest;

import java.awt.Window;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.CreditCard;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;

public class Payment_TC2 extends PaymentSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		
		//public static WebDriver driver = null;
		
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suitePaymentPageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{   
			    
			    //writing the Skip in the Xls file for the test case
			  
		    	
		    	TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases",TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()),"Skip");
		    	
				APP_LOGS.debug("skipping test Payment_TC2 (creditcard) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC2 (creditcard)");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void creditcard(String cardno,String EXmonth,String EXyear,String CVV,String name) throws InterruptedException
		{
			try{
			count++;
			//test the Runmode of the current Dataset
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				System.out.println("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//testing code which can be Selenium
			//***********************************************
			//this method is used to verify the Credit card page option 
			APP_LOGS.debug("****** Starting the Test case Payment_TC2 for Credit card option");
			APP_LOGS.debug("opening the credit card option");
		    
		     WebElement select = driver.findElement(By.className("payment_options"));
		    List<WebElement> allOptions = select.findElements(By.tagName("li"));
		    for (WebElement option : allOptions) {
		    	   	
		    	 if(option.getText().equalsIgnoreCase("Pay by Credit Card"))
				    {
		    	
		        option.click();
			    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
			    Thread.sleep(3000);
			    
		    APP_LOGS.debug("Verifying the Credit card option");
			
			   APP_LOGS.debug("Verifying the Credit card Txt");
				Assert.assertEquals(driver.findElement(By.cssSelector(OR.getProperty("CREDIT_CARD_CSS"))).getText(),"Card Type");
			    APP_LOGS.debug("Clicking on the Master card option ");
				driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_CARDTYPE_MASTERCARD_XPATH"))).click();
				//APP_LOGS.debug("Clicking on the VISA card option ");
				//driver.findElement(By.name(OR.getProperty("CREDIT_CARD_CARDTYPE_VISA_name"))).click();
				
				driver.findElement(By.id(OR.getProperty("CREDIT_CARD_NUMBER_ID"))).clear();
				driver.findElement(By.id(OR.getProperty("CREDIT_CARD_NUMBER_ID"))).sendKeys(cardno);
				
							
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
				 APP_LOGS.debug("Entering the Credit card Month");
				
				Select selectmonth = new Select(driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EXPIRATION_MONTH_ID"))));
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
	            APP_LOGS.debug("Entering the Year");
	            Select selectyear = new Select(driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EXPIRATION_YEAR_ID"))));
	            selectyear.selectByVisibleText(EXyear);
	        
	            
				//entering the CVV no.
	            APP_LOGS.debug("Entering the Credit Card CVV NO.");
	            driver.findElement(By.id(OR.getProperty("CREDIT_CARD_CVV_ID"))).clear();
	            driver.findElement(By.id(OR.getProperty("CREDIT_CARD_CVV_ID"))).sendKeys(CVV);
			
				
				
			    //Entering the name on the card
	            APP_LOGS.debug("Entering the Name");
	            driver.findElement(By.id(OR.getProperty("CREDIT_CARD_NAME_ID"))).clear();
	            driver.findElement(By.id(OR.getProperty("CREDIT_CARD_NAME_ID"))).sendKeys(name);
			   
				//verifying the shipping details page.
	            APP_LOGS.debug("Verifying the Please Enter Billing Information Text: is present for shipping");
				Assert.assertEquals(driver.findElement(By.cssSelector(OR.getProperty("PAYMENT_TYPE_BILLINGINFO_CSS"))).getText(),"Please Enter Billing Information");
				
				
				//check and uncheck the checkbox for the shipping details. 
				APP_LOGS.debug("clicking the Shipping checkbox");
				driver.findElement(By.id(OR.getProperty("CREDIT_CARD_SHIPPING_ADD_CHECKBOX_ID"))).click();
				APP_LOGS.debug("unchecking the Shiping checkbox");
				driver.findElement(By.id(OR.getProperty("CREDIT_CARD_SHIPPING_ADD_CHECKBOX_ID"))).click();
				
				APP_LOGS.debug("*****Completed Payment_TC2 (Credit card) test completed******* ");
				//***********************************************
				    }
		    }
				    }catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the Payment_TC2 (creditcard) is falied*****");
				APP_LOGS.error("ERROR "+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		
		
		
		
		@AfterMethod
		public void testdataReporter()
		{//System.out.println("value of skip"+count+skip);
		//System.out.println("test case fail"+count+fail);
		//System.out.println("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed or fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "Fail");
	    
	    	
	    	
	    }
	    
	    
		
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
	    @DataProvider
	    public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suitePaymentPageXls,this.getClass().getSimpleName());
		}
	
	

}
