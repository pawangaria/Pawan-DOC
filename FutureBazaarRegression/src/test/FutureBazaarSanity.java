package test;


import static org.testng.AssertJUnit.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FutureBazaarSanity extends FBConsts{
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
@BeforeClass
public void startSelenium() throws Exception{
	//FirefoxProfile profile = new FirefoxProfile();
	driver = new FirefoxDriver();
	/*
	if ((FBConsts.FF_PROXY).equalsIgnoreCase("YES")){
		FirefoxProfile profile = new FirefoxProfile();
		/*profile.setPreference("config.use_system_prefs",true);
		profile.setPreference("browser.cache.disk.enable",false);
		profile.setPreference("browser.cache.offline.enable",false);
		profile.setPreference("browser.cache.memory.enable",false);
		profile.setPreference("network.cache.use-cache.enable",false);
		*/
		/*
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.http", "10.202.11.11");
		//profile.setPreference("network.proxy.http", "");
		profile.setPreference("network.proxy.http_port", 3128);
		profile.setPreference("network.proxy.share_proxy_settings",true);
		profile.setPreference("network.proxy.no_proxies_on","localhost, 127.0.0.1, 10.202.18.161");
		profile.setPreference("security.default_personal_cert","Select Automatically");
		 */
		//profile.setPreference("network.proxy.http_port", "");
		//driver = new FirefoxDriver(profile);
	//}
	/*else if((FBConsts.FF_PROXY).equalsIgnoreCase("NO")){
		/* Uncomment the below line and comment the above 5 lines if your firefox is not using proxy */
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}


@AfterClass(alwaysRun=true)
public void stopSelenium() throws Exception{
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
		fail(verificationErrorString);
	}
	}

//@Test(groups={"Sanity"})
@Test
public void LoginTest(){
try{
	System.out.println(" ***Future Bazaar Sanity Script # 1 - Login Test");
	//driver.get("http://www.futurebazaar.com");
	driver.get(FBConsts.URL);
	Thread.sleep(8000);
	
	try{
		System.out.println("Verifying the Futurebazaar is opened or not");
		Assert.assertTrue(driver.findElement(By.id(FBConsts.FB_LOGO)).isDisplayed(),"Futurebazaaar.com is not loaded properly");
	}catch(Throwable t)
	{
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test -At Login log out Test, Futurebazaar.com is not Loaded");
    	fail(t.getMessage());
	}
	
	System.out.println(" ***Click on My Account link");
	driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
	
	Thread.sleep(2000);
	System.out.println(" ***Click on Sign in Link");
	driver.findElement(By.id(FBConsts.SIGN_IN)).click();
	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	
	//assertEquals("FutureBazaar: Discover great deals in Laptops, Mobiles, Computers, Electronics, Home Fashion and Clothing", driver.getTitle());
	Thread.sleep(2000);
	System.out.println(" ***Enter User Name");
	WebElement cashondel = driver.findElement(By.id(FBConsts.USER_NAME));
	System.out.println(cashondel);
	
	driver.findElement(By.id(FBConsts.USER_NAME)).clear();
	driver.findElement(By.id(FBConsts.USER_NAME)).sendKeys(FBConsts.USER_ID);
	Thread.sleep(2000);
	
	System.out.println(" ***Enter Password");
	driver.findElement(By.id(FBConsts.PASSWORD)).clear();
	driver.findElement(By.id(FBConsts.PASSWORD)).sendKeys(FBConsts.USER_PASSWORD);
	Thread.sleep(2000);
	
	System.out.println(" ***Click on Login Button");
	driver.findElement(By.id(FBConsts.LOGIN)).click();
	
    try{
    	Thread.sleep(4000);
    	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    	Assert.assertTrue(driver.getPageSource().contains("Hi"));
    System.out.println(" ***User logged in successfully");
    }catch(Throwable e)
    {
    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Login Test Sanity -Failed at Logging");
    	fail(e.getMessage());
    }
	
	
    Thread.sleep(2000);
	System.out.println(" ***Click on My Account link");
	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
	Thread.sleep(3000);
	System.out.println(" ***Click on Sign out Link");
	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	driver.findElement(By.id(FBConsts.LOG_OUT)).click();
	
	Thread.sleep(2000);
	System.out.println(" ***User logged out ");

	}catch(Throwable e){
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Login Test method-Login Check");
		fail(e.getMessage());
	}
}

