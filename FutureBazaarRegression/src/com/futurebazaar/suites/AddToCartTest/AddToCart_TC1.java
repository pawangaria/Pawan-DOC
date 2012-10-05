package com.futurebazaar.suites.AddToCartTest;

import java.util.List;
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
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

 public class AddToCart_TC1  extends AddToCartSuitebase{
	
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
				APP_LOGS.debug("skipping test AddToCart_TC1(quickViewAddToCart) as the runmode is NO");
				throw new SkipException("Skipping this testcase AddToCart_TC1(quickViewAddToCart) as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteAddToCartXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void quickViewAddToCart() throws InterruptedException
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
			//**************************
			//testing code which can be Selenium
			 //Removing the items in the cart by calling the remove item method.
		      APP_LOGS.debug("Item remove method called to remove items from the cart");
		      Itemcleanup.itemCleanup();
		

			//Clicking on about us link
			driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Getting the value of the Product Name and Link from Home Page");
			APP_LOGS.debug("Mouse Hover to the First Product From The Home Page");
			WebElement quickview = driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_IMAGE_XPATH")));
			Actions builder = new Actions(driver);
			builder.moveToElement(quickview).build().perform();
			//builder.clickAndHold(quickview).build().perform();
			APP_LOGS.debug("Clicking on the Quick View From the home Page");
			driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_QUICK_VIEW_XPATH"))).click();
			
			//cart_popup
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("QUICK_VIEW_VERIFICATION_XPATH"))));
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator))
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				
				//System.out.println(driver.getTitle());
				
				}
		    Thread.sleep(3000);
		  //add to cart button
			APP_LOGS.debug("Veriyfing the ADD to CART button on the Quick View on Home Page");
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).isDisplayed(),"Add to Cart button on the quick View pop up is not displayed");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))));
			
			driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).click();
					driver.switchTo().defaultContent();
					
			//clicking on the Futurebazaar.com home page to get the search result page 
			//......................................................
					
		    driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
		    
			//wait for the HOme Page to Load.
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			
			//Searching the Product 
			APP_LOGS.debug("Searching the random Product");
			driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));

			driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
			
			//verification of the search Result Page
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("SEARCH_RESULT_PAGE_VERFICATION_ID"))));
			APP_LOGS.debug("Search Page Opened");
			
			
			//Taking mouse to the product page to see the Quick View Link.
			APP_LOGS.debug("Mouse Hover to the First Product From The Search Result");
			WebElement quickviewSEARCH = driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_IMAGE_XPATH")));
			
			builder.moveToElement(quickviewSEARCH).build().perform();
			//builder.clickAndHold(quickview).build().perform();
			
			APP_LOGS.debug("Clicking on the Quick View From the Search Result Page");
			driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_QUICK_VIEW_XPATH"))).click();
			
			//cart_popup
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SEARCH_QUICK_VIEW_VERIFICATION_XPATH"))));
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator))
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				
				System.out.println(driver.getTitle());
				
				}
		    Thread.sleep(3000);
					//add to cart button
			APP_LOGS.debug("Veriyfing the ADD to CART button on the Quick View on Search Result Page");
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).isDisplayed(),"Add to Cart button on the quick View pop up is not displayed");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))));
			APP_LOGS.debug("Clicking on the BUY NOW button");
			driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).click();
				  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			
			 //Removing the items in the cart by calling the remove item method.
		      APP_LOGS.debug("Item remove method called to remove items from the cart");
		      Itemcleanup.itemCleanup();
		
			    APP_LOGS.debug("*****AddToCart_TC1(quickViewAddToCart) test completed ******* ");
	        
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("######### Execution for the AddToCart_TC1(quick View AddToCart Page) is falied #######");
				
				APP_LOGS.error(t.getMessage());
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
