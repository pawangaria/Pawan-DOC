package com.futurebazaar.suites.MyAccountTest;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.Loginlogout;

import com.futurebazaar.testing.util.TestUtil;

public class MyAccountSuitebase extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("in My Account suite base class");
				APP_LOGS.debug("Checking Runmode of MyaccountTest suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "MyAccountTest")){
					APP_LOGS.debug("Skipped MyAccountTest Suite as the runmode was set to NO");
					throw new SkipException("Runmode of MyAccountTest suite set to no. So Skipping all tests in PaymentPageTest");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the account test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(3000);
				  //User Log out from the Log out method if the user is logged in .
				  if(Loginlogout.checkuserloginStatus())
				  {
				  Loginlogout.logOut();
				  }
				
				APP_LOGS.debug("Runmode checked for MyAccountTest, It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
