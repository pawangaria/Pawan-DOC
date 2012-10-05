package com.futurebazaar.suites.HomeAndLivingTest;

import java.io.IOException;
import com.futurebazaar.common.methods.MenuInOutLink;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class HomeAndLiving_TC2 extends HomeAndLivingSuiteBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteHomeAndLivingXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteHomeAndLivingXls, "Test Cases",TestUtil.getRowNum(suiteHomeAndLivingXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("Skipping test Home&Living_TC2(Home&Livng Inner Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase Home&Living_TC2(Home&Livng Inner Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteHomeAndLivingXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void HLInner_DD() throws InterruptedException
		{
			//This method is used to check all the links in the fashion menu.
			
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
				//****** Starting the Test for Home&Living_TC2(Home&Livng Inner Check *******
				
				     APP_LOGS.debug("****** Starting the Test for Home&Living_TC2(Home&Livng Inner Check ******* ");	
			         String  HomeLiving_InnerLink_Xpath=null;
					 WebElement kk = driver.findElement(By.xpath(OR.getProperty("HOME&LIVING_DROPDOWN_BLOCK_XPATH")));
				 	 WebElement kk2 = kk.findElement(By.className("t2_l2_inner"));
					 WebElement  kk3 = kk2.findElement(By.className("t2_l2_inner_ul"));
					 List<WebElement> kk4 = kk3.findElements(By.className("t2_l2"));
			         //Getting the link count of Home &Living Drop down
					 APP_LOGS.debug("Getting the link count of Home &Living Drop down");
					 int size1=kk4.size();
					 
					 for(int i=1;i<=size1;i++)
					 {
				    HomeLiving_InnerLink_Xpath="//div[4]/div/ul/li[1]/div/div/ul/li["+i+"]/a";
			        String Xpath_Outer=OR.getProperty("HOME&LIVING_MAIN_LINK_XPATH");  
			        //Calling the function MenuInnerLink 
			        APP_LOGS.debug("Calling the Menu Inner Link function" + i +"Times");
			    	MenuInOutLink.MenuInnerLink(Xpath_Outer,HomeLiving_InnerLink_Xpath);
			        }
				}
				 catch(Throwable t)
					{
						APP_LOGS.debug("******Execution of the HomeAndLiving_TC2 (Home&Livng Inner Check) is falied******");
						APP_LOGS.error("ERROR "+t.getMessage());
						ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
					    fail=true;
					    return;
					}
			}
		
			
			
			public boolean check(WebElement option2)
			{
				try{
				option2.findElement(By.tagName("a"));
				}catch(NoSuchElementException t)
				{
					return false;
				}
				return true;
			}
			
		    }  
			