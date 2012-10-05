package com.futurebazaar.suites.NewArrivalsTest;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;

import com.futurebazaar.testing.util.TestUtil;

public class NewArrivalsSuitesBase  extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In NewArrivals Suite Base class");
				APP_LOGS.debug("Checking Runmode of NewArrivals Test suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "NewArrivalsTest")){
					APP_LOGS.debug("Skipped NewArrivals Test Suite as the runmode was set to NO");
					throw new SkipException("Runmode of NewArrivals Test suite set to no. So Skipping all tests in NewArrivals Test Suite");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the NewArrivals Suite");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(2000);
				
				APP_LOGS.debug("Runmode checked for NewArrivals Suite, It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
