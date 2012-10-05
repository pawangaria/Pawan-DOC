package com.futurebazaar.suites.TodaysDeals;


import org.testng.annotations.Test;
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
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.MenuInOutLink;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class TodaysDeals_TC1 extends TodaysDealsSuiteBase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		static boolean flag =false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static WebDriver driver = null;
		
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suiteTodaysDealsXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteTodaysDealsXls, "Test Cases",TestUtil.getRowNum(suiteTodaysDealsXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("Skipping test case TodaysDeals_TC1(TodaysDeals Drop down Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase TodaysDeals_TC1(TodaysDeals Drop down Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteTodaysDealsXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void TodaysDeals_DDCheck() throws InterruptedException
		{
			//This method is used to click and verify the Fashion link on homepage			
			try{
			count++;
			//test the Runmode of the current Dataset if present.
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				APP_LOGS.debug("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//****** Starting the Test for TodaysDeals_TC1(Todays Deal Drop down Check ******* 
			
            APP_LOGS.debug("****** Starting the Test for TodaysDeals_TC1(TodaysDeals drop down Check *******  ");
            Actions builder = new Actions(driver);
            //Clicking on the product name link to go to product page
            APP_LOGS.debug("Clicking on the product name link to go to product page");
   	        WebElement todaydeallink= driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_DROPDOWN_XPATH")));
            builder.clickAndHold(todaydeallink).build().perform();	
            String  productname_on_dd  =driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_PRODUCTNAME_XPATH"))).getText();
            driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_PRODUCTNAME_XPATH"))).click();
            if(driver.getPageSource().contains("Search Results"))
            {
            	flag =true;
            	
            	driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_LINK_XPATH"))).click();
            }
        	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
            String product_name_pdp = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
          //  Assert.assertTrue(product_name_pdp.contains(productname_on_dd) ,"Product name on Todays Deals doesn't match with that on the Product page");
            driver.navigate().back();
            
            //Clicking on the product image to go to product page 
            APP_LOGS.debug("Clicking on the product image to go to product page");
            todaydeallink= driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_DROPDOWN_XPATH")));
            builder.clickAndHold(todaydeallink).build().perform();	
            productname_on_dd  =driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_PRODUCTNAME_XPATH"))).getText();
            driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_PRODUCT_IMAGE_XPATH"))).click();
            if(flag)
            {
            	driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_LINK_XPATH"))).click();
            }
        	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
           // product_name_pdp = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
          //  Assert.assertTrue(product_name_pdp.contains(productname_on_dd) ,"Product name on Todays Deals doesn't match with that on the Product page");
            driver.navigate().back();
            
            //Clicking on the 'Add to Cart' button from Todaysdeals Drop Down
            APP_LOGS.debug("Clicking on the 'Add to Cart' button from Todaysdeals Drop Down");
            todaydeallink= driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_DROPDOWN_XPATH")));
            builder.clickAndHold(todaydeallink).build().perform();	
            productname_on_dd  =driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_PRODUCTNAME_XPATH"))).getText();
            driver.findElement(By.id(OR.getProperty("TODAYS_DEALS_ADDTOCART_ID"))).click();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
		    APP_LOGS.debug(" 'This is  cart page'  is verified");
            String   product_name_cartpage = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_CARTPAGE_XPATH"))).getText();
         //   Assert.assertTrue(product_name_cartpage.contains(productname_on_dd) ,"Product name on Todays Deals doesn't match with that on the Cartpage");
            driver.navigate().back();
            
           //Clicking on the todays link product name link from dropdown displayed under search box 
            APP_LOGS.debug("Clicking on the todays link product name link from dropdown displayed under search box ");
            WebElement Searchbox = driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID")));
            builder.clickAndHold(Searchbox).build().perform();	
            String product_name_searchbox = driver.findElement(By.xpath(OR.getProperty("SEARCH_DD_PRODCUTNAME_XPATH"))).getText();
            driver.findElement(By.xpath(OR.getProperty("SEARCH_DD_PRODCUTNAME_XPATH"))).click();
            if(flag)
            {
            	driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_LINK_XPATH"))).click();
            }
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
            // product_name_pdp = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
           //  Assert.assertTrue(product_name_pdp.contains(product_name_searchbox) ,"Product name on Todays Deals link  under searchbox doesn't match with that on the Product page");
            driver.navigate().back();      
            APP_LOGS.debug("******Completed Execution of the TodaysDeals_TC1(Todays Deal Drop down Check)******");
          
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the TodaysDeals_TC1(Todays Deal Drop down Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
		}
			
					
		
		@Test
		@AfterMethod
		public void testdataReporter()
		{//APP_LOGS.debug("value of skip"+count+skip);
		//APP_LOGS.debug("test case fail"+count+fail);
		//APP_LOGS.debug("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suiteTodaysDealsXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteTodaysDealsXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteTodaysDealsXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @Test
		@AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteTodaysDealsXls, "Test Cases", TestUtil.getRowNum(suiteTodaysDealsXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteTodaysDealsXls, "Test Cases", TestUtil.getRowNum(suiteTodaysDealsXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
}
