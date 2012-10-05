package com.futurebazaar.suites.PaymentPageTest;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.Loginlogout;

import com.futurebazaar.testing.util.TestUtil;

public class PaymentSuitebase extends TestBase{
	
	// Check if the Suite has to be Skipped or Not
	@BeforeSuite
		public void checkSuiteSkip() throws Exception{
			initialize();
			System.out.println("In Suite Base Class");
			APP_LOGS.debug("Checking Runmode of PaymentPageTest suite");
			//value of the suite is passes manually so it should be changed for every suite.
			if(!TestUtil.isSuiteRunnable(suiteBseXls, "PaymentPageTest")){
				APP_LOGS.debug("Skipped PaymentPageTest Suite as the runmode was set to NO");
				throw new SkipException("Runmode of PaymentPageTest set to no. So Skipping all tests in PaymentPageTest");
			}
			//opening the Url for the test in the suite.
			getURLOpen();
			APP_LOGS.debug("Runmode checked for PaymentPageTest, It is in runmode True.");
			
		}
			
		@AfterSuite(alwaysRun=true)
		public void suiteFinal()
		{	System.out.println("In after suite");
		APP_LOGS.debug("Logginout, if the any User is logged in, as the Suite Test is complete");
		if(TestUtil.isSuiteRunnable(suiteBseXls, "PaymentPageTest"))
		{
	      	if(Loginlogout.checkuserloginStatus())
	    	{
			Loginlogout.logOut();
		    }else
		     {
			APP_LOGS.debug("No user is logged in So Suite Test is complete");
		     }
		 }
		
		            }

}
