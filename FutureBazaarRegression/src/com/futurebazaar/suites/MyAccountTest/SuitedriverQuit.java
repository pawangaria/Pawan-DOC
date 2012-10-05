package com.futurebazaar.suites.MyAccountTest;

import org.testng.annotations.AfterSuite;

import com.futurebazaar.suites.PaymentPageTest.PaymentSuitebase;



public class SuitedriverQuit extends PaymentSuitebase {

	@AfterSuite(alwaysRun=true)
	public void quitdriver()
	{
		System.out.println("quitting the driver for the my Account Suite");
		driver.quit();
	}
}
