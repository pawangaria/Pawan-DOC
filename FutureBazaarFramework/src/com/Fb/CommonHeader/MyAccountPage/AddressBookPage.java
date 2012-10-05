/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * Address Book class contains all the methods related to the Address Book
 * Tab of the MY Address Book Page.
 * We can access the Address Book page from My Account or By clicking the TAb
 * This class is used to set the default address /Shipping Address and user can change his address settings
 * 
 * @author PawaG
 *
 */
public class AddressBookPage extends Base{
	
	public String AddressBookPageVerificationText;
	/**
	 * Set the Verification Text for the Address Book page on My Account Page
	 */
	 public AddressBookPage() {
		
		this.AddressBookPageVerificationText=new BasicUtilityMethods(OR.getProperty("MY_ORDERS_PAGE_VERIFICATION")).GetVerificationTextForPages();
	}
	

}
