package com.futurebazaar.suites.PaymentPageTest;

import java.awt.Window;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class Payment_TC3 extends PaymentSuitebase{
	
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
				TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases",TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test Payment_TC3 (creditcardEMI) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC3 (creditcardEMI)");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void creditcardEMI(String cardno,String EXmonth,String EXyear,String CVV,String name) throws InterruptedException
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
		
			//this is the method for the CREDIT Card EMI option verification
						
			APP_LOGS.debug("****** Starting the Test case Payment_TC3 for PAY by EMI option");
			
			Thread.sleep(2000);
			   WebElement select = driver.findElement(By.className("payment_options"));
			    List<WebElement> allOptions = select.findElements(By.tagName("li"));
			    for (WebElement option : allOptions) {
			    	   	
			    	 if(option.getText().equalsIgnoreCase("Pay in EMI"))
					    {
			    	
			        option.click();
				    APP_LOGS.debug("Clicked on the given payment option :"+ option.getText());
				    Thread.sleep(3000);
				    APP_LOGS.debug("Verifying the NOTE Present on the EMI page");
					
				    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("EMI_PAGE_NOTE_VERIFICATION_XPATH"))));
				  Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("EMI_PAGE_NOTE_VERIFICATION_XPATH"))).isDisplayed(), "NOTE on the EMI page is not displayed");
				    //clicking on the HDFC bank option  
					driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_HDFC_xpath"))).click();
					hdfcbank(cardno,EXmonth,EXyear,CVV,name);//calling the HDFC verification method.
					
					 APP_LOGS.debug("clicking on HSBC bank on the EMI page");
						
				    //clicking on HSBC bank.
				    driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_HSBC_XPATH"))).click();
					 
				    APP_LOGS.debug("clicking on ICICI bank on the EMI page");
					 //clicking on ICICI bank.
				    driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_ICICI_XPATH"))).click();
					
				    //clicking on KOTAK bank.
					 APP_LOGS.debug("clicking on KOTAK bank on the EMI page");
						
				    driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_KOTAK_XPATH"))).click();
					
				    //clicking on SBI bank.
					 APP_LOGS.debug("clicking on SBI bank on the EMI page");
						
				    driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_SBI_XPATH"))).click();
					
				    //clicking on AXIS bank.
					 APP_LOGS.debug("clicking on AXIS bank on the EMI page");
						
				    driver.findElement(By.name(OR.getProperty("CREDIT_CARD_EMI_AXIS_name"))).click();
								
			
			 //File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    // Now you can do whatever you need to do with it, for example copy somewhere
			   // FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));

			APP_LOGS.debug("*****Completed the Test Case Payment_TC3(Pay By EMI option)******* ");     
			//***********************************************
					    }
			    }
			}catch(Throwable t)
			{
				APP_LOGS.debug("Execution of the  Payment_TC3(Pay By EMI option) Failed");
				APP_LOGS.error(t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		
		private void hdfcbank(String cardno,String EXmonth,String EXyear,String CVV,String name)
		{
			try {
				Thread.sleep(2000);
				 APP_LOGS.debug("Verifying the options for the  HDFC bank for EMI page");
					
			driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_6MONTH_XPATH"))).click();
			
			driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_9MONTH_XPATH"))).click();
			
			driver.findElement(By.name(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_3MONTH_NAME"))).click();
			
			//Assert.assertEquals(getCSSobject("DEBIT_CARD_ISSUING_BANK_CSS").getText(),"Issuing Bank");
		   	/*
			//printing the bank names  values from the drop down list.
			List<WebElement> allBanks = driver.findElements(By.id("id_issuingBank"));
			
			for (WebElement option : allBanks) {
				APP_LOGS.debug("Bank name values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			   	}
	        */
			
		
					
			//enter Debit card no.
			driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_CARDNO_ID"))).clear();
			driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_CARDNO_ID"))).sendKeys(cardno);
			
						
			//printing the month values from the drop down list.
			/*
			List<WebElement> allOptions = driver.findElements(By.id("id_exp_month"));
			
			for (WebElement option : allOptions) {
				APP_LOGS.debug("month values from the drop down list"+option.getText()+",");
				System.out.print(option.getText()+",");
				
			   	}
	        */
			
			//Selecting the month from the drop down. 
			Select selectmonth = new Select(driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_EXPIRATION_MONTH_ID"))));
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
	        Select selectyear = new Select(driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_EXPIRATION_YEAR_ID"))));
	        selectyear.selectByVisibleText(EXyear);
	    
	        
			//entering the CVV no.
	    	driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_CVV_ID"))).clear();
			driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_CVV_ID"))).sendKeys(CVV);
		
			
			
		    //Entering the name on the card

			driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_NAME_ID"))).clear();
		   
			driver.findElement(By.id(OR.getProperty("CREDIT_CARD_EMI_HDFC_NAME_ID"))).sendKeys(name);
		   
			
			APP_LOGS.debug("*****Veryfied the EMI option for the HDFC Bank******* ");
			}catch(Throwable t)
			{
				APP_LOGS.debug("Payment_TC3 falied for the HDFC bank option");
				APP_LOGS.error("ERROR "+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		
		private void icicibank()
		{   try{
	        driver.findElement(By.xpath(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_6MONTH_XPATH"))).click();
			
	        driver.findElement(By.cssSelector(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_9MONTH_CSS"))).click();
			
	        driver.findElement(By.name(OR.getProperty("CREDIT_CARD_EMI_EMI_PLANS_3MONTH_NAME"))).click();
			APP_LOGS.debug("*****Credit card EMI test completed for the ICICI Bank******* ");
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Payment_TC3 falied for the ICICI bank option");
			APP_LOGS.error(t.getMessage());
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
