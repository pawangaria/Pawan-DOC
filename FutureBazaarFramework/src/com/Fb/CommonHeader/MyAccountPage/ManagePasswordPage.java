/**
 * 
 */
package com.Fb.CommonHeader.MyAccountPage;

import org.openqa.selenium.By;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;

/**
 * Manage Password class contains all the methods related to the Manage Password
 * Tab of the MY Account Page.
 * We can access the Manage Password page from My Account or By clicking the Tab
 * @author PawanGaria
 *
 */
public class ManagePasswordPage extends Base{
	public String ManagePasswordPageVerificationText;
	/**
	 * Set the Verification Text for the Manage Password page on My Account Page
	 */
	 public ManagePasswordPage() {
		
		this.ManagePasswordPageVerificationText=new BasicUtilityMethods(OR.getProperty("MANAGE_PASSWORD_PAGE_VERIFICATION")).GetVerificationTextForPages();
	}
	
	

}
