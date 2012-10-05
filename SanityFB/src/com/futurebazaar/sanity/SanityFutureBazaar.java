package com.futurebazaar.sanity;

import static org.testng.AssertJUnit.fail;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;





import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.statements.Fail;
import org.openqa.jetty.http.SSORealm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.selenesedriver.GetPageSource;
import org.openqa.selenium.internal.selenesedriver.IsElementDisplayed;
import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
import org.openqa.selenium.internal.seleniumemulation.IsTextPresent;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.reporters.FailedReporter;




import com.thoughtworks.selenium.condition.Presence;


public class SanityFutureBazaar {
	public WebDriver driver;
	public Properties option;
	public Properties or;
	public WebDriverWait wait;
	public String OrderID=null;
	public String Reference_ORDER_ID=null;
	public String fs=null;
	public String Order_conf=null;
	public String url=null;
	
	//method use to open the proper URl based on the choice from the Property file
	private boolean openURL(String url)
	{
		if (url.equalsIgnoreCase("QA"))
		{
			driver.get("http://qa.futurebazaar.com");
		}else if (url.equalsIgnoreCase("PP"))
		{
			driver.get("http://pp.futurebazaar.com");
		}else if(url.equalsIgnoreCase("www"))
		{
			driver.get("http://www.futurebazaar.com");
		}
		return true;
	}
	
	
@BeforeSuite
@Parameters({"Env","order"})
public void initalization(String env,String order) throws IOException
{ 
	//webdriver wait for the driver to wait for the element
	//wait = new WebDriverWait(driver,20);
	 try{
		 System.out.println("value of Environemt is "+ env);
	System.out.println("value of the ORder placing or not is "+order);
		 System.out.println("Loading the property Files");
    fs = File.separator;
	option = new Properties();
    FileInputStream ff = new FileInputStream(System.getProperty("user.dir")+fs+"util"+fs+"options.properties");
    option.load(ff);
    System.out.println(System.getProperty("user.dir")+fs+"util"+fs+"options.properties");
	or = new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+fs+"util"+fs+"OR.properties");
    or.load(ip);
    System.out.println("Property files loaded successfully");
	//DesiredCapabilities capabilitie = new DesiredCapabilities();
    //Proxy proxy = new Proxy();
    //proxy.setHttpProxy("10.114.61.7:3128");
    //proxy.setSslProxy("10.114.61.7:3128");
    //capabilitie.setCapability("proxy",proxy);
   
      
  //Loading the Browser
    System.out.println("Loading the proper browser Drivers");
    
  		if(option.getProperty("browserType").equals("FF"))
  		{ 
  			FirefoxProfile profile = new FirefoxProfile();
  			profile.setPreference("network.proxy.type", 1);
  			profile.setPreference("network.proxy.http", "10.114.61.7");
  			profile.setPreference("network.proxy.http_port", 3128);
  			profile.setPreference("network.proxy.ssl", "10.114.61.7");
  			profile.setPreference("network.proxy.ssl_port", 3128);
  			
  			
  			profile.setAcceptUntrustedCertificates(true);
  			profile.setAssumeUntrustedCertificateIssuer(true);
  			//profile.setPreference("network.proxy.share_proxy_settings",true);
  			DesiredCapabilities capabilities = new DesiredCapabilities();
  			//capabilities.setCapability("network.proxy.http", "10.202.18.156");

  			//capabilities.setCapability("network.proxy.http_port", 3128);
  			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
  			
  			
  			
  			 driver = new FirefoxDriver(profile);
  			
  			
  			
  		}else if(option.getProperty("browserType").equals("IE"))
  				{
  					driver = new InternetExplorerDriver();
  				}else if(option.getProperty("browserType").equals("CC"))
  					{
  					       
  					System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+fs+"lib"+fs+"chromedriver.exe");
  					driver = new ChromeDriver();
  					
  					}		
    System.out.println("Browser driver loaded");
    
    //String URl= option.getProperty("testURL");
    openURL(env);
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    Thread.sleep(1000);
    //driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
    wait = new WebDriverWait(driver,30);		
	 }catch(Throwable t)
	 {
		 t.getMessage();
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"BrowserFailure.png"));

	 }
	 }

// ************
// Testing Methods Started
/*
@Test
public void facebookLoginCheck() throws InterruptedException
{
	System.out.println("in facebook login");
	//this method is used to log in by Facebook account 
			System.out.println("login by the facebook Account method");
			driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
			System.out.println("My Account link clicked");
			driver.findElement(By.className(or.getProperty("FACEBOOK_LOGINLINK_CLASS"))).click();
			System.out.println("Clicked on facebook link");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//navigating to the facebook window
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				
				System.out.println(driver.getTitle());
				
				}
			
				
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.id(or.getProperty("FACEBOOK_USERID_ID"))).sendKeys(option.getProperty("FBUserid"));
			System.out.println("user id as an email is entered on facebook popup");
			driver.findElement(By.id(or.getProperty("FACEBOOK_PASSWORD_ID"))).sendKeys(option.getProperty("Password"));
			System.out.println("users password is entered on facebook");
		
			try {		
			WebElement element = driver.findElement(By.name(or.getProperty("FACEBOOK_LOGIN_NAME")));
			element.sendKeys("",Keys.ENTER);
			
			Thread.sleep(5000);	
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Clicked on Sign in link on facebook");	
			
			//giving handle to the Futurebazaar 
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				
				System.out.println(driver.getTitle());
				
				}
			driver.switchTo().defaultContent();
			
			//Verifying the login by facebook.
			
			Assert.assertTrue(driver.getPageSource().contains("Hi")," user is not able to Login from My Account Option ");
			System.out.println(" Facebook login Verifed");
			
			Thread.sleep(7000);
			logoutFacebook();
			
			
}
*/

