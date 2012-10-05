package com.Fb.TestCase.BigBazaar;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BigBazaar.BBLandingPage.BigBazaarLandingPage;
import com.Fb.CommonHeader.Header;
import com.Fb.homePage.HomePage;

public class ClickBigBazaarLinkAndGetTheWindowTitle {
	
	@BeforeClass
	public void setup()
	{
		System.out.println("SEt up method");
		HomePage home = new HomePage();
		
	}
	
	@Test
	public void clickBigBazaarLinkAndCheckForWindowTitle() throws Exception
	{   
		
		
		BigBazaarLandingPage bigbazaar = new Header().clickTakeMeToBigBazaarComLink();
	    bigbazaar.switchToBigBazaarWindow(); 
	   
		System.out.println("is opened"+bigbazaar.isBigBazaarLandingPageOpened());
		System.out.println(bigbazaar.getBigBazaarWindowTitle()); 
		 bigbazaar.clickStartButton();
		 System.out.println(bigbazaar.getMessageAfterClickingStartOnBigBazaarLandingPage());
		 bigbazaar.enterPINCodeForShopping("262501");
		 bigbazaar.clickStartButton();
		 String jj = bigbazaar.getMessageAfterClickingStartOnBigBazaarLandingPage();
	     Assert.assertTrue(jj.equalsIgnoreCase("We haven't come to your pincode yet"));
		 bigbazaar.closeBigBazaarWindowAndGoBackToFutureBazaar();
	
	}
	
	
	

}
