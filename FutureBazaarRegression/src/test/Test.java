package test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.thoughtworks.selenium.Wait;

public class Test {
	
	public static WebDriver driver = null;
    public static int count=0;
    public static int box=2;
    public static WebDriverWait wait = null;
    public static Properties OR=null;
    public static FileInputStream ip=null;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
   
    public void newarrival()
    {
  
		WebElement kk = driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[1]/div"));
		
		List<WebElement> ll = kk.findElements(By.id("grid_page_"));
		
		for(WebElement option : ll){
			WebElement aa = option.findElement(By.className("item_img"));
			//Assert.assertTrue(aa.isDisplayed());
			
	                  List<WebElement> saveTag = aa.findElements(By.tagName("span"));
			for(WebElement option2 : saveTag){
				System.out.println(option2.getText());
				
			}
		
			 int num = saveTag.size();
			  
			  //  Verifying the total no of values in Blue Tag
			    Assert.assertTrue(num>0,"Some value is missing from the Save Blue Tag on the Product");
			    
			    System.out.println("to link");
			      //Link for the image is present
			      WebElement link1 = aa.findElement(By.tagName("a"));
			      String link2 = aa.findElement(By.tagName("a")).getAttribute("href");
			     
			      Assert.assertTrue(link2!=null);
			      System.out.println(link1.isDisplayed());
				
			      System.out.println("to Image");
			      //Image is present or not
			      WebElement img1 = aa.findElement(By.tagName("img"));
			     
				  Assert.assertTrue(img1.isDisplayed(),"product Image is not displayed");
			  
				  
				   //Checking the product Link By the Name of the product  
				  WebElement prolink= option.findElement(By.tagName("h3"));   
				  WebElement link = prolink.findElement(By.tagName("a"));
				  Assert.assertTrue(link.isDisplayed());
				  String link3 = prolink.findElement(By.tagName("a")).getText();
				  System.out.println(link3);
				  Assert.assertTrue(prolink.isDisplayed());
				/*  
				  // market price and offer price Check
			      WebElement twoprices = option.findElement(By.xpath(".//*[@id='grid_page_']/div/div[3]"));
					List<WebElement> priceTag = twoprices.findElements(By.tagName("span"));
					for(WebElement option3 : priceTag){
						System.out.println(option3.getText());
					}
				    
					// Verifying the Prices on the Box
					Assert.assertFalse(priceTag.isEmpty(),"Some value from the Market Price or offer price is missing in the product");
		
					 //Buy Now Button check
				    System.out.println("buy now button");
					      WebElement buynowbt = option.findElement(By.xpath(".//*[@id='grid_page_']/div/div[3]"));
							WebElement buyTag = buynowbt.findElement(By.tagName("a"));
						     
					 Assert.assertTrue(buyTag.isDisplayed(),"buy Now button is not displayed for the TODays Deals -Product BOx No."+box);
				      //get the values from the home page
					
					
					*/
		
		
		}
    }
    
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//driver = new FirefoxDriver();
		//driver = new InternetExplorerDriver();
		driver = new FirefoxDriver();
		
		//loading OR.properties file
				OR = new Properties();
				ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//futurebazaar//config//OR.properties");
				OR.load(ip);
		// wait = new WebDriverWait(driver,30);
		driver.get("http://futurebazaar.com/home");
		WebElement mm = driver.findElement(By.id("menu"));
		WebElement mm2 = mm.findElement(By.className("menu_ul"));
		List<WebElement> ll = mm2.findElements(By.className("t1_11"));
		System.out.println(ll.size());
		
		for(WebElement option : ll)
		{
			System.out.println(option.getText());
		}
		
	    /* 
		WebClient webClient = new WebClient();
	    int code = webClient.getPage("http://futurebazaar.com").getWebResponse().getStatusCode();
	    webClient.closeAllWindows();
	    System.out.println(code);
	*/
	    
	    /*
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
		//Clicking on the Todays Deals to go to the Product description Page
		driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_HOME_XPATH"))).click();
		
		WebElement similarpro = driver.findElement(By.className("similar_prod"));
		//verifying the SIMILAR PRODUCT TExt.
		String TExt1 = similarpro.findElement(By.tagName("h4")).getText();
		Assert.assertEquals(TExt1,"SIMILAR PRODUCTS");
		
		//GEtting the valus one by one from the LIst of products.
		List<WebElement> productlist = similarpro.findElements(By.className("similar_prod_item")); 
		System.out.println(productlist.size());
		
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
		
		//clicking on the Product Description
		if(driver.getPageSource().contains("Product Description"))
		{   System.out.println("clicking on the Product Description link on PDP");
			driver.findElement(By.cssSelector(OR.getProperty("Product_Description_CSS"))).click();
			
		}
        
		//clicking on the KEY_FEATURES
		if(driver.getPageSource().contains("KEY FEATURES"))
		{
			System.out.println("clicking on the KEY FEATURES link on PDP");
			driver.findElement(By.cssSelector(OR.getProperty("KEY_FEATURES_CSS"))).click();
		}
		
		System.out.println("getting the valus of the product from the link on PDP page from SIMILAR PRODUCTS option");
		String PDP_Link_Text =driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_LINK_XPATH"))).getText();
     //		System.out.println(PDP_Link_Text);
		
		System.out.println("clicking on the product link on the SIMILAR PRODUCTS option");
		driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_LINK_XPATH"))).click();
		
		//verifying the Product Description page for the New Product after clicking the Link. 
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
		System.out.println("getting text of the PDP after clicking on the link from SIMILAR PRODUCTS option");
		String PDP_PAGE_TExt=driver.findElement(By.xpath(OR.getProperty("PDP_PAGE_PRODUCT_LINK_TEXT_VERIFCATION_XPATH"))).getText();
		//System.out.println(PDP_PAGE_TExt);
		Assert.assertTrue(PDP_PAGE_TExt.equalsIgnoreCase(PDP_Link_Text),"Similar product text link in PDP Page and PDP page Text for the Similar product is not matching");
		driver.navigate().back();
		
		//clicking the Image for the Product
		System.out.println("SIMILAR PRODUCTS option product image Link verification");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
		System.out.println("clicking on the image link on SIMILAR PRODUCTS");
		driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_IMAGE_XPATH"))).click();
		Assert.assertTrue(driver.getPageSource().contains("Key Features"),"Unable to get the Product details page");
		*/
		System.out.println("test successfull");
		
