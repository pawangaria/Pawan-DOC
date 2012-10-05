/**
 * 
 */
package com.Fb.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * This class provides all the basic methods which are used 
 * By all the pages of functions to get some value or some basic java functions.
 * @author PawanGaria
 *
 */
public class BasicUtilityMethods extends Base{
                private String VerificationTextForPages;
             
                //private WebDriver driver;
                /**
                 * Calls the Set verification text method and accept the Element of the page which will return Text.
                 * @param ElementKEY
                 */
                public BasicUtilityMethods(String ElementKEY)
                {
                     SetVerificationTextForPages(ElementKEY);
                }
	            /**
	             * Set the Verification text value from the Page
	             * @param elemenString
	             */
	            private void SetVerificationTextForPages(String elemenString)
	            {
	               this.VerificationTextForPages=driver.findElement(By.xpath(elemenString)).getText();
	            }
	            
	            /**
	             * Get the Verification Text For Pages and assert the text to verify the Page opened 
	             * 
	             * @return
	             */
	            public String GetVerificationTextForPages()
	            {
	            	return VerificationTextForPages;
	            } 
	            
	          
	            
}
