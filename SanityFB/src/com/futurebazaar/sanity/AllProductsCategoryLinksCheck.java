package com.futurebazaar.sanity;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllProductsCategoryLinksCheck {
	
	public WebDriver driver = null;
	public Properties or=null;
	public WebDriverWait wait=null;
	public AllProductsCategoryLinksCheck(WebDriver driver,Properties or,WebDriverWait wait)
	{
		this.driver=driver;
		this.or=or;
		this.wait=wait;
	}
	/*
	
	public Properties option;
	
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
	
	private void clickOnTheAllProductsLinkInHomePage()
	{
		
	}
	
	public void testing()
	{
		driver.get("http://manager.publishthis.com/login");
		driver.findElement(By.name("email")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		
	}
    
	public void clickTodaysDealsAllProductLinks()
	{
		WebElement todaysDeals= driver.findElement(By.xpath(".//*[@id='feature_category_2240_1']/div[3]"));
		//WebElement NewArrival=driver.findElement(By.xpath(or.getProperty(MenuXpath)));
		Dimension dd = driver.findElement(By.xpath(".//*[@id='feature_category_2240_1']/div[3]")).getSize();
		EventFiringWebDriver fire = new EventFiringWebDriver(driver);
		
		//Coordinates x = driver.findElement(By.xpath("")).getLocation();
		
		List<WebElement> allElement = todaysDeals.findElements(By.tagName("li"));
		
		
	}
}