@Test(dependsOnMethods="VerifyingAllCategoryLinksOnHomePage",groups={"Sanity"})
public void signInSingOutCheck() throws InterruptedException, IOException
{
	try{
	//method used to login or Sign UP and Log Out by the UserId as Email 
		System.out.println("login by the userEmail method");
		
		driver.findElement(By.linkText(or.getProperty("SIGN_IN_LINK"))).click();
	
		System.out.println("My Account link clicked");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("lightbox_content")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lightbox_content")));
		driver.findElement(By.id(or.getProperty("USER_NAME_ID"))).sendKeys(option.getProperty("testuser"));
		System.out.println("user id as an email is entered");
		driver.findElement(By.id(or.getProperty("USER_PASSWORD_ID"))).sendKeys(option.getProperty("testuserpassword"));
		System.out.println("users password is entered");
		driver.findElement(By.id(or.getProperty("USER_LOGIN_ID"))).click();
		System.out.println("Clicked on Sign in link");
		Thread.sleep(4000);
		//System.out.println(driver.findElement(By.cssSelector("div.top_nav")));
		
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("html/body/div[3]/div/div[1]/div[1]")));
		//wait.until(ExpectedConditions.textToBePresentInElementLocatedBy(By.cssSelector("li.mart2 > span"),"Hi"));
		
		//wait.withMessage("Waiting for the User to log in properly and visible");
		if(option.getProperty("browserType").equals("IE"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
			wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
			
		}else if(option.getProperty("browserType").equals("CC"))
		{   
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
			//wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
			//System.out.println(driver.findElement(By.cssSelector("li.mart2 > span")).getText());
			//System.out.println(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText());
			
	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header_main")));
			wait.until(ExpectedConditions.textToBePresentInElement(By.className("header_main"),"Hi"));
			System.out.println(driver.findElement(By.className("header_main")).getText());
			
			//System.out.println(driver.findElement(By.xpath("html/body/div[4]/div/div[1]/ul/li[2]/span")).getText());
	
		}
		
	
		
			Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("html/body/div[4]/div/div[1]/ul/li[2]/span")).getText().contains("Hi")," user is not able to Login from My Account Option ");
		}catch(Throwable t)
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"SignUP.png"));

			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed. - User is not able to log in ");
			
			fail(t.getMessage());
		}
		System.out.println("login Verified");
		
} 

@Test(dependsOnMethods="signInSingOutCheck",groups={"Sanity"})
public void myAccountCheck() throws InterruptedException
{
	//user logged in 
	try{
	System.out.println(">>>>>>> My Account Link Check Test Method>>>");
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))));
	driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
	System.out.println("My Account Clicked");
	Thread.sleep(2000);
	//wait.until(ExpectedConditions.elementToBeClickable(By.linkText(or.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))));
	System.out.println("Clicking My Orders");
	driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_MY_ORDERS_LINK"))).click();
	System.out.println("verifying the My Orders tab");
	
	//Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_ORDERS_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.getPageSource().contains("Order Status"));
      }catch(Throwable t)
           {
    	  //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My Orders tab ");
			
	fail(t.getMessage());
        }
	
	try{
		System.out.println("clicking MY WALLET Tab");
		driver.findElement(By.xpath(or.getProperty("MY_WALLET"))).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_WALLET_VERIFICATION_TEXT_XPATH"))));
			Assert.assertTrue(driver.getPageSource().contains("Welcome to your Wallet"));
			System.out.println(" *** My Wallet Tab Page verified");
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My WishList tab ");
	fail(t.getMessage());
}	

	
	
	
	try{
		System.out.println("clicking MY WISHLIST Tab");
		driver.findElement(By.xpath(or.getProperty("MY_WISHLIST"))).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_ORDERS_VERIFICATION_TEXT_XPATH"))));
			Assert.assertTrue(driver.getPageSource().contains("Browse through our products and"));
			System.out.println(" *** My Wish List Tab Page verified");
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My WishList tab ");
	fail(t.getMessage());
}	
		
   /*try{	
	driver.findElement(By.linkText(or.getProperty("MY_SUBCRIPTIONS_LINK"))).click();
	System.out.println("verifying the My Subscription tab");
	//Thread.sleep(2000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_SUBSCRIPTION_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.getPageSource().contains("Send me daily notifications for deals"));
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My Subscription tab ");
	fail(t.getMessage());
}
	*/
        try{  
		driver.findElement(By.xpath(or.getProperty("MY_PROFILE_XPATH"))).click();
	//Thread.sleep(2000);
	System.out.println("verifying the My Profile tab");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_PROFILE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.getPageSource().contains("Name"));
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My Profile tab ");
	fail(t.getMessage());
}
	
        try{
		driver.findElement(By.xpath(or.getProperty("ADDRESS_BOOK_XPATH"))).click();
	//Thread.sleep(2000);
	System.out.println("verifying the My Address Book tab");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MY_ADDRESS_VERIFICATION_TEXT_XPATH"))));
					Assert.assertTrue(driver.getPageSource().contains("Add New Address"));
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - My Address Book tab ");
	fail(t.getMessage());
}
		
        try{
					driver.findElement(By.xpath(or.getProperty("MANAGE_PASSWORD_XPATH"))).click();
	//Thread.sleep(2000);
	System.out.println("verifying the Password tab");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("MANAGE_PASSWORD_VERIFICATION_TEXT_XPATH"))));
	Assert.assertTrue(driver.getPageSource().contains("Old Password"));
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in My Account Page - Manage Password  tab ");
	fail(t.getMessage());
}

        try{
	System.out.println(" ***Checking the Track Order link");
	//waitForElementPresent(driver,"linkText",FBConsts.TRACK_ORDER);
	//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	driver.findElement(By.linkText(or.getProperty("TRACK_ORDER_LINK"))).click();
	//Thread.sleep(5000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("TRACK_ORDER_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.getPageSource().contains("You can track your individual order with your Order number"));
		System.out.println(" ***My Track order page is verified");
}catch(Throwable t)
{
	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed at - Track Order Page");
	fail(t.getMessage());
}
	
	System.out.println("*****All the Tabs from My account is verified*****");
	 Thread.sleep(3000);
		driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
		System.out.println("Clicked on user Log out. ");
		driver.findElement(By.id(or.getProperty("LOG_OUT_ID"))).click();
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("LOG_OUT_VERIFICATION_TEXT_XPATH"))));
		 
	 
	   // Assert.assertFalse(driver.getPageSource().contains("Hi"),"user is not able to Log out from My account link");
		System.out.println("user Logged out Successfully");
	
	
}

@Test(groups={"Sanity"})
public void CheckForHomePageCategoryLinks() throws InterruptedException
{
	HomePageCheck home = new HomePageCheck(driver, or, wait);
	home.NewArrivalsCheck();
	home.BedAndBathCheck();
	home.appliancesCheck();
	home.KitchenAndDiningCheck();
	home.OfficeCheck();
	home.ClearanceCheck();
}

