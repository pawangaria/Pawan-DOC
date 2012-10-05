package com.BigBazaar.BBLandingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;
import com.Fb.CommonHeader.Header;

public class BigBazaarLandingPage extends Base {
	
	public boolean isBigBazaarLandingPageOpened()
	{
		//String BigBazaarVerification = driver.getCurrentUrl();
		String BigBazaarVerification = driver.findElement(By.id("logo")).getAttribute("title");
		System.out.println("value"+ BigBazaarVerification);
		if(BigBazaarVerification.contains("Big Bazaar"))
		{
			return true;
		}else
			return false;
	}
     
	/**
	 * Get the title of the BigBazaar.com
	 * @return
	 */
	public String getBigBazaarWindowTitle() 
	{
		String Title = driver.getTitle();
		return Title;
	}
	
	/**
	 * Enter PIn code in the text field to start shopping and going to the home page of big bazaar. 
	 * @param PINcode
	 */
	public void enterPINCodeForShopping(String PINcode)
	{
		driver.findElement(By.id(OR.getProperty("SHOP_ON_PINCODE_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("SHOP_ON_PINCODE_TEXTBOX"))).sendKeys(PINcode);
	}
	/**
	 * Click on the Start button present in the Landing page, after entering PIN or with out entering PIN
	 * 
	 */
	public void clickStartButton()
	{
		driver.findElement(By.xpath(OR.getProperty("START_BUTTON"))).click();
	}
	
	/**
	 * Return the Response message after clicking the start button on big bazaar.
	 * Message can be depending upon the pin code you are entering. 
	 * @return
	 * @throws Exception 
	 */
	public String getMessageAfterClickingStartOnBigBazaarLandingPage() throws Exception
	{
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("RESONSE_MESSAGE_AFTER_START_CLICK"))));
		new CommonActionUtil().waitForAnElementToPresentBy(By.id(OR.getProperty("RESONSE_MESSAGE_AFTER_START_CLICK")));
		String BBmessage = driver.findElement(By.id(OR.getProperty("RESONSE_MESSAGE_AFTER_START_CLICK"))).getText();
	    return BBmessage;
	}
	
	/**
	 * switch to the BigBazaar window opened from the futurebazaar link. 
	 */
	public void switchToBigBazaarWindow()
	{
		new CommonActionUtil().switchToNewWindow();
	}
	
	/**
	 * Close Bigbazaar window and return handle to the futurebazaaar.com.
	 */
	public void closeBigBazaarWindowAndGoBackToFutureBazaar()
	{
		new CommonActionUtil().switchBackToMainWindow();
		
	}
}
