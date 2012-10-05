package com.Fb.PDPpage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;
import com.Fb.LoginPage.LoginPage;

public class CheckDeliveryDatePage extends Base {
	
	public String getCheckDeliveryDatePageVerificationText()
	{
		String CheckDeliveryPage = driver.findElement(By.cssSelector(OR.getProperty("CHECK_DELIVERY_PAGE_VERIFICATION_TEXT"))).getText();
	     return CheckDeliveryPage;
	}
    
	public void closeCheckDeliveryDatePOPupPage()
	{
		new LoginPage().closeLoginPageLightBOX();
	}
	
	public void enterPINcodetoTheTextBox(String AreaPinNO)
	{
		driver.findElement(By.id(OR.getProperty("ENTER_PIN_CODE_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("ENTER_PIN_CODE_TEXTBOX"))).sendKeys(AreaPinNO);
	}
	
	public void clickOnSubmitButtonForDeliveryDateCheck() 
	{
		driver.findElement(By.id(OR.getProperty("SUBMIT_BUTTON"))).click();
	}
	
	public String getTheResponseAfterClickingSubmitInCheckDeliveryPage()
	{
		String ResponseText = driver.findElement(By.id(OR.getProperty("RESPONSE_MESSAGE"))).getText();
		return ResponseText;
	}
}