@Test(dependsOnMethods="CheckForHomePageCategoryLinks",alwaysRun=true ,groups={"Sanity"})
public void homePageCheck() throws InterruptedException, IOException
{ try{
	System.out.println("\n");
	System.out.println(">>>>>>>> Home Page Check Method >>>>>>>>>");
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	Assert.assertTrue(driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).isDisplayed(),"Home Page for the Futurebazaar is not loaded properly");
	System.out.println("Home page is displayed");
	System.out.println("verifying the CARousel Links on home page");

	/*Assert.assertTrue(driver.findElement(By.linkText(or.getProperty("CAROUSEL_FIRST_LINK"))).isDisplayed(),"Carousel link is not displayed");
	Assert.assertTrue(driver.findElement(By.linkText(or.getProperty("CAROUSEL_SECOND_LINK"))).isDisplayed(),"Carousel link is not displayed");
	Assert.assertTrue(driver.findElement(By.linkText(or.getProperty("CAROUSEL_THIRD_LINK"))).isDisplayed(),"Carousel link is not displayed");
	*/
//Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("TODAYS_DEALS"))).isDisplayed(),"TODAYS DEALS option in menu is not displayed");	


//Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("POPULAR_DEALS"))).isDisplayed(),"POPULAR DEALS option in menu is not displayed");

	/*try{
	System.out.println("Clicking OUTDOOR");
	//driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("OUTDOOR"))));
driver.findElement(By.xpath(or.getProperty("OUTDOOR"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));
Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Sports & Outdoor"));
}catch(Throwable t)
{    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"OUTDOORFail.png"));

	 //PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-Popular deals Link ");
	fail(t.getMessage());
}
//Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("NEW_ARRIVALS"))).isDisplayed(),"NEW ARRIVALS option in menu is not displayed");

//Assert.assertTrue(driver.findElement(By.cssSelector(or.getProperty("EUREKA_CSS"))).isDisplayed(),"EUREKA option in menu is not displayed");
*/
/*try{
System.out.println("Clicking STORAGE ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("STORAGE"))));
driver.findElement(By.xpath(or.getProperty("STORAGE"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Storage & Organizers"));

}catch(Throwable t)
{
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// Now you can do whatever you need to do with it, for example copy somewhere
	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Storagefail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}
*/
try{
System.out.println("Clicking MEN");

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("MEN"))));
driver.findElement(By.xpath(or.getProperty("MEN"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Men"));

}catch(Throwable t)
{    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Menfail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking WOMEN");

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("WOMEN"))));
driver.findElement(By.xpath(or.getProperty("WOMEN"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Women"));

}catch(Throwable t)
{    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Womenfail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking KIDS");

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("KIDS"))));
driver.findElement(By.xpath(or.getProperty("KIDS"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Kids"));

}catch(Throwable t)
{      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//Now you can do whatever you need to do with it, for example copy somewhere
FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"kidsfail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking BABY");

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("BABY"))));
driver.findElement(By.xpath(or.getProperty("BABY"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Baby"));

}catch(Throwable t)
{    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"BABYfail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking Mobiles ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("MOBILES"))));
driver.findElement(By.xpath(or.getProperty("MOBILES"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Mobiles"));

}catch(Throwable t)
{
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// Now you can do whatever you need to do with it, for example copy somewhere
	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Mobilefail.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}


try{
System.out.println("Clicking JEWELLERY ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("JEWELLERY"))));
driver.findElement(By.xpath(or.getProperty("JEWELLERY"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Jewellery"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Jewelleryfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking Watches ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("WATCHES"))));
driver.findElement(By.xpath(or.getProperty("WATCHES"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Watches"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Watchesfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking gifts ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("GIFTS"))));
driver.findElement(By.xpath(or.getProperty("GIFTS"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("GIFTS_PAGE_VERIFICATION"))).getText().contains("Gifts"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"giftfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking gift Vouchers ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("GIFT_VOUCHERS"))));
driver.findElement(By.xpath(or.getProperty("GIFT_VOUCHERS"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));

Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Gift Vouchers"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"giftVouchersfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking EGv option ");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("EGV"))));
driver.findElement(By.xpath(or.getProperty("EGV"))).click();
//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));

Assert.assertTrue(driver.getPageSource().contains("Let your loved ones choose the right gift for themselves"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"EGVfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}
/*
try{
System.out.println("Clicking EUREKA ");
driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("EUREKA_CSS"))));
driver.findElement(By.xpath(or.getProperty("EUREKA_CSS"))).click();
wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH")),"Innovative products"));
Assert.assertTrue(driver.getPageSource().contains("Innovative products"));
}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File("C:\\workspace\\SanityFB\\Snapshot For Sanity failure\\EUREKAfail.png"));
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
	fail(t.getMessage());
}
*/

try{
System.out.println("Clicking Promotions");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("PROMOTIONS_XPATH"))));
driver.findElement(By.xpath(or.getProperty("PROMOTIONS_XPATH"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));
Assert.assertTrue(driver.getPageSource().contains("Promotions"));
}catch(Throwable t)
{
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Promotions Link ");
	fail(t.getMessage());
}

try{
System.out.println("Clicking SELLERS");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("SELLERS"))));
driver.findElement(By.xpath(or.getProperty("SELLERS"))).click();
//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));
Assert.assertTrue(driver.getPageSource().contains("Why sell with us?"));

}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Sellersfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());
}

try{
System.out.println("Clicking BULK ORDERS");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("BULK_ORDERS_XPATH"))));
driver.findElement(By.xpath(or.getProperty("BULK_ORDERS_XPATH"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_FOR_PROMOTIONS"))));
Assert.assertTrue(driver.getPageSource().contains("To place a bulk order"));
}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"BulkOrdersfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());
}

try{
System.out.println("Clicking PAYBACK");
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("PAYBACK"))));
driver.findElement(By.xpath(or.getProperty("PAYBACK"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_FOR_PROMOTIONS"))));
Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("PAYBACK"));
}catch(Throwable t)
{
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Paybackfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());
}


//Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("CLEARANCE"))).isDisplayed(),"CLEARANCE option in menu is not displayed");
try{
System.out.println("Clicking CLEARANCE");
driver.findElement(By.xpath(or.getProperty("CLEARANCE"))).click();

wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='breadcrumb']")));
Assert.assertTrue(driver.getPageSource().contains("Clearance Sale"));
}catch(Throwable t)
{
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-Clearance Link ");
	fail(t.getMessage());
}
System.out.println("home menu check pass");

System.out.println("clicking on the home page logo");
driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
Assert.assertTrue(driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).isDisplayed());
System.out.println("Home page is displayed");

//Calling a function for the Category items click.
//clickingCategoryLinks();




}catch(Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"HomepageFilure.png"));
    fail(t.getMessage());
}


}

@Test(dependsOnMethods="homePageCheck",groups={"Sanity"})
public void VerifyingAllCategoryLinksOnHomePage() throws IOException
{
	try{
		System.out.println("Clicking Home and Living");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("HOMEandLIVING"))));
		driver.findElement(By.xpath(or.getProperty("HOMEandLIVING"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Home & Living"));
		}catch(Throwable t)
		{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    // Now you can do whatever you need to do with it, for example copy somewhere
			    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"HOmeANDlivingfail.png"));
				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
				fail(t.getMessage());		}
        
	try{
		System.out.println("Clicking Bed and Bath");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("BEDandBATH_XPATH"))));
		driver.findElement(By.xpath(or.getProperty("BEDandBATH_XPATH"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Bed & Bath"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"BedAndBathfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());}	
	
	try{
		System.out.println("Clicking Kitchen And Dining");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("KITCHENandDINING"))));
		driver.findElement(By.xpath(or.getProperty("KITCHENandDINING"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Kitchen & Dining"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"KitchenAndDiningfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());	}
	
	try{
		System.out.println("Clicking Appliances");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("APPLIANCES"))));
		driver.findElement(By.xpath(or.getProperty("APPLIANCES"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Appliances"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Appliancesfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());	}
	
	try{
		System.out.println("Clicking Entertainment");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("ENTERTAINMENT"))));
		driver.findElement(By.xpath(or.getProperty("ENTERTAINMENT"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Entertainment"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Entertainmentfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());	}
	
	try{
		System.out.println("Clicking OFFICE");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("OFFICE"))));
		driver.findElement(By.xpath(or.getProperty("OFFICE"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("HOME_AND_LIVING_PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Office"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"electronincsfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());		}
	
	/*try{
		System.out.println("Clicking BigBAzaar");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("BigBAzaar"))));
		driver.findElement(By.xpath(or.getProperty("BigBAzaar"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))));
		Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("PAGE_VERIFICATION_TEXT_XPATH"))).getText().contains("Big Bazaar"));
		}catch(Throwable t)
		{ File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Bigbazaarfail.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page- Eureka Link ");
		fail(t.getMessage());	}
     */
	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	Assert.assertTrue(driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).isDisplayed());
	System.out.println("Home page is displayed");


}



@Test(dependsOnMethods="myAccountCheck",groups={"Sanity"})
@Parameters("order")
public void productPurchase(String order) throws InterruptedException, IOException
{   
	System.out.println("Going to Payment Page");
	goTOPaymentPage();
		//verify all the payment options.
	System.out.println("Verifying All the Options of the Payment Type");	
	paymentPageOptionsVerification();
	System.out.println("Purchasing the Product test completed Succefully");
	
	//Payment By Cheque if the Testing type is 
	if(order.equalsIgnoreCase("yes"))
	{
	purchaseProduct();
	}
	System.out.println("Not Purchasing the Product & calling item clean UP.");
}


@Test(dependsOnGroups= {"Sanity"})
@Parameters({"Env","order"})
public void CODproductpurchase(String env, String order) throws IOException
 {
	//Skipping this test if the TestType is set to NO and PLace order if Set to YES
	if(order.equalsIgnoreCase("yes"))
	{ //checking if the Testing type is Yes then only run the CAsh on Delivery payment Type
 
	
	System.out.println("******* Testing the Cash on Delivery Purchase.....");
	selectCODproductAndGoToPaymentPage(env);
	System.out.println("******* Going to Payment Page for Cash on Delivery Purchase.....");
	try{
		System.out.println("**** Purchasing the product by Cash on delivery ");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("payment_options")));
		WebElement Purchaseselect = driver.findElement(By.className("payment_options"));
	    List<WebElement> PurchaseallOptions = Purchaseselect.findElements(By.tagName("li"));
	    for (WebElement Purchaseoption : PurchaseallOptions) {
	    	   	
	    	
	    	//System.out.println("verifying the given option and clicking :"+option.getText());
	    	
		       if(Purchaseoption.getText().equalsIgnoreCase("Cash on Delivery"))
		    {
		    	
		    	Purchaseoption.click();
		    
		    		Thread.sleep(2000);
		    		System.out.println("Verifying the Payment option :"+Purchaseoption.getText());
		    		wait.until(ExpectedConditions.elementToBeClickable(By.id("verify")));
		    		System.out.println(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText());
		    		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText().contains("Verify your mobile number"));	
		    		
		    		//calling the Cash on delivery Method for COD
		    		CashOnDeliveryProcess();
		    							
		    		System.out.println("Buying the Product So Clicking on the Payment ");
		    		wait.until(ExpectedConditions.elementToBeClickable(By.id(or.getProperty("COD_PROCEED_BUTTON_ID"))));
		    		driver.findElement(By.id(or.getProperty("COD_PROCEED_BUTTON_ID"))).click();
		    		Thread.sleep(8000);
					do{
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='content']/div[2]/div/div")));
					 url = driver.getCurrentUrl();
						System.out.println(" Order Confirmation Page");
									
					Order_conf= driver.findElement(By.xpath("//div[@id='content']/div[2]/div/div")).getText();
					Assert.assertTrue(driver.getPageSource().contains("Congrats on your purchase."),"Order Confirmation Page is not Displayed.");
					if(Order_conf!=null)
					{
						break;
					}
					}while(Order_conf==null && url.contains("confirmation"));
					Thread.sleep(2000);
					
					//Getting the value of the Status of the order from the DataBase give the Oder ID 
					ConnectToDB conne = new ConnectToDB();
					String SQL= "select support_state from orders_order where reference_order_id="+OrderID;
					String Status_CODE= conne.ReturnStatusCode(SQL);
                    System.out.println(Status_CODE);
					System.out.println("verifying the Status code for the COD should be confirmed");
					Assert.assertEquals(Status_CODE,"confirmed");
					
					//Checking the SAP status Code 
					System.out.println("verifying the SAP Status code for the COD should not be No Stock");
					String SQLforSAPstatus= "select status from orders_saporderitem where order_id=1"+Reference_ORDER_ID; //1 value is added to the order id after removing first 3 values. 
					String SAP_Status_CODE= conne.ReturnSAPStatusCode(SQLforSAPstatus);
					System.out.println(SAP_Status_CODE);
					System.out.println(SAP_Status_CODE.contains("No Stock"));
					Assert.assertFalse(SAP_Status_CODE.contains("No Stock"),"No Stock - status is presnt on the SAP status");
					System.out.println("SAP Status code for the COD should not be No Stock");
					       
					
					
					System.out.println(" Order has been placed successfully ");
					//System.out.println(Order_conf);
					SendCODordersMail.Mailsend(Order_conf,option.getProperty("testURL"));
					
					// Thread.sleep(1000);
						//driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
					    //driver.findElement(By.id(or.getProperty("LOG_OUT_ID"))).click();
					    //System.out.println("user Logged out");
					    
				break;	
		    }
		      
		    	
	         }
		    	    
	    }catch(Throwable t)
		{
			//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-Popular de");
			t.getMessage();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   	 
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"CODMakePaymentPage.png"));
		    
			//throw new script
		 fail(t.getMessage());
		}

	
	}else{
		throw new SkipException("Skipping the Cash on Delivery Purchase as the order placing Is Set to NO...");
	}
	
}

