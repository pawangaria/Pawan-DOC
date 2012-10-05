package com.futurebazaar.suites.LoginLogOutTest;


import org.testng.annotations.Test;
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
import com.futurebazaar.testing.util.BasicCommonTestUtils;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class LoginLogOutTest_TC1 extends LoginLogOutSuiteBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteLoginLogOutXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteLoginLogOutXls, "Test Cases",TestUtil.getRowNum(suiteLoginLogOutXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("Skipping test case LoginLogOutTest_TC1(Login/Logout from My Account) as the runmode is NO");
				throw new SkipException("Skipping this testcase LoginLogOutTest_TC1(Login/Logout from My Account) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteLoginLogOutXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void LogInLogOutCheck() throws InterruptedException
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
						 
			APP_LOGS.debug("****** Starting the Test for LoginLogOutTest_TC1(Login/Logout from My Account *******");
			
			// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			//User Log in from the LoginUser Method.
			  Loginlogout.loginUser(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"));
			  
			  APP_LOGS.debug("User Logged in Successfully now Loggin Out");
				
			  //User Log out from the Log out method.
			  if(Loginlogout.checkuserloginStatus())
			  {
			  Loginlogout.logOut();
			  }
			  APP_LOGS.debug("****** Completed the Test for LoginLogOutTest_TC1(Login/Logout from My Account Check *******");
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the LoginLogOutTest_TC1(Login/Logout from My Account Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
		}
			
					
		
		@Test
		@AfterMethod
		public void testdataReporter()
		{//APP_LOGS.debug("value of skip"+count+skip);
		//APP_LOGS.debug("test case fail"+count+fail);
		//APP_LOGS.debug("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suiteLoginLogOutXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteLoginLogOutXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteLoginLogOutXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
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
	    		TestUtil.reportDataSetResult(suiteLoginLogOutXls, "Test Cases", TestUtil.getRowNum(suiteLoginLogOutXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteLoginLogOutXls, "Test Cases", TestUtil.getRowNum(suiteLoginLogOutXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
}
