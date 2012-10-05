package com.futurebazaar.suites.KidsAndBabiesTest;


import org.testng.annotations.Test;
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

public class KidsAndBabies_TC1 extends KidsAndBabiesSuiteBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteKidsAndBabiesXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteKidsAndBabiesXls, "Test Cases",TestUtil.getRowNum(suiteKidsAndBabiesXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test case KidsAndBabies_TC1 ( KidsAndBabies)as the runmode is NO");
				throw new SkipException("Skipping KidsAndBabies_TC1 ( KidsAndBabies)as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteKidsAndBabiesXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void KidsAndBabiesLink_DD() throws InterruptedException
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
					 
			
           APP_LOGS.debug("****** Starting the Test for KidsAndBabies_TC1( KidsAndBabies outer link Check *******");
			
			String Xpath_Outer =OR.getProperty("KidsAndBabies_MAIN_LINK_XPATH");
			String Xpath_Block =OR.getProperty("KidsAndBabies_DROPDOWN_BLOCK_XPATH");
			//Calling the MenuMainLink function
			APP_LOGS.debug("Calling the MenuMainLink function");
			MenuInOutLink. MenuMainLink(Xpath_Outer, Xpath_Block);
			APP_LOGS.debug("***Completed the KidsAndBabies_TC1 (KidsAndBabies Outer Link Check) ******");
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the KidsAndBabies_TC1 (KidsAndBabies outer Link Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
		}
			
			


@Test
@AfterMethod
public void testdataReporter()
{//System.out.println("value of skip"+count+skip);
//System.out.println("test case fail"+count+fail);
//System.out.println("test case a"+count);
	if(skip)
		TestUtil.reportDataSetResult(suiteKidsAndBabiesXls,this.getClass().getSimpleName(),count+2,"Skip");
	else if(fail)
	{
    	TestUtil.reportDataSetResult(suiteKidsAndBabiesXls,this.getClass().getSimpleName(),count+2,"Fail");
        istestpass=false; //checking for the TestCase is failed or passed
	}
        else
       TestUtil.reportDataSetResult(suiteKidsAndBabiesXls,this.getClass().getSimpleName(),count+2,"Pass");
   	   	        
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
		TestUtil.reportDataSetResult(suiteKidsAndBabiesXls, "Test Cases", TestUtil.getRowNum(suiteKidsAndBabiesXls,this.getClass().getSimpleName()), "PASS");
	}else
		TestUtil.reportDataSetResult(suiteKidsAndBabiesXls, "Test Cases", TestUtil.getRowNum(suiteKidsAndBabiesXls,this.getClass().getSimpleName()), "Fail");
	
}
}
/*
@DataProvider
//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
public Object[][] getTestData()
{
     
	return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
}*/
				