///Method is used to Process the Cash on Delivery type of Payment 
private void CashOnDeliveryProcess() throws SQLException, ClassNotFoundException, InterruptedException
{   Thread.sleep(2000);
	driver.findElement(By.id(or.getProperty("COD_PHONE_TEXT_ID"))).clear();
  	driver.findElement(By.id(or.getProperty("COD_PHONE_TEXT_ID"))).sendKeys("2222222222");
	
	//System.out.println("clicking on the shipping address check box");
	//driver.findElement(By.id("shipping_address_checkbox")).click();
    Thread.sleep(3000);
	driver.findElement(By.id(or.getProperty("COD_PHONE_VERIFY_BUTTON_ID"))).click();
	
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("COD_VERIFICATION_CODE_ID"))));
	//Getting the Value of the Order ID
	System.out.println("Getting the Order ID");

	OrderID=driver.findElement(By.cssSelector(or.getProperty("COD_ORDER_ID_CSS"))).getText();
	System.out.println(OrderID);
	//generating the Reference code for database 
	Reference_ORDER_ID=OrderID.substring(3);      	
	//Getting the value of the Verification Code from the DataBase
	
	ConnectToDB conne = new ConnectToDB();
	String SQL= "select verification_code  from orders_codorderverification where reference_order_id="+OrderID;
	Integer Verification_CODE= conne.ReturnVerificationCode(SQL);
	System.out.println("GET CODE"+Verification_CODE);
	//Converting the Integer to String
	String Verification_CODE_After= new Integer(Verification_CODE).toString();
	System.out.println("Enter the Verification code to the Tect Box");
	driver.findElement(By.id(or.getProperty("COD_VERIFICATION_CODE_ID"))).clear();
	driver.findElement(By.id(or.getProperty("COD_VERIFICATION_CODE_ID"))).sendKeys(Verification_CODE_After);
    
	System.out.println("Clicking on the Proceed Button");
	
	
}

