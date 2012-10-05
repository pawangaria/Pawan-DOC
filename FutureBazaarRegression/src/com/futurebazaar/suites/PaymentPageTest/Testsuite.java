package com.futurebazaar.suites.PaymentPageTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.futurebazaar.testing.util.TestUtil;

public class Testsuite extends PaymentSuitebase{
	
	@Test(dataProvider="getTestData")
    public void testing(String aa,String ee,String dd,String cc,String bb)
	{
		System.out.println(aa);
		System.out.println(ee);
		System.out.println(dd);
	}
	
	 @DataProvider
	    public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suitePaymentPageXls,this.getClass().getSimpleName());
		}
	
	
}
