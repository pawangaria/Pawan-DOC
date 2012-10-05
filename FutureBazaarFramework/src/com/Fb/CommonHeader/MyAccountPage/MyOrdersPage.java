/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * My Orders class contains all the methods related to the My Orders
 * Tab of the MY Account Page.
 * We can access the My Orders page from My Account or By clicking the Tab in My account
 * You can view any orders placed by you depending upon the user profile used.
 * @author PawanGaria
 *
 */
public class MyOrdersPage extends Base{
	public String MyOrdersPageVerificationText;
	/**
	 * Set the Verification Text for the My Orders page on My Account Page
	 */
	public MyOrdersPage()
	{
		this.MyOrdersPageVerificationText=new BasicUtilityMethods(OR.getProperty("MY_ORDERS_PAGE_VERIFICATION")).GetVerificationTextForPages();
	}
	
	

}