private void goTOPaymentPage() throws IOException
{
	System.out.println("\n");

	System.out.println(" >>>>>>> Product purchase Testing by Searching a product and Buying the product >>>>>>");

	System.out.println("opening the home page by clicking the  futurebazaar LOGO");

	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	try{
	System.out.println("Entering the search item to the search field");
	driver.findElement(By.id(or.getProperty("SEARCH_WINDOW_ID"))).sendKeys(option.getProperty("SearchItem"));

	driver.findElement(By.id(or.getProperty("SEARCH_ID"))).click();
    
	System.out.println("selecting the first item from the serach results");
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("SELECT_FIRST_ITEM"))));
	//driver.findElement(By.xpath(or.getProperty("SELECT_FIRST_ITEM"))).click();
  
	System.out.println("Going to the HOme PAge to Select the Product");
	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	System.out.println("clicking first");
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("HOME_SECOND_PRODUCT_LINK_XPATH"))));
	driver.findElement(By.xpath(or.getProperty("HOME_SECOND_PRODUCT_LINK_XPATH"))).click();
	 
	//driver.findElement(By.xpath(".//*[@id='menu']/ul/li/a/div")).click();
   if(driver.findElement(By.className("pdp_add_qty")).getText().contains("OUT OF STOCK")                )
	{   		System.out.println("NOTE: Product is out of Stock So selecting second item");

	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	
		driver.findElement(By.xpath(or.getProperty("HOME_THIRD_PRODUCT_LINK_XPATH"))).click();
	
		//Verifying the Product Description page.
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("BUY_NOW"))));
		
	}
  
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("BUY_NOW"))));
	System.out.println("clicking on the Buy now button");
	Thread.sleep(1000);
   
	driver.findElement(By.xpath(or.getProperty("BUY_NOW"))).click();
	Thread.sleep(2000);
	/*
	//Clicking on the Add More Items Link from the cart Item Page 
	Assert.assertTrue(driver.findElement(By.linkText(or.getProperty("ADD_MORE_ITEMS_LINK"))).isDisplayed(),"ADD MORE ITEMS LINK page is not displayed on the Cart Item Page.");	
	}catch(Throwable t)
	{
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in PDP page ");

		fail(t.getMessage());
	}
	
	try{
	System.out.println("Clicking on the Add More Items Link");
	driver.findElement(By.linkText(or.getProperty("ADD_MORE_ITEMS_LINK"))).click();
	
	//Verifying the Home Page By Carousel Links
	System.out.println("verifying the Home page By Carousel Links");
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	Assert.assertTrue(driver.findElement(By.linkText(or.getProperty("CAROUSEL_FIRST_LINK"))).isDisplayed()," Home page is not Loaded after clicking the Add more items to cart link.");
}catch(Throwable t)
{
	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in ADD more items link");

	fail(t.getMessage());
}
	
	//clicking on the Cart Item Link to get back to the Cart Item Page
	try{
	System.out.println("clicking on the Cart Item Link to get back to the Cart Item Page");
	driver.findElement(By.cssSelector(or.getProperty("CART_ITEM_CSS"))).click();
	*/
	//clicking the Proceed Button on the cart item page
	
	//Assert.assertTrue(driver.findElement(By.name(or.getProperty("continue_name"))).isDisplayed(),"Item Cart Page is not displayed by clicking the cart Item link");
}catch(Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfteBUYNOW.png"));

	fail(t.getMessage());
}
	
	
	try{
		
		Thread.sleep(1000);
	for(String handle : driver.getWindowHandles())
	{
		driver.switchTo().window(handle);
	}
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Proceed")));
	  System.out.println("Cart pop up");
	System.out.println("clicking on the Continue in cart pop up");

	  Assert.assertTrue(driver.findElement(By.name("Proceed")).isDisplayed());
	driver.findElement(By.name("Proceed")).click();
	
		Thread.sleep(1000);
	//user log in for the purchase
	
		if(!driver.getPageSource().contains("Select your shipping address"))
		{
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("mobileLabel")));

		driver.findElement(By.id("mobileLabel")).clear();
		driver.findElement(By.id("mobileLabel")).sendKeys(option.getProperty("testuser"));
		//Thread.sleep(2000);
		driver.findElement(By.id("AlreadyCustomer")).click();
		
		System.out.println("Enter Password");
		driver.findElement(By.id("userPassword")).clear();
		driver.findElement(By.id("userPassword")).sendKeys(option.getProperty("testuserpassword"));
		//Thread.sleep(2000);
		
		System.out.println("Click on Login Button");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		}
		
	}catch (Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfterItemCart.png"));

	fail(t.getMessage());
}
try{
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(or.getProperty("PROCEED_ID"))));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("delivery_table")));
		
		System.out.println("Opening the Payment page by clicking on Shipping Address");
		driver.findElement(By.xpath("//div[@id='page_no_1']/div/p[3]/span[2]")).click();
		//driver.findElement(By.id("delivery_table")).click();
		
		
		//driver.findElement(By.xpath("//input[starts-with(@name,'78030')]")).click();
	
		//wait.until(ExpectedConditions.elementToBeClickable(By.id(or.getProperty("PROCEED_ID"))));
		
	    //driver.findElement(By.id(or.getProperty("PROCEED_ID"))).click();
		Thread.sleep(2000);
		System.out.println("URl of pAyment" +driver.getCurrentUrl());
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PAYMENT_PAGE_VERIFICATION_ID"))));
		
}catch (Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfterProceed.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page after Clicking Proceed.");
	
	fail(t.getMessage());
}

}



