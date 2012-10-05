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

public class MyAccount_TC3 extends MyAccountSuitebase{
	
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
				APP_LOGS.debug("skipping MyAccount_TC3 as the runmode is NO");
				throw new SkipException("Skipping this MyAccount_TC3 as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteMyAccountXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void TrackOrdersCheck() throws InterruptedException
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
			APP_LOGS.debug("********* Starting the Testcase for (MyAccount_TC3)TrackOrder Link for guest user(No logged in user) ******* ");
		    
			APP_LOGS.debug(" Clicking on the TrekOrder Link ");
			driver.findElement(By.linkText(OR.getProperty("TRACK_ORDER_LINK"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("TRACK_ORDER_VERIFICATION_TEXT_XPATH"))));
			Assert.assertTrue(driver.getPageSource().contains("You can track your individual order with your Order number"));
			APP_LOGS.debug("My Track order page is verified");
			APP_LOGS.debug("Entering the Order ID and Email ID in the Text Field");
		    driver.findElement(By.name(OR.getProperty("TRACK_ORDER_INPUT_OREDERno_TEXT_NAME"))).sendKeys(CONFIG.getProperty("TREK_ORDER_test_ORDER_no"));
		    
		    driver.findElement(By.cssSelector(OR.getProperty("TRACK_ORDER_INPUT_EMAil_TEXT_CSS"))).sendKeys(CONFIG.getProperty("testuser"));
			
		    driver.findElement(By.cssSelector(OR.getProperty("TRACK_ORDER_BUTTON_CSS"))).click();
			APP_LOGS.debug("Clicked on the Trek Order Button");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("MY_ACCOUNT_VERIFICATION_TEXT_XPATH"))));
			//System.out.println(driver.findElement(By.xpath(OR.getProperty("MY_ACCOUNT_VERIFICATION_TEXT_XPATH"))).getText());
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("MY_ACCOUNT_VERIFICATION_TEXT_XPATH"))).getText().equalsIgnoreCase("My Account"), "MY Account Page is not displayed After enetring the order ID");
			String orderIDonMYAccount=driver.findElement(By.xpath(OR.getProperty("MY_ACCOUNT_ORDERid_VERIFICATION_TEXT_XPATH"))).getText();
			System.out.println(orderIDonMYAccount);
			Assert.assertTrue(orderIDonMYAccount.contains(CONFIG.getProperty("TREK_ORDER_test_ORDER_no")));
			
			APP_LOGS.debug("Completed the Test case MyAccount_TC3 Track Order Link");
			
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the MyAccount_TC3 (Track Oder Options Check) is falied******");
				APP_LOGS.error("ERROR"+t.getMessage());
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
