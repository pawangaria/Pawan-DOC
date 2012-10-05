package com.Fb.TestCase.LogInLogOut;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.Fb.Base.TestCaseBaseStarter;
import com.Fb.CommonHeader.Header;
import com.Fb.LoginPage.ForgotPasswordPage;
import com.Fb.LoginPage.LoginPage;
import com.Fb.homePage.HomePage;

public class CheckForgotPasswordLink {
	
	@BeforeClass
	public void setup()
	{
		System.out.println("SEt up method");
		HomePage home = new HomePage();
		
	}
	
	@Test
	public void CheckForgotPasswordLinkOnSignInPage()
	{   
		System.out.println("Forgot password testcase");
		LoginPage loginpage = new Header().clickOnSignInLink();
		ForgotPasswordPage forgotpassword = loginpage.clickOnForgotPasswordLink();
		String forgotpasswordText = forgotpassword.getForgotPasswordPageVerificationText();
		Assert.assertTrue(forgotpasswordText.contains("Forgot"), "Forgot password page is not displayed");
		
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("in Tear down method");
		new TestCaseBaseStarter();
	}

}
