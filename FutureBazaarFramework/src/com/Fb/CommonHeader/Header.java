package com.Fb.CommonHeader;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.BigBazaar.BBLandingPage.BigBazaarLandingPage;
import com.Fb.AddToCart.ShoppingCartPage;
import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;
import com.Fb.Base.HelperUtil;
import com.Fb.Base.TestCaseBaseStarter;
import com.Fb.CommonHeader.MyAccountPage.AddressBookPage;
import com.Fb.CommonHeader.MyAccountPage.ManagePasswordPage;
import com.Fb.CommonHeader.MyAccountPage.MyOrdersPage;
import com.Fb.CommonHeader.MyAccountPage.MyProfilePage;
import com.Fb.CommonHeader.MyAccountPage.MyWishListPage;
import com.Fb.LoginPage.LoginPage;
import com.Fb.TrackOrderPage.TrackOrderPage;
import com.Fb.homePage.HomePage;

/**
 * This is the Page header class which is common through out the pages
 * it contains like Home page Logo , Search text box , My account.
 * so every page object just calls the functions from this common header class 
 * @author PawanGaria
 *
 */
public class Header extends Base{
	public WebDriver driver = HelperUtil.driver;
	Logger logger = Logger.getLogger(TestCaseBaseStarter.class);
	/**
	 * Method to click on the FutureBazaar logo on the Header and
	 * Return the home page of the website.
	 * @return
	 */
	public HomePage clickHomePageLogoOnHeader()
	{   logger.info("Clicking on the Home page logo");
		driver.findElement(By.id(OR.getProperty("FB_HOME_PAGE_LOGO_ID"))).click();
		return PageFactory.initElements(driver,HomePage.class);
	}
	
	/**
	 * Search Some item by entering the Item in 
	 * Search Field and Clicking the search button.
	 * @param SearchITEM
	 */
	public void SearchSomeItem(String SearchITEM)
	{
		logger.info("Searching the Items by search bar");
		EnterSearchItemInSearchBox(SearchITEM);
		ClickSearchButton();
		// how to use the Property file elements as the files object are in base class
		
	}
	
	/**
	 * Enter item name in search Field.
	 * @param searchItem
	 */
	public void EnterSearchItemInSearchBox(String searchItem)
	{
		logger.info("Enter Search item In search Bar");
		driver.findElement(By.id(OR.getProperty("SEARCH_BOX"))).sendKeys(searchItem);
		
	}
	
	/**
	 * Clicking the Search Button.
	 */
	public void ClickSearchButton()
	{
		logger.info("Click on Search Button");
		driver.findElement(By.id(OR.getProperty("SEARCH_BUTTON"))).click();
	}
	
	/**
	 * Click on the Sign in Link on Header	
	 * @return
	 */
	public LoginPage clickOnSignInLink()
	{
		driver.findElement(By.linkText(OR.getProperty("SIGN_IN_LINK"))).click();
    	return PageFactory.initElements(driver,LoginPage.class);
   
	}
	
	/**
	 * Click on the Sign UP Link on the Header
	 * To Sign up a new user 
	 * @return
	 */
	public LoginPage clickOnSignUpLink()
	{
		driver.findElement(By.linkText(OR.getProperty("SIGN_UP_LINK"))).click();
    	return PageFactory.initElements(driver,LoginPage.class);
   
	}
        /**
         * Clicking on the My Account Link on Home Page
         * @return
         */
	    public void clickMyAccountLink() 
	    {
	    	//verifyHomePage();
	    	driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
	    	
	    }
	    
	    /**
	     * Clicking on the Sign Out Link, but the link should be visible
	     * @return
	     */
	    public HomePage clickOnSignOut()
	    {
	    	driver.findElement(By.id(OR.getProperty("SIGN_OUT_LINK"))).click();
	    	return PageFactory.initElements(driver,HomePage.class);
	    	  
	    }
	    /**
	     * Method used to Sign Out the User after clicking My account and than Sign Out Link
	     * @return
	     */
	    public HomePage userSignOut()
	    {
	    	clickMyAccountLink();
	    	clickOnSignOut();
	    	return PageFactory.initElements(driver,HomePage.class);
	    }
	    
