/**
 * 
 */
package com.Fb.Base;


import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.base.Function;

/**
 * @author PawanGaria
 *
 */
public class CommonActionUtil extends Base{
	  
	  /**
	   * Method used to select a value in the list, method receives the value of the WebElement and the value to be selected.
	   * @param element
	   * @param valueToBeSlected
	   */
	  public void selectItemFromList(WebElement element,String valueToBeSlected)
      {
      	Select select = new Select(element);
      	select.selectByValue(valueToBeSlected);
      }
      
	  /**
	   * method returns the value from the list which is currently selected.
	   * method accepts the Webelement value of the list, and returns the current value which is selected. 
	   * @param element
	   * @return
	   */
	  public String getTheSelectedValueFromList(WebElement element)
	  {
		  Select mySelectedElement = new Select(element);
		 // List<WebElement> allSelectedOptions = mySelectedElement.getAllSelectedOptions();
	     WebElement firstSelectedOption = mySelectedElement.getFirstSelectedOption();
	     return firstSelectedOption.getText();
		 
	  }
	  
	  /**
	   * Method to Move mouse the the element in the page. 
	   * @param element
	   */
	  public void moveMouseToAnElement(WebElement element)
	  {
		  Actions action = new Actions(driver);
		    action.moveToElement(element).build().perform();
	  }
	  
		  /**
	   * Method to switch to the Window opened by clicking a link, and passing the driver handle to new window.
	   */
	  public void switchToNewWindow()
	  {
		  //Set<String> setOfNewHandles =saveNewHandles(driver);
		  //System.out.println(setOfNewHandles);
		  //if (!setOfNewHandles.isEmpty()) {
	        //    String newWindowHandle = setOfNewHandles.iterator().next(); // here IF we have new window it will shift on it
	         // driver.switchTo().window(newWindowHandle);
	        //}     
		 
		  Set<String> setOfNewHandles =saveNewHandles(driver);
		
		  
		  for(String handle : driver.getWindowHandles())
		   {
			  
			driver.switchTo().window(handle);
		   }
		  
		  
		  
		  
	  }
	  
	  /**
	   * Method to switch back to the main window, from where the new window opened.
	   *  
	   */
	  public void switchBackToMainWindow()
	  {  
		  //Set<String> setOfNewHandles =saveNewHandles(driver);
		  //System.out.println(setOfNewHandles);
	  //String current = driver.getWindowHandle();
	  
	  //setOfNewHandles.remove(current);
	  //String newTab = setOfNewHandles.iterator().next();
	    //driver.switchTo().window(newTab);
	   
	    driver.close();
	 
	    Set<String> setOfNewHandles =saveNewHandles(driver);
	    
	    for(String handle : driver.getWindowHandles())
		   {
			driver.switchTo().window(handle);
		   }
	   
	  }
	  
	  /**
	   * Method to get the window count which are opened. 
	   * @param driver
	   * @return
	   */
	  public Set<String> saveNewHandles(WebDriver driver) 
		  {
	        Set<String> setOfNewHandles = driver.getWindowHandles(); // here we save id's of windows
	        
	        return setOfNewHandles;
	  }
	  
	  public WebElement waitForMe(By locatorname, int timeout){  
          WebDriverWait wait = new WebDriverWait(driver, timeout);  
          return wait.until(presenceOfElementLocated(locatorname));  
     } 
	  
	  public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {  
          // TODO Auto-generated method stub  
          return new Function<WebDriver, WebElement>() {  
               @Override  
               public WebElement apply(WebDriver driver) {  
                    return driver.findElement(locator);  
               }  
          };  
     }  
	  
	  
	  
	  /**
	   * Customized wait method to wait for the 10 milliseconds, before throwing an exception 
	   * 
	   * @param element
	   * @throws Exception
	   */
	  public void waitForAnElementToPresentBy(By element) throws Exception          // Wait function to wait for element
      { 
		  
          for (int second =0; ; second++)
              {
                  if (second >= 10000) Assert.fail("Timeout: Unable to find the Element for the time duration");
                  try
                  {   
                	                 	  
                	  if (driver.findElement(element).isDisplayed()) break;
                	  Thread.sleep(second * 1000); 
                	  
                  }
                  catch (Exception e)
                  { 
                	  throw new Exception("Element : "+element+" is not present in the page, So throwing an Exception : "+e);
                  }
                  
               }  
      }
}
