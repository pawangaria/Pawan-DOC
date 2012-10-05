package com.Fb.TestCase;

import org.testng.Assert;

import com.Fb.AddToCart.ShoppingCartPage;
import com.Fb.CommonHeader.Header;

import com.Fb.CommonHeader.MyAccountPage.MyOrdersPage;
import com.Fb.LoginPage.LoginPage;
import com.Fb.PDPpage.CheckDeliveryDatePage;
import com.Fb.PDPpage.PDPpage;
import com.Fb.TrackOrderPage.TrackOrderPage;
import com.Fb.homePage.HomePage;
import com.Fb.homePage.HomePageMenu;

public class GoToGuestLoginPage {
	
	public static void main (String [] args) throws InterruptedException
	{
		HomePage home = new HomePage();
		
		//PDPpage pdp = home.clickOnFirstProduct();
		
		//Search for the Product
	    Header head = new Header();
	   // head.moveMouseToShoppingCartIcon();
	    Thread.sleep(4000);
	    
	    
	    //LoginPage login = head.clickMyAccountLink();
	    //home =login.loginAndGoToHomePage("pawan1@gmail.com","p0513231068");
	    //head.clickMyAccountLink();
	    //MyOrdersPage myorders= head.clickOnMyOrdersLinkInMyAccount();
	    //TrackOrderPage track= head.ClickOnTrekOrderLink();
	 //   Assert.assertTrue(track.TrackOrderPageVerificationText.equals("Order Status"),"Track order page is not displayed");
	 	//track.ClickONTrackOrderButton();
	    //Assert.assertTrue(myorders.MyOrdersPageVerificationText.contains("Track your packages"),"My Orders page is not displayed");
	    //head.SearchSomeItem("Mobile");
	   
	    PDPpage pdp = home.clickOnFirstProduct();
	        System.out.println(pdp.checkAvailabilityOfTheProduct());
	        System.out.println(" be the first write review  "+pdp.isBeTheFirstToWriteAReviewDisplayed());
	      //  pdp.clickBeTheFirstToWriteAReviewLinkWhenUserAreadyLogIN();
	       System.out.println(pdp.getBrandNameOnPDP());
	       System.out.println("delivery"+pdp.isCheckDeliveryDateLinkPresentOnPDP());
	       System.out.println("out of stock"+pdp.isOutOfStockProduct());
	        //System.out.println(pdp.clickOnBrandNameOnPDPpage());
	        System.out.println(pdp.isOutOfStockProduct());
	        System.out.println(" read review link" + pdp.isReadReviewLinkDisplayed());
	        System.out.println("item list before"+pdp.getTheSelectedItemValueFromList()); 
	        pdp.selectValueFromAddQuantityListInPDP("3");
	        System.out.println("item list after"+pdp.getTheSelectedItemValueFromList()); 
	        
	        CheckDeliveryDatePage checkDD= pdp.clickCheckDeliveryDateLinkOnPDP();
	        System.out.println(checkDD.getCheckDeliveryDatePageVerificationText());
	        checkDD.enterPINcodetoTheTextBox("400086");
	        checkDD.clickOnSubmitButtonForDeliveryDateCheck();
	        checkDD.closeCheckDeliveryDatePOPupPage();
	        System.out.println(checkDD.getTheResponseAfterClickingSubmitInCheckDeliveryPage());
	        /*  ShoppingCartPage cart=  pdp.clickONBuynowbutton(); 
	    cart.clickAddMoreItemsLinkOnCartPage();
	     head.clickHomePageLogoOnHeader();
	    */ //HomePageMenu menu = new HomePageMenu();
	    // menu.noOfItemsInShoppingCart();
	     
	    //Assert.assertTrue(shoppingcart.ShoppingCartPageVerificationText.contains("Your Cart"),"Shopping cart page is not displayed");
	    	//}
	     //ShoppingCartPage shop = new ShoppingCartPage();
	     //shop.updateQuantityOfItemsInCart("2");
	     //System.out.println(shop.getTheItemQuantityfromList());
	
	}
}
