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

public class HomePage_TC2 extends HomePageSuitebase{
	
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
		    	
				APP_LOGS.debug("skipping test HomePage_TC2 as the runmode is NO");
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
			
				APP_LOGS.debug("verifying the Home Page Test Case 2");
				
				APP_LOGS.debug("Verifying the CLEARANCE on HOme page");
				
											
				//Checking the Clearance header.
				WebElement clearanceheader = driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/div[4]"));
				
				String Todays_Deals_String= clearanceheader.findElement(By.tagName("h1")).getText();
			     System.out.println(Todays_Deals_String);
				Assert.assertEquals(Todays_Deals_String,"CLEARANCE");
				
				//Clearance BOX no 2
				checkboxes(".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[2]/div",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[2]/div/div[3]",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[2]/div/h3",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[2]/div/div[4]");
				//Clearance BOX no 3
				checkboxes(".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[3]/div",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[3]/div/div[3]",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[3]/div/h3",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[3]/div/div[4]");
				//Clearance BOX no 4
				checkboxes(".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[4]/div",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[4]/div/div[3]",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[4]/div/h3",".//*[@id='home']/div[2]/div[2]/div[5]/ul/li[4]/div/div[4]");

				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the HomePage_TC2 is falied*****");
				t.getMessage();
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		 public static void checkboxes(String boxxpath,String approval,String productlink,String pricesAndbuynow) throws InterruptedException
		    {
		         System.out.println("For the CLEARANCE Product box no."+box);
				
		         
		         
		         
		         // Save Blue tag
		               
				//WebElement pp = driver.findElement(By.xpath(".//*[@id='grid_page_']/div/div[1]"));
				//.//*[@id='grid_page_']/div/div[1]
				WebElement pp = driver.findElement(By.xpath(boxxpath));
				//System.out.println(aa.isDisplayed());
				
				//verifying the Blue Save tag
				//Assert.assertTrue(aa.isDisplayed());
				WebElement aa = pp.findElement(By.className("item_img"));
		                  List<WebElement> saveTag = aa.findElements(By.tagName("span"));
				for(WebElement option : saveTag){
					System.out.println(option.getText());
				}
				
			    int num = saveTag.size();
			  
			    //Verifying the total no of values in Blue Tag
			    Assert.assertTrue(num>0,"Some value is missing from the Save Blue Tag on the Product");
			    
			       
			      System.out.println("to link");
			      //Link for the image is present or not
			      WebElement link1 = aa.findElement(By.tagName("a"));
			     // System.out.println(link1.isDisplayed());
				
			      System.out.println("to Image");
			      //Image is present or not
			      WebElement img1 = aa.findElement(By.tagName("img"));
			     
				  Assert.assertTrue(img1.isDisplayed(),"product Image is not displayed");
			    
				  //time
			      System.out.println("to time");
			   			 Thread.sleep(5000);
					
					WebElement menu = pp.findElement(By.className("time"));
					Assert.assertTrue(menu.isDisplayed(),"countdown time watch is not displayed for the Product");
						
					//build and perform the mouseOver with Advanced User Interactions API  
					Actions builder = new Actions(driver);    
					builder.moveToElement(menu).build().perform();

					 //time units
				      System.out.println("to time unit");
				      
				      WebElement tu = menu.findElement(By.className("timer_comment"));
						List<WebElement> timeunitTag = tu.findElements(By.tagName("span"));
						for(WebElement option2 : timeunitTag)
						{
							System.out.println(option2.getText());
						}
					    int tim = timeunitTag.size();
					   Assert.assertTrue(tim==4,"In timer watch for the product the one time unit is not displayed like ");
						   
					   //Checking the product Link By the Name of the product  
					  WebElement prolink= driver.findElement(By.xpath(productlink));   
					  System.out.println(prolink.getText());
					    Assert.assertTrue(prolink.isDisplayed());
					    
					    //Checking the Approval image
					    WebElement app = driver.findElement(By.xpath(approval));
					    WebElement imge = app.findElement(By.tagName("img"));
					    Assert.assertTrue(imge.isDisplayed());
					    System.out.println("image is present"+imge.isDisplayed());
					   
					      // market price and offer price Check
					      WebElement twoprices = driver.findElement(By.xpath(pricesAndbuynow));
							List<WebElement> priceTag = twoprices.findElements(By.tagName("span"));
							for(WebElement option : priceTag){
								System.out.println(option.getText());
							}
						    
							// Verifying the Prices on the Box
							Assert.assertFalse(priceTag.isEmpty(),"Some value from the Market Price or offer price is missing in the product");
					       
							     
							     //Buy Now Button check
						    System.out.println("buy now button");
							      WebElement buynowbt = driver.findElement(By.xpath(pricesAndbuynow));
									WebElement buyTag = buynowbt.findElement(By.tagName("a"));
								     
							 Assert.assertTrue(buyTag.isDisplayed(),"buy Now button is not displayed for the TODays Deals -Product BOx No."+box);
						      //get the values from the home page
		            box++;
		    	
		    }
		
		
		@Test
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