private void logoutFacebook() throws InterruptedException
{//this method is used to log out from the facebook login
	Thread.sleep(3000);
	System.out.println("facebook user Log out by the logoutFacebook method");
	driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
	System.out.println("My Account link clicked");
	driver.findElement(By.cssSelector("#f_logout > img")).click();
	System.out.println("FB User logged out");
}

//@AfterClass(alwaysRun=true)
@Test(dependsOnMethods="productPurchase",groups={"Sanity"})
@Parameters("Env")
public void ItemCleanupTest(String env) throws InterruptedException {
		

		System.out.println(" **- Item Cleanup method");
		//driverdriver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	        
		//String URl= option.getProperty("testURL");
	    openURL(env);
	    
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	System.out.println("clicking on the Cart Item Link to get back to the Cart Item Page");
		
	    Integer val = noOfItemsInShoppingCart();
	    if(val==null)
	    {
	    	System.out.println("Shopping cart is empty.");
	    }else 
	    {
	    	driver.findElement(By.cssSelector(or.getProperty("SHOPPING_CART_CSS"))).click();
	    	Thread.sleep(3000);
			for(String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
			}
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cart_content")));
			for(int i=1;i<=val;i++)
		    {
		    	System.out.println("Removing the Items from cart");
		    	Thread.sleep(4000);
		    	driver.findElement(By.cssSelector("button.linkbtn")).click();
		    	
			
		    System.out.println(i+"item cleaned");
		    }
		    Thread.sleep(4000);
		    System.out.println("Item cleaned up from the cart");
		    System.out.println("Clicking on the Start Shopping Link to go to home page");
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Start Shopping")));
			
			driver.findElement(By.linkText("Start Shopping")).click();

			
	    }
				
	
}

public boolean isShoppingCartEmpty()
{
	if(noOfItemsInShoppingCart()==null)
    {
    	return true;     //Return true if shopping cart value is 0 or Null
    }else{
    return false;  // return false if the shopping cart contains some items.
    }
    }

