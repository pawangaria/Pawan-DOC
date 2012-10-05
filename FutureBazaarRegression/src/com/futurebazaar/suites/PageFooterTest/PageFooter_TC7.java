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

 public class PageFooter_TC7  extends PageFooterSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static WebDriver driver = null;
		public boolean bool_break = true;
		public boolean bl_break =true;
		public int flgcount1=0;
		public int count1=0;
		public boolean flag_column =false;
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suitePageFooterXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				APP_LOGS.debug("skipping test PageFooter_TC7 (SiteMAp Links Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase PageFooter_TC7 (SiteMAp Links Check)as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePageFooterXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void SiteMApLinksCheck() throws InterruptedException
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
			APP_LOGS.debug("****** Starting the  test case PageFooter_TC7 (SiteMAp Links Check)");
            APP_LOGS.debug(">>>>>>> Electronics and Essentials Links");
			LINK_Footer_check(".//*[@id='content']/div/div[1]",15);
			//APP_LOGS.debug("bet 1 & 2 "+ count1);
			//APP_LOGS.debug(flgcount1);
			APP_LOGS.debug(">>>>>>> Fashion and Gift Vouchers Links");
			LINK_Footer_check(".//*[@id='content']/div/div[2]",14);
			
			APP_LOGS.debug(">>>>>>> Gifts and Ideas and Home and Living Links");
		LINK_Footer_check(".//*[@id='content']/div/div[3]",15);
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the PageFooter_TC7 (SiteMAp Links Check in Footer On HOme Page) is falied*****");
				
				APP_LOGS.error("ERROR :" +t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;

			}
			
					}
		
		
		 public void LINK_Footer_check(String xpath,int num) throws InterruptedException
			{  
					
			        flgcount1=0;
				    count1=0;
				    	//APP_LOGS.debug("In styart of metod:"+count1);
					//.//*[@id='content']/div/div[1]
					
					//Clicking on Site map link
					
						APP_LOGS.debug("Clicking on  SITEMAP ");		
			        driver.findElement(By.linkText(OR.getProperty("SITEMAP_LINK"))).click();
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CONTENT_VERIFICATION_ABOUTLINK_XPATH"))));
				    //Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PAGE_VERIFICATION_COMMON_FOOTER_XPATH"))).getText().contains(OR.getProperty("String_Sitemap")));
					for(int i=0;i<=num;i++)
					{
				       count1++;
				       		//APP_LOGS.debug(count1);	
					//WebElement we = driver.findElement(By.className("sitemap"));
					List<WebElement> pp = driver.findElements(By.xpath(xpath));
					//	APP_LOGS.debug(pp.size());
					
					for(WebElement option : pp){
						
						//	APP_LOGS.debug("in loop");
						List<WebElement> cc =option.findElements(By.tagName("ul"));
						//	APP_LOGS.debug("list of ul "+ cc.size());
						
				
						
						
						for(WebElement option1 : cc){
						
						//	APP_LOGS.debug(option1.getTagName());
							//	APP_LOGS.debug(option1.findElement(By.tagName("a")).getText());
							List<WebElement> kk =option1.findElements(By.tagName("li"));
							
							//	APP_LOGS.debug(kk.size());
							
							for(WebElement option2 : kk){
							   flgcount1++;
								if(flgcount1==count1)
								{
									
									flgcount1=0;
							   String src =  option2.findElement(By.tagName("a")).getText();	
							   APP_LOGS.debug("Clicking on the "+ src);
							   option2.findElement(By.tagName("a")).click();
							   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='breadcrumb']")));
							   String str  = driver.findElement(By.xpath(OR.getProperty("Sitemap_Varifiaction_xpath"))).getText();
							    
							    try{
									   Assert.assertTrue(str.contains(src));
									   APP_LOGS.debug("Verified the link "+ src);
									   }
									   catch (Throwable e ) {
										   APP_LOGS.error("ERROR :" +e.getMessage());
									    	APP_LOGS.debug("Failed for link in sitemap:"+src);
									    }
							   driver.navigate().back();
							     bool_break = false;
							   
							   break;
								}
							}
							 if(bool_break == false)
							 {
							
							  bool_break = true;
							
							  break;
							 }
							
						   }
						 break;
						 }
					  
					   
					    
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
