package com.Fb.Base;

import org.apache.log4j.Logger;

import com.Fb.AddToCart.ShoppingCartPage;
import com.Fb.CommonHeader.Header;
import com.Fb.homePage.HomePageMenu;

/**
 * Class is used to check for the restate of every Testcase's and 
 * Configure the portal to start as a fresh instance.
 * so we are going to clean the ITem cart and log out the use and click on the Home page Logo.
 * @author PawanGaria
 *
 */
public class TestCaseBaseStarter {
	Header header = new Header();
	HomePageMenu menu = new HomePageMenu();
	Logger logger = Logger.getLogger(TestCaseBaseStarter.class);
	
	/**
	 * constructor to configure the state for the Next test case to start.
	 * calling methods for home page, logout and item clean up in cart.
	 */
	public TestCaseBaseStarter()
	{
		GotoTheHomePage();
		CheckForTheUserLogIn();
		CleanItemCart();
		
	}
    
	/**
	 * click on home page log to go to home page
	 */
	
	public void GotoTheHomePage()
	{
		logger.info("Clicking On Home Page Logo for Next Test Case");
		header.clickHomePageLogoOnHeader();
		
	}
	
	/**
	 * Check for the user log in or log out
	 */
	public void CheckForTheUserLogIn()
	{
		logger.info("Checking for user is logged in or not for Next Test case");
		boolean checkuserLogIn= header.isUserLoggedIn();
	    if(checkuserLogIn)
	    {
	    	logger.info("User log out ");
	    	header.userSignOut();
	    }
	    
	}
	
	/**
	 * Method to clean the Cart items 
	 */
	public void CleanItemCart()
	{
		logger.info("Cleaning the shopping cart for next Test case");
		if(menu.isShoppingCartEmpty())
		{
			return;
		}else{
		
			ShoppingCartPage cart = menu.clickOnShoppingCartIconToGoToShoppingCartPage();
			cart.removeAllShoppingCartItems();
			if(!cart.checkShoppingCartEmptyMessageOnCartPage().equalsIgnoreCase("null"));
			{
				cart.clickStartShoppingLinkWhenCartIsEmpty();
			}
			
			
		}
		
	}
	
}
