package com.futurebazaar.suites.AddToCartTest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.testing.util.TestUtil;

public class AddToCartSuitebase extends TestBase{
    // check if the suite My Account suite has to be Skipped
			@BeforeSuite()
			public void checkSuiteSkip() throws Exception{
				initialize();
				System.out.println("In AddTo Cart suite Base class");
				APP_LOGS.debug("Checking Runmode of AddTo Cart Suite");
				//value of the suite is passes manually so it should be changed for every suite.
				if(!TestUtil.isSuiteRunnable(suiteBseXls, "AddToCartTest")){
					APP_LOGS.debug("Skipped AddToCart Suite as the runmode was set to NO");
					throw new SkipException("Runmode of AddToCart suite set to no. So Skipping all tests in AddToCart Suite");
				}
					//APP_LOGS.debug("Opening the browser for the given My Account Suite");
					APP_LOGS.debug(">>>>>>>>> opening the Url for the AddToCart Suite test");
					getURLOpen();
				//TestBase.getURLOpen();
				Thread.sleep(3000);
				//User Log out from the Log out method if the user is logged in .
				  if(Loginlogout.checkuserloginStatus())
				  {
				  Loginlogout.logOut();
				  }
				
				APP_LOGS.debug("Runmode checked for AddToCartSuite Test, It is in runmode True.");
				
			}
			
	

}