public Integer noOfItemsInShoppingCart()
{   
	//Get the value of the Items in Cart.
	try{
	String TotalItemInCartString=driver.findElement(By.cssSelector(or.getProperty("SHOPPING_CART_CSS"))).getText();
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

public void selectCODproductAndGoToPaymentPage(String env) throws IOException
{

	
	System.out.println(" >>>>>>> Product purchase for Buying the product Having THe Cash on Delivery >>>>>>");

	System.out.println("opening the home page by clicking the  futurebazaar LOGO");

	//String URl= option.getProperty("testURL");
    openURL(env);
    
	 
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("HOME_PAGE_ID"))));
	try{
		
	driver.findElement(By.xpath(or.getProperty("CLEARANCE_THIRD_PRODUCT_XPATH"))).click();
     
	//if the Product is not available for chash on Delivery
	//driver.findElement(By.xpath(".//*[@id='menu']/ul/li/a/div")).click();
   if(checkingCOD())
	{   		System.out.println("NOTE: Product is out of Stock So selecting second item");

	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
          System.out.println("clicking forth");	
		driver.findElement(By.xpath(or.getProperty("CLEARANCE_FORTH_PRODUCT_XPATH"))).click();
	
		//Verifying the Product Description page.
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("BUY_NOW"))));
		
	}
   //checking for the second product is NOt out of stock or not
   if(checkingCOD())

	{
		
		System.out.println("NOTE: Product is out of Stock So selecting Third item");

	driver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	     System.out.println("clicking fifth");
		driver.findElement(By.xpath(or.getProperty("CLEARANCE_FIFTH_PRODUCT_XPATH"))).click();
	
		//Verifying the Product Description page.
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("BUY_NOW"))));

		
	}
  
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty("BUY_NOW"))));
	System.out.println("clicking on the Buy now button");
	Thread.sleep(1000);
 
	driver.findElement(By.xpath(or.getProperty("BUY_NOW"))).click();
	
	
	//Assert.assertTrue(driver.findElement(By.name(or.getProperty("continue_name"))).isDisplayed(),"Item Cart Page is not displayed by clicking the cart Item link");
}catch(Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfteBUYNOW.png"));

	fail(t.getMessage());
}
	
	System.out.println("Cart pop up");
	try{
		
	System.out.println("clicking on the Continue in cart pop up");
	Thread.sleep(3000);
	for(String handle : driver.getWindowHandles())
	{
		driver.switchTo().window(handle);
	}
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Proceed")));
    Assert.assertTrue(driver.findElement(By.name("Proceed")).isDisplayed());
	driver.findElement(By.name("Proceed")).click();
	
		Thread.sleep(3000);
	//user log in for the purchase
	
		if(!driver.getPageSource().contains("Select your shipping address"))
		{
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("mobileLabel")));

		driver.findElement(By.id("mobileLabel")).clear();
		driver.findElement(By.id("mobileLabel")).sendKeys(option.getProperty("testuser"));
		//Thread.sleep(2000);
		driver.findElement(By.id("AlreadyCustomer")).click();
		
		System.out.println("Enter Password");
		driver.findElement(By.id("userPassword")).clear();
		driver.findElement(By.id("userPassword")).sendKeys(option.getProperty("testuserpassword"));
		//Thread.sleep(2000);
		
		System.out.println("Click on Login Button");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		}
		
	}catch (Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfterItemCart.png"));

	fail(t.getMessage());
}
try{
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(or.getProperty("PROCEED_ID"))));
		System.out.println("Opening the Payment page by clicking on Shipping Address");
		//driver.findElement(By.xpath("//div[@id='delivery_table']/div/div/div/p[3]/span[2]")).click();
		driver.findElement(By.xpath("//div[@id='page_no_1']/div/p[3]/span[2]")).click();
		
		//driver.findElement(By.xpath("//input[starts-with(@name,'78030')]")).click();
	
		//wait.until(ExpectedConditions.elementToBeClickable(By.id(or.getProperty("PROCEED_ID"))));
		
	    //driver.findElement(By.id(or.getProperty("PROCEED_ID"))).click();
		Thread.sleep(2000);
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PAYMENT_PAGE_VERIFICATION_ID"))));
		
}catch (Throwable t)
{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"AfterProceed.png"));

	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page after Clicking Proceed.");
	
	fail(t.getMessage());
}
	
	
}



//This method is used to do the Actual Payment of the Product.
private void purchaseProduct() throws IOException
{
	try{
	System.out.println("**** Purchasing the product by Cheque ");
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("payment_options")));
	WebElement Purchaseselect = driver.findElement(By.className("payment_options"));
    List<WebElement> PurchaseallOptions = Purchaseselect.findElements(By.tagName("li"));
    for (WebElement Purchaseoption : PurchaseallOptions) {
    	   	
    	
    	//System.out.println("verifying the given option and clicking :"+option.getText());
    	
	       if(Purchaseoption.getText().equalsIgnoreCase("Cheque/DD"))
	    {
	    	
	   
	   	Purchaseoption.click();
	    
	    		Thread.sleep(2000);
	    		System.out.println("Verifying the Payment option :"+Purchaseoption.getText());
	    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='payment_mode_container']/p[1]")));
				
				System.out.println(driver.findElement(By.xpath("//div[@id='payment_mode_container']/p[1]")).getText());
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='payment_mode_container']/p[1]")).getText().contains("You can make a Cheque/DD payment for any order placed"));	
				
				//System.out.println("clicking on the shipping address check box");
				//driver.findElement(By.id("shipping_address_checkbox")).click();
				
	    		System.out.println("Buying the Product So Clicking on the Payment ");
	    		driver.findElement(By.id(or.getProperty("MAKE_PAYMENT_ID"))).click();
				
				//wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("div.f18.marb5"),"Congrats on your purchase"));
				Assert.assertTrue(driver.getPageSource().contains("Thank you for booking the order"));
				System.out.println(" Order Confirmation Page");
								
				String Order_conf= driver.findElement(By.xpath("//div[@id='content']/div[2]/div/div")).getText();
				Thread.sleep(2000);
				System.out.println(" Order has been placed successfully ");
				//System.out.println(Order_conf);
			   Mail.Mailsend(Order_conf,option.getProperty("testURL"));
				// Thread.sleep(1000);
					//driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
				    //driver.findElement(By.id(or.getProperty("LOG_OUT_ID"))).click();
				    //System.out.println("user Logged out");
				    
			break;	
	    }
	      
	    	
         }
	    	    
    }catch(Throwable t)
	{
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-Popular de");
		t.getMessage();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	 
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"MakePaymentPage.png"));
	
		//throw new script
	 fail(t.getMessage());
	}
}

