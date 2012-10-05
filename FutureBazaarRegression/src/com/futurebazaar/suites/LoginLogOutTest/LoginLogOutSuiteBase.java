package com.futurebazaar.suites.LoginLogOutTest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.testing.util.TestUtil;

public class LoginLogOutSuiteBase  extends TestBase {
	@BeforeSuite()
	public void checkSuiteSkip() throws Exception{
		initialize();
		System.out.println("In Login LogOut Suite class");
		APP_LOGS.debug("Checking Runmode of Login LogOut Suite");
		//value of the suite is passes manually so it should be changed for every suite.
		if(!TestUtil.isSuiteRunnable(suiteBseXls, "LoginLogoutTest")){
		APP_LOGS.debug("Skipped Login LogOut Test Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Login LogOut suite set to no. So Skipping all tests in Login LogOut Suite");
		}
			//APP_LOGS.debug("Opening the browser for the given LoginLogOut Suite");
		APP_LOGS.debug(">>>>>>>>> opening the Url for the LoginLogOut Suite");
			getURLOpen();
		//TestBase.getURLOpen();
		Thread.sleep(3000);
		  //User Log out from the Log out method if the user is logged in .
		  if(Loginlogout.checkuserloginStatus())
		  {
		  Loginlogout.logOut();
		  }
		
		APP_LOGS.debug("Runmode checked for LoginLogOut Suite,It is in runmode True.");
		
	}
	/*
	@AfterSuite(alwaysRun=true)
	public void quitdriver()
	{
		System.out.println("in sfter suite");
		driver.quit();
	}*/
}
