package com.Fb.LoginPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;
import com.Fb.Base.TestCaseBaseStarter;
import com.Fb.TestCase.LogInLogOut.VerifyUserSignUPCodeOrEmailFromSignUpPage;
import com.Fb.homePage.HomePage;


public class LoginPage extends Base{
	
	Logger logger = Logger.getLogger(LoginPage.class);
	/**
	 * Enter the user name in the Username field 
	 * in the login page for the user
	 * @param login
	 */
	public void enterUserName(String login)
	{//Entering the User Name 
		logger.info("Enter user Name in sign in page");
		driver.findElement(By.id(OR.getProperty("USER_NAME"))).clear();
		driver.findElement(By.id(OR.getProperty("USER_NAME"))).sendKeys(login);
	}
	/**
	 * Enter the password in the password field
	 * in the login page
	 * @param pass
	 */
	public void enterPassword(String pass)
	{//Enter Password
		logger.info("Enter Password in sign in page");
		driver.findElement(By.id(OR.getProperty("PASSWORD"))).clear();
		driver.findElement(By.id(OR.getProperty("PASSWORD"))).sendKeys(pass);
	}
	/**
	 * Click the SignIN Button to login to the website.
	 */
	public void clickSignINbutton()
	{//Click on the Sign IN Button
		logger.info("Click on Sign IN On user sign in page");
		driver.findElement(By.id(OR.getProperty("LOGIN_BUTTON"))).click();
	}
	
	/**
	 * This method enters the username and password in the 
	 * login page and click on the Sign in button to log in 
	 * @param usename
	 * @param passowrd
	 * @return
	 * @throws InterruptedException
	 */
	
	public HomePage loginAndGoToHomePage(String usename, String passowrd) throws InterruptedException
   	{   //Wait for the Light Box to come up for the Testing
		//waitForTheLoginPopUPtoCome();
		
		//Entering the Username and password by using the Methods in the Login page
		logger.info("Method to log in and opening the Home page");
   	    enterUserName(usename);
		enterPassword(passowrd);
		clickSignINbutton();
		
		// Returning the page object for the HOme page after login.  
		return PageFactory.initElements(driver,HomePage.class);
	}
	
	/**
	 * method to click on the Forgot password link on the sign In page
	 * which takes to user to the forgot password page
	 * @return
	 */
	public ForgotPasswordPage clickOnForgotPasswordLink()
	 {
		 logger.info("Click on the Forgot Password sign in page");
		 driver.findElement(By.id(OR.getProperty("FORGOT_PASSWORD_link"))).click();
		 return new ForgotPasswordPage();
	 }
	
	 /**
	  * Click on the Sign UP tab option in the Login page
	  */
     public void clickOnSignUpTAB()
     {
    	 driver.findElement(By.id(OR.getProperty("SIGN_UP_TAB_LINK"))).click();
    	 
     }
     
     /**
      * Click on the Sign IN tab on sign up page
      */
     public void clickONSignInTAB()
     {
    	 driver.findElement(By.id(OR.getProperty("SIGN_IN_TAB_LINK"))).click();
    	 
     }

     /**
      * Returns the Password Text from the Sign IN tab of 
      * User Sign In page.
      * @return
     * @throws Exception 
      */
     public String getVerifyPasswordTextForSignInTab() throws Exception
     {
    	new CommonActionUtil().waitForMe(By.id(OR.getProperty("PASSWORD_TEXT_SIGN_IN_TAB")),10);
    	
    	String passwordText = driver.findElement(By.id(OR.getProperty("PASSWORD_TEXT_SIGN_IN_TAB"))).getText();
         return passwordText;
     }

     /**
      * Returns the Confirm Password Text from the Sign UP tab of 
      * User Sign UP page
      * @return
     * @throws Exception 
      */
     public String getVerifyConfirmPasswordTextForSignUpTab() throws Exception
     {
    	// new CommonActionUtil().waitForAnElementToPresentBy(By.xpath(OR.getProperty("CONFIRM_PASSWORD_TEXT_SIGN_UP_TAB")));
    	 new CommonActionUtil().waitForMe(By.xpath(OR.getProperty("CONFIRM_PASSWORD_TEXT_SIGN_UP_TAB")),10);
     	
    	 //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("LOGIN_PAGE_VERIFICATION"))));
    	 String confirmPasswordText = driver.findElement(By.xpath(OR.getProperty("CONFIRM_PASSWORD_TEXT_SIGN_UP_TAB"))).getText();
      
    	 return confirmPasswordText;
     }
     
     /**
      * Click on the Already signed up, go to the user sign up verification code page
      * @return
      */
     public VerifyUserSignUPPage clickOnVerifyMobileLinkOnSignUP()
     {
    	 driver.findElement(By.linkText(OR.getProperty("USER_VERIFICATION_LINK"))).click();
          return new VerifyUserSignUPPage();
     }
     
     

     /**
      * Close the light box opened for the User Sign UP and Sign IN.
      * After closing return to the page opened before clicking signIN/Sign UP
      */
     public void closeLoginPageLightBOX()
     {
    	 driver.findElement(By.cssSelector(OR.getProperty("CLOSE_LOGIN_PAGE_LIGHTBOX"))).click();
                 	 
     }
}
