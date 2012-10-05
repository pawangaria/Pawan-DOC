package com.Fb.PDPpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Fb.AddToCart.AddToCart;
import com.Fb.AddToCart.ShoppingCartPage;
import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;
import com.Fb.CommonHeader.MyAccountPage.MyWishListPage;
import com.Fb.LoginPage.LoginPage;
import com.Fb.SearchResultPage.SearchResultPage;
import com.Fb.WriteAReviewPage.WriteAReviewPage;

public class PDPpage extends Base{
	
	/**
	 * Buy Now button In PDP page is displayed or not.
	 * Return True if Buy Now button is displayed or false 
	 * @return
	 */
	public boolean isBuyNowDisplayed()
	{
		boolean buyNow=driver.findElement(By.xpath(OR.getProperty("BUY_NOW"))).isDisplayed();
	     if(buyNow)
	     {
	    	 return true;
	    	 
	     }else
	    	 return false;
	}
	
	/**
	 * Click on the Buy Now button and return the Shopping cart page. 
	 * @return
	 */
	public ShoppingCartPage clickONBuynowbutton()
	{
		driver.findElement(By.xpath(OR.getProperty("BUY_NOW"))).click();
		return new ShoppingCartPage();
	}
	
    /**
     * Method to click on the Brand Name for the product on PDP
     * Method returns the Search result page for the Brand clicked.
     * @return
     */
	public SearchResultPage clickOnBrandNameOnPDPpage()
	{
		driver.findElement(By.xpath(OR.getProperty("BRAND_LINK"))).click();
		return new SearchResultPage();
	}
	
	/**
	 * Method to get the Brand name from the PDP page for the Product opened.
	 * @return
	 */
	public String getBrandNameOnPDP()
	{
		String BrandName=driver.findElement(By.xpath(OR.getProperty("BRAND_LINK"))).getText();
		return BrandName;
		
	}
	
	public LoginPage clickBeTheFirstToWriteAReviewLinkWithOutUserLogIN()
	{
		if(isBeTheFirstToWriteAReviewDisplayed())
		{
		driver.findElement(By.linkText(OR.getProperty("BE_FIRST_TO_WRITE_A_REVIEW"))).click();
		
		}
		return new LoginPage();
	}
	
	public WriteAReviewPage clickBeTheFirstToWriteAReviewLinkWhenUserAreadyLogIN()
	{
		if(isBeTheFirstToWriteAReviewDisplayed())
		
		driver.findElement(By.linkText(OR.getProperty("BE_FIRST_TO_WRITE_A_REVIEW"))).click();
		
		
		return new WriteAReviewPage();
	}
	
	
	/**
	 * Method to check the link Be the First to write a Review is displayed or not.
	 * Return True when link is displayed.
	 * @return
	 */
	public boolean isBeTheFirstToWriteAReviewDisplayed()
	{
		boolean isDisplayed = driver.findElement(By.linkText(OR.getProperty("BE_FIRST_TO_WRITE_A_REVIEW"))).isDisplayed();
	     if(isDisplayed)
	     {
	    	 return true;
	     }else
	    	 return false;
	}
    
	/**
	 * Method to check is the Read a Review for the product link is displayed or not
	 * Returns true when link is displayed.
	 * @return
	 */
	public boolean isReadReviewLinkDisplayed()
	{
		boolean isDisplayed= driver.findElement(By.xpath(OR.getProperty("READ_REVIEW"))).isDisplayed();
	     if(isDisplayed)
	     {
	    	 return true;
	     }else
	    	 return false;
	
	}
	
	/** 
	 * Selecting a add to quantity list and adding some quantity of the product from the list.
	 * @param valueToBeSlected
	 */
	public void selectValueFromAddQuantityListInPDP(String valueToBeSlected)
	{
		WebElement AddQuantityList= driver.findElement(By.id(OR.getProperty("ADD_QUANTITY_LIST")));
		new CommonActionUtil().selectItemFromList(AddQuantityList, valueToBeSlected);
	}
	
	/**
	 * To get the value in the List for the Item Quantity from the PDP page.
	 * returns the Item count from list.
	 * @return
	 */
	public String getTheSelectedItemValueFromList()
	{
		WebElement AddQuantityList= driver.findElement(By.id(OR.getProperty("ADD_QUANTITY_LIST")));
		return new CommonActionUtil().getTheSelectedValueFromList(AddQuantityList);
	}
	/**
	 * Check the Availability of the product By the word IN STOCK.
	 * true is returned if the In STOCK is present in the PDP page.
	 * @return
	 */
	public boolean  checkAvailabilityOfTheProduct()
	{
		boolean InStock = driver.findElement(By.cssSelector(OR.getProperty("AVAILABILITY_CSS"))).getText().contains("IN STOCK");
		if(InStock)
		{
			return true;
		}else 
			return false;
	}
	
