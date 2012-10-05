package com.futurebazaar.suites.AddToCartTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.futurebazaar.common.methods.Itemcleanup;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.BasicCommonTestUtils;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.sun.enterprise.tools.admin.appserver.AppServerForm;

 public class AddToCart_TC5 extends AddToCartSuitebase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteAddToCartXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				APP_LOGS.debug("skipping test AddToCart_TC4 (priceVerification In AddtoCart) as the runmode is NO");
				throw new SkipException("Skipping this testcase AddToCart_TC5 ( Buy Now ,Payback ,EMI link check) as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteAddToCartXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void PaybackCODLinkcheck() throws InterruptedException
		{
			try{
			count++;
			//test the Runmode of the current Dataset
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				APP_LOGS.debug("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			
			
			APP_LOGS.debug("Starting the LinkCheck method of checking payback ,EMI and COD link leading to Cart page");
			driver.findElement(By.id("logo")).click();
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			
			//Opening the Product details Page.
	           //String productname=	driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_LINK_XPATH"))).getText();
	    		driver.findElement(By.xpath(OR.getProperty("HOME_SECOND_PRODUCT_LINK_XPATH"))).click();
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
			 //Verify that COD Is available or not
			boolean  b =driver.getPageSource().contains("This product is eligible for CASH ON DELIVERY");
			//System.out.println("CAsh on dlvry"+b);
			boolean  p =driver.getPageSource().contains("PayBack Points");
		//	System.out.println(p);
			boolean  e =driver.getPageSource().contains("Buy this in 3 EMI's at no extra cost for");	
		//	System.out.println(e);
			if(b)
			{
				//Verifying that COD is available or not
	           APP_LOGS.debug("Verifying that COD is available or not");
			   driver.findElement(By.linkText(OR.getProperty("COD_BUYNOW_XPATH"))).click();
			   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
		       APP_LOGS.debug(" 'This is  cart page'  is verified");
		       driver.navigate().back();
			   
			}
		 if(p)
			{ 
				 //Verfying that PAYBACK is applicable or not
			       APP_LOGS.debug("Verfying that PAYBACK is applicable or not");
				   driver.findElement(By.linkText(OR.getProperty("PAYBACK_REDEEM_XPATH"))).click();
				   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
			       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			       APP_LOGS.debug(" 'This is  cart page'  is verified");
			       driver.navigate().back();
			}
		 if(e)
			{
				 //Verifying that EMI is available or not 
			       APP_LOGS.debug("Verifying that EMI is available or not ");
				   driver.findElement(By.linkText(OR.getProperty("EMI_AVAIL_XPATH"))).click();
				   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
			       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			       APP_LOGS.debug(" 'This is  cart page'  is verified");
			       driver.navigate().back();
				
			}
		  APP_LOGS.debug("****** Completed the Test case AddToCart_TC5 (Buy Now ,Payback ,EMI link check)");
				
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("####### Execution for the AddToCart_TC5 (Buy Now ,Payback ,EMI link check) is falied ######");
				APP_LOGS.error("ERROR"+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;

			}
			
					}
		
		
		
		
		
		@AfterMethod
		public void testdataReporter()
		{//APP_LOGS.debug("value of skip"+count+skip);
		//APP_LOGS.debug("test case fail"+count+fail);
		//APP_LOGS.debug("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteAddToCartXls, "Test Cases", TestUtil.getRowNum(suiteAddToCartXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteAddToCartXls, "Test Cases", TestUtil.getRowNum(suiteAddToCartXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    /*
		@DataProvider
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
		}*/
	
	

}

