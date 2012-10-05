package com.futurebazaar.suites.PaymentPageTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class Payment_TC1 extends PaymentSuitebase{
	
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
				APP_LOGS.debug("skipping test case A as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void paymentoptions() throws InterruptedException
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
			
			APP_LOGS.debug("entering the test case A1");
			Productpurchase.purchaseItem(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"),CONFIG.getProperty("SearchItem"));
			//PaymentPage.paymentPageOptionsVerification();
			//*********************************************************
			//this method clicks on the every link on the payment page and verify each link.
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("PAYMENT_PAGE_VERIFICATION_XPATH"))));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(OR.getProperty("PAYMENt_MAKE_PAYMENT_BUTTON_ID"))));
			Assert.assertTrue(driver.findElement(By.id(OR.getProperty("PAYMENt_MAKE_PAYMENT_BUTTON_ID"))).isDisplayed(), "Make payment button is not displayed on the Payment page");
			WebElement select = driver.findElement(By.className("payment_options"));
		    List<WebElement> allOptions = select.findElements(By.tagName("li"));
		    for (WebElement option : allOptions) {
		    	   	
		    	
		    	APP_LOGS.debug("verifying the given option and clicking :"+option.getText());
		        option.click();
		        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PAYMENT_PAGE_AFTERCLICK_VERIFICATION_XPATH"))));
			    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
			    Thread.sleep(3000);
			    if(option.getText().equalsIgnoreCase("Pay by Net Banking"))
			    {
			    	Thread.sleep(3000);
			    	APP_LOGS.debug("Verifying the Payment option : "+option.getText());
			       Assert.assertTrue(driver.getPageSource().contains("We will redirect you to the bank or payment partner site for your payment."));
			    	APP_LOGS.debug(option.getText()+"Option verified");
			    	
			   
			    }else if(option.getText().equalsIgnoreCase("Pay by Credit Card"))
			    {
			    	Thread.sleep(3000);
				    	APP_LOGS.debug("Verifying the Payment option : "+option.getText());
				       Assert.assertTrue(driver.getPageSource().contains("Card Type"));
				       Assert.assertTrue(driver.getPageSource().contains("Card Number"));
				       Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
				       Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
				       Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card"));
				       APP_LOGS.debug(option.getText()+"Option verified");
				    	
			    
		}else if(option.getText().equalsIgnoreCase("Pay using Cash Drop"))
		{
			Thread.sleep(3000);
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
				//Assert.assertTrue(driver.getPageSource().contains("Select a partner that has a location near you."));
				System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText());
				Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("learn How to Pay by Cash & to find a nearest Cash payment location"));	
			
				APP_LOGS.debug(option.getText()+"Option verified");
				
				
		
		
		}else if(option.getText().equalsIgnoreCase("Pay by Debit Card"))
		{
			Thread.sleep(3000);
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
				Assert.assertTrue(driver.getPageSource().contains("Issuing Bank"));
				Assert.assertTrue(driver.getPageSource().contains("Card Number"));
				Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
				Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
				Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card "));
				APP_LOGS.debug(option.getText()+"Option verified");
				
			
		}else if(option.getText().equalsIgnoreCase("Pay in EMI"))
		{
			Thread.sleep(3000);
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("EMI_PAGE_NOTE_VERIFICATION_XPATH"))));
				  Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("EMI_PAGE_NOTE_VERIFICATION_XPATH"))).isDisplayed(), "NOTE on the EMI page is not displayed");
			
				Assert.assertTrue(driver.getPageSource().contains("Select your bank"));
				Assert.assertTrue(driver.getPageSource().contains("Select EMIs"));
				APP_LOGS.debug(option.getText()+"Option verified");
				
				
			
		}else if(option.getText().equalsIgnoreCase("Pay by Cheque/DD"))
		{
			Thread.sleep(3000);
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
				//Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed."));
				
			       Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("You can make a Cheque/DD payment for any order placed."));
			    
				APP_LOGS.debug(option.getText()+"Option verified");
				
				
			
		}else if(option.getText().equalsIgnoreCase("Pay by COD"))
		{Thread.sleep(3000);
			
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
				//Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("CASH_ON_DELIVERY_VERIFY_TEXT"))).getText().contains("In order to verify the Cash on Delivery Payment, please enter a valid contact number."));	

				System.out.println(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText());
				Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText().contains("We need your mobile number to verify your COD order."));	
			
				APP_LOGS.debug(option.getText()+"Option verified");
				
				
			
		}else if(option.getText().equalsIgnoreCase("Pay by Payback"))
		{
			Thread.sleep(3000);
				APP_LOGS.debug("Verifying the Payment option :"+option.getText());
				//Assert.assertTrue(driver.getPageSource().contains("Redeem your i-mint point."));
				System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/ul/li[1]")).getText());
				Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/ul/li[1]")).getText().contains("PAYBACK (imint) is a loyalty program"));
				
				APP_LOGS.debug(option.getText()+"Option verified");
				
				
			
		}

			    APP_LOGS.debug("*****Completed the (Payment_TC1) All Payment options Testcase ******* ");
	        
		    }	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the Payment_TC1(All Payment option Check) is falied******");
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
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    /*
		@DataProvider
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
		}*/
	
	

}