//THIS METHOD IS USED TO TEST ALL THE PAYMENT OPTION 
private void paymentPageOptionsVerification() throws IOException, InterruptedException
{ //this method clicks on the every link on the payment page and verify each link.
	WebElement select=null;
	try{
	Thread.sleep(5000);
	  System.out.println("Payment page URL : "+driver.getCurrentUrl());
	 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PAYMENT_PAGE_VERIFICATION_ID"))));
	 select = driver.findElement(By.className("payment_options"));
	}
	 catch(Throwable t)
		{
	    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      	 
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"PaymentPAgeFAilure.png"));
		
	    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Credit Card Option");
			fail(t.getMessage());
		}
	
	List<WebElement> allOptions = select.findElements(By.tagName("li"));
    for (WebElement option : allOptions) {
    	   	
    	
    	//System.out.println("verifying the given option and clicking :"+option.getText());
        option.click();
	    System.out.println("Clicked on the given payment option :"+ option.getText());
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='tbl_book']")));
	    try {
	   
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PAYMENT_PAGE_VERIFICATION_ID"))));
    if(option.getText().equalsIgnoreCase("Credit Card"))
    {
    	try{
	    	System.out.println("Verifying the Payment option : "+option.getText());
	      
	   	Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("Select your credit card type"));
		
	       System.out.println(option.getText()+"Option verified");
	      
    }
    catch(Throwable t)
	{
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      	 
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"CreditCardFailure.png"));
	
    	//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Credit Card Option");
		fail(t.getMessage());
	}	
    }else if(option.getText().equalsIgnoreCase("Debit Card"))
    {
    	try{
    		System.out.println("Verifying the Payment option :"+option.getText());
    		//Assert.assertTrue(driver.getPageSource().contains("Issuing Bank"));

    	   	Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("Select your debit card type"));
    		
    		//Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
    		//Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
    		//Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card "));
    		System.out.println(option.getText()+"Option verified");
    		
    		
    	}catch(Throwable t)
    	{
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          	 
    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"DebitCARD.png"));
    	
    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Debit Card Option");
    		fail(t.getMessage());
    	}
    }else if(option.getText().equalsIgnoreCase("NetBanking"))
    {
    	try{
    		System.out.println("Verifying the Payment option :"+option.getText());
    		//Assert.assertTrue(driver.getPageSource().contains("Issuing Bank"));

    	   	Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/p")).getText().contains("Select your bank"));
    		
    		//Assert.assertTrue(driver.getPageSource().contains("Expiration Date"));
    		//Assert.assertTrue(driver.getPageSource().contains("Card Security Code (CVV)"));
    		//Assert.assertTrue(driver.getPageSource().contains("Name on Credit Card "));
    		System.out.println(option.getText()+"Option verified");
    		
    		
    	}catch(Throwable t)
    	{
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          	 
    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Netbanking.png"));
    	
    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Debit Card Option");
    		fail(t.getMessage());
    	}
    }else if(option.getText().equalsIgnoreCase("Cash on Delivery"))
    {
    	try{
    		System.out.println("Verifying the Payment option :"+option.getText());
    		//Assert.assertTrue(driver.findElement(By.xpath(or.getProperty("CASH_ONDELIVERY_BUTTON_XPATH"))).isDisplayed());
    		wait.until(ExpectedConditions.elementToBeClickable(By.id("verify")));
    		System.out.println(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText());
    		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='phone_verification']/p")).getText().contains("Verify your mobile number"));	
    			System.out.println(option.getText()+"Option verified");
    		
    		
    	}catch(Throwable t)
    	{   //if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       	 
    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"CashOnDelivery.png"));
    	
    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Cash on Delivery Option");
    		fail(t.getMessage());
    	}
    }else if(option.getText().equalsIgnoreCase("Payback"))
    {
    	try{
    		System.out.println("Verifying the Payment option :"+option.getText());
    		//Assert.assertTrue(driver.getPageSource().contains("Redeem your i-mint point."));
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.payment_actions")));
    		
    		System.out.println(driver.findElement(By.cssSelector("div.payment_actions")).getText());
    		Assert.assertTrue(driver.findElement(By.cssSelector("div.payment_actions")).getText().contains("To know more about PAYBACK"));
    	
    		System.out.println(option.getText()+"Option verified");
    		
    		
    	}catch(Throwable t)
    	{
    		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	 
    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"PaybyPayBack.png"));
    	
    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Pay by Payback Option");
    		
    		fail(t.getMessage());
    	}
    }else if(option.getText().equalsIgnoreCase("Cash Drop"))
    {
    	try{
    		System.out.println("Verifying the Payment option :"+option.getText());
    		
    		//Assert.assertTrue(driver.getPageSource().contains("Select a partner that has a location near you."));
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='payment_mode_container']/div[3]/h4")));
    		System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div[3]/h4")).getText());
    		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div[3]/h4")).getText().contains("Pay using ITZ Cash World/Outlet"));	
    	
    		System.out.println(option.getText()+"Option verified");
    		
    		
    	}catch(Throwable t)
    	{

    		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	 
    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Cash.png"));
    	
    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page : Cash Option");fail(t.getMessage());
    		fail(t.getMessage());
    	}


    }else  if(option.getText().equalsIgnoreCase("Cheque/DD"))
	    {
	    	try{
	    	System.out.println("Verifying the Payment option : "+option.getText());
	     //Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed."));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='payment_mode_container']/p[1]")));
			
			System.out.println(driver.findElement(By.xpath("//div[@id='payment_mode_container']/p[1]")).getText());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='payment_mode_container']/p[1]")).getText().contains("You can make a Cheque/DD payment for any order placed"));	
			
			System.out.println(option.getText()+"Option verified");
			
	    	}catch(Throwable t)
			{   
	    		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
	    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	 
	    	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Cheque.png"));
	    		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page :cheque Option.");
				fail(t.getMessage());
			}
	    	

}else if(option.getText().equalsIgnoreCase("eGift Voucher"))
{
	try{
		System.out.println("Verifying the Payment option :"+option.getText());
	    //Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed."));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='payment_mode_container']/div/p")));
				
				System.out.println(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/p")).getText());
				Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='payment_mode_container']/div/p")).getText().contains("Redeem your Future Bazaar eGV"));	
				
				System.out.println(option.getText()+"Option verified");
				
		
		
	}catch(Throwable t)
	{     
		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For Sanity failure"+fs+"Egv.png"));
		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Payment Page :cheque Option.");
		fail(t.getMessage());
	}

}
    }

          
}


@AfterClass
@Parameters("Env")
public void RemoveItem(String env) throws InterruptedException
{
	ItemCleanupTest(env);
}

@AfterSuite
public void quitdriver()
{
	driver.quit();
}


public boolean checkingCOD()
{
	//check for the COD and Out of stock condition..
    boolean checking = false;
    
    if(!driver.getPageSource().contains("Pay at your doorstep"))
    {//System.out.println("in cod");
   	 checking = true;
    }
    
    String TextforSTOCK = driver.findElement(By.className("pdp_add_qty")).getText();
    if(TextforSTOCK.contains("OUT OF STOCK"))
    {
    	//System.out.println("in out stock");
   	 checking = true;
    }
    
    System.out.println("value of " + checking);
    if(checking==true)
    { 
    	checking = false;
    	return true;
    }else
    	return false;
}










}
