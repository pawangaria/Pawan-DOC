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

public class GoToUserLoginPage {
	
	@BeforeClass
	public void setup()
	{
		System.out.println("SEt up method");
		HomePage home = new HomePage();
		
	}

	@Test
	public void goToLoginPage() throws InterruptedException
	{   
		
		System.out.println("In the login test case");
		Header header = new Header();
		LoginPage loginpage = header.clickOnSignInLink();
		HomePage homepage =loginpage.loginAndGoToHomePage("pawan1@gmail.com","p0513231068");
	    Assert.assertTrue(header.isUserLoggedIn());
	    Assert.assertTrue(homepage.isHomePageDisplayed());
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("in Tear down method");
		new TestCaseBaseStarter();
	}
}
