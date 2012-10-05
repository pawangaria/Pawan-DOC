package com.futurebazaar.suites.NewArrivalsTest;


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
import com.futurebazaar.common.methods.LowerMenuInOutLink;
import com.futurebazaar.common.methods.MenuInOutLink;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class NewArrivals_TC2 extends NewArrivalsSuitesBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteNewArrivalsXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteNewArrivalsXls, "Test Cases",TestUtil.getRowNum(suiteNewArrivalsXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("Skipping test case NewArrivals_TC1(Electronics_TC1 Drop down Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase NewArrivals_TC1(Electronics_TC1 Drop down Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteNewArrivalsXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void NewArrivalsInnerLink_DD() throws InterruptedException
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
			//"****** Starting the Test for NewArrivalsTest_TC1(NewArrivalsTest Inner link Check ******* 
						 
			APP_LOGS.debug("****** Starting the Test for NewArrivalsTest_TC2(NewArrivalsTest Inner link Check ******");
			String Xpath_Outer =null;
		     //int link_number= nextIntInRange(1,12,rnd);
				 WebElement kk = driver.findElement(By.xpath(OR.getProperty("NEWARRIVALS_DROPDOWN_BLOCK_XPATH")));
			 	 WebElement kk2 = kk.findElement(By.className("t1_l2_inner"));
				// WebElement  kk3 = kk2.findElement(By.tagName("ul"));
				 List<WebElement> kk4 = kk2.findElements(By.className("t1_l2"));
				 System.out.println(kk4.size());
				 int size1=kk4.size();
				 
				 for(int i=1;i<=size1;i++)
				 {
					 //*[@id='menu']/ul[1]/li[2]/div/div[1]/ul/li[1]/a
		       // System.out.println("link_number:"+i);
		        String  NewArrival_InnerLink_Xpath="//div[@id='menu']/ul/li[3]/div/div/ul/li["+i+"]/a"; 
		        Xpath_Outer=OR.getProperty("NEWARRIVALS_MAIN_LINK_XPATH");
		        LowerMenuInOutLink.LowerMenu_InnerLink(Xpath_Outer,NewArrival_InnerLink_Xpath);
		    	APP_LOGS.debug("****** Completed the Test for NewArrivalsTest_TC2(NewArrivalsTest Inner link Check ******");
				
			}
	
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the NewArrivals_TC2(NewArrivalsTest Inner link Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
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
				TestUtil.reportDataSetResult(suiteNewArrivalsXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteNewArrivalsXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteNewArrivalsXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteNewArrivalsXls, "Test Cases", TestUtil.getRowNum(suiteNewArrivalsXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteNewArrivalsXls, "Test Cases", TestUtil.getRowNum(suiteNewArrivalsXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
}