//@Test(dependsOnMethods="LoginTest",groups={"Sanity"})
@Test(dependsOnMethods="LoginTest",groups={"Sanity"})
public void BasicLinksTest()
{
	try{

		System.out.println(" ***Future Bazaar Sanity Script # 2 - Basic LINK Test");
		driver.get(FBConsts.URL);
		
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		//assertEquals("FutureBazaar: Discover great deals in Laptops, Mobiles, Computers, Electronics, Home Fashion and Clothing", driver.getTitle());
		//Login to the Account
		Thread.sleep(5000);
		try{
			System.out.println("Verifying the Futurebazaar is opened or not");
			Assert.assertTrue(driver.findElement(By.id(FBConsts.FB_LOGO)).isDisplayed(),"Futurebazaaar.com is not loaded properly");
		}catch(Throwable t)
		{
			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test -At Basic Links Test, Futurebazaar.com is not Loaded");
	  	fail(t.getMessage());
		}
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		System.out.println(" ***Click on My Account link");
		waitForElementPresent(driver,"linkText",FBConsts.MY_ACCOUNT);
		driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
		Thread.sleep(2000);
		System.out.println(" ***Click on Sign in Link");
	
		driver.findElement(By.id(FBConsts.SIGN_IN)).click();
		
		Thread.sleep(5000);
			//assertEquals("FutureBazaar: Discover great deals in Laptops, Mobiles, Computers, Electronics, Home Fashion and Clothing", driver.getTitle());

			System.out.println(" ***Enter User Name");
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.id(FBConsts.USER_NAME)).clear();
			driver.findElement(By.id(FBConsts.USER_NAME)).sendKeys(FBConsts.USER_ID);
			Thread.sleep(2000);
			
			System.out.println(" ***Enter Password");
			driver.findElement(By.id(FBConsts.PASSWORD)).clear();
			driver.findElement(By.id(FBConsts.PASSWORD)).sendKeys(FBConsts.USER_PASSWORD);
			Thread.sleep(2000);
			
			System.out.println(" ***Click on Login Button");
			waitForElementPresent(driver,"id",FBConsts.LOGIN);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.id(FBConsts.LOGIN)).click();
			
		    try{
		    	Thread.sleep(5000);
		    	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		    	Assert.assertTrue(driver.getPageSource().contains("Hi"));
		    System.out.println(" ***User logged in successfully");
		    }catch(Throwable e)
		    {
		    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Basic Link Check method -User Login");
		    	fail(e.getMessage());
		    }
			
			//Checking the Account Link
			System.out.println(" ***checking My Account link");
			waitForElementPresent(driver,"linkText",FBConsts.MY_ACCOUNT);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
			Thread.sleep(3000);
			/*try
			{
				Assert.assertTrue(driver.getPageSource().contains("My Account"));
				System.out.println(" *** Your Account page");
			}
			catch(Throwable e){
				fail(e.getMessage());
			}*/	
			
			System.out.println(" ***checking My Orders Tab");
			//waitForElementPresent(driver,"linkText",FBConsts.MY_ORDERS);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.linkText(FBConsts.MY_ORDERS)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Track your packages and see the history of your orders placed with us"));
				System.out.println(" *** Your Order tab Page");
			}
			catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Basic Link Check -My Order");
				fail(e.getMessage());
			}
			System.out.println(" ***checking My Wish List Tab");
			//waitForElementPresent(driver,"linkText",FBConsts.MY_ORDERS);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector(FBConsts.MY_WISHLIST)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Browse through our products and"));
				System.out.println(" *** My Wish List Tab Page verified");
			}
			catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Basic Link Check -MY Wish List TAb in My Account");
				fail(e.getMessage());
			}
			Thread.sleep(5000);
			System.out.println(" ***checking Subscription Tab");
			//waitForElementPresent(driver,"xpath",FBConsts.MY_SUBCRIPTIONS);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.linkText(FBConsts.MY_SUBCRIPTIONS)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Send me daily"));
				System.out.println(" *** Your Subcritption tab Page");
			}
			catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar sanity Test :Basic Link Check Method -Failed at My Subcription Tab");
				fail(e.getMessage());
			}
			
			Thread.sleep(1000);
			System.out.println(" ***checking My Profile Tab");
			//waitForElementPresent(driver,"xpath",FBConsts.MY_PROFILE);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.xpath(FBConsts.MY_PROFILE)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Name"));
				System.out.println(" *** Your Profile Tab Page");
			}
			catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Failed at Basic Link Check Method -My Profile Tab");
				fail(e.getMessage());
			}
			Thread.sleep(2000);
			System.out.println(" ***checking Address Book Tab");
			//waitForElementPresent(driver,"xpath",FBConsts.ADDRESS_BOOK);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.xpath(FBConsts.ADDRESS_BOOK)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Manage your Shipping Addresses here"));
				System.out.println(" *** In My Address Tab");
			}
			catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed atBasic Link Check Method  -Address book Tab");
				fail(e.getMessage()); 
			}
			Thread.sleep(2000);
			System.out.println(" ***checking manage Password Tab");
			//waitForElementPresent(driver,"xpath",FBConsts.MANAGE_PASSWORD);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.xpath(FBConsts.MANAGE_PASSWORD)).click();
			Thread.sleep(5000);
			try
			{
				Assert.assertTrue(driver.getPageSource().contains("Use the form below to change your password for"));
				System.out.println(" *** In Manage Password Tab");
			}catch(Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in  Basic Link Check Method-Manage Password Tab");
				fail(e.getMessage());
			}
			Thread.sleep(1000);
			System.out.println(" ***Checking the Track Order link");
			//waitForElementPresent(driver,"linkText",FBConsts.TRACK_ORDER);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.findElement(By.linkText(FBConsts.TRACK_ORDER)).click();
			try{
				Thread.sleep(5000);
				Assert.assertTrue(driver.getPageSource().contains("You can track your individual order with your Order number"));
				System.out.println(" ***My Track order page is verified");
			}
			catch(Throwable e)
			{
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in  Basic Link Check Method -Track Order Tab");
				fail(e.getMessage());
			}
			
					
		Thread.sleep(1000);
		System.out.println(" ***Click on My Account link");
		//waitForElementPresent(driver,"linkText",FBConsts.MY_ACCOUNT);
		driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
		Thread.sleep(2000);
		System.out.println(" ***Click on Sign out Link");
		driver.findElement(By.id(FBConsts.LOG_OUT)).click();
		
		Thread.sleep(5000);
		
		System.out.println(" ***User logged out ");

	
	
}catch(Throwable e){
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in  Basic Link Check Method");
	fail(e.getMessage());
}
}

