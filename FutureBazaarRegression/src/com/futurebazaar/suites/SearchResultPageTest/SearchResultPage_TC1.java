package com.futurebazaar.suites.SearchResultPageTest;


	


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


public class SearchResultPage_TC1  extends SearchResultSuiteBase{
	
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
		    	
				APP_LOGS.debug("skipping test SearchResultPage_TC1 as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : SearchResultPage_TC1");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteHomePageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void searchResultPageCheck() throws InterruptedException
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
			//**************************
			APP_LOGS.debug("****** Starting the  Test case SearchResultPage_TC1 (searchResult Page Check)");
		    
			//***********************************************
			//this method is used to verify the Search Result Page Option Like the Search Result values by Category and By Brand and Matching it with the total Count of search.  
			//int count =0;
			driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));

			driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
			Thread.sleep(3000);
			  //Getting the Search Item number	
	    	 String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
		     APP_LOGS.debug("***** \t\t\tThe Searched Item number is:"+Prevno+"\t");
		     String no_item =Prevno;
		       
		       String Number_string[] = no_item.split(" ");
		       Integer No_item= Integer.valueOf(Number_string[0]);
		     APP_LOGS.debug("The total No of items Search for the product are"+No_item);
		    
		     APP_LOGS.debug(" ****** getting the value of the count for the Search By Category");
			WebElement we =driver.findElement(By.id("cat_filter"));
			List<WebElement> pp = we.findElements(By.tagName("li"));
		    int size = pp.size();
		    System.out.println(size);
		    int totalsearch=0;
		    for (WebElement option : pp)
		    {   //System.out.println(option.findElement(By.tagName("a")).getText());
		     String input = option.findElement(By.tagName("a")).getText();
	           int start = input.indexOf("(");
		       int end = input.indexOf(")");
		    
		    
		    String getval = input.substring(start+1,end);
		    //System.out.println(getval);
		    int value = Integer.parseInt(getval);
		    totalsearch+=value;
		    
		    //option.findElement(By.tagName("a")).click();
		    	
		    	    	
		    }
		    
		    //verifying the Total Count Value by category and the Search Count value
		    APP_LOGS.debug("Total count for the Search By Category"+ totalsearch);
		    APP_LOGS.debug(" ****** verifying the count for the Search By Category");
		    Assert.assertTrue(No_item.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
		   
		    APP_LOGS.debug("******verified the count for the Search By Category with the search count");
		    
		    //for the By brand Search
		    APP_LOGS.debug("verifying the total By Brand search items by count");
		    WebElement brand =driver.findElement(By.id("brands_filter"));
		    WebElement brand2 = brand.findElement(By.className("filter_scroll"));
			List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
		    int brandlistsize = brandlist.size();
		    APP_LOGS.debug(brandlistsize);
		    int totalBrandsearch=0;
		    //getting the values for the count of brands.
		    for (WebElement option : brandlist)
		    {   //System.out.println(option.findElement(By.tagName("label")).getText());
		     String input = option.findElement(By.tagName("label")).getText();
	           int start = input.indexOf("(");
		       int end = input.indexOf(")");
		      String getval = input.substring(start+1,end);
		    //  APP_LOGS.debug(getval);
		    int value = Integer.parseInt(getval);
		    totalBrandsearch+=value;
		    
		    //option.findElement(By.tagName("a")).click();
		        	    	
		    }
		    APP_LOGS.debug("Total count for the Search By BRAND "+ totalBrandsearch);
	       
		    //verifying the Total Count Value Search By BRAND and the Search Count value
		    APP_LOGS.debug("******verifying the count for the Search By BRAND");
		    Assert.assertTrue(No_item.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
		    
		    APP_LOGS.debug("******verified the count for the Search By BRAND with the search count");
		  	    
		    APP_LOGS.debug(" ***** Verifying the By Category option Collapse***");
		    APP_LOGS.debug(driver.findElement(By.id("cat")).getText());
		    Assert.assertTrue(driver.findElement(By.id("cat")).getText().equalsIgnoreCase("CATEGORY"),"Category Text is not matching with the value");
		    APP_LOGS.debug("Collaspse the By Category list");
		    driver.findElement(By.id("cat")).click();
		    APP_LOGS.debug("****** Collaspse the By Category list Verified");
		    
		     
		    APP_LOGS.debug(" ****** Verifying the By Brand  option Collapse");
		    Assert.assertTrue(driver.findElement(By.id("brands")).getText().equalsIgnoreCase("By Brand"),"By Brand Text is not matching with the value");
		    APP_LOGS.debug("Collaspse the By Category list");
		    driver.findElement(By.id("brands")).click();
		    APP_LOGS.debug("****** Collaspse the By Brand list"); 
		    
		    if(driver.getPageSource().contains("tags"))
		    {   
		    	APP_LOGS.debug(" ****** Verifying the By Retailer  option Collapse");
		    	APP_LOGS.debug("Verifying the By Retailer option");
			    Assert.assertTrue(driver.findElement(By.id("tags")).getText().equalsIgnoreCase("By Retailer")," By Retailer Text is not matching with the value");
			    APP_LOGS.debug("Collaspse the  By Retailer list");
			    driver.findElement(By.id("tags")).click();
			    APP_LOGS.debug(" ****** Verified the By Retailer  option Collapse");
		    }
		    APP_LOGS.debug("*******The SearchResultPage_TC1 is passed *****");
			
		 
				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the SearchResultPage_TC1 is falied*****");
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
	    @Test
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
