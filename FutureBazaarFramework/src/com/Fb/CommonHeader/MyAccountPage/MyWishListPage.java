/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * My WishList class contains all the methods related to the my WishList
 * Tab of the MY Account Page.
 * We can access the WishList page from My Account or By clicking the TAb.
 * Note:WishList can be Empty or it contains the Products depends upon the User Account.
 * @author PawanGaria
 *
 */
public class MyWishListPage extends Base{

	public String MyWishListPageVerificationText;
	/**
	 * Set the Verification Text for the My WishList page on My Account Page
	 */
	public MyWishListPage()
	{
		this.MyWishListPageVerificationText=new BasicUtilityMethods(OR.getProperty("MY_WISHLIST_PAGE_VERIFICATION")).GetVerificationTextForPages();
	}
	
	
	
}
