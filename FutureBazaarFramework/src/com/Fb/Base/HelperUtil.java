/**
 * 
 */
package com.Fb.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This the Helper utility class which is used to 
 * Configure the Environment before the Test Start
 * This creates the instance of WebDriver , loads the WebElement property files  and opens the URL.
 * @author PawaGaria
 *
 */
public class HelperUtil {

	public static WebDriver driver = null;
	public static Properties option;
	public static Properties OR;
	public static String fs=null;
	public static WebDriverWait wait=null;
	public static Logger log=null;
	

	/**
	 * Method to get the Instance of the Driver.
	 * Using the Singleton Method to get the Instance only once
	 * Choosing the which browser instance  
	 * @return
	 */
	private static WebDriver getTheDriverInstance()
	{
		if(driver == null)
		{	
			if(option.getProperty("BROWSER.TYPE").equals("FF"))
	  		{ 
	  			FirefoxProfile profile = new FirefoxProfile();
	  		  /*
	  			profile.setPreference("network.proxy.type", 1);
	  			profile.setPreference("network.proxy.http", option.getProperty("NETWORK.PROXY.IP"));
	  			profile.setPreference("network.proxy.http_port", option.getProperty("NETWORK.PROXY.PORT.NO"));
	  			profile.setPreference("network.proxy.ssl", option.getProperty("NETWORK.PROXY.IP"));
	  			profile.setPreference("network.proxy.ssl_port",option.getProperty("NETWORK.PROXY.PORT.NO"));
	  			
	  			
	  			profile.setAcceptUntrustedCertificates(true);
	  			profile.setAssumeUntrustedCertificateIssuer(true);
	  			//profile.setPreference("network.proxy.share_proxy_settings",true);
	  			DesiredCapabilities capabilities = new DesiredCapabilities();
	  			//capabilities.setCapability("network.proxy.http", "10.202.18.156");

	  			//capabilities.setCapability("network.proxy.http_port", 3128);
	  			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	  			*/
	  			
	  			
	  			 driver = new FirefoxDriver(profile);
	  		}else if(option.getProperty("BROWSER.TYPE").equals("IE"))
	  		{
	  					driver = new InternetExplorerDriver();
	  		}else if(option.getProperty("BROWSER.TYPE").equals("CC"))
	  		{
	  				try{
	  					System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+"\\chromedriver.exe");
	  				   }catch(Exception e)
	  				   {
	  					   System.out.println("Exception in the Environment selection method "+ e.getMessage());
	  				   }
	  				
	  					driver = new ChromeDriver();
	  					
	  		}		
				
									
			}
		    return driver;
	}
	
	/**
	 * Opening the Url based upon the value 
	 * from the option.property file for TEST.ENVIRONMENT.OPEN
	 * @param URL
	 */
	private static String OpenTheURL(String URL)
	{
		try{
		if (URL.equalsIgnoreCase(option.getProperty("QA.VARIABLE")))
		{
		driver.get(option.getProperty("QA.URL.ENVIRONMENT"));
		}else if (URL.equalsIgnoreCase(option.getProperty("PP.VARIABLE")))
		{
		driver.get(option.getProperty("PP.URL.ENVIRONMENT"));
		}else if(URL.equalsIgnoreCase(option.getProperty("WWW.VARIABLE")))
		{
		driver.get(option.getProperty("WWW.URL.ENVIRONMENT"));
		}
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); // implicit wait at the time of driver initialization. 
		
		} catch(Exception e)
		{
			System.out.println("Exception in the open Url Method "+e.getMessage());
		}
		return URL;
	}
	
	/**
	 * Used to load the Property files.
	 * Property files are used to store WebElements.
	 * one argument takes webElemets Repository file and second is the configuration file.  
	 */
	private static void LoadPropertyFiles(String objectRepositry, String configurations)
	{
		System.out.println("Loading the Property files");
		//Loading the Property files.
		try {
			    
				option = new Properties();
				OR = new Properties();
			
			    FileInputStream ff = new FileInputStream(objectRepositry);
			    FileInputStream ip = new FileInputStream(configurations);
			
			    //loading the option.property file
					option.load(ff);
					//Loading the OR.property file
					OR.load(ip);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				}
	}
		
	/**
	 * static block to Initialized all the 
	 * Configurations which are related to the Test
	 * Like loading property file, selecting the browser, selecting URl  
	 */
	static {   
		System.out.println("Starting the TestCAses");
		fs = File.separator; // file separator object is initialized
		String objectrepositry=System.getProperty("user.dir")+fs+"src"+fs+"com"+fs+"Fb"+fs+"util"+fs+"options.properties";
		String configuration=System.getProperty("user.dir")+fs+"src"+fs+"com"+fs+"Fb"+fs+"util"+fs+"OR.properties";
	
		log = Logger.getLogger("devpinoyLogger");
		 
		//Loading the Configuration Property files
		LoadPropertyFiles(objectrepositry,configuration);
	    
		// Getting the Proper Driver Instance with the Browser Driver.
		getTheDriverInstance();
	    
	    //Initializing the Xls sheets for Test Case
	    new XlsFileInitalizer().Initalize_XlsTestCase();
	    
	    //Opening the URl for the selected Environment
	    OpenTheURL(option.getProperty("SELECT.TEST.ENVIRONMENT"));
	    
	    //Initializing the WebDriver Wait
	    wait = new WebDriverWait(driver,10);
	    
	    //Initializing the Implicit Wait in the Driver Instance
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
}
