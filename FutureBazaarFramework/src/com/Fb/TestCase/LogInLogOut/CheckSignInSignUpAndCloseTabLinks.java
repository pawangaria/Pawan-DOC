package com.Fb.TestCase.LogInLogOut;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Fb.Base.TestCaseBaseStarter;
import com.Fb.CommonHeader.Header;
import com.Fb.LoginPage.LoginPage;
import com.Fb.homePage.HomePage;

public class CheckSignInSignUpAndCloseTabLinks {
     
	@BeforeClass
	public void setup()
	{
		System.out.println("SEt up method");
		HomePage home = new HomePage();
		
	}
	
	@Test
	public void CheckSignInSignUpAndCloseTabLinks() throws Exception
	{   System.out.println("In Sign up, TAb check method ");
		LoginPage loginpage = new Header().clickOnSignInLink();
		
		loginpage.clickOnSignUpTAB();
		Thread.sleep(3000);
		String ConfirmPasswordText = loginpage.getVerifyConfirmPasswordTextForSignUpTab();
		System.out.println(ConfirmPasswordText);
		Assert.assertTrue(ConfirmPasswordText.contains("Confirm Password"));
        
		loginpage.clickONSignInTAB();
		Thread.sleep(3000);
		String PasswordText = loginpage.getVerifyPasswordTextForSignInTab();
		Assert.assertTrue(PasswordText.contains("Password"));

		loginpage.closeLoginPageLightBOX();
	    
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("in Tear down method");
		new TestCaseBaseStarter();
	}
}
