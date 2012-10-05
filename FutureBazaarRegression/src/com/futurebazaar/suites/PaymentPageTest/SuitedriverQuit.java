package com.futurebazaar.suites.PaymentPageTest;

import org.testng.annotations.AfterSuite;



public class SuitedriverQuit extends PaymentSuitebase {

	@AfterSuite(alwaysRun=true)
	public void quitdriver()
	{
		System.out.println("quitting the driver");
		driver.quit();
	}
}
