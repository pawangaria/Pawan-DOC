package com.futurebazaar.suites.SupermarketTest;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;

import com.futurebazaar.testing.util.TestUtil;

public class SupermarketSuitesBase  extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In SupermarketTest Suite Base class");
				APP_LOGS.debug("Checking Runmode of SupermarketTest suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "SupermarketTest")){
					APP_LOGS.debug("Skipped Supermarket Suite as the runmode was set to NO");
					throw new SkipException("Runmode of Supermarket suite set to no. So Skipping all tests in Supermarket Page");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the Supermarket test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(2000);
				
				APP_LOGS.debug("Runmode checked for Supermarket, It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
