package com.futurebazaar.suites.PDPtest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.awt.Window;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


public class ProductDescPage_TC7 extends ProductDesPageSuiteBase {
	
	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	//boolean pass=false;
	static boolean istestpass=true;
	//public static int count=0;
	//public static WebDriver driver = null;
	
	
	@BeforeTest
	public void checkTestSkip()
	{
		if(!TestUtil.isTestCaseRunnable(suiteProductDescPageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
			//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
		{   
		    
		    //writing the Skip in the Xls file for the test case
		  
	    	
	    	TestUtil.reportDataSetResult(suiteProductDescPageXls, "Test Cases",TestUtil.getRowNum(suiteProductDescPageXls,this.getClass().getSimpleName()),"Skip");
	    	
			APP_LOGS.debug("Skipping test ProductDescPage_TC6(Review System Check) as the runmode is NO");
			throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : ProductDescPage_TC6 (Review System Check)");
		
		}
		//Load the RunModes of the Test
		runmodes=TestUtil.getDataSetRunmodes(suiteProductDescPageXls, this.getClass().getSimpleName());
		
	}
	
	//@Test(dataProvider="getTestData")
	//data is provided by the XLS files 
	// all the columns values should be passed to the TestCase function as parameters.
	
	//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
	
	@Test(dataProvider="getTestData")
	public void checkDelivey() throws InterruptedException
	{
		try{
		count++;
		//test the Runmode of the current Dataset
		
		if(!runmodes[count].equalsIgnoreCase("Y"))
		{ 
			skip=true;
			//System.out.println("skipping a"+count);
			throw new SkipException("Run mode for the test set data is Set to NO"+count); 
		}
	
		  APP_LOGS.debug("****** Starting the Test case ProductDescPage_TC7 (Check Delivery System Check)");
		  Loginlogout.loginByMyAccount(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"));
		  APP_LOGS.debug("User Logged in Successfully ");
		  APP_LOGS.debug("Opening the home page by clicking the Futurebazaar logo on home Page");
		  driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
		
		//wait for the HOme Page to Load.
		  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
		
		//Opening the Product details Page.
           //String productname=	driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_LINK_XPATH"))).getText();
    		driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_LINK_XPATH"))).click();
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
		    driver.findElement(By.linkText(OR.getProperty("CHECKDELIVERY_LINK"))).click();
		    
		    for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
			}
			//VERIFYING dELIVERY TIME POP UP
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Common_Popup_verfication_link"))));
			  APP_LOGS.debug(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText());
		      Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText().equalsIgnoreCase(OR.getProperty("CHECKDELIVERY_LINK")),"Delivery pop up header is not matched");
		 //   Entering the Invalid pincode and verifying the response
		      APP_LOGS.debug("Entering the valid uncovered pincode and verifying the response");
		     
		     driver.findElement(By.xpath(OR.getProperty("PINCODE_BOX_XPATH"))).sendKeys("395102");
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))));
		     driver.findElement(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))).click();
		     Thread.sleep(3000);
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))));
		     String response = driver.findElement(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))).getText();
		     System.out.println(response);
		     Assert.assertTrue(response.contains("Sorry, we cannot ship this product at your pincode"),"Invalid response for Valid uncovered pincode");
		     //Entering valid pincode and verifying the response
	
		     
		     APP_LOGS.debug("Entering the Valid covered pincode and verifying the response");
		     driver.findElement(By.xpath(OR.getProperty("PINCODE_BOX_XPATH"))).clear();
		     driver.findElement(By.xpath(OR.getProperty("PINCODE_BOX_XPATH"))).sendKeys("395006");
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))));
		     driver.findElement(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))).click();
		     Thread.sleep(3000);
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))));
		    String  response1 = driver.findElement(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))).getText();
	     
		      System.out.println(response1);
		     Assert.assertTrue(response1.contains("Buy this product"),"Invalid response for Valid covered pincode");
		     //Entering Valid uncovered Pincode and verifying the pincode
		     APP_LOGS.debug("Entering Invalid Pincode and verifying the pincode");
		     driver.findElement(By.xpath(OR.getProperty("PINCODE_BOX_XPATH"))).clear();
		     driver.findElement(By.xpath(OR.getProperty("PINCODE_BOX_XPATH"))).sendKeys("12345");
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))));
		     driver.findElement(By.xpath(OR.getProperty("SUBMIT_BUTTON_CHECK_XPATH"))).click();
		     Thread.sleep(3000);
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))));
		     String  response2 = driver.findElement(By.id(OR.getProperty("RESPONSE_MESSAGE_ID"))).getText();
		     System.out.println(response2);
		     Assert.assertTrue(response2.contains("Please enter a valid pincode."),"Invalid response for InValid pincode");
		     
		     driver.switchTo().defaultContent();
			 driver.findElement(By.xpath(OR.getProperty("POPUP_CLOSE_XPATH"))).click();
			
			 APP_LOGS.debug("Delivey Time POP UP is verified");
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
			 
			 

			 
			  APP_LOGS.debug("****** Completed Test case ProductDescPage_TC7 (Check Delivery System Check)");
				
		
		
		
		}

		catch(Throwable t)
		{
			APP_LOGS.debug("*******Execution for the ProductDescPage_TC7(Check Delivery System Check ) is falied*****");
			
			APP_LOGS.error("ERROR :" +t.getMessage());
			ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
		    fail=true;
		    return;
		}
		
}
	
	@AfterMethod
	public void testdataReporter()
	{//System.out.println("value of skip"+count+skip);
	// System.out.println("test case fail"+count+fail);
	//System.out.println("test case a"+count);
		if(skip)
			TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Skip");
		else if(fail)
		{
	    	TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Fail");
	        istestpass=false; //checking for the TestCase is failed or passed
		}
	        else
	       TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Pass");
	   	   	        
		skip=false;
	   	fail=false;
	    
	}
    
	@AfterTest
    //Writing the final result for the TestCase whether it is passed or fail.
    public void testReporter()
    {
    	if(istestpass)
    	{
    		TestUtil.reportDataSetResult(suiteProductDescPageXls, "Test Cases", TestUtil.getRowNum(suiteProductDescPageXls,this.getClass().getSimpleName()), "PASS");
    	}else
    		TestUtil.reportDataSetResult(suiteSearchPageXls, "Test Cases", TestUtil.getRowNum(suiteSearchPageXls,this.getClass().getSimpleName()), "Fail");
    
    	
    	
    }
    
    
	
	//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
    @DataProvider
    public Object[][] getTestData()
	{
	     
		return TestUtil.getData(suiteProductDescPageXls,this.getClass().getSimpleName());
	}


}	
		
		


