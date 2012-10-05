package com.futurebazaar.suites.PaymentPageTest;

import java.awt.Window;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
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
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;

public class Payment_TC9 extends PaymentSuitebase{
	public int noOFbanks;
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
				APP_LOGS.debug("skipping test Payment_TC2 (netbanking) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC2 (netbanking)");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void PayBackCouponCodeCheck() throws InterruptedException
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
		//this method is used to check the Coupen  option on payemnt page.
			
			APP_LOGS.debug("********Starting the testcase for(Payment_TC9) PAYBACK Coupons option");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("COUPONS_VERIFICATION"))));
			Assert.assertTrue(driver.getPageSource().contains("Use a coupon code to redeem deals"), "coupon code text is not displayed on Payemnt page");
			
			Assert.assertTrue(driver.findElement(By.id(OR.getProperty("COUPON_CODE_TEXTFIELD_ID"))).isDisplayed(),"coupon code text field is not displayed");
			
			APP_LOGS.debug("entering the wrong values in coupon code text field");
			
			driver.findElement(By.id(OR.getProperty("COUPON_CODE_TEXTFIELD_ID"))).sendKeys("231234");
			driver.findElement(By.id(OR.getProperty("APPLY_FB_COUPON_ID"))).click();
		   Assert.assertFalse(driver.findElement(By.id(OR.getProperty("ERROR_APPLY_MESSAGE_ID"))).getText().isEmpty() ,"Error message is not displayed for wrong coupon value");
			APP_LOGS.debug("*****Wrong COUPON CODE option is verified");
			APP_LOGS.debug("*****Applying the PAYBACK COUPON CODE");
			driver.findElement(By.id(OR.getProperty("COUPON_CODE_TEXTFIELD_ID"))).clear();
			driver.findElement(By.id(OR.getProperty("COUPON_CODE_TEXTFIELD_ID"))).sendKeys("PAYBACK");
			driver.findElement(By.id(OR.getProperty("APPLY_FB_COUPON_ID"))).click();
			if(driver.findElement(By.id(OR.getProperty("ERROR_APPLY_MESSAGE_ID"))).getText().equalsIgnoreCase("Oops! The coupon is not applicable on this purchase"))
			{
				APP_LOGS.debug(" PAY back Coupon can not be applyed to this Product,So message is displayed");
				
			}
			else
			{
		   Assert.assertFalse(driver.findElement(By.id(OR.getProperty("ERROR_APPLY_MESSAGE_ID"))).getText().isEmpty() ,"Coupon Applied");
		   APP_LOGS.debug("*****Verifying the Pay By PAY back option Opened");
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PAYBACK_PAGE_VERIFICATION_TEXT_XPATH"))));
		   Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PAYBACK_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("PAYBACK (imint) is a loyalty program"));
			
		   System.out.println(driver.findElement(By.cssSelector(OR.getProperty("REMOVE_PAYBACK_button_CSS"))).getText());
		   Assert.assertTrue(driver.findElement(By.cssSelector(OR.getProperty("REMOVE_PAYBACK_button_CSS"))).getText().contains("Remove coupon x"));
		
		   APP_LOGS.debug("Verifyed the Payback Applied and Clicking on the Remove coupons Link");
			driver.findElement(By.cssSelector(OR.getProperty("REMOVE_PAYBACK_button_CSS"))).click();
			Assert.assertFalse(driver.getPageSource().contains("Remove coupon x"),"Payback Coupon is not removed Successfully");
			}
			   APP_LOGS.debug("Verifyed the Payback Applied and Clicking on the Remove coupons Link");
			   APP_LOGS.debug("*******Completed Execution for the Payment_TC9(PAY back Coupon Code)*****");
				
		   				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the Payment_TC9(for PAY back Coupon Code) is falied*****");
				APP_LOGS.error("ERROR"+ t.getMessage());
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
