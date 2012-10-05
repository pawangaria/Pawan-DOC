package com.futurebazaar.suites.PageFooterTest;






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
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

 public class PageFooter_TC2  extends PageFooterSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static WebDriver driver = null;
		public static int count2 =0;
		   public static int flg =0;
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suitePageFooterXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				APP_LOGS.debug("skipping test PageFooter_TC2 (DealCategory link check) as the runmode is NO");
				throw new SkipException("Skipping this testcase PageFooter_TC2 (DealCategory link check)as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePageFooterXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void DealCategorylinkcheck() throws InterruptedException
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
			APP_LOGS.debug("****** Starting the  test case PageFooter_TC2 (Deal Category link check)");
	        
			APP_LOGS.debug("Opening the home page by clicking the Futurebazaar logo on home Page");
			driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
			
		    	
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
				//System.out.println("Clicking on  Home & Living ");	
				
				WebElement ff = driver.findElement(By.className(OR.getProperty("Base_class_deal_footer")));
				
				List<WebElement> pp  = ff.findElements(By.tagName("li"));
				
				//System.out.println(pp.size());
				
				for(int i=0;i<=pp.size();i++)
				{
					//System.out.println("coming to this"); 
					count2 ++;
					WebElement ff2 = driver.findElement(By.className(OR.getProperty("Base_class_deal_footer")));
					
					List<WebElement> pp2  = ff2.findElements(By.tagName("li"));
					
					
				for(WebElement option : pp2)
					
				{	
					flg++;
					if(flg == count2)
					{
						String str = option.findElement(By.tagName("a")).getText();
						APP_LOGS.debug("Clicking on the link = "+str);
				    option.findElement(By.tagName("a")).click();
				    String breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
				   Assert.assertEquals(breadcumb,str,"assert fails");
				   APP_LOGS.debug("Verified the link = "+str);
				    driver.navigate().back();
					flg =0;
					break;
					}
				
					
				}
				}
			
			
			
			/*
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on  Home & Living ");	
			driver.findElement(By.xpath(OR.getProperty("DEALS_HOME_LIVING_XPATH"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
			String breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Home & Living","assert fails");
			//driver.navigate().back();
			
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on Fashion ");
			driver.findElement(By.xpath(OR.getProperty("DEALS_FASHION_XPATH"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
			breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Fashion");
		    APP_LOGS.debug("Fashion Link verified");
			//driver.navigate().back();
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on  Electronics ");
			driver.findElement(By.xpath(OR.getProperty("DEALS_ELECTRONICS_XPATH"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
			breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Electronics");
		    APP_LOGS.debug("Electronics Link verified");
		    //driver.navigate().back();
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on  Essentials ");
			driver.findElement(By.xpath(OR.getProperty("DEALS_ESSENTIALS_XPATH"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
			breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Essentials");
		    APP_LOGS.debug("Essentials Link verified");
			//driver.navigate().back();
			
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on  Gift & Ideas ");
			driver.findElement(By.xpath(OR.getProperty("DEALS_GIFTS_IDEAS_XPATH"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
			breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Gifts & Ideas");
		    APP_LOGS.debug("Gifts & Ideas Link verified");
		    //driver.navigate().back();
			
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			APP_LOGS.debug("Clicking on  Gift Voucher ");
			driver.findElement(By.xpath(OR.getProperty("DEALS_GIFT_VOUCHER_LINK"))).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("VERIFICATION_FILTERPANEL_XPATH"))));
		    breadcumb =driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		    Assert.assertEquals(breadcumb, "Gift Vouchers");
		    APP_LOGS.debug("Gift Vouchers Link verified");
		    //driver.navigate().back();
		    
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
	*/
	
		
			    APP_LOGS.debug("*****PageFooter_TC2 for DealCategory link check test completed ******* ");
	        
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the PageFooter_TC2 (DealCategory linkcheck in Footer On HOme Page) is falied*****");
				
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
				TestUtil.reportDataSetResult(suitePageFooterXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suitePageFooterXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suitePageFooterXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suitePageFooterXls, "Test Cases", TestUtil.getRowNum(suitePageFooterXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suitePageFooterXls, "Test Cases", TestUtil.getRowNum(suitePageFooterXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    /*
		@DataProvider
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
		}*/
	
	

}
