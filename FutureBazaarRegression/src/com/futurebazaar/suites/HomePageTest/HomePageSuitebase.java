package com.futurebazaar.suites.HomePageTest;



import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.Loginlogout;

import com.futurebazaar.testing.util.TestUtil;

public class HomePageSuitebase extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("in Home Page suite base class");
				APP_LOGS.debug("Checking Runmode of HomePageTest suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "HomePageTest")){
					APP_LOGS.debug("Skipped HomePageTest Suite as the runmode was set to NO");
					throw new SkipException("Runmode of HomePageTest suite set to no. So Skipping all tests in PaymentPageTest");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the Home Page test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(3000);
				
				APP_LOGS.debug("Runmode checked for HomePageTest, It is in runmode True.");
				
			}
			

}
