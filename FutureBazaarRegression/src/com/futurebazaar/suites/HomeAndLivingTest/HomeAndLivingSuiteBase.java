package com.futurebazaar.suites.HomeAndLivingTest;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;

import com.futurebazaar.testing.util.TestUtil;

public class HomeAndLivingSuiteBase extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In HomeAndLiving Suite Base class");
				APP_LOGS.debug("Checking Runmode of HomeAndLivingTest suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "HomeAndLivingTest")){
					APP_LOGS.debug("Skipped HomeAndLiving Suite as the runmode was set to NO");
					throw new SkipException("Runmode of HomeAndLiving suite set to no. So Skipping all tests in HomeAndLiving Page");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the HomeAndLiving test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(2000);
				
				APP_LOGS.debug("Runmode checked for HomeAndLivingTest, It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
