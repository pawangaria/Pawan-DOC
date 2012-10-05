package com.futurebazaar.suites.FashionTest;
import com.futurebazaar.common.methods.MenuInOutLink;

import java.util.List;

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
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class FashionTest_TC1 extends FashionSuiteBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteFashionXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteFashionXls, "Test Cases",TestUtil.getRowNum(suiteFashionXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test case suiteFashion as the runmode is NO");
				throw new SkipException("Skipping suiteFashion testcase as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteFashionXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void FashionLink_DD() throws InterruptedException
		{
			//This method is used to click and verify the Fashion link on homepage
			try{
			count++;
			//test the Runmode of the current Dataset if present.
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				System.out.println("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//"****** Starting the Test for Fashion_TC1(Fashion outer link Check ******* 
			
			 
			APP_LOGS.debug("****** Starting the Test for Fashion_TC1(Fashion outer link Check *******  ");
			
			String Xpath_Outer =OR.getProperty("FASHION_MAIN_LINK_XPATH");
			String Xpath_Block =OR.getProperty("FASHION_DROPDOWN_BLOCK_XPATH");
			//Calling the MenuMainLink function
			APP_LOGS.debug("Caaling the MenuMainLink function");
			MenuInOutLink. MenuMainLink(Xpath_Outer, Xpath_Block);
			APP_LOGS.debug("***Completed the FashionTest_TC1 (Fashion outer DD  Check) ******");
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the FashionTest_TC1 (Fashion outer DD  Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
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
		TestUtil.reportDataSetResult(suiteFashionXls,this.getClass().getSimpleName(),count+2,"Skip");
	else if(fail)
	{
    	TestUtil.reportDataSetResult(suiteFashionXls,this.getClass().getSimpleName(),count+2,"Fail");
        istestpass=false; //checking for the TestCase is failed or passed
	}
        else
       TestUtil.reportDataSetResult(suiteFashionXls,this.getClass().getSimpleName(),count+2,"Pass");
   	   	        
	skip=false;
   	fail=false;
    
}
@AfterTest
//Writing the final result for the TestCase whether it is passed of fail.
public void testReporter()
{
	if(istestpass)
	{
		TestUtil.reportDataSetResult(suiteFashionXls, "Test Cases", TestUtil.getRowNum(suiteFashionXls,this.getClass().getSimpleName()), "PASS");
	}else
		TestUtil.reportDataSetResult(suiteFashionXls, "Test Cases", TestUtil.getRowNum(suiteFashionXls,this.getClass().getSimpleName()), "Fail");
	
}
}
/*
@DataProvider
//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
public Object[][] getTestData()
{
     
	return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
}*/
				