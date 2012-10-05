/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * My Profile class contains all the methods related to the My Profile
 * Tab of the MY Account Page.
 * We can access the Manage Password page from My Account or By clicking the TAb
 * My Profile class have methods which are used to manage all the setting related to the 
 * profile changes.
 * @author PawanGaria
 *
 */
public class MyProfilePage extends Base{
	
	public String MyProfilePageVerificationText;
	/**
	 * Set the Verification Text for the My Profile page on My Account Page
	 */
	
	public MyProfilePage()
	{
		this.MyProfilePageVerificationText=new BasicUtilityMethods(OR.getProperty("MY_PROFILE_PAGE_VERIFICATION")).GetVerificationTextForPages();
	}
	

}