		/*
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));	
		String Home_Link_Text =driver.findElement(By.xpath("//div[@id='home']/div[2]/div[2]/ul/li/div/h3/a")).getText();
		System.out.println(Home_Link_Text);
		
		WebElement quickview = driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/ul/li[1]/div/div[1]/a[1]/img"));
		Actions builder = new Actions(driver);
		builder.moveToElement(quickview).build().perform();
		//builder.clickAndHold(quickview).build().perform();
		driver.findElement(By.xpath(".//*[@id='home']/div[2]/div[2]/ul/li[1]/div/div[1]/a[2]/span")).click();
		
		//cart_popup
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='cart_popup']/div")));
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator))
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			System.out.println(driver.getTitle());
			
			}
	    Thread.sleep(3000);
		String POP_UP_TExt=driver.findElement(By.xpath(".//*[@id='cart_popup']/div/div[2]/h3")).getText();
		Assert.assertTrue(Home_Link_Text.equalsIgnoreCase(POP_UP_TExt),"pop up text is not matching");
		
		//add to cart button
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='quick_add_to_cart_form']/button")).isDisplayed(),"Add to Cart button on the quick View pop up is not displayed");
				
		// clicking on the Read Full Product Details link.
		driver.findElement(By.linkText("Read Full Product Details »")).click();
		
		//wait for the Product details page to Load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
		String PDP_TExt=driver.findElement(By.xpath(".//*[@id='product']/div/div/div[2]/h1")).getText();
		System.out.println(PDP_TExt);
		Assert.assertTrue(PDP_TExt.equalsIgnoreCase(POP_UP_TExt),"PDP page text and Quick View pop up text is not matching");
		
		System.out.println("test Successfull");
		*/
		//driver.findElement(By.id("id_q")).isEnabled();
		
	//	driver.findElement(By.cssSelector("div.search_dod_name")).click();
		
		//String value = driver.findElement(By.xpath("//div[@id='product']/div/div/div[2]/table/tbody/tr/td[2]")).getText();
		//System.out.println(value);
		/*
		String value="Rs. 2,94923423423 (Rs. 983 p.m. x 3 EMI)";
		int aa =value.indexOf("(");
		System.out.println(aa);
		String val1=value.substring(0,aa);
		System.out.println(val1);
		*/
		//if(value.contains("EMI"))
				//{
		//String	val1[]=value.split(" (");
			//  System.out.println(val1[0]);
			  
				//}
		
	
	/*	WebElement menu = driver.findElement(By.id("menu"));
		//List<WebElement> menulist = menu.findElements(By.className("t1_11"));
	WebElement ll = menu.findElement(By.className("menu_ul"));
	List<WebElement> ll2 = menu.findElements(By.className("t1_l1"));
		//ll2.findElement(By.tagName("a")).click();
		int num = ll2.size();
		System.out.println(num);
		int count=0;
				for (WebElement option : ll2)
		{ 
		           count++;
		           driver.get(option.findElement(By.tagName("a")).getAttribute("href"));
		           System.out.println(count);
					System.out.println(option.findElement(By.tagName("a")).getAttribute("href"));
					option.findElement(By.tagName("a")).click();
			/*if(option.findElement(By.tagName("a")).getAttribute("href").equalsIgnoreCase("http://www.futurebazaar.com/deals/new_arrivals/"));
			{
				option.findElement(By.tagName("a")).click();
			}*/
			//driver.navigate().back();
		//Thread.sleep(10000);
		
		}
			
			
		
		
		
		
	     //System.out.println(New_arrivals_String);
		//Assert.assertEquals(New_arrivals_String,"NEW ARRIVALS");
		
		//checkboxes(".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[2]/div",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[2]/div/h3",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[2]/div/div[3]");
		//checkboxes(".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[3]/div",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[3]/div/h3",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[3]/div/div[3]");
		//checkboxes(".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[4]/div",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[4]/div/h3",".//*[@id='home']/div[2]/div[2]/div[11]/ul/li[4]/div/div[3]");
		//checkboxes(".//*[@id='home']/div[2]/div[2]/div[2]/ul/li[4]/div",".//*[@id='home']/div[2]/div[2]/div[2]/ul/li[4]/div/h3",".//*[@id='home']/div[2]/div[2]/div[2]/ul/li[4]/div/div[3]");		
		/*
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		driver.findElement(By.linkText("My Account")).click();	
		
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
       
		driver.findElement(By.id("header_signin_signup")).click();
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("pawan1@gmail.com");
        driver.findElement(By.id("password")).sendKeys("p0513231068");
        driver.findElement(By.id("login")).click();
        
        String no = driver.findElement(By.cssSelector("div.cart_items")).getText();
        System.out.println(no);
        
        
        String ll[]=no.split(" ");
        String v2=ll[0];
        int val = Integer.parseInt(v2);
        System.out.println(val);
        System.out.println("value is "+ll[0]);
        driver.findElement(By.cssSelector("div.cart_items")).click();
		*/

	}


