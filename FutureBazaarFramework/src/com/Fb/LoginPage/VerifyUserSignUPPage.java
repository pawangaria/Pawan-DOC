package com.Fb.LoginPage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;

/**
 * Class for the Sign UP user registration verification page class.
 * @author PawaG
 *
 */
public class VerifyUserSignUPPage extends Base {

	/**
	 * Return the Verification text for the USer sign up page.
	 * by this text we can verify the page opened is correct.
	 * @return
	 */
	public String getPageVerificationText()
	{
		String UserSignUPVerificationPageText= driver.findElement(By.xpath(OR.getProperty("USER_SIGN_UP_VERIFICATION_TEXT"))).getText();
	    return UserSignUPVerificationPageText;
	
	}
	
	
}
