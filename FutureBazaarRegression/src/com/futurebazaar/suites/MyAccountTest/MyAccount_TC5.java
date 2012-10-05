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

public class MyAccount_TC5 extends MyAccountSuitebase{
	
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
				APP_LOGS.debug("Skipping MyAccount_TC5(Add to wishlist) as the runmode is NO");
				throw new SkipException("Skipping this MyAccount_TC5(Add to wishlist) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteMyAccountXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void AddtoWishlist() throws InterruptedException
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
			
			
			
			  APP_LOGS.debug("****** Starting the Test case  MyAccount_TC5(Add to wishlist)");
			  Loginlogout.loginByMyAccount(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"));
			  APP_LOGS.debug("User Logged in Successfully ");
			  APP_LOGS.debug("Calling gotoPDP method to go to prodcut page");
			  //calling gotoPDP method to go to Product page
			  GotoPDP();
			
			  System.out.println("This is Check wishlist method");
			  driver.findElement(By.xpath(OR.getProperty("ADD_WISHLIST_LINK_XPATH"))).click();
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("WISHLIST_BLOCK_XAPTH"))));
			  //Clicking on checkbox 
			  APP_LOGS.debug("Clicking(checking) on checkbox ");
			  driver.findElement(By.id(OR.getProperty("WISHLIST_CHECKBOX_ID"))).click();
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("WISHLIST_BLOCK_XAPTH"))));
			  Thread.sleep(3000);
			
			  Assert.assertTrue(driver.getPageSource().contains("public Wishlist"),"The public wishlist is not loaded");
		     // Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("WISHLIST_TWITTER_ICON_XPATH"))).isDisplayed(),"Twitter logo is not displayed in wishlist block");
		     // Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("WISHLIST_FB_ICON_XPATH"))).isDisplayed(),"Facebook logo is not displayed in wishlist block");
			  APP_LOGS.debug("Clicking(unchecking) on checkbox ");
			  driver.findElement(By.id(OR.getProperty("WISHLIST_CHECKBOX_ID"))).click();
			  Thread.sleep(3000);
			  
			  //Clicking on the product image to go to Product page 
			  APP_LOGS.debug("Clicking on the product image to go to Product page ");
			  driver.findElement(By.xpath(OR.getProperty("WISHLIST_PRODUCT_IMAGE_XPATH"))).click();
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
			  driver.navigate().back();
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("WISHLIST_BLOCK_XAPTH"))));
			 
			  
			 //CLICKING ON 'Add TO Cart' button on wish list page
			   APP_LOGS.debug("CLICKING ON 'Add TO Cart' button on wish list page");
			   driver.findElement(By.id(OR.getProperty("WISHLIST_ADDTOCART_PAGE_ID"))).click();
			   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
		      // APP_LOGS.debug(" 'This is  cart page'  is verified");
		       driver.navigate().back();
		       
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("WISHLIST_BLOCK_XAPTH"))));
		       //Clicking on Remove Item on wishlist page
		       APP_LOGS.debug("Clicking on Remove Item on wishlist page");
		       driver.findElement(By.cssSelector(OR.getProperty("WISHLIST_REMOVE_ITEM_XPATH"))).click();
		       Thread.sleep(3000);
		    if(driver.getPageSource().contains("Your Wishlist is empty.") == false)
		    {
		    	driver.findElement(By.cssSelector(OR.getProperty("WISHLIST_REMOVE_ITEM_XPATH"))).click();
		    	Assert.assertTrue(driver.getPageSource().contains("Your Wishlist is empty.") ,"Remove item x link is not working as clicked twice");
		    	
		    }
		    else
		    {
		    	APP_LOGS.debug("Verified page after clicking on 'Remove item x'");
		    	
		    }
		       driver.findElement(By.id("logo")).click();
		   	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
			APP_LOGS.debug("Clicked on user Log out. ");
			driver.findElement(By.id(OR.getProperty("SIGN_OUT_ID"))).click();
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("LOG_OUT_VERIFICATION_TEXT_XPATH"))));
			 APP_LOGS.debug("******Completed the Test case  MyAccount_TC5(Add to wishlist)");
		      	
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the MyAccount_TC5(Add to wishlist) is falied******");
				APP_LOGS.error("ERROR"+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			

			 
		}
			
			public void GotoPDP() throws InterruptedException
			   {   
				    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("TODAY_DEAL_IMG_WAIT_XPATH"))));
					System.out.println("Navigating to PDP by clicking on product image from HP\n");	
					driver.findElement(By.xpath(OR.getProperty("FIRST_PORDUCTIMAGE_HP_XPATH"))).click();
					
					if(driver.getPageSource().contains("OUT OF STOCK")){
						
					driver.navigate().back();
					driver.findElement(By.xpath(OR.getProperty("SECOND_PORDUCT_ITEM_HP_XPATH"))).click();
						
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

			