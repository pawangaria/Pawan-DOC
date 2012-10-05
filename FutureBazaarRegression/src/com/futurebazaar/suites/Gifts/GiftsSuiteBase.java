package com.futurebazaar.suites.Gifts;



import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;

import com.futurebazaar.testing.util.TestUtil;

public class GiftsSuiteBase  extends TestBase{
	
		        // check if the suite Gifts suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In Gifts Suite Base class");
				APP_LOGS.debug("Checking Runmode of Gifts Test suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "GiftsTest")){
					APP_LOGS.debug("Skipped Gifts Test Suite as the runmode was set to NO");
					throw new SkipException("Runmode of Gifts Test suite set to no. So Skipping all tests in Gifts Test Suite");
				}
					//APP_LOGS.debug("Opening the browser for the given Gifts Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the Gifts Suite");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(2000);
				
				APP_LOGS.debug("Runmode checked for Gifts Suite, It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
