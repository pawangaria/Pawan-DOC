/**
 * 
 */
package com.Fb.CommonHeader;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.Fb.Base.Base;
import com.Fb.homePage.HomePage;

/**
 * Never Miss A Deal class Provides the functionality related to the Never Miss a Deal window which 
 * will open after clicking the NeverMiss A Deal button on Header.
 * There are methods for Entering user name , Email, Mobile No. click on the Subscribe button,close window. 
 * @author PawanGaria
 *
 */
public class NeverMissADealWindow extends Base{
	
	/**
	 * Enter Email ID of the Subscriber for Never Miss a Deal.
	 * Note: Email ID is mandatory for the Subscription of the Deal.
	 * @param EmailID
	 */
	public void enterEmailForNeverMissAdealSubscription(String EmailID)
	{
	driver.findElement(By.id(OR.getProperty("ENTER_EMAIL_TEXTFIELD"))).clear();	
	driver.findElement(By.id(OR.getProperty("ENTER_EMAIL_TEXTFIELD"))).sendKeys(EmailID);
	}
    
	/**
	 * Enter the Mobile No of the Subscriber for Never Miss a Deal.
	 * @param MobileNO
	 */
	public void enterMobileNOForNeverMissAdealSubscription(String MobileNO)
	{
		driver.findElement(By.id(OR.getProperty("ENTER_MoBILE_NO_TEXTFIELD"))).clear();	
		driver.findElement(By.id(OR.getProperty("ENTER_MoBILE_NO_TEXTFIELD"))).sendKeys(MobileNO);		
		}
	
	/**
	 * Enter name of the Subscriber for Never Miss a Deal. 
	 * @param name
	 */
	public void enterNameOfTheSubscriber(String name)
	{
		driver.findElement(By.id(OR.getProperty("ENTER_NAME_TEXTFIELD"))).clear();	
		driver.findElement(By.id(OR.getProperty("ENTER_NAME_TEXTFIELD"))).sendKeys(name);		
		
	}
	
	/**
	 * Click on the Subscribe Now button for the Never Miss a Deal.
	 * Note:After clicking the subscribe button the confirmation message is displayed for the correct values.  
	 */
	public void clickOnSubscribeNowButtonForNeverMissADeal()
	{
		driver.findElement(By.id(OR.getProperty("SUBSCRIBE_NOW_BUTTON"))).click();
	}
	
	/**
	 * Method used to close the Never Miss a Deal Window.
	 */
	public void closeNeverMissADealWindow()
	{
		driver.findElement(By.linkText(OR.getProperty("NEVER_MISS_ADEAL_WINDOW_CLOSE_LINK"))).click();
	}
    
	/**
	 * Returns the Never Miss a deals confirmation Text heading.
	 * Note: This is not the confirmation message about subscribed successfully or not.  
	 * @return
	 */
	public String neverMissADealConfirmation()
	{
		String neverMissADealConfirmationTEXT=driver.findElement(By.xpath(OR.getProperty("NEVER_MISS_A_DEAL_CONFIRMATION_POPUP_TEXT"))).getText();
         return neverMissADealConfirmationTEXT;
	}
	
	/**
	 * Method to close the confirmation pop which comes for the 
	 * Never miss a Deal.The method returns the same page which is opened. 
	 * 
	 */
	public void neverMissADealConfirmationPOPupClose()
	{
		driver.findElement(By.xpath(OR.getProperty("NEVER_MISS_A_DEAL_CONFIRMATION_CLOSE"))).click();
		
	}
}
