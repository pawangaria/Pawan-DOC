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

 public class PageFooter_TC3  extends PageFooterSuitebase{
	
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
			if(!TestUtil.isTestCaseRunnable(suitePageFooterXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				APP_LOGS.debug("skipping test PageFooter_TC3 (FBtrustlinks Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase PageFooter_TC3 (FBtrustlinks Check)as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePageFooterXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void FBtrustlinksCheck() throws InterruptedException
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
			APP_LOGS.debug("****** Starting the  test case PageFooter_TC3 (FBtrust links check)");
		     
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			  driver.findElement(By.xpath(OR.getProperty("FB_TRUST_COD_XPATH"))).click();
			  
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
			}
			//verify text on COD POP UP
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Common_Popup_verfication_link"))));
			 APP_LOGS.debug(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText());
		     Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText().equalsIgnoreCase(OR.getProperty("STRING_TRUST_COD")));
		     driver.switchTo().defaultContent();
			 driver.findElement(By.xpath(OR.getProperty("POPUP_CLOSE_XPATH"))).click();
			 APP_LOGS.debug("COD POP UP is verified");
			 
			//opening return popup
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			 driver.findElement(By.xpath(OR.getProperty("FB_TRUST_RETURN_XPATH"))).click();
			  
				for(String handle : driver.getWindowHandles())
				{
					driver.switchTo().window(handle);
				}
				//verify text on POP UP
				 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Common_Popup_verfication_link"))));
				 APP_LOGS.debug(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText());
			     Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText().equalsIgnoreCase(OR.getProperty("STRING_TRUST_RETURN")));
			     driver.switchTo().defaultContent();
				 driver.findElement(By.xpath(OR.getProperty("POPUP_CLOSE_XPATH"))).click();
				 APP_LOGS.debug("Return POP UP is verified");
				 
				  
				 //opening fianance pop up
				  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			      driver.findElement(By.xpath(OR.getProperty("FB_TRUST_FIANANCE_XPATH"))).click();
				  
					for(String handle : driver.getWindowHandles())
					{
						driver.switchTo().window(handle);
					}
					//verify text on Fianance POP UP
					 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Common_Popup_verfication_link"))));
					 APP_LOGS.debug(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText());
				     Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText().equalsIgnoreCase(OR.getProperty("STRING_TRUST_FIANANCE")));
				     driver.switchTo().defaultContent();
					 driver.findElement(By.xpath(OR.getProperty("POPUP_CLOSE_XPATH"))).click();
					 APP_LOGS.debug("Fianance POP UP is verified");
			
					 //Opening Warranty pop up
					 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
					 WebElement pp = driver.findElement(By.className("footer_inner"));
					 WebElement ww = pp.findElement(By.className("col3"));
					 List<WebElement> ll = ww.findElements(By.tagName("li")); 
					for (WebElement option : ll)
					{
						APP_LOGS.debug(option.findElement(By.tagName("a")).getText());
						if(option.findElement(By.tagName("a")).getText().equalsIgnoreCase("Manufacturer's Warranty"))
						{
							option.findElement(By.tagName("a")).click();
						}
					}
					    
					// APP_LOGS.debug(ll.size());
					 
					 
			        	for(String handle : driver.getWindowHandles())
						{
							driver.switchTo().window(handle);
						}
						//verify text on Warranty pop up
						 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Common_Popup_verfication_link"))));
						 APP_LOGS.debug(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText());
					     Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("Common_Popup_verfication_link"))).getText().equalsIgnoreCase(OR.getProperty("STRING_TRUST_WARRANTY")));
					     driver.switchTo().defaultContent();
						 driver.findElement(By.xpath(OR.getProperty("POPUP_CLOSE_XPATH"))).click();
						 APP_LOGS.debug("Warranty POP UP is verified");
				
			
		
			    APP_LOGS.debug("*****PageFooter_TC3(FBtrustlinksCheck) test completed ******* ");
	        
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the PageFooter_TC3 (FBtrust links Check in Footer On HOme Page) is falied*****");
				
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
