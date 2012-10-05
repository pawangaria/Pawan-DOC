package com.futurebazaar.suites.MyAccountTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class MyAccount_TC1 extends MyAccountSuitebase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteMyAccountXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteMyAccountXls, "Test Cases",TestUtil.getRowNum(suiteMyAccountXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test case A as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteMyAccountXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void accountlinkOptionsCheck() throws InterruptedException
		{
			//This method is used to check all the links in the my account on home page So  checking all options by clicking.
			try{
			count++;
			//test the Runmode of the current Dataset if present.
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				System.out.println("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//testing code which can be Selenium
			APP_LOGS.debug("*********  In the Account link Suite ******* ");
			//TestBase.getURLOpen();
			Loginlogout.loginByMyAccount(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"));
			//user logged in 
			APP_LOGS.debug("Verifying the My orders Link");
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))).click();
			Thread.sleep(3000);
				Assert.assertTrue(driver.getPageSource().contains("Track your packages and see the history of your orders placed with us"));
			
				APP_LOGS.debug("Verifying the My Subscription Link");
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_SUBSCRIPTION_LINK"))).click();
			Thread.sleep(3000);
				Assert.assertTrue(driver.getPageSource().contains("Send me daily notifications for deals"));
			
				APP_LOGS.debug("Verifying the My profile Link");
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_PROFILE_LINK"))).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.getPageSource().contains("Name"));
			
			APP_LOGS.debug("Verifying the My Address Book Link");
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ADDRESSBOOK_LINK"))).click();
			Thread.sleep(3000);
				Assert.assertTrue(driver.getPageSource().contains("Manage your Shipping Addresses here"));
			
				APP_LOGS.debug("Verifying the Manage Password Link");
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_MANAGEPASSWORD_LINK"))).click();
			Thread.sleep(3000);
				Assert.assertTrue(driver.getPageSource().contains("Use the form below to change your password"));
				APP_LOGS.debug(" Test Case complete for the My Account Links");
			
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the Test case 1 (account link Options Check) is falied******");
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
				TestUtil.reportDataSetResult(suiteMyAccountXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteMyAccountXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteMyAccountXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteMyAccountXls, "Test Cases", TestUtil.getRowNum(suiteMyAccountXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteMyAccountXls, "Test Cases", TestUtil.getRowNum(suiteMyAccountXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    /*
		@DataProvider
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
		}*/
	
	

}
