package com.Fb.guestLoginPage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;
import com.Fb.ShippingAddressPage.ShippingAddressPage;

public class GuestLoginPage extends Base{
	
       public void verifyTheGuest()
       {
    	   System.out.println("user is on guest login page");
    	   
       }
       
       /**
        * Return the Verification text from the Guest Login Page.which is used to verify the 
        * PAge is loaded or not ?
        * @return
        */
       public String getGuestLoginPageVerificationText()
       {
    	   String GuestPageText = driver.findElement(By.xpath(OR.getProperty("GUEST_LOGIN_PAGE_VERIFICATION_TEXT"))).getText();
         return GuestPageText;
       }
       
       
       /**
        * Enter user name which can be an Email ID or Mobile NO.
        * @param username
        */
       public void enterUserNameOrEmailForGuestLogin(String username)
       {
    	   driver.findElement(By.id(OR.getProperty("USER_EMAIL_TEXTBOX"))).clear();
    	   driver.findElement(By.id(OR.getProperty("USER_EMAIL_TEXTBOX"))).sendKeys(username);
       }
       
       /**
        * Click on the check box, if the user is already a registered User and want to enter the 
        * password for his login, after checking this Password field will be displayed. 
        *  
        */
       public void checkAlreadyACustomerCheckBox()
       {
    	   driver.findElement(By.id(OR.getProperty("ALREADY_CUSTOMER_CHECK_BOX"))).click();
       
       }
       
       /**
        *Enter Password in the Password field if the Check box is checked. 
        * @param password
        */
       public void enterPasswordForGuestLogin(String password)
       {
    	   driver.findElement(By.id(OR.getProperty("PASSWORD_TEXTBOX"))).clear();
    	   driver.findElement(By.id(OR.getProperty("PASSWORD_TEXTBOX"))).sendKeys(password);
       }
       
       /**
        * Click the continue without entering password link for continue to shipping Page
        * This link is displayed after checking the already user check box.
        */
       public void ClickContinueLinkWithOutEnteringPassword()
       {
    	   driver.findElement(By.id(OR.getProperty("CONTINUE_TO_PURCHASE_LINK"))).click();
       }
       
       /**
        * Click on the Continue Button on the Guest login page to go to the Shipping Page.
        * @return
        */
       public ShippingAddressPage clickOnContinueButton()
       {
    	   driver.findElement(By.cssSelector(OR.getProperty("CONTINUE_BUTTON"))).click();
           return new ShippingAddressPage();
       }
       
}
