package com.futurebazaar.suites.SearchResultPageTest;


	

import org.testng.annotations.Test;

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


public class SearchResultPage_TC2  extends SearchResultSuiteBase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static int count=0;
	    public static int box=1;
		
		//public static WebDriver driver = null;
		
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suiteSearchPageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{   
			    
			    //writing the Skip in the Xls file for the test case
			  
		    	
		    	TestUtil.reportDataSetResult(suiteSearchPageXls, "Test Cases",TestUtil.getRowNum(suiteHomePageXls,this.getClass().getSimpleName()),"Skip");
		    	
				APP_LOGS.debug("skipping test SearchResultPage_TC2 as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : SearchResultPage_TC2");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteSearchPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void searchResultLinkCheck() throws InterruptedException
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
			APP_LOGS.debug("****** Starting the  test case SearchResultPage_TC2 (searchResult Link Check)");
		    
			//***********************************************
			//this method is used to verify the HOme page option 
            
			 APP_LOGS.debug("Entering Keyword in search Box*");
			 driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).clear();
			 driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));
			 driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
			 APP_LOGS.debug(" Search Button is clicked");
	         APP_LOGS.debug("Verify that page is 'Search result page");
		     String searchresult_link =driver.findElement(By.id(OR.getProperty("SEARCH_RESULT_PAGE_VERFICATION_ID"))).getText();
		     Assert.assertEquals(searchresult_link, "Home Search Results");
			 APP_LOGS.debug("This is search page");
	         
		   //Verifying that searched keyword is same as entered in Search box	 
			 APP_LOGS.debug("*Verifying that searched keyword is same as entered in search box*t ");
			 String searched_kyword=  driver.findElement(By.cssSelector(OR.getProperty("SEARCHED_KEYWORD"))).getText();
			 APP_LOGS.debug(searched_kyword);
			 String search_kyword = CONFIG.getProperty("SearchItem");
			 Assert.assertTrue(searched_kyword.contains(search_kyword),"Key word does not match with the search Keyword");
			 APP_LOGS.debug("The above verification is done\t");
		
		  //Getting the Search Item number	
	    	 String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
		     APP_LOGS.debug("The Searched Item number is:"+Prevno+"\t");
		  
	     //Clicking  on discount link 
		 	APP_LOGS.debug("Clicking on discount link*");
		 	driver.findElement(By.linkText(OR.getProperty("DISCOUNT_LINK"))).click();
		 	Assert.assertTrue( driver.findElement(By.linkText(OR.getProperty("DISCOUNT_LINK"))).isEnabled());
		 	APP_LOGS.debug("Problem:Discount Link remains enabled");
		 	APP_LOGS.debug("Checking that search items remains same");
		 	String Afterno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
		 	APP_LOGS.debug("The search number after clicking is :" + Afterno+"\t");
		 	Assert.assertEquals(Prevno, Afterno,"The items doesn't remain same after clicking discount");  
		    
		 //Clicking on Price link
	     	APP_LOGS.debug("Clicking on Price link**");
	        driver.findElement(By.linkText(OR.getProperty("PRICE_LINK"))).click();
	        Assert.assertTrue( driver.findElement(By.linkText(OR.getProperty("PRICE_LINK"))).isEnabled());
	        APP_LOGS.debug("Problem:Price Link remains enabled");
			APP_LOGS.debug("Checking that search items remains same");
			Afterno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
			APP_LOGS.debug("The search number after clicking is :" + Afterno+"\t");
		    Assert.assertEquals(Prevno, Afterno,"The items remain not same after clicking price");
			
		  //Clicking on Popular link  
		    APP_LOGS.debug("*Clicking on Popular link*");
	        driver.findElement(By.linkText(OR.getProperty("POP_LINK"))).click();
		    Assert.assertTrue( driver.findElement(By.linkText(OR.getProperty("POP_LINK"))).isEnabled());
			APP_LOGS.debug("Problem:Popular Link remains enabled");
			APP_LOGS.debug("Checking that search items remains same");
			Afterno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
			APP_LOGS.debug("The search number after clicking is :" + Afterno+"\t");
			Assert.assertEquals(Prevno, Afterno,"The items doesn't remains same after clicking Popular");
		  
		 
		   APP_LOGS.debug("All is successful");
		  
		/*   APP_LOGS.debug("checking previous link");
		   WebElement aa1 =driver.findElement(By.className("pagination left"));
		   Assert.assertFalse(aa1.findElement(By.tagName("a")).isEnabled(),"The Pre link is enabled ");
		   */
		   
		   
		   
		  // APP_LOGS.debug("Now Chcecking Previous link\n");
		   
		   //Checking previous and next link
		/*   APP_LOGS.debug(driver.findElement(By.cssSelector(or.getProperty("PREVIOUS_LINK"))).isSelected());
		  
		   
		   Assert.assertFalse(driver.findElement(By.cssSelector(or.getProperty("PREVIOUS_LINK"))).isSelected(),"Previous link is not disable");
		   Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("NEXT_LINK"))).isSelected(),"NEXT link is not enable");
	       */
		  
		   
		   String no_item =Prevno;
	       
	       String Number_string[] = no_item.split(" ");
	       Integer No_item= Integer.valueOf(Number_string[0]);
	        
	       APP_LOGS.debug("The value of NO_item is :"+ No_item+"\n");
	       int mx_pageno;
	       if(No_item % 20 == 0)
	       {
	    	  mx_pageno =(int)No_item/20;
	       }
	       else
	       {
	    	 mx_pageno =(((int)No_item/20)+1);
	       }
	       String mx_pagestr = Integer.toString(mx_pageno);
	      
	       if(mx_pageno>1)
	       {//if the page no is greater than 1 than this verification would be performed.
	    	   String maxPage= driver.findElement(By.linkText(mx_pagestr)).getText();
		       
	    	 Assert.assertEquals(mx_pagestr, maxPage, "There is mismatch in actual page and displayed page");
	       Assert.assertTrue(driver.findElement(By.linkText(mx_pagestr)).isDisplayed(), "MAX page link not displayed");
	       driver.findElement(By.linkText(mx_pagestr)).click();
	       
	       String currentUrl = driver.getCurrentUrl();
	       APP_LOGS.debug("\nThe current url is:"+currentUrl);
	       
	       Assert.assertTrue(currentUrl.contains(mx_pagestr),"The URL Doesn't contain that maximun page number");
	       
	       //This is block for checking Previous Link,and  next link of upper right corner
	     
	       WebElement ww = driver.findElement(By.className("sort_bar"));
		   WebElement pp = ww.findElement(By.className("right"));
		 //  APP_LOGS.debug(pp.findElement(By.tagName("a")).getText());
		   Assert.assertEquals(pp.findElement(By.tagName("a")).getText(), "Prev");
		   Thread.sleep(2000);
		   
		   driver.findElement(By.linkText("Prev")).click();
		if(mx_pageno>2)
		{
		   WebElement ww2 = driver.findElement(By.className("sort_bar"));
		   WebElement pp2 = ww2.findElement(By.className("right"));
		 //  APP_LOGS.debug(pp2.findElement(By.tagName("a")).getText());
		   Assert.assertEquals(pp2.findElement(By.tagName("a")).getText(), "Prev");
		}else{
			 WebElement ww3 = driver.findElement(By.className("sort_bar"));
			   WebElement pp3 = ww3.findElement(By.className("right"));
			 Assert.assertEquals(pp3.findElement(By.tagName("a")).getText(), "Next");
		}
		
	       }else
	       {
	    	   //verifying the buttons as the buttons should be disabled for the single page.
	    	   APP_LOGS.debug("For Single page the next and previous button,s are not present ");
	    	   Assert.assertFalse(driver.findElement(By.linkText(OR.getProperty("NEXT_BUTTON_LINK"))).isEnabled(),"Next button is not displayed for the single page");
		    	 Assert.assertFalse(driver.findElement(By.linkText(OR.getProperty("PREVIOUS_BUTTON_LINK"))).isEnabled(),"Previous button is not displayed for the single page");
		    	 //driver.findElement(By.linkText(OR.getProperty("PREVIOUS_BUTTON_LINK"))).click();
	       }
	       
	       
	      	 APP_LOGS.debug("Execution for the SearchResultPage_TC2 passed ");
				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the SearchResultPage_TC2 is falied*****");
				APP_LOGS.error("ERROR :" +t.getMessage());
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
				TestUtil.reportDataSetResult(suiteSearchPageXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteSearchPageXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteSearchPageXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    
		@AfterTest
	    //Writing the final result for the TestCase whether it is passed or fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteSearchPageXls, "Test Cases", TestUtil.getRowNum(suiteSearchPageXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteSearchPageXls, "Test Cases", TestUtil.getRowNum(suiteSearchPageXls,this.getClass().getSimpleName()), "Fail");
	    
	    	
	    	
	    }
	    
	    
		
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
	    @DataProvider
	    public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteSearchPageXls,this.getClass().getSimpleName());
		}
	
	

}
