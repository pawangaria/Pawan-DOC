package com.futurebazaar.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.futurebazaar.testing.util.*;

public class TestBase {
	//WebDriver variable
	public static WebDriver driver = null;
	
	//log4j logger variable
	public static Logger APP_LOGS=null;
	public static  WebDriverWait wait;
	//property file loader variables
	public static Properties CONFIG=null;
	public static Properties OR=null;
	
	//all XLS file loader variables
	public static Xls_Reader suiteBseXls=null;
	public static Xls_Reader suitePaymentPageXls=null;
	public static Xls_Reader suiteMyAccountXls=null;
	public static Xls_Reader suiteHomePageXls=null;
	public static Xls_Reader suiteSearchPageXls=null;
	public static Xls_Reader suiteProductDescPageXls=null;
	public static Xls_Reader suitePageFooterXls=null;
	public static Xls_Reader suiteAddToCartXls=null; 
	public static Xls_Reader suiteHomeAndLivingXls=null; 
	public static Xls_Reader suiteFashionXls=null; 
	public static Xls_Reader suiteElectronicsXls=null; 
	public static Xls_Reader suiteSupermarketsXls=null; 
	public static Xls_Reader suiteKidsAndBabiesXls=null;
	public static Xls_Reader suiteNewArrivalsXls=null;
	public static Xls_Reader suitePopularsXls=null;
	public static Xls_Reader suiteGiftVoustesXls=null;
	public static Xls_Reader suiteGiftsXls=null;
	public static Xls_Reader suiteLoginLogOutXls=null;
	public static Xls_Reader suiteTodaysDealsXls=null;
	//public static Xls_Reader suiteBxls=null;
	//public static Xls_Reader suiteCxls=null;
	
	//General variables
	public static boolean isInitalized=false;
	//public static Wait<WebDriver> wait;
	
	// initializing the Tests
	
	public static void initialize() throws Exception{
		System.out.println("in base class");
		
		// logs initialization
		if(!isInitalized){
		APP_LOGS = Logger.getLogger("devpinoyLogger");
         Date today;
		String result;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("dd MMMMM yyyy hh:mm aaa");
		today = new Date();
		result = formatter.format(today);
		APP_LOGS.debug("*************** New Test Started on the Date"+result);
		
		// All the configuration files (OR and config) are initalized here
		//loading config.properties file
		APP_LOGS.debug("Loading Property files");
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//futurebazaar//config//config.properties");
		CONFIG.load(ip);
		APP_LOGS.debug("Loaded config.Property files successfully");
		
		//loading OR.properties file
		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//futurebazaar//config//OR.properties");
		OR.load(ip);
		APP_LOGS.debug("Loaded OR.Property files successfully");
        
		//Loading the browser driver selected by the User from the config file		
		//Loading the firefox Browser 
		if(CONFIG.getProperty("browserType").equalsIgnoreCase("FF"))
		{
			 driver = new FirefoxDriver();
		     APP_LOGS.debug("firefox driver gets loaded");	
		
		     //Loading the Internet Explorer Browser 	
		}else if(CONFIG.getProperty("browserType").equalsIgnoreCase("IE"))
				{
					driver = new InternetExplorerDriver();
					APP_LOGS.debug("Internet explorer driver gets loaded");
		
					//Loading the Chrome Browser 
		}else if(CONFIG.getProperty("browserType").equalsIgnoreCase("CC"))
					{
					System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+"\\thirdparty\\chromedriver.exe");
					driver = new ChromeDriver();
					APP_LOGS.debug("Chrome driver gets loaded");
					}		
   				
		//Initializing the Wait For the Webdriver
		 wait = new WebDriverWait(driver,30);	
		// Loading all the XLS file.for every new XlS file please initialize that file here.	
		
		APP_LOGS.debug("Loading XLS Files");

		suitePaymentPageXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\PaymentPageTest.xlsx");
		suiteMyAccountXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\MyAccountTest.xlsx");
		suiteHomePageXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\HomePageTest.xlsx");
		suiteBseXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\Suite.xlsx");
		suiteSearchPageXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\SearchResultPageTest.xlsx");
		suiteProductDescPageXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\ProductDescPageTest.xlsx");
		suitePageFooterXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\PageFooterTest.xlsx");
		suiteAddToCartXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\AddToCartTest.xlsx");
		suiteHomeAndLivingXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\HomeAndLivingTest.xlsx");
		suiteFashionXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\FashionTest.xlsx");
		suiteElectronicsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\ElectronicsTest.xlsx");
		suiteSupermarketsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\SupermarketTest.xlsx"); 
		suiteKidsAndBabiesXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\KidsAndBabiesTest.xlsx"); 
		suiteNewArrivalsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\NewArrivalsTest.xlsx"); 
		suitePopularsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\PopularTest.xlsx"); 
		suiteGiftVoustesXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\GiftVouchersTest.xlsx"); 
		suiteGiftsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\GiftsTest.xlsx"); 
		suiteLoginLogOutXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\LoginLogoutTest.xlsx"); 
		suiteTodaysDealsXls= new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\futurebazaar\\suiteXLSsheet\\xls\\TodaysDealsTest.xlsx"); 
		APP_LOGS.debug("Loaded XLS Files successfully");
		
		isInitalized=true;
		}
		
		//Get the URL You want to Test for the first time
			
	}

	public static void getURLOpen()
	{  System.out.println(isInitalized);
		APP_LOGS.debug("Getting the URL from the config.property file");
		driver.get(CONFIG.getProperty("testURL"));
		//driver.get("http://pp.futurebazaar.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    APP_LOGS.debug("URL opened successfully");	
	    
	}
	
    // get object function returns the object.Xpath of the element by its key
 public static WebElement getxpathobject(String xpathKeys)
   {
	 try
	 {
		 
	   return driver.findElement(By.xpath(OR.getProperty(xpathKeys)));
	 }
	 catch(Throwable t)
	 {
		 //throw the Error if the object not found
		 return null;
	 }
	 }
 
 //this function takes the  " id " of the element and returns it 
 public static WebElement getIDobject(String ids)
   {
	 try
	 {
		 
	   return driver.findElement(By.id(OR.getProperty(ids)));
	 }
	 catch(Throwable t)
	 {
		 //throw the Error if the object not found
		 return null;
	 }
	 }
 
//this function takes the  " CSS " of the element and returns it 
	 public static WebElement getCSSobject(String css)
	   {
		 try
		 {
		   return driver.findElement(By.cssSelector(OR.getProperty(css)));
		 }
		 catch(Throwable t)
		 {
			 //throw the Error if the object not found
			 return null;
		 }
		 }
	 
		//this function takes the  " LinkText " of the element and returns it 
	 public static WebElement getlinktextobject(String linktext)
	   {
		 try
		 {
		   return driver.findElement(By.linkText(OR.getProperty(linktext)));
		 }
		 catch(Throwable t)
		 {
			 //throw the Error if the object not found
			 return null;
		 }
		 }

	 public static WebElement getclassobject(String classname)
	   {
		 try
		 {
			 
		   return driver.findElement(By.className(OR.getProperty(classname)));
		 }
		 catch(Throwable t)
		 {
			 //throw the Error if the object not found
			 return null;
		 }
		 }
	 
	 public static WebElement getnameobject(String name)
	   {
		 try
		 {
			 
		   return driver.findElement(By.name(OR.getProperty(name)));
		 }
		 catch(Throwable t)
		 {
			 //throw the Error if the object not found
			 return null;
		 }
		 }

	
	
	
	
	
	
	
	
	
	
	
	
}
