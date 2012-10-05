package com.Fb.TestCase.LogInLogOut;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Fb.Base.TestCaseBaseStarter;
import com.Fb.CommonHeader.Header;
import com.Fb.LoginPage.LoginPage;
import com.Fb.LoginPage.VerifyUserSignUPPage;
import com.Fb.homePage.HomePage;

public class VerifyUserSignUPCodeOrEmailFromSignUpPage {

	@BeforeClass
	public void setup()
	{
		System.out.println("SEt up method");
		HomePage home = new HomePage();
		
	}
	
	@Test
	public void VerifyUserSignUpOrEmailFromSignUP()
	{
		LoginPage loginpage = new Header().clickOnSignUpLink();
		VerifyUserSignUPPage verifyCodepage = loginpage.clickOnVerifyMobileLinkOnSignUP();
	     String verificationTExt = verifyCodepage.getPageVerificationText();
	    Assert.assertTrue(verificationTExt.contains("Verify"));
	    
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("in Tear down method");
		new TestCaseBaseStarter();
	}
	
	
	
}
