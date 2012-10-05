package com.futurebazaar.suites.GiftVouchersTest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.TestUtil;

public class GiftVouchersSuiteBase extends TestBase {

	@BeforeSuite()
	public void checkSuiteSkip() throws Exception{
		initialize();
		System.out.println("In GiftVouchers Suite class");
		APP_LOGS.debug("Checking Runmode of GiftVouchers Suite");
		//value of the suite is passes manually so it should be changed for every suite.
		if(!TestUtil.isSuiteRunnable(suiteBseXls, "GiftVouchersTest")){
		APP_LOGS.debug("Skipped GiftVouchers Test Suite as the runmode was set to NO");
			throw new SkipException("Runmode of GiftVouchers suite set to no. So Skipping all tests in GiftVouchers Test Suite Page");
		}
			//APP_LOGS.debug("Opening the browser for the given My Account Suite");
		APP_LOGS.debug(">>>>>>>>> opening the Url for the GiftVouchers Suite");
			getURLOpen();
		//TestBase.getURLOpen();
		Thread.sleep(3000);
		
		APP_LOGS.debug("Runmode checked for GiftVouchers Suite,It is in runmode True.");
		
	}
	/*
	@AfterSuite(alwaysRun=true)
	public void quitdriver()
	{
		System.out.println("in sfter suite");
		driver.quit();
	}*/
}
