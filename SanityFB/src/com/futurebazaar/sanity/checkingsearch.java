package com.futurebazaar.sanity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class checkingsearch {
	public WebDriver driver;
	public Properties option;
	public Properties or;
	public WebDriverWait wait;
	
	@BeforeClass
	public void initalization() throws IOException
	{  
		//webdriver wait for the driver to wait for the element
		//wait = new WebDriverWait(driver,20);
		 
		System.out.println("Loading the property Files");
	    option = new Properties();
	    FileInputStream ff = new FileInputStream(System.getProperty("user.dir")+"\\util\\options.properties");
	    option.load(ff);
		or = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\util\\OR.properties");
	    or.load(ip);
	    System.out.println("Property files loaded successfully");
	    
	  //Loading the Browser
	    System.out.println("Loading the proper browser Drivers");
	    
	  		if(option.getProperty("browserType").equals("FF"))
	  		{
	  			 driver = new FirefoxDriver();
	  			
	  		}else if(option.getProperty("browserType").equals("IE"))
	  				{
	  					driver = new InternetExplorerDriver();
	  				}else if(option.getProperty("browserType").equals("CC"))
	  					{
	  					System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
	  					driver = new ChromeDriver();
	  					}		
	    System.out.println("Browser driver loaded");
	    driver.get(option.getProperty("testURL"));
	    //driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    wait = new WebDriverWait(driver,20);		
	}
	
	@Test
	public void searchresult() throws InterruptedException
	{
		int count =0;
		driver.findElement(By.id(or.getProperty("SEARCH_WINDOW_ID"))).sendKeys(option.getProperty("SearchItem"));

		driver.findElement(By.id(or.getProperty("SEARCH_ID"))).click();
		Thread.sleep(3000);
		WebElement we =driver.findElement(By.id("cat_filter"));
		List<WebElement> pp = we.findElements(By.tagName("li"));
	    int size = pp.size();
	    System.out.println(size);
	    int totalsearch=0;
	    for (WebElement option : pp)
	    {   System.out.println(option.findElement(By.tagName("a")).getText());
	     String input = option.findElement(By.tagName("a")).getText();
           int start = input.indexOf("(");
	       int end = input.indexOf(")");
	    
	    
	    String getval = input.substring(start+1,end);
	    System.out.println(getval);
	    int value = Integer.parseInt(getval);
	    totalsearch+=value;
	    
	    //option.findElement(By.tagName("a")).click();
	    	
	    	    	
	    }
	    System.out.println("Total count for the Search By Category"+ totalsearch);
	  
	    //for the By brand Search
	    System.out.println("verifying the total brand search items by count");
	    WebElement brand =driver.findElement(By.id("brands_filter"));
	    WebElement brand2 = brand.findElement(By.className("filter_scroll"));
		List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
	    int brandlistsize = brandlist.size();
	    System.out.println(brandlistsize);
	    int totalBrandsearch=0;
	    //getting the values for the count of brands.
	    for (WebElement option : brandlist)
	    {   System.out.println(option.findElement(By.tagName("label")).getText());
	     String input = option.findElement(By.tagName("label")).getText();
           int start = input.indexOf("(");
	       int end = input.indexOf(")");
	      String getval = input.substring(start+1,end);
	    System.out.println(getval);
	    int value = Integer.parseInt(getval);
	    totalBrandsearch+=value;
	    
	    //option.findElement(By.tagName("a")).click();
	        	    	
	    }
	    System.out.println("Total count for the Search By BRAND "+ totalBrandsearch);
        
	    System.out.println("Verifying the By Category option");
	    System.out.println(driver.findElement(By.id("cat")).getText());
	    Assert.assertTrue(driver.findElement(By.id("cat")).getText().equalsIgnoreCase("BY CATEGORY"),"Category Text is not matching with the value");
	    System.out.println("Collaspse the By Category list");
	    driver.findElement(By.id("cat")).click();
	    
	    
	     
	    System.out.println("Verifying the By Brand  option");
	    Assert.assertTrue(driver.findElement(By.id("brands")).getText().equalsIgnoreCase("By Brand"),"By Brand Text is not matching with the value");
	    System.out.println("Collaspse the By Category list");
	    driver.findElement(By.id("brands")).click();
	    
	    
	    if(driver.findElement(By.id("tags")).isDisplayed())
	    {
	    	System.out.println("Verifying the By Retailer option");
		    Assert.assertTrue(driver.findElement(By.id("tags")).getText().equalsIgnoreCase("By Retailer")," By Retailer Text is not matching with the value");
		    System.out.println("Collaspse the  By Retailer list");
		    driver.findElement(By.id("tags")).click();
		    	    	
	    }
	    /*
	    WebElement totalitem = driver.findElement(By.className("srp_greed_view"));
	    WebElement totalitem2 = totalitem.findElement(By.id("greed_products"));
	    List<WebElement> itemsperpage = we.findElements(By.tagName("li"));
	    int totalitemsperpage=itemsperpage.size();
	    int noofImages=0;
	    int noofbuttons=0;
	    System.out.println(totalitemsperpage);
	    for (WebElement option2 : itemsperpage)
	    {    
	    	
	    System.out.println("images");
	    	System.out.println(option2.findElement(By.tagName("a")));
	    	noofImages++;
	    	System.out.println("button");
	    	//System.out.println(option2.findElement(By.tagName("button")));
	    	//noofbuttons++;
	    }
	    System.out.println("totall count button"+noofbuttons);
	    System.out.println("totall no of images"+noofImages);
	    */
	}
	

}
