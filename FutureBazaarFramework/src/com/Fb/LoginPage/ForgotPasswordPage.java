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
public class ForgotPasswordPage extends Base{  
	
	public String ForgotPasswordPageVerificationText;
	/**
	 * Set the Verification Text for the Forgot Password page.
	 */
	 public ForgotPasswordPage() {
		
		this.ForgotPasswordPageVerificationText=new BasicUtilityMethods(OR.getProperty("FORGOT_PASSWORD_VERIFICATION_TEXT")).GetVerificationTextForPages();
	}
	 
	 /**
	  * Return the Forgot Password Page Verification Text 
	  * @return
	  */
	 public String getForgotPasswordPageVerificationText()
	 {
		 //Returning the text for the Forgot Password Page for Verification
		 return ForgotPasswordPageVerificationText;
	 }
	 
	 /**
	  * Enter the Email Id or the Mobile no in the Forgot Password 
	  * Page to get the Instruction for the password recovery.
	  * @param EmailOrMobile
	  */
	public void EnterEmailOrMbileNoForForgotPasswordInstruction(String EmailOrMobile)
	{
		driver.findElement(By.name(OR.getProperty("ENTER_EMAIL_MOBILE_FOR_INSTRUCTION_TEXTBOX"))).clear();
		driver.findElement(By.name(OR.getProperty("ENTER_EMAIL_MOBILE_FOR_INSTRUCTION_TEXTBOX"))).sendKeys(EmailOrMobile);
	}
	
	/**
	 * click on the Send Forgot Password Instruction Button
	 * On Forgot Password Page
	 */
	public void ClickSendInstructionButton()
	{
		driver.findElement(By.xpath(OR.getProperty("SEND_INSTRUCTION_BUTTON"))).click();
	}
	

}
