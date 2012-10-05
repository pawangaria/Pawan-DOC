/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;
import com.Fb.LoginPage.LoginPage;
import com.Fb.homePage.HomePage;

/**
 * All the API's Related to the My Account Page.
 * in My Account you can find the Pages related to the User.
 * Like You can manage your password, Address, View your orders.
 *   
 * @author PawanGaria
 *
 */
public class MyAccountPage extends Base{
	public String MyAccountPageVerificationText;
	/**
	 * Set the Verification Text for the My Account Page
	 */
	public MyAccountPage()
	{
		this.MyAccountPageVerificationText=new BasicUtilityMethods(OR.getProperty("MY_ACCOUNT_PAGE_VERIFICATION_TEXT")).GetVerificationTextForPages();
	}
	
	 /**
	  * click on the My Account Link on the header of the Page.
	  */
	public LoginPage ClickonMyAccountLink()
	{
		driver.findElement(By.linkText(OR.getProperty("MY_ACCOUNT_LINK"))).click();
		return PageFactory.initElements(driver,LoginPage.class);
	}
	
	 /**
	  * click on the My Orders Tab in the My Account Page
	  */
	 public MyOrdersPage ClickOnMyOrdersTabLink()
	 {
		 driver.findElement(By.xpath(OR.getProperty("MY_ORDERS_TAB"))).click();
		 return PageFactory.initElements(driver,MyOrdersPage.class);
	 }
	
	 /**
	  * click on the My Orders Tab in the My Account Page
	  */
	 public MyWishListPage ClickOnMyWishListTabLink()
	 {
		 driver.findElement(By.xpath(OR.getProperty("MY_WISHLIST_TAB"))).click();
		 return PageFactory.initElements(driver,MyWishListPage.class);
	 }
	 
	 /**
	  * click on the My ProfileTab in the My Account Page
	  */
	 public MyProfilePage ClickOnMyProfileTabLink()
	 {
		 driver.findElement(By.xpath(OR.getProperty("MY_PROFILE_TAB"))).click();
		 return PageFactory.initElements(driver,MyProfilePage.class);
	 }
	
	 /**
	  * click on the My Address Book Tab in the My Account Page
	  */
	 public AddressBookPage ClickOnMyAddressBookTabLink()
	 {
		 driver.findElement(By.xpath(OR.getProperty("MY_ADDRESS_BOOK"))).click();
		 return PageFactory.initElements(driver,AddressBookPage.class);
	 }
	 
	 /**
	  * click on the Manage Password Tab in the My Account Page
	  */
	 public ManagePasswordPage ClickOnManagePasswordTabLink()
	 {
		 driver.findElement(By.xpath(OR.getProperty("MANAGE_PASSWORD"))).click();
		 return PageFactory.initElements(driver,ManagePasswordPage.class);
	 }
	  
	 

}
