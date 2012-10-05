/**
 * 
 */
package com.Fb.LoginPage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * @author PawanGaria
 *
 */
public class ResetPasswordVerificationCode extends Base{
	public String ResetPasswordPageVerificationText;
	/**
	 * Set the Verification Text for the Forgot Password Verification Code Check page.
	 */
	 public ResetPasswordVerificationCode() {
		
		this.ResetPasswordPageVerificationText=new BasicUtilityMethods(OR.getProperty("FORGOT_PASSWORD_VERIFICATION_TEXT")).GetVerificationTextForPages();
	  }
	 
	 /**
	  * Enter he verification code which is sent to the user By Email Or mobile
	  * depending upon the input given by the user.
	  * @param verificationCODE
	  */
	 public void enterVerificationCodeSendToYouForResetPassword(String verificationCODE)
	 {
		 driver.findElement(By.id(OR.getProperty("RESET_PASSWORD_VERIFICATION_CODE_TEXTBOX"))).clear();
		 driver.findElement(By.id(OR.getProperty("RESET_PASSWORD_VERIFICATION_CODE_TEXTBOX"))).sendKeys(verificationCODE);
	 }
     
	 /**
	  * Click on the Submit button in Reset Password verification Code page
	  * This Method is used to take user to the Reset Current Password Page if 
	  * user Enters the correct verification Code.
	  * @return
	  */
	 public void EnterSubmitButtonOnResetPasswordVerification()
	 {
		driver.findElement(By.id(OR.getProperty("RESET_PASSWORD_VERIFICATION_CODE_SUBMIT_BUTTON"))).click();
	 }
}