	    /**
	     * Returning the String after User logged in with Hi, in the String
	     * If the user is not loged in the Element can not be found exception is thrown, so returning the null value for the exception.
	     * @return
	     */
	    public String ReturnMessageForTheLoggedInUser()
		{//
	    	logger.info("Returning the Hi Text after user log in");
	    	try{
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("AFTER_LOGIN_USER_VERIFICATION_TEXT")))); 
	    	String LoggedINusernameString = driver.findElement(By.xpath(OR.getProperty("AFTER_LOGIN_USER_VERIFICATION_TEXT"))).getText();
			System.out.println("The String for the User Login"+LoggedINusernameString);
			return LoggedINusernameString;
	    	}catch(Exception e)
	    	{
	    		return "No Login";
	    	}
	    	
		}
	    
	    /**
	     * click on the Track Order link and Return the Track Order Page.
	     * @return 
	     */
	    public TrackOrderPage ClickOnTrekOrderLink()
	    {
	    	logger.info("Clicking on the Track Order Link on Header");
	    	driver.findElement(By.linkText(OR.getProperty("TRACK_ORDER"))).click();
	    	return PageFactory.initElements(driver,TrackOrderPage.class);
	    }
	    
	    /**
	     * click on the Never Miss a deal link present on the Header.
	     * After clicking on the link it opens a window to enter user id and Phone no.
	     */
	    public NeverMissADealWindow clickOnNeverMissADealLink()
	    {
	    	logger.info("Clicking on the Never Miss a Deal Link on Header");
	    	driver.findElement(By.linkText(OR.getProperty("NEVER_MISS_A_DEAL"))).click();
	    	return PageFactory.initElements(driver,NeverMissADealWindow.class);
	    }
	    
	    
	    /**
	     * Click on the my Orders link present in the My Account Link drop down
	     * (This is accessible after clicking on My Account)
	     */
	    public MyOrdersPage clickOnMyOrdersLinkInMyAccount()
	    {
	    	logger.info("Clicking on the My Orders LInk In My Account on Header");
	    	driver.findElement(By.linkText(OR.getProperty("MY_ORDERS"))).click();
	    	return PageFactory.initElements(driver,MyOrdersPage.class);
	    }
	    
	    /**
	     * Click on the my WishList link present in the My Account Link drop down
	     * (This is accessible after clicking on My Account)
	     */
	    public MyWishListPage clickOnMyWishListLinkInMyAccount()
	    {
	    	logger.info("Clicking on the MY Whish List Link in My Account on Header");
	    	driver.findElement(By.linkText(OR.getProperty("MY_WISHLIST"))).click();
	    	return PageFactory.initElements(driver,MyWishListPage.class);
	    }
	    
	    /**
	     * Click on the my Profile link present in the My Account Link drop down
	     * (This is accessible after clicking on My Account)
	     */
	    public MyProfilePage clickOnMyProfileLinkInMyAccount()
	    {
	    	logger.info("Clicking on the MY Profile Link in My Account on Header");
	 	    	
	    	driver.findElement(By.linkText(OR.getProperty("MY_PROFILE"))).click();
	    	return PageFactory.initElements(driver,MyProfilePage.class);
	    }
	    
	    /**
	     * Click on the my Address Book link present in the My Account Link drop down
	     * (This is accessible after clicking on My Account)
	     */
	    public AddressBookPage clickOnAddressBookLinkInMyAccount()
	    {   
	    	logger.info("Clicking on the MY Address Book Link in My Account on Header");
	 	   
	    	driver.findElement(By.linkText(OR.getProperty("ADDRESS_BOOK"))).click();
	    	return PageFactory.initElements(driver,AddressBookPage.class);
	    }
	    
	    /**
	     * Click on the Manage Password Link present in the My Account Link drop down
	     * (This is accessible after clicking on My Account)
	     */
	    public ManagePasswordPage clickOnManagePasswordLinkInMyAccount()
	    {
	    	logger.info("Clicking on the Manage Password Link in My Account on Header");
	 	   
	    	driver.findElement(By.linkText(OR.getProperty("MANAGAE_PASSWORD"))).click();
	    	return PageFactory.initElements(driver,ManagePasswordPage.class);
	    }
	    
	    /**
	     * To check if the user is logged in or not.
	     * Method returns true of the user is already logged in 
	     * 
	     * @return
	     */
	    public boolean isUserLoggedIn()
	    {
	    	logger.info("Checking for the is user logged in or not ??");
	 	   
	    	if(ReturnMessageForTheLoggedInUser().contains("Hi"))
	    	{
	    	return true;          // Return True if the User is already logged in. 
	    	}
	    	
	    	return false;        //Return the false if user is not logged In and the value is null.
	    }
       
	    
	    /**
	     * Click on the Take me to The Big BAzaar link on Header which will open a Big Bazaar.con 
	     * in a new tab or new window.  
	     * @return
	     */
	    public BigBazaarLandingPage clickTakeMeToBigBazaarComLink()
	    {
	    	driver.findElement(By.linkText(OR.getProperty("TAKE_ME_TO_BIGBAZAAR_LINK"))).click();
	    	return new BigBazaarLandingPage();
	    }
	   
}
