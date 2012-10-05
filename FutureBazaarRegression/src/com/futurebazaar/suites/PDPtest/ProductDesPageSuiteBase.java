package com.futurebazaar.suites.PDPtest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.TestUtil;

public class ProductDesPageSuiteBase extends TestBase	{
	
    // check if the suite Product Description Page suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("in Product Description Page suite base class");
				APP_LOGS.debug("Checking Runmode of Product Description Page suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "ProductDescPageTest")){
					APP_LOGS.debug("Skipped Product Description Page Suite as the runmode was set to NO");
					throw new SkipException("Runmode of Product Description Page Test suite set to no. So Skipping all tests in Product Description Page Test");
				}
					//APP_LOGS.debug("Opening the browser for the given Product Description Page Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the Product Description Page test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(3000);
				
				APP_LOGS.debug("Runmode checked for Product Description Page Test, It is in runmode True.");
				
			}
			

}