//@Test(dependsOnMethods="BasicLinksTest",groups={"Sanity"})
@Test(dependsOnMethods="BasicLinksTest",groups={"Sanity"})
public void PurchaseProductTest()
{ 
  boolean ship = false;
  
  
  System.out.println(" ***Shipping is false 1st time " + ship);
	try{

		System.out.println(" ***Future Bazaar Sanity Script # 3 - Purchase Product Test");
		driver.get(FBConsts.URL);
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		try{
			System.out.println("Verifying the Futurebazaar is opened or not");
			Assert.assertTrue(driver.findElement(By.id(FBConsts.FB_LOGO)).isDisplayed(),"Futurebazaaar.com is not loaded properly");
		}catch(Throwable t)
		{
			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test -At Product purchase Test, Futurebazaar.com is not Loaded");
	  	fail(t.getMessage());
		}
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		//Search the Purchase Product test
				System.out.println(" ***Enter search item and click on Search");
				driver.findElement(By.id(FBConsts.SEARCH_WINDOW)).clear();
				driver.findElement(By.id(FBConsts.SEARCH_WINDOW)).sendKeys(FBConsts.SEARCH_ITEM);
				driver.findElement(By.id(FBConsts.SEARCH)).click();
				System.out.println("****Search clicked");
				Thread.sleep(8000);
				try {
					System.out.println(" ***verifying the Search results");
					driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
					Assert.assertTrue(driver.getPageSource().contains("Search Results"));
				}
					catch (Throwable e)		{
						//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  -Search Item");
					fail(e.getMessage());
					}


				//Start Purchasing Product
				//System.out.println(" ***Sort product and selecting 1st Product");
				//will select value from lowest to height
				//selectValue(driver,"Price: Low to High");
               
				System.out.println("*****selecting the first product from the search");
				waitForElementPresent(driver,"xpath",FBConsts.SELECT_FIRST_ITEM);
				
				//driver.findElement(By.linkText(FBConsts.CATEGORY)).click();
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				driver.findElement(By.xpath(FBConsts.SELECT_FIRST_ITEM)).click();
				System.out.println("*** First item clicked from the list");
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				System.out.println("verifying the shipping details");
				if(driver.getPageSource().contains("Also available on CASH ON DELIVERY")){ 	// more the 1000 ship=true
					ship = true;	
					System.out.println(" ***Shipping is true 1st time" + ship);
					}
				waitForElementPresent(driver,"xpath",FBConsts.BUY_NOW);
				System.out.println(" ***verifying the Product Details Page");
				//System.out.println(driver.getPageSource());
				
				try {
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
					Assert.assertTrue(driver.getPageSource().contains("Product Description"));
					}
					catch (Throwable e)		{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  -Product Details Page");
					fail(e.getMessage());
					}
				
				System.out.println(" ***Review your cart page");
				
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				
				driver.findElement(By.xpath(FBConsts.BUY_NOW)).click();
				//if(Assert.assertEquals("exact:Shipping:","exact:Shipping:"))
				
									
				waitForElementPresent(driver,"id",FBConsts.PROCEED);
				
				System.out.println(" ***Shipping should be true 2nd time" + ship);
				System.out.println(" ***verifying the Review your CART Page");
				try {
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
					Assert.assertTrue(driver.getPageSource().contains("Total Payable"));
					}
					catch (Throwable e)		{
						//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  -Total Payable at review Cart page");
					fail(e.getMessage());
					}

				System.out.println(" ***Proceed page");
				//driver.findElement(By.xpath(FBConsts.PROD_QTY)).clear();
				//driver.findElement(By.xpath(FBConsts.PROD_QTY)).sendKeys("1");
				//driver.findElement(By.cssSelector(FBConsts.PROD_UPDATE)).click();
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				driver.findElement(By.id(FBConsts.PROCEED)).click();
				waitForElementPresent(driver,"id",FBConsts.LOG_IN_USER);
				System.out.println(" ***Sign in page is displayed");
				System.out.println(" ***verifyng the Sign in page");
				try {
					Thread.sleep(5000);
					Assert.assertTrue(driver.getPageSource().contains("Already a Member"));
					}
					catch (Throwable e)		{
						//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - User is not able to log in ");
					fail(e.getMessage());
					}

				System.out.println(" ***User log in ");
				System.out.println(" ***Enter User Name");
				
				driver.findElement(By.id(FBConsts.USER_LOG_IN)).clear();
				driver.findElement(By.id(FBConsts.USER_LOG_IN)).sendKeys(FBConsts.USER_ID);
				Thread.sleep(2000);
				
				System.out.println(" ***Enter Password");
				driver.findElement(By.id(FBConsts.USER_LOG_PASSWORD)).clear();
				driver.findElement(By.id(FBConsts.USER_LOG_PASSWORD)).sendKeys(FBConsts.USER_PASSWORD);
				Thread.sleep(2000);
				
				System.out.println(" ***Click on Login Button");
				driver.findElement(By.id(FBConsts.LOG_IN_USER)).click();
				System.out.println(" ***User logged in");
			   /* try{
			    	Thread.sleep(3000);
			    	Assert.assertTrue(driver.getPageSource().contains("Total amount you need to pay"));
			    System.out.println(" ***User is at the Review your cart");
			    }catch(Throwable e)
			    {
			    	fail(e.getMessage());
			    }
				*/
				
				System.out.println(" ***To shipping details page");
				waitForElementPresent(driver,"id",FBConsts.PROCEED);
				//driver.findElement(By.id(FBConsts.PROCEED)).click();
				try{
					Thread.sleep(5000);
			    	Assert.assertTrue(driver.getPageSource().contains("First Name"));
			    System.out.println(" ***User is at shipping details page");
			    	}
				catch(Throwable e){
			    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Shipping Details Page");
			    	fail(e.getMessage());
			    	}
			    Thread.sleep(10000);
				//waitForElementPresent(driver,"id",FBConsts.PROCEED);
				System.out.println(" ***To Secure Payment page");
				driver.findElement(By.id(FBConsts.PROCEED)).click();
                            ///SOLD out 
			if(driver.getPageSource().contains("temporarily Sold Out"))
			{   
				System.out.println(" ***the product is sold out so selecting other product");
				//click on shopping cart and remove the item
				driver.findElement(By.cssSelector(FBConsts.MY_SHOPPING_SACK)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.cssSelector(FBConsts.REMOVE_ITEM)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				//boolean sold = true;
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.id(FBConsts.SEARCH_WINDOW)).sendKeys(FBConsts.SEARCH_ITEM2);
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.id(FBConsts.SEARCH)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.xpath(FBConsts.SELECT_FIRST_ITEM)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.xpath(FBConsts.BUY_NOW)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.id(FBConsts.PROCEED)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				driver.findElement(By.id(FBConsts.PROCEED)).click();
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
			}
		//	else
		//	{	
				//waitForElementPresent(driver,"xpath",FBConsts.CREDIT_CARD);
				System.out.println(" ***verifying the Payment Option Page");
							
				/*try{
			    	
			    	Assert.assertTrue(driver.getPageSource().contains("Secure Payment Options"));
			        System.out.println(" ***User is at Payment details page");
			    }catch(Throwable e){
			    	fail(e.getMessage());
			    }
			*/
				//this method clicks on the every link on the payment page and verify each link.
				WebElement select = driver.findElement(By.className("payment_options"));
			    List<WebElement> allOptions = select.findElements(By.tagName("li"));
			    for (WebElement option : allOptions) {
			    	   	
			    	
			    	System.out.println(" ***verifying the given option and clicking :"+option.getText());
			        option.click();
			        System.out.println(" ***Clicked on the given payment option :"+ option.getText());
			        Thread.sleep(6000);
				    
				    if(option.getText().equalsIgnoreCase("Net Banking"))
				    {
				    	try{
				    		System.out.println(" ***Verifying the Payment option : "+option.getText());
				       Assert.assertTrue(driver.getPageSource().contains("We will redirect you to the bank or payment partner site for your payment."));
				       System.out.println(option.getText()+"Option verified");
				    	
				    }
				    catch(Throwable t)
				    {
				    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Net Banking Page");
				    	fail(t.getMessage());
				    }
				    }else if(option.getText().equalsIgnoreCase("Credit Card"))
				    {
				    	try{
				    		System.out.println(" ***Verifying the Payment option : "+option.getText());
					       Assert.assertTrue(driver.getPageSource().contains("Card Type"));
					       Assert.assertTrue(driver.getPageSource().contains("Card Number"));
					       Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
					       Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
					       Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card"));
					       System.out.println(option.getText()+"Option verified");
					    	
				    }
				    	 catch(Throwable t)
						    {
				    		 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Credit Card Page");
						    	fail(t.getMessage());
						    }	
			}else if(option.getText().equalsIgnoreCase("Cash"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("Select a partner that has a location near you."));
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  - at Cash Page");
					fail(t.getMessage());
				}
			
			}else if(option.getText().equalsIgnoreCase("Debit Card"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("Issuing Bank"));
					Assert.assertTrue(driver.getPageSource().contains("Card Number"));
					Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
					Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
					Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card "));
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Debit Card Page");
					fail(t.getMessage());
				}
			}else if(option.getText().equalsIgnoreCase("Credit Card EMI"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("Credit Card Bank"));
					Assert.assertTrue(driver.getPageSource().contains("EMI Plan"));
					Assert.assertTrue(driver.getPageSource().contains("Card Type"));
					Assert.assertTrue(driver.getPageSource().contains("Card Number"));
					Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
					Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
					Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card"));
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  - at Credit Card EMI page");
					fail(t.getMessage());
				}
			}else if(option.getText().equalsIgnoreCase("Cheque"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed."));
					
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method  - at Cheque page");
					fail(t.getMessage());
				}
			}else if(option.getText().equalsIgnoreCase("Cash On Delivery"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("You need to make a cash payment for any order placed only at the time of delivery,"));
					
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Cash on Delivery Page");
					fail(t.getMessage());
				}
			}else if(option.getText().equalsIgnoreCase("Pay by Payback"))
			{
				try{
					System.out.println(" ***Verifying the Payment option :"+option.getText());
					Assert.assertTrue(driver.getPageSource().contains("Redeem your i-mint point."));
					
					System.out.println(option.getText()+"Option verified");
					
					
				}catch(Throwable t)
				{
					//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Product Purchase Method - at Pay BY PayBack Point Page");
					fail(t.getMessage());
				}
				
			}
			    }
			    Thread.sleep(3000);
				waitForElementPresent(driver,"linkText",FBConsts.MY_ACCOUNT);
				System.out.println(" ***Click on My Account link");
				driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
				Thread.sleep(2000);
				System.out.println(" ***Click on Sign out Link");
				driver.findElement(By.id(FBConsts.LOG_OUT)).click();

				//*******************************************************************
				//Item Clean up script to clean up the cart item
				
								
				
	}catch (Throwable e){
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Product Purchase - Product Purchase Test");
		fail(e.getMessage());
	    }
	}
