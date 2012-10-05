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

public class HomePage_TC6 extends HomePageSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static int count=0;
	    public static int box=2;
		
		//public static WebDriver driver = null;
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suiteHomePageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{   
			    
			    //writing the Skip in the Xls file for the test case
			  
		    	
		    	TestUtil.reportDataSetResult(suiteHomePageXls, "Test Cases",TestUtil.getRowNum(suiteHomePageXls,this.getClass().getSimpleName()),"Skip");
		    	
				APP_LOGS.debug("skipping test HomePage_TC6(Carousel Image Checking on home page) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : HomePage_TC6(Carousel Image Checking on home page)");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteHomePageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void CarouselImageCheck() throws InterruptedException
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
			
			//***********************************************
			//this method is used to verify the Home page Carousel Image and Carousel links.
			//At last we will check the No of images should be Equal to the No of Links on Home page.
			
			APP_LOGS.debug("Opening the home page by clicking the Futurebazaar logo on home Page");
			driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
			
			//wait for the HOme Page to Load.
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			
			WebElement car1 = driver.findElement(By.className(OR.getProperty("CAROUSEL_SElECTOR_CLASS")));
			List<WebElement> car2 = car1.findElements(By.tagName("li"));
			int imagecount_link =car2.size();
			APP_LOGS.debug("No of Carousel links on hame page are "+car2.size());
			for (WebElement option : car2)
			{
				//option.findElement(By.tagName("a")).click();
				Thread.sleep(2000);
				option.findElement(By.tagName("a")).click();
				
				
				Assert.assertTrue(driver.findElement(By.id(OR.getProperty("CAROUSEL_SLIDES_DISPLAYED_ID"))).isDisplayed());
				APP_LOGS.debug("Carousel verified");
			}
			
			APP_LOGS.debug("Verifying the images present on the carousel");
			//Verifying the images present on the carousel
			WebElement imageTAG = driver.findElement(By.id(OR.getProperty("CAROUSEL_SLIDES_DISPLAYED_ID")));
			
			List<WebElement> image = imageTAG.findElements(By.tagName("img"));
			int imagecount_image =image.size();
			//System.out.println("No of Carousel Images on hame page are "+image.size());
			for(WebElement option2 : image){
			//System.out.println(option2.getAttribute("src"));
				APP_LOGS.debug("Verifying the Image  for "+option2.getAttribute("src"));	
			String src = option2.getAttribute("src");
			Assert.assertTrue(src.endsWith(".jpg")||src.endsWith(".JPG"));
			APP_LOGS.debug("All images present on the carousel Verified");
			
			}
			
			APP_LOGS.debug("verifying the CAROUSEL Links and the total no of images On home page are same or not");
			Assert.assertEquals(imagecount_image,imagecount_link,"No of images on carousel and no of links for carousel are not matching on home page");
			
			APP_LOGS.debug("*******Execution for the HomePage_TC6(Carousel Image Checking on home page) is verified");
			
				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the HomePage_TC6(Carousel Image Checking on home page) is falied*****");
				APP_LOGS.error(t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
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
