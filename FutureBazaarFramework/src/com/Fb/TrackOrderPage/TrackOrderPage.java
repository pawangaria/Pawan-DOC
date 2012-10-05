package com.Fb.TrackOrderPage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;
/**
 * Track Order class is used to provide the API's related to the Track Order Page.
 * In the constructor we set the value of the Page verification text which can be used for the 
 * Verification of the page. 
 * @author PawanGaria
 *
 */
public class TrackOrderPage extends Base{
	public String TrackOrderPageVerificationText;
	
	public TrackOrderPage()
	{   
		System.out.println("In the Track order constaructor");
		
		this.TrackOrderPageVerificationText=new BasicUtilityMethods(OR.getProperty("GET_TRACK_ORDER_VERIFICATION_TEXT")).GetVerificationTextForPages();
		
	}
	/*
	private void SetTrackOrderPageVerificationText()
	{
		this.TrackOrderPageVerificationText=driver.findElement(By.xpath(OR.getProperty("GET_TRACK_ORDER_VERIFICATION_TEXT"))).getText();
	}
	
	public String GetTrackOrderPageVerificationText()
	{
		return TrackOrderPageVerificationText;
	}
	*/
	
	
	
	/**
	 * In the Track Order page, enter the Order id in the OrderID text field. 
	 * @param orderid
	 */
	public void EnterOrderIDinTrackOrder(String orderid)
	{
		driver.findElement(By.name(OR.getProperty("ORDER_ID"))).clear();
		driver.findElement(By.name(OR.getProperty("ORDER_ID"))).sendKeys(orderid);
	}

	/**
	 * In the Track Order page, enter the UserName in the UserName/Email text field. 
	 * @param username
	 */
	public void EnterUsernameInTrackOrder(String username)
	{
		driver.findElement(By.name(OR.getProperty("USER_NAME"))).clear();
		driver.findElement(By.name(OR.getProperty("USER_NAME"))).sendKeys(username);
	}
	
	/**
	 * Click on the Track Order button on the Track Order Page
	 */
	public void ClickONTrackOrderButton()
	{
		driver.findElement(By.xpath(OR.getProperty("TRACK_ORDER_BUTTON"))).click();
	}
	
	/**
	 * Method to enter the UserName and OrderID
	 * and Click on the Track Order button and Navigated to the Order Details page. 
	 * @param orderid
	 * @param username
	 */
	public void TrackOrderByEmailAndOrderId(String orderid, String username)
	{
		EnterOrderIDinTrackOrder(orderid);
		EnterUsernameInTrackOrder(username);
		ClickONTrackOrderButton();
	}
	/**
	 * Track Order when user is already logged in 
	 * So only Order id would be provided.
	 * @param orderid
	 */
	public void TrackOrderWhenUserIsLoggedIn(String orderid)
	{
		EnterOrderIDinTrackOrder(orderid);
	
		ClickONTrackOrderButton();
	}
}
