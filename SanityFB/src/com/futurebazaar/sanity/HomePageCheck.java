package com.futurebazaar.sanity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.hamcrest.text.pattern.Parse;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageCheck {
	public WebDriver driver = null;
	public Properties or=null;
	public WebDriverWait wait=null;
	public HomePageCheck(WebDriver driver,Properties or,WebDriverWait wait)
	{
		this.driver=driver;
		this.or=or;
		this.wait=wait;
	}
	/*
	public WebDriver driver=null;
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
    EventFiringWebDriver fire = new EventFiringWebDriver(driver);
		
	fire.executeScript("scroll(0,20000)");
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
 */
	
	//Common method to get the Links on the Category.
	private void CommonMethodToCheckCategory(String MenuXpath,String BlockVerifcationXpath) throws InterruptedException
	{
		
	WebElement NewArrival=driver.findElement(By.xpath(or.getProperty(MenuXpath)));
	WebElement NewArrival1=NewArrival.findElement(By.tagName("ul"));
	List<WebElement> allElement = NewArrival1.findElements(By.tagName("li"));
	
	System.out.println("No of Menu :"+allElement.size()+" Clicking the menu for "+MenuXpath+"");
	for(WebElement list : allElement)
	{
		
		//System.out.println("Clicking the menu for "+MenuXpath+"is : ");
		list.findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		verifyTheBlockIsDisplayedOrNot(BlockVerifcationXpath);
	}
	
	}
    
	//Verify after clicking category or new page, the category block should be displayed. 
	private void verifyTheBlockIsDisplayedOrNot(String BlockVerifcationXpath) throws InterruptedException
	{
		System.out.println("verifying the Block "+BlockVerifcationXpath );
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty(BlockVerifcationXpath))));
		Thread.sleep(1000);
		boolean isDisplayed = driver.findElement(By.xpath(or.getProperty(BlockVerifcationXpath))).isDisplayed();
		
		Assert.assertTrue(isDisplayed);
	}
	
	
	//Getting the NO of pages present in the category
	private Integer getNoOfPagesPerCategory(String pageCountXPATH)
	{   
		
		boolean pageIsPresent= driver.findElement(By.xpath(or.getProperty(pageCountXPATH))).getText().contains("Page");
		if(!pageIsPresent)
		{
	    	
	    	//	throw new SkipException("No of Pages Element is not PResent So Skipping the Test for Pages in Category ");
	    	
	         return null; 
		}else{
		String CountString = driver.findElement(By.xpath(or.getProperty(pageCountXPATH))).getText();
	    String[] count =CountString.split("of ");
	    System.out.println("Total No. of pages: "+ Integer.parseInt(count[1]));
	    
	    return Integer.parseInt(count[1]);
	     }
	     
	}
	
	private void clickTheNextPageButtonForCategory(Integer Pagecount,String ButtonXpath,String BlockVerifcationXpath) throws InterruptedException
	{
		if(Pagecount==null)
		{
		  System.out.println("Page MEnu is not present.");	
		}else{
		for(int i=1;i<Pagecount;i++)
	    {   
			System.out.println("Clicking for "+i);
	    	driver.findElement(By.xpath(or.getProperty(ButtonXpath))).click();
	    	Thread.sleep(2000);
			verifyTheBlockIsDisplayedOrNot(BlockVerifcationXpath);
	    }
		}
	}
	
	@Test
	public void NewArrivalsCheck() throws InterruptedException
	{
		System.out.println("Checking NEw Arrivals");	
     	CommonMethodToCheckCategory("NEW_ARRIVALS_MENU_XPATH","NEW_ARRIVALS_BLOCK_XPATH");
	    System.out.println("Clicking for the Next Arrow for Next Page");
	    
	    clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("NEW_ARRIVALS_NO_OF_PAGES"),"NEW_ARRIVAL_NEXT_PAGE_ARROW","NEW_ARRIVALS_BLOCK_XPATH");
	}


	@Test
	public void BedAndBathCheck() throws InterruptedException
	{   
		System.out.println("Checking Bed and Bath");
		CommonMethodToCheckCategory("BED_AND_BATH_MENU","BED_AND_BATH_BLOCK");
		 System.out.println("Clicking for the Next Arrow for Next Page");
		    
		 clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("BED_AND_BATH_NO_OF_PAGES"),"BED_AND_BATH_NEXT_PAGE_ARROW","BED_AND_BATH_BLOCK");

	}

	@Test
	public void appliancesCheck() throws InterruptedException
	{
		System.out.println("Checking Appliances");
		CommonMethodToCheckCategory("APPLICANCES_MENU","COMMON_CATEOGORY_BLOCK_XPATH");
	    
		 System.out.println("Clicking for the Next Arrow for Next Page");
		    
		 clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("APPLIANCES_NO_OF_PAGES"),"APPLIANCES_NEXT_PAGE_ARROW","COMMON_CATEOGORY_BLOCK_XPATH");

	}
	
	@Test
	public void KitchenAndDiningCheck() throws InterruptedException
	{
		System.out.println("Checking Kitchen and Dining");
		CommonMethodToCheckCategory("KITCHEN_DINING_MENU","KITCHEN_DINING_BLOCK");
		 System.out.println("Clicking for the Next Arrow for Next Page");
		    
		 clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("KITCHEN_DINING_NO_OF_PAGES"),"KITCHEN_DINING_NEXT_PAGE_ARROW","KITCHEN_DINING_BLOCK");

	}
	
	@Test
	public void OfficeCheck() throws InterruptedException
	{
		System.out.println("Checking Office ");
		CommonMethodToCheckCategory("OFFICE_MENU","OFFICE_BLOCK");
	    
		 System.out.println("Clicking for the Next Arrow for Next Page");
		    
		 clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("OFFICE_NO_OF_PAGES"),"OFFICE_NEXT_PAGE_ARROW","OFFICE_BLOCK");

	
	}
	
	@Test
	public void ClearanceCheck() throws InterruptedException
	{
		System.out.println("Checking Clearance ");
		CommonMethodToCheckCategory("CLEARANCE_MENU","CLEARANCE_BLOCK_XPATH");
		 System.out.println("Clicking for the Next Arrow for Next Page");
		    
		 clickTheNextPageButtonForCategory(getNoOfPagesPerCategory("CLEARANCE_NO_OF_PAGES"),"CLEARANCE_NEXT_PAGE_ARROW","CLEARANCE_BLOCK_XPATH");

	}
} 