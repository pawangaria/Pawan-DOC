package com.futurebazaar.suites.PageFooterTest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.TestUtil;

public class PageFooterSuitebase extends TestBase{

	// check if the suite My Account suite has to be Skipped
				@BeforeSuite()
				public void checkSuiteSkip() throws Exception{
					initialize();
					System.out.println("In PageFooter suite Base class");
					APP_LOGS.debug("Checking Runmode of FutureBazaar Page Footer Suite");
					//value of the suite is passes manually so it should be changed for every suite.
					if(!TestUtil.isSuiteRunnable(suiteBseXls, "PageFooterTest")){
						APP_LOGS.debug("Skipped FutureBazaar Page Footer Suite as the runmode was set to NO");
						throw new SkipException("Runmode of FutureBazaar Page Footer suite set to no. So Skipping all tests in FutureBazaarPage Footer Suite");
					}
						//APP_LOGS.debug("Opening the browser for the given My Account Suite");
						APP_LOGS.debug(">>>>>>>>> opening the Url for the FutureBazaar Page Footer Suite test");
						getURLOpen();
					//TestBase.getURLOpen();
					Thread.sleep(3000);
					
					APP_LOGS.debug("Runmode checked for FutureBazaar Page Footer Suite, It is in runmode True.");
					
				}
				
	
}