//Item Clean up script to clean up the cart item


//@Test(dependsOnMethods ="PurchaseProductTest",groups={"Sanity"})
//@Test()
@Test(dependsOnMethods ="PurchaseProductTest",groups={"Sanity"})
public void ItemCleanupTest() {
		try{	

		System.out.println(" ***Future Bazaar Sanity Script # 4 - Item Cleanup");
		driver.get(FBConsts.URL);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		Thread.sleep(2000);
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		//assertEquals("FutureBazaar: Discover great deals in Laptops, Mobiles, Computers, Electronics, Home Fashion and Clothing", driver.getTitle());
		Thread.sleep(3000);
		try{
			System.out.println("Verifying the Futurebazaar is opened or not");
			Assert.assertTrue(driver.findElement(By.id(FBConsts.FB_LOGO)).isDisplayed(),"Futurebazaaar.com is not loaded properly");
		}catch(Throwable t)
		{
			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test -At item Clean up Test, Futurebazaar.com is not Loaded");
	  	fail(t.getMessage());
		}
		
		System.out.println(" ***Click on My Account link");
		waitForElementPresent(driver,"linkText",FBConsts.MY_ACCOUNT);
		driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
		Thread.sleep(3000);
		System.out.println(" ***Click on Sign in Link");
			driver.findElement(By.id(FBConsts.SIGN_IN)).click();
			
				
		//Login to the Account
	  Thread.sleep(10000);
      System.out.println(" ***Enter User Name");

      driver.findElement(By.id(FBConsts.USER_NAME)).clear();
      driver.findElement(By.id(FBConsts.USER_NAME)).sendKeys(FBConsts.USER_ID);
      Thread.sleep(2000);

      System.out.println(" ***Enter Password");
      driver.findElement(By.id(FBConsts.PASSWORD)).clear();
      driver.findElement(By.id(FBConsts.PASSWORD)).sendKeys(FBConsts.USER_PASSWORD);
      Thread.sleep(2000);

      System.out.println(" ***Click on Login Button");
      driver.findElement(By.id(FBConsts.LOGIN)).click();
		System.out.println(" ***User logged in");
		
		Thread.sleep(3000);
		System.out.println(" ***Clean Up The Item Added In Cart In Previous Step");
		waitForElementPresent(driver,"css",FBConsts.MY_SHOPPING_SACK);
		driver.findElement(By.cssSelector(FBConsts.MY_SHOPPING_SACK)).click();
				Thread.sleep(2000);	


				if(driver.getPageSource().contains("Your shopping cart is empty."))
				{
					System.out.println(" ***No Items In Cart!!");
					Thread.sleep(1000);
				}
		else
				{  
			       
					driver.findElement(By.cssSelector(FBConsts.REMOVE_ITEM)).click();
					Thread.sleep(3000);
					if(driver.getPageSource().contains("Remove item x"))
					{
						System.out.println(" ***Removing the other items");
						driver.findElement(By.cssSelector(FBConsts.REMOVE_ITEM)).click();
					}
					else
					Thread.sleep(1000);
					System.out.println(" ***Items Removed From Cart");
				}
				System.out.println(" ***Click on My Account link");
				driver.findElement(By.linkText(FBConsts.MY_ACCOUNT)).click();
				Thread.sleep(1000);
		System.out.println(" ***Click on Sign out Link");
		driver.findElement(By.id(FBConsts.LOG_OUT)).click();
		System.out.println(" ***User logged out ");


		}catch (Throwable e){
			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Item Clean UP method- Item Clean Up Test Method");
			fail(e.getMessage());
		}
	
}
/*
 * Name: waitForElementPresent
 * Description: Wait for an element in the browser present
 * @param selenium
 * @param element
 * @param timeout (in second)
 * @throws InterruptedThrowable 
 */
	public static void waitForElementPresent(WebDriver driver,String identifyBy,  String locator) throws InterruptedException {
		int DEFAULT_WAIT_TIME = 60; //1 Min
		for (int i = 0;; i++) {
			if (i >= DEFAULT_WAIT_TIME){ 
				System.out.println( "WaitForElementPresent timeout: " + locator);
				break; 
			}
			try { 
				if (identifyBy.equalsIgnoreCase("xpath")){
					driver.findElement(By.xpath(locator));
					break; 
				}else if (identifyBy.equalsIgnoreCase("id")){
					driver.findElement(By.id(locator));
					break; 
				}else if (identifyBy.equalsIgnoreCase("name")){
					driver.findElement(By.name(locator));
					break; 
				}else if (identifyBy.equalsIgnoreCase("linkText")){
					driver.findElement(By.linkText(locator));
					break; 
				}else if (identifyBy.equalsIgnoreCase("css")){
					driver.findElement(By.cssSelector(locator));
					break; 
				}	

			} catch (Throwable e) {
				//do nothing if not found yet
			}
			Thread.sleep(1000);
		}

	}
public static void selectValue(WebDriver driver, String valToBeSelected){
    List <WebElement> options = driver.findElements(By.tagName("option"));
	for (WebElement option : options) {
		if (valToBeSelected.equalsIgnoreCase(option.getText())){
			option.click();
			break;
		}
	    }
}
public static boolean assertLinkNotPresent (WebDriver driver, String idvalue) throws Exception {
	List<WebElement> bob = driver.findElements(By.id(idvalue));
	  if (bob.isEmpty() == false) {
	    throw new Exception (idvalue + " (Link is present)");
	  }
	return false;
	}
}

	
