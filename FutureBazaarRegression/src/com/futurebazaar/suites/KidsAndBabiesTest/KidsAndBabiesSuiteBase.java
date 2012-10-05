package com.futurebazaar.suites.KidsAndBabiesTest;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;

import com.futurebazaar.testing.util.TestUtil;

public class KidsAndBabiesSuiteBase extends TestBase{
	
		        // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In KidsAndBabies Suite Base class");
				APP_LOGS.debug("Checking Runmode of KidsAndBabies suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "KidsAndBabiesTest")){
				APP_LOGS.debug("Skipped KidsAndBabies Suite as the runmode was set to NO");
					throw new SkipException("Runmode of KidsAndBabies suite set to no. So Skipping all tests in KidsAndBabies Suite");
				}
					//APP_LOGS.debug("Opening the browser for the given My KidsAndBabies Suite");
				APP_LOGS.debug(">>>>>>>>> opening the Url for the KidsAndBabies Test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(3000);
				
				APP_LOGS.debug("Runmode checked for KidsAndBabies Test,It is in runmode True.");
				
			}
			/*
			@AfterSuite(alwaysRun=true)
			public void quitdriver()
			{
				System.out.println("in sfter suite");
				driver.quit();
			}*/
}
