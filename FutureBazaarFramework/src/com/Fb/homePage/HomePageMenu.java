package com.Fb.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Fb.AddToCart.ShoppingCartPage;
import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;

/**
 * 
 * @author PawanGaria
 * This is the Class used to perform actions on the Home page Menu Bars at the top.
 * Menu bar is common in some pages so created the separate java file for this Menu.
 * This Menu bar also contains the Item cart.
 */
public class HomePageMenu extends Base {
	
     
	 /**
     * Click on the Shopping cart Logo on the Menu bar, which returns the shopping cart page.
     * And the SHopping cart page is opened for the user.
     * @return
     */
    public ShoppingCartPage clickOnShoppingCartIconToGoToShoppingCartPage()
    {
        System.out.println("Clicking on the Item Cart Logo");	
    	driver.findElement(By.cssSelector(OR.getProperty("SHOPPING_CART_CSS"))).click();
    	return PageFactory.initElements(driver,ShoppingCartPage.class);
    	
    	}
    
    /**
     * Method to check weather the Shopping cart is empty or not 
     * @return
     */
    public boolean isShoppingCartEmpty()
	{
		if(noOfItemsInShoppingCart()==null)
	    {
	    	return true;     //Return true if shopping cart value is 0 or Null
	    }else{
	    return false;  // return false if the shopping cart contains some items.
	    }
	    }
    
    /**
     * Method to get the No of Items Present in the shopping cart of the User.
     * NOTE: if the Cart is empty Exception is thrown So returning a value Null.
     * Return the no of items.
     * @return
     */
	public Integer noOfItemsInShoppingCart()
	{   
		//Get the value of the Items in Cart.
		try{
		String TotalItemInCartString=driver.findElement(By.cssSelector(OR.getProperty("SHOPPING_CART_CSS"))).getText();
		System.out.println(TotalItemInCartString);
	    //String ll[]=TotalItemInCartString.split(" ");
	    //int TotalItemInCart = Integer.parseInt(ll[0]);       //Converting String item count to the integer value.
		int TotalItemInCart = Integer.parseInt(TotalItemInCartString);
		return  TotalItemInCart;  //Return the value of the total items in Cart.
		}catch(Exception e)
		{
			return null;
		}
	
	}
    
	public void moveMouseToShoppingCartIcon()
	{
		WebElement ShoppingCartIcon = driver.findElement(By.cssSelector(OR.getProperty("SHOPPING_CART_CSS")));
	    new CommonActionUtil().moveMouseToAnElement(ShoppingCartIcon);
	
	}
}
