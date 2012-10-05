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
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;

public class Payment_TC7 extends PaymentSuitebase{
	
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
				APP_LOGS.debug("skipping test Payment_TC7(Cheque page) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC7");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void chequePage() throws InterruptedException
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
		
			APP_LOGS.debug("****Started Testcase for verifying the cheque OPtion(Payment_TC7) ******");
			//this method clicks on the every link on the payment page and verify each link.
			WebElement select = driver.findElement(By.className("payment_options"));
		    List<WebElement> allOptions = select.findElements(By.tagName("li"));
		    for (WebElement option : allOptions) {
		    	
		    	if(option.getText().equalsIgnoreCase("Pay by Cheque/DD"))
		    			{
		    		option.click();
		    		Thread.sleep(2000);
		    	
		      
			     APP_LOGS.debug("verifying the given option and clicking :"+option.getText());
			    
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='payment_mode_container']/p")));
			    Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed"));
		    					    	
		    	//clicking on the click here link for the options
				 Thread.sleep(3000);
		    	 APP_LOGS.debug("clicking on the CLICK HERE link to open frame for Cheque");
		    	    driver.findElement(By.linkText(OR.getProperty("CASH_CLICK_HERE_LINK"))).click();
			        
		    	    WebElement frmname1= driver.findElement(By.id("fancybox-frame"));
		    	    String framename = frmname1.getAttribute("name");
		    	    System.out.println(framename);
		    	    APP_LOGS.debug("Switching to the opened frame for Cheque page");
		    	    driver.switchTo().frame(framename);
		    	    instructionFrameVerif();
			    APP_LOGS.debug("Completed the verifying the Cheque option (Payment_TC7) Frame");
			    
		    			}
		    
		    			}
		    
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("***** Execution for the Payment_TC7 for the Cheque failed ******");
				APP_LOGS.error("ERROR"+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
						
		}
		private void instructionFrameVerif()
		{
			try{
				
			//this method is used to verify the payment option page opened from the payment page option.
			Thread.sleep(2000);
			APP_LOGS.debug("verifying the Payment instruction frame");
			Assert.assertTrue(driver.getPageSource().contains("Paying by Cheque or Demand Draft"));
		   
		     APP_LOGS.debug("closing the frame");
		    driver.switchTo().defaultContent();
		    driver.findElement(By.id(OR.getProperty("CASH_FRAME_CLOSE_ID"))).click();
		    APP_LOGS.debug("Clicked on the Frame close");
		  
		    APP_LOGS.debug("frame successfully closed");
		    
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("failed at the Payment Instruction frame for Cheque from the payment page");
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
