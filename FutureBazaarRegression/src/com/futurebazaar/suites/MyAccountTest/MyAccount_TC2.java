package com.futurebazaar.suites.MyAccountTest;

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
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class MyAccount_TC2 extends MyAccountSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static WebDriver driver = null;
		
		
		@BeforeTest()
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suiteMyAccountXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteMyAccountXls, "Test Cases",TestUtil.getRowNum(suiteMyAccountXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test case 1 (myAccount Page Link Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase 1 (myAccount Page Link Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteMyAccountXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void myAccountPageLinkCheck() throws InterruptedException
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
			APP_LOGS.debug("********* verifying the myAccount Page all the LinkCheck ******* ");
			//TestBase.getURLOpen();
			
			//user logged in 
			//driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			//driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))).click();
			APP_LOGS.debug("verifying the My Orders tab");
			Thread.sleep(5000);
			APP_LOGS.debug(">>>>>>> My Account Link Check Test Method>>>");
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))));
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))));
			driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))).click();
			APP_LOGS.debug("verifying the My Orders tab");
			
			//Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MY_ORDERS_VERIFICATION_TEXT_XPATH"))));
				Assert.assertTrue(driver.getPageSource().contains("Track your packages and see the history of your orders placed with us"));
				
				APP_LOGS.debug("clicking MY WISHLIST Tab");
				driver.findElement(By.cssSelector(OR.getProperty("MY_WISHLIST"))).click();
				//Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MY_WISHLIST_VERIFICATION_TEXT_XPATH"))));
					Assert.assertTrue(driver.getPageSource().contains("Browse through our products and"));
					APP_LOGS.debug(" *** My Wish List Tab Page verified");
				
				
			
			driver.findElement(By.linkText(OR.getProperty("MY_SUBCRIPTIONS_LINK"))).click();
			APP_LOGS.debug("verifying the My Subscription tab");
			//Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MY_SUBSCRIPTION_VERIFICATION_TEXT_XPATH"))));
				Assert.assertTrue(driver.getPageSource().contains("Send me daily notifications for deals"));
			
			
				driver.findElement(By.xpath(OR.getProperty("MY_PROFILE_XPATH"))).click();
			//Thread.sleep(2000);
				APP_LOGS.debug("verifying the My Profile tab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MY_PROFILE_VERIFICATION_TEXT_XPATH"))));
				Assert.assertTrue(driver.getPageSource().contains("Name"));
			
			
				driver.findElement(By.xpath(OR.getProperty("ADDRESS_BOOK_XPATH"))).click();
			//Thread.sleep(2000);
				APP_LOGS.debug("verifying the My Address Book tab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MY_ADDRESS_VERIFICATION_TEXT_XPATH"))));
							Assert.assertTrue(driver.getPageSource().contains("Manage your Shipping Addresses here"));
						
							driver.findElement(By.xpath(OR.getProperty("MANAGE_PASSWORD_XPATH"))).click();
			//Thread.sleep(2000);
							APP_LOGS.debug("verifying the Password tab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MANAGE_PASSWORD_VERIFICATION_TEXT_XPATH"))));
			Assert.assertTrue(driver.getPageSource().contains("Use the form below to change your password"));
			
			APP_LOGS.debug(" ***Checking the Track Order link");
			//waitForElementPresent(driver,"linkText",FBConsts.TRACK_ORDER);
			//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.linkText(OR.getProperty("TRACK_ORDER_LINK"))).click();
			//Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("TRACK_ORDER_VERIFICATION_TEXT_XPATH"))));
				Assert.assertTrue(driver.getPageSource().contains("You can track your individual order with your Order number"));
				APP_LOGS.debug(" ***My Track order page is verified");
			
			
			APP_LOGS.debug("*****All the Tabs from My account is verified*****");
			 Thread.sleep(3000);
				driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
				APP_LOGS.debug("Clicked on user Log out. ");
				driver.findElement(By.id(OR.getProperty("SIGN_OUT_ID"))).click();
				 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("LOG_OUT_VERIFICATION_TEXT_XPATH"))));
				 APP_LOGS.debug("User Logged out after the My Account Test");
					
			/*WebElement featured_view = getclassobject("MY_ACCOUNT_TAB_CLASS");
			
			List<WebElement> alloptions= featured_view.findElements(By.tagName("a"));
				
			
			for(WebElement options : alloptions){
				System.out.println(options.getText());
				options.click();
				
				if(options.getText().equalsIgnoreCase("My Orders"))
				{
					
					
					try{
						Assert.assertTrue(driver.getPageSource().contains("Track your packages"));
						System.out.println("my order verify");
					}catch(Throwable t)
					{
						t.getCause();
						fail=true;
						return;
					}
				}else if(options.getText().equalsIgnoreCase("My Subscriptions"))
					{
						
						
						try{
							Assert.assertTrue(driver.getPageSource().contains("Send me daily notifications for deals"));
						}catch(Throwable t)
						{
							t.getCause();
							fail=true;
						}
					}else if(options.getText().equalsIgnoreCase("My Profile"))
					{
						
						
						try{
							Assert.assertTrue(driver.getPageSource().contains("Name"));
						}catch(Throwable t)
						{
							t.getCause();
							fail=true;
						}
					}else if(options.getText().equalsIgnoreCase("Address Book"))
					{
						
						
						try{
							Assert.assertTrue(driver.getPageSource().contains("Manage your Shipping Addresses here"));
						}catch(Throwable t)
						{
							t.getCause();
							fail=true;
						}
					}else if(options.getText().equalsIgnoreCase("Manage Password"))
					{
						
						
						try{
							Assert.assertTrue(driver.getPageSource().contains("Use the form below to change your password"));
						}catch(Throwable t)
						{
							t.getCause();
							fail=true;
						}
					}


				
			}
			
			*/
				
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the MyAccount_TC2 (myAccount Page Link Check)is falied******");
				APP_LOGS.error(t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
					}
		
		
		
		
		
		@AfterMethod
		public void testdataReporter()
		{System.out.println("value of skip"+count+skip);
		System.out.println("test case fail"+count+fail);
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
