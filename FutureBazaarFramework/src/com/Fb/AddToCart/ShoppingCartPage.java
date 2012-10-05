/**
 * 
 */
package com.Fb.AddToCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Fb.Base.Base;
import com.Fb.Base.BasicUtilityMethods;
import com.Fb.Base.CommonActionUtil;
import com.Fb.CommonHeader.Header;
import com.Fb.ShippingAddressPage.ShippingAddressPage;
import com.Fb.guestLoginPage.GuestLoginPage;
import com.Fb.homePage.HomePage;
import com.Fb.homePage.HomePageMenu;

/**
 * @author PawaG
 *
 */
public class ShoppingCartPage extends Base{
	public String ShoppingCartPageVerificationText;
	/**
	 * Set the Verification Text for the My Account Page
	 */
	public ShoppingCartPage()
	{
		this.ShoppingCartPageVerificationText=new BasicUtilityMethods(OR.getProperty("SHOPPING_CART_PAGE_VERIFICATION_TEXT")).GetVerificationTextForPages();
	}
	
	/**
	 * This method is used to delete all the items which are present in the cart one by one.
	 */
	public void removeAllShoppingCartItems()
	{
		Integer NoOfItems =new HomePageMenu().noOfItemsInShoppingCart();
	      for(int i=0;i<NoOfItems;i++)
	      {   
	    	  System.out.println("Removing Item from the cart");
	    	  driver.findElement(By.cssSelector(OR.getProperty("REMOVE_ITEM_LINK_CSS"))).click();
	      }
	      
	}
	
	/**
	 * click on the Add More items Link on the shopping cart page, the page would be redirected to the Home page.
	 * @return
	 */
	public HomePage clickAddMoreItemsLinkOnCartPage()
	{
		driver.findElement(By.id(OR.getProperty("ADD_MORE_ITEMS_LINK"))).click();
		return PageFactory.initElements(driver,HomePage.class);
	}
	
	/**
	 * Method will take you to the Guest Login page to go to the Shipping address page.
	 * @return
	 */
	public GuestLoginPage goToGuestLoginPageFromShoppingCartPage()
	{
		driver.findElement(By.xpath(OR.getProperty("GO_TO_NEXT_PAGE_FROM_SHOPPING_CART"))).click();
		
			return PageFactory.initElements(driver,GuestLoginPage.class);
		
	}
    
	/**
	 * Method to take you to the Shipping address page as the user is already logged in. 
	 * @return
	 */
	public ShippingAddressPage goToShippingAddressPageFromShoppingCartPage()
	{
		driver.findElement(By.xpath(OR.getProperty("GO_TO_NEXT_PAGE_FROM_SHOPPING_CART"))).click();
		
			return PageFactory.initElements(driver,ShippingAddressPage.class);
		
	}
	
	/**
	 * Return the Selected value from the Item quantity list in the shopping cart page. 
	 * to get the selected value calling the getTheSlectedValueFromList method which is in CommonActionUtil.
	 * @return
	 */
	public String getTheItemQuantityfromList()
	{
		WebElement itemList = driver.findElement(By.xpath(OR.getProperty("CHANGE_ITEM_QUANTITY")));
	    String selectedvaluefromList = new CommonActionUtil().getTheSelectedValueFromList(itemList);
		return selectedvaluefromList;
	}
    
	/**
	 * Method used to update the Quantity of the Items in the Shopping cart page.
	 * in this Method calling the common utility method selectItemFromList(), which is used to select the quantity of item from the List. 
	 * @param ItemQuantity
	 */
	public void updateQuantityOfItemsInCart(String ItemQuantity )
	{ 
		WebElement ChangeItemQuantityList = driver.findElement(By.xpath(OR.getProperty("CHANGE_ITEM_QUANTITY")));
		new CommonActionUtil().selectItemFromList(ChangeItemQuantityList, ItemQuantity);
		
	}
	
	/**
	 * close the shopping cart light box, click on the close button present in the to right. 
	 */
	public void closeShoppingCartLightBox()
	{
		driver.findElement(By.xpath(OR.getProperty("CLOSE_SHOPPINGCART_LIGHTBOX"))).click();
	}
	
	/**
	 * Method return the Message about the Cart Empty from the shopping cart page
	 * when no item is present in cart and user opens the cart page.
	 * @return
	 */
	public String checkShoppingCartEmptyMessageOnCartPage()
	{
		String shoppingCartIsEmptyMessage = driver.findElement(By.xpath(OR.getProperty("SHOPPING_CART_IS_EMPTY_MESSAGE_TEXT"))).getText();
	  return shoppingCartIsEmptyMessage;
	}
	
	/**
	 * Method to click on the Start Shopping link from the cart, when there is no item present in the cart.
	 * method returns the home page as home page is opened after clicking 
	 * @return
	 */
	public HomePage clickStartShoppingLinkWhenCartIsEmpty()
	{
		driver.findElement(By.linkText(OR.getProperty("START_SHOPPING_BUTTON_LINK"))).click();
		return  new PageFactory().initElements(driver,HomePage.class);
	}
	
	
	
	
}
