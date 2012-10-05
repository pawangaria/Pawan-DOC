package p.futurebazaarTest;

import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import net.sf.saxon.functions.Id;

import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.seleniumemulation.Click;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;



public class Pfuturebazaar {
	
	public WebDriver d=null;
	public EventFiringWebDriver driver=null;
	public  WebDriverWait wait=null;
	@Test
	public void pfuturebazaarTest() throws IOException, InterruptedException
	{
		FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.http", "10.202.18.156");
			profile.setPreference("network.proxy.http_port", 3128);
			profile.setPreference("network.proxy.ssl", "10.202.18.156");
			profile.setPreference("network.proxy.ssl_port", 3128);
			
		 d = new FirefoxDriver(profile);
		 driver = new EventFiringWebDriver(d);
	    wait = new WebDriverWait(d, 20);
		try{
		System.out.println("opening p.futurebazaar.com");
		driver.get("http://p.futurebazaar.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_username")));
		driver.findElement(By.id("id_username")).sendKeys("futurebazaarqa@gmail.com");
		driver.findElement(By.id("id_password")).sendKeys("fbqa");
		System.out.println("user name and password enterd");
		driver.getKeyboard().sendKeys("",Keys.ENTER);
		Thread.sleep(10000);
		System.out.println(driver.manage().window().getSize());
		Dimension dd = new Dimension(1364,768);
		driver.manage().window().setSize(dd);
		Thread.sleep(10000);
		System.out.println(driver.manage().window().getSize());
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("custNewUser")));
       // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.userbox_show")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='userbox_outer']/div[2]")));
        
        System.out.println("clicking on the user box");
       // driver.findElement(By.cssSelector("div.userbox_show")).click();
		//driver.findElement(By.xpath("//div[@id='userbox_outer']/div[2]")).click();
        Thread.sleep(5000);
		System.out.println("Entering user Mobile no and click TAB");
		driver.findElement(By.id("custNewUser")).sendKeys("9920694762");
		//driver.getKeyboard().sendKeys("",Keys.TAB);
		System.out.println("login button clicked");
		driver.findElement(By.id("cc_login")).click();
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("content")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
		wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
	
		Assert.assertTrue(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText().contains("Hi")," user is not able to Login from My Account Option ");
	
		System.out.println("TEst Completed successfully");
		
		
		 Thread.sleep(10000);
	        
		}catch(Throwable t)
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    //Now you can do whatever you need to do with it, for example copy somewhere
		    FileUtils.copyFile(scrFile, new File("C:\\workspace\\P_FutureBazaarTest\\FailureScreenshots\\pFuturebazaarSnapshot.png"));
		    //getting the size
			   File file = new File("C:\\workspace\\P_FutureBazaarTest\\FailureScreenshots\\pFuturebazaarSnapshot.png");
		      long filesize = file.length();
		      long filesizeInKB = filesize / 1024;
		      //Send mail only if the size is grater than 2KB
		      if(filesizeInKB>2)
		      {
			   // FailureMail.sendMail();
		      }
		      fail(t.getMessage());
		}
		
		//purchasing the item
		 long CRtime = System.currentTimeMillis(); 
			String longtime = String.valueOf(CRtime);
			 SimpleDateFormat formatter = new SimpleDateFormat("HH");
			// longModtime should be an public variable 
			String longModtime = formatter.format(new Date(Long.parseLong(longtime)));
			//"1328784301256"
			System.out.println("the time "+longModtime);
			try{
		if(longModtime.equalsIgnoreCase("14"))
		{
			System.out.println("Placing order for the Day IN p.futurebazaar");
			/*
			//login the user 	
			driver.findElement(By.id("custNewUser")).sendKeys("pawan1@gmail.com");
			driver.findElement(By.id("cc_login")).click();
			//Closing the USer box
			driver.findElement(By.cssSelector("div.userbox_close")).click();
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
			wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
			System.out.println(driver.findElement(By.cssSelector("li.mart2 > span")).getText());
			System.out.println(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText());
		*/
		    
			driver.findElement(By.id("id_q")).sendKeys("knife");
			driver.findElement(By.id("go")).click();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
			Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='breadcrumb']")).getText().contains("Search Results"));
		
			//Selecting a Product from the Search Result
			
			driver.findElement(By.xpath("//li[@id='grid_page_1']/div/h3/a")).click();
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
			
			System.out.println("Clicking on by now button ");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/button")));
			driver.findElement(By.xpath("//div[2]/div/button")).click();
			
			System.out.println("Clicking on the Proceed button on the CArt Item Page");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
	        wait.until(ExpectedConditions.elementToBeClickable(By.name("Proceed")));
			
			driver.findElement(By.name("Proceed")).click();
			
			
			
			Thread.sleep(2000);
			System.out.println("Clicking on the Default Shipping Address");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
		    
			driver.findElement(By.xpath("//div[@id='delivery_table']/div/div/div/p[4]")).click();
			
			
			System.out.println("To Secure Payment page");
			System.out.println("**** Purchasing the product by Cheque ");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='content']")));
			  
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("payment_options")));
			WebElement Purchaseselect = driver.findElement(By.className("payment_options"));
		    List<WebElement> PurchaseallOptions = Purchaseselect.findElements(By.tagName("li"));
		    for (WebElement Purchaseoption : PurchaseallOptions) {
		    	   	
		    	
		    	//System.out.println("verifying the given option and clicking :"+option.getText());
		    	
			       if(Purchaseoption.getText().equalsIgnoreCase("Cheque/DD"))
			    {
			    	
			    	Purchaseoption.click();
			    	
			    		Thread.sleep(5000);
			    		//System.out.println("Verifying the Payment option :"+Purchaseoption.getText());
			    		//Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed."));
			    		
			    		System.out.println("Buying the Product So Clicking on the Payment ");
			    		driver.findElement(By.xpath(".//*[@id='book_button']/div/button")).click();
			    		Thread.sleep(3000);
						wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("div.fb.f16"),"Order booked"));
						Assert.assertTrue(driver.getPageSource().contains("Order booked"));
						System.out.println(" Order Confirmation Page");
										
						String Order_conf= driver.findElement(By.cssSelector("div.f14")).getText();
						Assert.assertTrue(Order_conf.contains("Order number is"));
						System.out.println(Order_conf+" Order has been placed successfully ");
						//System.out.println(Order_conf);
						Thread.sleep(5000);
						 String orderno =(removeCharacters(Order_conf, "0123456789"));
							System.out.println(orderno);
						//Mail.sendMail(orderno);
						 Thread.sleep(1000);
							//driver.findElement(By.linkText(or.getProperty("MY_ACCOUNT_LINK"))).click();
						    //driver.findElement(By.id(or.getProperty("LOG_OUT_ID"))).click();
						    System.out.println("user Logged out");
			    }
		    }
			
		   
		}
			}catch(Throwable t)
			{
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    //Now you can do whatever you need to do with it, for example copy somewhere
			    FileUtils.copyFile(scrFile, new File("C:\\workspace\\P_FutureBazaarTest\\FailureScreenshots\\pFuturebazaarPaymentFail.png"));
			    //getting the size
				  // File file = new File("C:\\workspace\\P_FutureBazaarTest\\FailureScreenshots\\pFuturebazaarSnapshot.png");
			    fail(t.getMessage());
			}
		System.out.println("Test Successfull");
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Close this call x")));
        //driver.findElement(By.linkText("Close this call x")).click();
       

		
	}
	
	 public static String removeCharacters(String text, String charsToKeep) {  
         StringBuffer buffer = new StringBuffer();  
         for(int i = 0; i < text.length(); i++) {  
             char ch = text.charAt(i);  
             if(charsToKeep.indexOf(ch) > -1) {  
                buffer.append(ch);  
             }  
         }  
         return buffer.toString();  
     }  

	@AfterClass(alwaysRun=true)
	 public void ItemCleanupTest() throws InterruptedException {
	 		

	 		System.out.println(" **- Item Cleanup method");
	 		//driverdriver.findElement(By.id(or.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
	 		//driver.get("http://p.futurebazaar.com");
	 		driver.findElement(By.id("logo")).click();
	 		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PROMOTION_ID"))));
	 		String no = driver.findElement(By.cssSelector("div.cart_items")).getText();
	 		System.out.println("clicking on the Cart Item Link to get back to the Cart Item Page");
	 		driver.findElement(By.cssSelector("div.cart_items")).click();
	 		
	 		
	 		Thread.sleep(3000);
	 		for(String handle : driver.getWindowHandles())
	 		{
	 			driver.switchTo().window(handle);
	 		}
	 	if(driver.getPageSource().contains("Your shopping cart is empty."))
	 	{
	 		System.out.println("Shopping cart is empty.");
	 		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Click here")));
	 			
	 		driver.findElement(By.linkText("Click here")).click();
	 	}else
	 	{	//System.out.println("Removing the Cart Items");
	 	 //driver.findElement(By.id("logo")).click();
	 		
//	 	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("HOME_PAGE_VERIFICATION_ID")));
	 	
	 	
	 	//getCSSobject("MY_SHOPPING_SACK_CSS").click();
	 	
	 	//REmoving the cart items one by one
	 	
	 	System.out.println("no is:"+no);
	 	
	 	String ll[]=no.split(" ");
	     String v2=ll[0];
	     int val = Integer.parseInt(v2);
	     for(int i=1;i<=val;i++)
	     {
	     	System.out.println("Removing the Items from cart");
	     	Thread.sleep(4000);
	     	driver.findElement(By.cssSelector("button.linkbtn")).click();
	     	
	 	
	     System.out.println(i+"item cleaned");
	     }
	     Thread.sleep(4000);
	     System.out.println("Item cleaned up from the cart");
	     System.out.println("Clicking on the Click Here Link to go to home page");
	     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Click here")));
	 	
	 	driver.findElement(By.linkText("Click here")).click();
	 	}
	 				
	 				


	 		
	 }
					
	@AfterSuite
	public void teardown()
	{
		driver.quit();
	}
public static void main(String[] args) throws InterruptedException, IOException {

		
				
}

}
