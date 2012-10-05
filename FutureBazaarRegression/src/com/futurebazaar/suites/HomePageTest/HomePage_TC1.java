package com.futurebazaar.suites.HomePageTest;

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
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;

public class HomePage_TC1 extends HomePageSuitebase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteHomePageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{   
			    
			    //writing the Skip in the Xls file for the test case
			  
		    	
		    	TestUtil.reportDataSetResult(suiteHomePageXls, "Test Cases",TestUtil.getRowNum(suiteHomePageXls,this.getClass().getSimpleName()),"Skip");
		    	
				APP_LOGS.debug("skipping test HomePage_TC1 as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC2");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteHomePageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void todaysDeals() throws InterruptedException
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
		
			//***********************************************
			//this method is used to verify the HOme page option 
			
				APP_LOGS.debug("***** Starting the test case HomePage_TC1 (Todays Deals)Home Page Test Case 1");
				
				APP_LOGS.debug("Verifying the Todays Deals on HOme page");
				WebElement ww = driver.findElement(By.className("home_header"));
				String Todays_Deals_String= ww.findElement(By.tagName("h1")).getText();
				//System.out.println(Todays_Deals_String);
				Assert.assertEquals(Todays_Deals_String,"TODAY'S DEALS");
			// BOX PATH	.//*[@id='home']/div[2]/div[2]/ul/li[1]
			// PRODUCT LINK	.//*[@id='home']/div[2]/div[2]/ul/li[1]/div[1]/h3/a
		    // PRICE AND ADD TO CART .//*[@id='home']/div[2]/div[2]/ul/li[1]/div[1]/div[2]	
			//	.//*[@id='home']/div[2]/div[2]/ul/li[1]/div[2]/a
				APP_LOGS.debug("Getting No of items for Todays Deals From Property File");
					String no_of_item = OR.getProperty("TODAY'SDEALS_NO_OF_ITEM");
				     int no = Integer.parseInt(no_of_item);
				     //System.out.println("The no is:"+no);
				  for(int i=1;i<=no;i++)
				  {  
				   
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
					String boxxpath= "//*[@id='home']/div[2]/div[2]/ul/li["+i+"]";
					String productlink= "//*[@id='home']/div[2]/div[2]/ul/li["+i+"]/div[1]/h3/a";
					String pricesAndbuynow = "//*[@id='home']/div[2]/div[2]/ul/li["+i+"]/div[1]/div[2]";
					
					APP_LOGS.debug("Checking for product no:"+i);
					checklink(boxxpath);
				  }
		
					APP_LOGS.debug("***** Execution of test case HomePage_TC1 (Todays Deals) Completed");
					
		 
				//***********************************************
			     }catch(Throwable t)
			      {
				   APP_LOGS.debug("*******Execution for the HomePage_TC1 (Todays Deals) is falied*****");
				   t.getMessage();
				   ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			       fail=true;
			       return;
			      }
			
			
		         }
		
              public void  checklink(String boxxpath)
              {
           	    String accessorylink=boxxpath+"/div[2]/a"; 
           	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	  
           	    Assert.assertTrue(driver.findElement(By.xpath(boxxpath)).isDisplayed(),"Image Box is not dispalyed");
           	    //System.out.println("coming to this");
                String linktext =driver.findElement(By.xpath(accessorylink)).getText();
                APP_LOGS.debug("product link is:"+linktext);
               
           	      driver.findElement(By.xpath(accessorylink)).click();	  
                  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("SEARCH_BRAND_TEXT_VERIFICATION_XPATH")))).isDisplayed();
                  String breadcumtext = driver.findElement(By.xpath(OR.getProperty("SEARCH_BRAND_TEXT_VERIFICATION_XPATH"))).getText();
                  breadcumtext =  breadcumtext.substring(0, breadcumtext.length()/2);
        	      driver.findElement(By.id("logo")).click();
        	      APP_LOGS.debug("Breadcumb link is:"+breadcumtext);  
               	  Assert.assertTrue(linktext.contains(breadcumtext),"The linktext doesn't match with breadcumb text");
               	APP_LOGS.debug("Verified");
              }


		
		
	
		@AfterMethod
		public void testdataReporter()
		{//System.out.println("value of skip"+count+skip);
		//System.out.println("test case fail"+count+fail);
		//System.out.println("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suiteHomePageXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteHomePageXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteHomePageXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	   
		@AfterTest
	    //Writing the final result for the TestCase whether it is passed or fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteHomePageXls, "Test Cases", TestUtil.getRowNum(suiteHomePageXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteHomePageXls, "Test Cases", TestUtil.getRowNum(suiteHomePageXls,this.getClass().getSimpleName()), "Fail");
	    
	    	
	    	
	    }
	    
	    
		
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
	    @DataProvider
	    public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteHomePageXls,this.getClass().getSimpleName());
		}
	
	

}
