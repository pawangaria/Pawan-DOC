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

public class HomePage_TC5 extends HomePageSuitebase{
	
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
		    	
				APP_LOGS.debug("skipping test HomePage_TC5 as the runmode is NO");
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
		public void ourPartnersHeader() throws InterruptedException
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
			//testing code which can be Selenium
			/*
			APP_LOGS.debug("entering the test case A1");
			//Productpurchase.purchaseItem(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"),CONFIG.getProperty("SearchItem"));
			System.out.println(cardno);
			System.out.println(EXmonth);
			System.out.println(CVV);
			System.out.println(EXyear);
			CreditCard cc = new CreditCard();
			cc.creditcardVerification(cardno,EXmonth,EXyear,CVV,name);
			
			APP_LOGS.debug("Executed the test case for the Payment link test (For credit card)");
			System.out.println("test case a");
		    */
			//***********************************************
			//this method is used to verify the HOme page option 
			
				APP_LOGS.debug("verifying the Home Page Test Case 5");
				
				APP_LOGS.debug("Verifying the OUR PARTNERS on HOme page");
								
				
				//Checking the OUR PARTNERS header.
				String topsellers = driver.findElement(By.className("our_partners_header")).getText();
				System.out.println(topsellers);
				Assert.assertEquals(topsellers,"OUR PARTNERS");
				
				//Payback options
				WebElement partner = driver.findElement(By.className("our_partners"));
				WebElement knowmore = partner.findElement(By.tagName("a"));
				String know = knowmore.getText();
				
				String payback = partner.findElement(By.tagName("span")).getText();
				System.out.println(know);
				System.out.println(payback);
				Assert.assertTrue(know.length()>0,"Payback Know more link is not dispalyed");
				Assert.assertTrue(payback.length()>0,"Payback is not dispalyed");
				
				//Click on know more for Pay back
				knowmore.click();
				Thread.sleep(2000);
				Assert.assertTrue(driver.getPageSource().contains("POINTS AT EVERY STEP"));
				driver.navigate().back();
				
				//VISA option
				WebElement know2 = driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/div[13]/div[3]/div"));
				WebElement visa = know2.findElement(By.tagName("span"));
				WebElement visaknow3 = know2.findElement(By.tagName("a"));
				
				System.out.println(visa.getText());
				System.out.println(visaknow3.getText());
				//Assert.assertTrue(visaknow3.length()>0,"know more for Visa is not displayed");
				
				//click on know more for Visa
				visaknow3.click();
				Thread.sleep(3000);
				Assert.assertTrue(driver.getPageSource().contains("Card Offers"));
				driver.navigate().back();

				
				//HDFC option
				WebElement know33 = driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/div[13]/div[4]"));
				String hdfc = know33.findElement(By.tagName("span")).getText();
				WebElement knowHDFC = know33.findElement(By.tagName("a"));
				
				String knowtext= knowHDFC.getText();
				System.out.println(hdfc);
				Assert.assertTrue(hdfc.length()>0,"HDFC values are not present");
				Assert.assertTrue(knowtext.length()>0,"Know more for HDFC is not displayed");
				
				//click on know more for Visa
				 knowHDFC.click();
				Thread.sleep(2000);
				 WebElement frmname1= driver.findElement(By.id("fancybox-frame"));
		 	    String framename = frmname1.getAttribute("name");
		 	    System.out.println(framename);
		 	    
		 	    driver.switchTo().frame(framename);
				Assert.assertTrue(driver.getPageSource().contains("Easy EMIs for HDFC"));
				Thread.sleep(3000);
				 driver.switchTo().defaultContent();
				
				driver.findElement(By.id("fancybox-close")).click();
				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the HomePage_TC4 is falied*****");
				t.getMessage();
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		 
	   
		
	
		@AfterMethod
		public void testdataReporter()
		{System.out.println("value of skip"+count+skip);
		System.out.println("test case fail"+count+fail);
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
	    @Test
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