	/**
	 * Check the if the product is out of Stock By the word out of STOCK.
	 * true is returned if the OUT OF STOCK is present in the PDP page.
	 * @return
	 */
	public boolean  isOutOfStockProduct()
	{
		boolean OutStock = driver.findElement(By.cssSelector(OR.getProperty("AVAILABILITY_CSS"))).getText().contains("OUT OF STOCK");
		if(OutStock)
		{
			return true;
		}else 
			return false;
	}
	
	/**
	 * check if the product is shipped by some other vendor or not
	 * true if the shipped by option is present.
	 * @return
	 */
	public boolean isShippedByOptionForProduct()
	{
		boolean shippedBy = driver.getPageSource().contains("Shipped By");
		if(shippedBy)
		{
			return true;
			
		}else
			return false;
	}
	
	/**
	 * Method to check Is Check Delivery date on your Pin code link is present or not.
	 * Return true if link is present or false.    
	 * @return
	 */
	public boolean isCheckDeliveryDateLinkPresentOnPDP()
	{
		boolean checkDelliveryDate=driver.findElement(By.linkText(OR.getProperty("CHECK_DELIVERY_DATE_LINK"))).isDisplayed();
		if(checkDelliveryDate)
		{
			return true;
			
		}else
			return false;
	    
	}
	
	/**
	 * Click on check delivery date for your pin code link
	 * On PDP page.which returns the Check Delivery date Page. 
	 * @return
	 */
	public CheckDeliveryDatePage clickCheckDeliveryDateLinkOnPDP()
	{
		driver.findElement(By.linkText(OR.getProperty("CHECK_DELIVERY_DATE_LINK"))).click();
	    return new CheckDeliveryDatePage();
	}
	
	public boolean isAddToWishListDisplayed()
	{
		boolean addToWishList=driver.findElement(By.xpath(OR.getProperty("ADD_TO_WISHLIST_LINK"))).isDisplayed();
		if(addToWishList)
		{
			return true;
			
		}else
			return false;

		
	}
	
	/**
	 * click on the Add to wish list option ON PDP when user is not logged in and after clicking Add to wishlist 
	 * user sign in page is displayed.
	 * 
	 * @return
	 */
	public LoginPage clickAddToWishListWhenUserIsNotLoggedIN()
	{
		driver.findElement(By.xpath(OR.getProperty("ADD_TO_WISHLIST_LINK"))).click();
	    return new LoginPage();
	}
	
	/**
	 * Click on the Add to wish list option on PDP when the user is already logged in. 
	 * @return
	 */
	public MyWishListPage clickAddToWishListWhenUserIsLoggedIn()
	{
		driver.findElement(By.xpath(OR.getProperty("ADD_TO_WISHLIST_LINK"))).click();
	    return new MyWishListPage();
	}
	
	/**
	 * Click on the PAy using COD option on PDP, which opens a lightbox.  
	 */
	public void payUsingCODLinkOnPDP()
	{
		driver.findElement(By.xpath(OR.getProperty("PAY_USING_COD_LINK"))).click();
	}
	
	/**
	 * Click on the Pay using PAYBAcK option link on PDP, which opens an LIghtbox which describes about the 
	 * How to pay using PAYBACK
	 */
	public void payUsingPAYBACKLinkOnPDP()
	{
		driver.findElement(By.xpath(OR.getProperty("PAY_USING_PAYBACK_LINK"))).click();
	}
	
	/**
	 * click on the Write Review button present on the PDP page for the Product
	 * if the User is already logged in.  
	 * @return
	 */
	public WriteAReviewPage clickWriteReviewButtonOnPdpIfUserIsLoggedIn()
	{
		driver.findElement(By.linkText(OR.getProperty("WRITE_A_REVIEW_BUTTON"))).click();
	    return new WriteAReviewPage();
	}
	
	/**
	 * Click on the Write review when the USer is not logged in and after clicking the 
	 * link user is asked for the login on login page.
	 * @return
	 */
	public LoginPage clickWriteReviewButtonOnPdpIfUserIsNOTLoggedIn()
	{
		driver.findElement(By.linkText(OR.getProperty("WRITE_A_REVIEW_BUTTON"))).click();
	    return new LoginPage();
	}
	
	/**
	 * Return the true of false value depending upon the review, if the Review is already present for the PRoduct
	 * true is returned and FAlse if not. 
	 * @return
	 */
	public boolean isReviewAlreadyPresentForTheProduct()
	{
		boolean isRewview=driver.getPageSource().contains("Average Rating");
	    if(isRewview)
	    {
	    	return true;
	    }else
	    	return false;
	}
	
}
