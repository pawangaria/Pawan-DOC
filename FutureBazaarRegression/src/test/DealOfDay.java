
package test;


import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class DealOfDay extends DODConsts {
    
	public String searchTitle = "";
	public String searchSellPrice = null;
	
	public String homePageTitle = "";
	public String homePageSellPrice = null;
	public String homePageMarketPrice =null;
	
	public String productTitle = "";
	public String productSellPrice = null;
	public String productMarketPrice =null;
	public String productStealPrice =null;
	public boolean  SearchDODVariable=false;
	public WebDriver driver=	new FirefoxDriver();
	//public WebDriver driver=	new InternetExplorerDriver();
	//public WebDriver driver=	new HtmlUnitDriver();
	 public WebDriverWait wait = new WebDriverWait(driver,30);
	@Test
	public void dealCheck() throws IOException
	{
		
		
		
		
		try{
		// entering futurebazaar and click on search and Deal Of Day
		driver.get(DODConsts.DOD_URL);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(DODConsts.HOME_PAGE_VERIFICATION_ID)));
		driver.findElement(By.id(DODConsts.SEARCH_WINDOW)).click();
		
		searchTitle = driver.findElement(By.cssSelector(DODConsts.SEARCH_DOD_TITLE)).getText();	//check to confirm
		searchSellPrice = driver.findElement(By.cssSelector(DODConsts.SEARCH_DOD_SELLPRICE)).getText();		//check to confirm
		System.out.println("before" +searchSellPrice);
		System.out.println(searchTitle);
		if(searchSellPrice.contains("Rs."))
		{
			// String getsearchSellPrice[]=searchSellPrice.split(".");
			 
			//searchSellPrice=getsearchSellPrice[1];
			searchSellPrice =(removeCharacters(searchSellPrice, "0123456789"));
			 System.out.println(searchSellPrice);
		}
		
		System.out.println("after"+searchSellPrice);
		
		driver.findElement(By.cssSelector(DODConsts.SEARCH_DOD_TITLE)).click();
		Thread.sleep(5000);
		}catch(Exception e)
		{
			e.printStackTrace();
			
			//PaymentPageFailureMail.sendFailureMail("Deal of the Day Purchase - On Search Bar Title or Price are missing or not proper");
		fail(e.getMessage());
		}
		if(driver.getPageSource().contains("Search Results"))
		{  SearchDODVariable=true;
			driver.findElement(By.xpath("//li[@id='grid_page_1']/div/h3/a")).click();
		
		}
		
		// taking values from deal page
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(DODConsts.PDP_PAGE_VERIFICATION_XPATH)));
		
		//taking a daily snapshot.
		driver.findElement(By.id(DODConsts.SEARCH_WINDOW)).click();
		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    //Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File("C:\\workspace\\DODsnapshot\\DODsnapshot.png"));
		
		
		
		ProductPage ppage = new ProductPage(driver);
		try{
		productTitle = ppage.getProductTitle();	//check to confirm
		System.out.println(productTitle);
		
		productSellPrice = ppage.getProductSellPrice();	//check to confirm
		System.out.println(" from product: before"+productSellPrice);
		
		//if price contains EMI options
		if(productSellPrice.contains("EMI")){
		 int getindx = productSellPrice.indexOf("(");
			
		productSellPrice =productSellPrice.substring(0,getindx);
		System.out.println("after split the price from EMI"+productSellPrice);
		}
		if(productSellPrice.contains("Rs."))
		{
			// String getproductSellPrice[]=productSellPrice.split(" ");
		     //productSellPrice=getproductSellPrice[1];
			productSellPrice =(removeCharacters(productSellPrice, "0123456789"));
		}
		System.out.println("new prce : after"+ productSellPrice);
		
		
		productMarketPrice = ppage.getProductMarketPrice(); //check if present
		System.out.println("product market prie : before"+productMarketPrice);
		
		
		
		if(driver.findElement(By.cssSelector(DODConsts.PRODUCTPAGE_PRODUCT_MARKETPRICE)).isDisplayed())
		{
		
		if(productMarketPrice.contains("Rs.")||productMarketPrice.contains(","))
		{
			 //String getproductMarketPrice[]=productMarketPrice.split(" ");
			 //productMarketPrice=getproductMarketPrice[1];
			productMarketPrice =(removeCharacters(productMarketPrice, "0123456789"));
		}
		}
		
		
		System.out.println("after"+productMarketPrice);
		}catch(Exception e)
		{
			e.printStackTrace();
			//PaymentPageFailureMail.sendFailureMail("Deal of the Day: PDP Page Values - Product Title or Price Missing on Product Page or the values are not prpoer on PDP page.");
		fail(e.getMessage());
		}
		/*
		productStealPrice = ppage.getProductStealPrice();
		if(productStealPrice.contains("Rs."))
		{
			 String getproductStealPrice[]=productStealPrice.split("(");
			 productStealPrice=getproductStealPrice[0];
		}
		System.out.println("new product steal price"+productStealPrice);
		*/
		//check if present
		
	/*
		//going to previous page
		try{
		driver.navigate().back();
		Thread.sleep(5000);
		//Moving mouse focus to the deal of the Day
	WebElement menu = driver.findElement(By.xpath(DODConsts.DOD_HOME));
		Actions builder = new Actions(driver);    
		//builder.moveToElement(menu).build().perform();
		builder.clickAndHold(menu).build().perform();
		
		//builder.moveToElement(menu);
		//builder.clickAndHold(menu);
		//builder.perform();
		 Actions builder = new Actions(driver);
		   Action action = builder.moveToElement(menu).build(); 

		//String oldUrl = driver.getCurrentUrl();
		   int nn=0;
		  while(nn<3){
			try {
				action.perform();
				homePageTitle = driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_TITLE)).getText(); //
				homePageSellPrice = driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_SELLPRICE)).getText();
				
				if (driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_MARKETPRICE)).isDisplayed())
				{
					homePageMarketPrice = driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_MARKETPRICE)).getText();
					System.out.println("before homePageMarketPrice "+homePageMarketPrice);
					
					if(homePageMarketPrice.contains("Rs.")||homePageMarketPrice.contains(","))
					{
						 //String getproductMarketPrice[]=productMarketPrice.split(" ");
						 //productMarketPrice=getproductMarketPrice[1];
						homePageMarketPrice =(removeCharacters(homePageMarketPrice, "0123456789"));
					
						System.out.println("after homePageMarketPrice "+homePageMarketPrice);
				}
		              // driver.findElement(By.xpath(DODConsts.DOD_LINK_HOME)).click(); 
				nn++;
				System.out.println(nn);
				System.out.println(homePageTitle);
				System.out.println(homePageMarketPrice);
		      System.out.println(homePageSellPrice);
				//}	if(!this.driver.getCurrentUrl().equalsIgnoreCase(oldUrl)) break;
				}
				} catch (Exception e) {}
		  }

		*/
	
		//homePageTitle = driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_TITLE)).getText(); //
		//homePageSellPrice = driver.findElement(By.xpath(DODConsts.HOMEPAGE_DOD_SELLPRICE)).getText();
		 // Thread.sleep(8000);
	/*	System.out.println("home page values");
		System.out.println(homePageTitle);
		Thread.sleep(2000);
		
		System.out.println(homePageSellPrice);
		if(homePageSellPrice.contains("Rs.")||homePageSellPrice.contains(","))
		{
			 //String getproductMarketPrice[]=productMarketPrice.split(" ");
			 //productMarketPrice=getproductMarketPrice[1];
			homePageSellPrice =(removeCharacters(homePageSellPrice, "0123456789"));
		}
		
		System.out.println("After homePageSellPrice "+homePageSellPrice);
		Thread.sleep(2000);
	
		Thread.sleep(3000);
		//removing the .. after the String
		homePageTitle = homePageTitle.substring(0, homePageTitle.length()-2).trim();
		
		System.out.println(homePageTitle);
		//homePageTitle.trim();
		//System.out.println(homePageTitle.trim());
		}catch(Exception e)
		{
			e.printStackTrace();
			//PaymentPageFailureMail.sendFailureMail("Deal of the Day Purchase - Product Title or Price Missing on Home Page");
		}
		*/
		System.out.println(searchTitle);
		System.out.println(productTitle);
		if(SearchDODVariable)
		{
			productTitle=productTitle.substring(0,10);
			System.out.println("when the two products then After substring"+productTitle);
		}
		//System.out.println(homePageTitle);
		System.out.println(searchSellPrice);
		//System.out.println(productStealPrice);
		//System.out.println(homePageSellPrice);
		
	//	driver.navigate().refresh();
		//if ( searchTitle.equals(productTitle) && searchTitle.contains(homePageTitle)  && searchSellPrice.equals(productStealPrice) && searchSellPrice.equals(homePageSellPrice))
		
		try
		{
		if (searchSellPrice!=null)
		{System.out.println("checking the prices and title and market price");
			if ( searchTitle.contains(productTitle) && productSellPrice.equals(searchSellPrice))
			{   
				
			
			
			dealpayment();
			}else
			{//PaymentPageFailureMail.sendFailureMail("Deal of the Day - Script failed as the prices or title for the product are not matching in all pages.");
				System.out.println("Script failed for DOD");
				return;
			}
			
		}
		else if(searchTitle.equals(productTitle)&&searchSellPrice.equals(productSellPrice))
		{
			System.out.println("checking the prices and title and mrket price is not present");
			dealpayment();
		}
				
			
	}catch(Throwable e)
	{//PaymentPageFailureMail.sendFailureMail("Deal of the Day - Script failed as the prices or title for the product are not matching in all pages.");
		fail(e.getMessage());
		
	}
	}
	
	
	
	   public  void dealpayment() throws InterruptedException, IOException
	   {

					  
			
				System.out.println("it works, proceeding to the Payment");
				//driver.get(DODConsts.DOD_URL);
				
				Thread.sleep(2000);
				System.out.println("deal clicked");
				//driver.findElement(By.cssSelector(DODConsts.SEARCH_DOD_TITLE)).click();
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(DODConsts.HOME_PAGE_VERIFICATION_ID)));
				//driver.findElement(By.xpath(DODConsts.DOD_HOME)).click();
				////////////////////////////////////
				Thread.sleep(5000);
				driver.findElement(By.xpath(".//*[@id='menu']/ul/li/a/div")).click();
				//driver.findElement(By.xpath(".//div[@id='product']/div/div/div[2]/div/div[2]/button")).click();
				System.out.println("by now clicked");
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/button")));
				driver.findElement(By.xpath("//div[2]/div/button")).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.name("Proceed")));
				
				driver.findElement(By.name("Proceed")).click();
				
				Thread.sleep(2000);
				System.out.println("User log in ");
				System.out.println("Enter User Name");
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobileLabel")));
				Thread.sleep(2000);
				driver.findElement(By.id("mobileLabel")).clear();
				driver.findElement(By.id("mobileLabel")).sendKeys("dealofdaytest@gmail.com");
				//Thread.sleep(2000);
				driver.findElement(By.id("AlreadyCustomer")).click();
				
				System.out.println("Enter Password");
				driver.findElement(By.id("userPassword")).clear();
				driver.findElement(By.id("userPassword")).sendKeys("qatest123");
				//Thread.sleep(2000);
				
				System.out.println("Click on Login Button");
				driver.findElement(By.xpath("//input[@value='Continue']")).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(DODConsts.PROCEED)));
				
				driver.findElement(By.xpath(".//*[@id='delivery_table']/div[1]/div[2]/div/p[1]/input")).click();
				//driver.findElement(By.xpath("//input[starts-with(@name,'78030')]")).click();
			
				
				
								//waitForElementPresent(driver,"id",DODConsts.PROCEED);
				System.out.println("To Secure Payment page");
				driver.findElement(By.id(DODConsts.PROCEED)).click();
				
				//driver.findElement(By.xpath(DODConsts.BUY_NOW)).click();
				
				//System.out.println(driver.findElement(By.xpath(".//div[@id='product']/div/div/div[2]/div/div[2]/button")).isDisplayed());
				//driver.findElement(By.xpath(".//div[@id='product']/div/div/div[2]/div/div[2]/button")).click();
				//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(DODConsts.PDP_PAGE_VERIFICATION_XPATH)));
			
	                        ///SOLD out 
			
		//	else
		//	{	
				
				System.out.println("verifying the Payment Option Page");
				// new code
			     //String new_url =driver.getCurrentUrl();
				//System.out.println("opening Payment Option Page:"+new_url);
				
				//driver.get(new_url);
				//Wait ("//div[@id='content']/div[2]/div/div[3]");
				//System.out.println("verifying new url open");
				//end new code
				
				/*try{
			    	
			    	Assert.assertTrue(driver.getPageSource().contains("Secure Payment Options"));
			        System.out.println("User is at Payment details page");
			    }catch(Throwable e){
			    	fail(e.getMessage());
			    }
			*/
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("payment_options")));
try{
					Thread.sleep(1000);
	                WebElement totalprice =driver.findElement(By.id("orderPayableAmount"));
	                String price = totalprice.getText();
	                System.out.println(price);
	                if(price.contains("Rs."))
	                {
	                	price =(removeCharacters(price, "0123456789")); 
	                }
	                System.out.println(price);
	                
	              //converting homePageSellPrice to string 
	                
	                	
	                
	               int finalprice = Integer.parseInt(price);
	               System.out.println(finalprice);
	               
	               //converting homePageSellPrice to string 
	               if(searchSellPrice.contains(","))
	                {
	            	   searchSellPrice =(removeCharacters(searchSellPrice, "0123456789")); 
	                }
	                	
	               int finalsearchSellPrice= Integer.parseInt(searchSellPrice);
	              System.out.println(finalsearchSellPrice);
	               
	              if(finalsearchSellPrice<500)
	               {
	            	  finalsearchSellPrice = (finalsearchSellPrice+50);
	            	   
	               }
	               System.out.println("after adding 50, if added "+finalsearchSellPrice);
	               System.out.println(finalprice);
	               System.out.println(finalsearchSellPrice);
	               
	               System.out.println("verifying the values from home page and from payment page");
	                Assert.assertEquals(finalprice,finalsearchSellPrice);
	              }catch(Throwable t)
	              {
	                  //PaymentPageFailureMail.sendFailureMail("DOD script failed at the payment page as Payable amount does not match with the DOD amount");
	                  fail(t.getMessage());
	              }
				
				
             WebElement select = driver.findElement(By.className("payment_options"));
                  List<WebElement> allOptions = select.findElements(By.tagName("li"));
           for (WebElement option : allOptions) {
	   	    
	
    //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty("PAYMENT_PAGE_VERIFICATION_ID"))));
	
    if(option.getText().equalsIgnoreCase("Pay by Cheque/DD"))
    {//System.out.println("verifying the given option and clicking :"+option.getText());
        option.click();
        System.out.println("Clicked on the given payment option :"+ option.getText());
       
    	

				System.out.println("verifying the cheque Page");
				
				try {
					Thread.sleep(7000);
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='payment_mode_container']")));
					Assert.assertTrue(driver.getPageSource().contains("You can make a Cheque/DD payment for any order placed. Please mention your order ID behind the Cheque/DD"));
				}
					catch (Throwable e)		{
			//PaymentPageFailureMail.sendFailureMail("DOD product purchase :Test failed at - Cheque payment page");
					fail(e.getMessage());
					}
				
    }
           }
				//Code to actually purchase something. If you uncomment, then please send mail to Rakesh Vichare with order ID
				driver.findElement(By.id(DODConsts.MAKE_PAYMENT)).click();
				Thread.sleep(10000);
				try{
				Assert.assertTrue(driver.getPageSource().contains("Thank you for booking the order"));
				System.out.println("Confirmation Page2");
				}
				catch (Throwable e)
				{
				  	//PaymentPageFailureMail.sendFailureMail("DOD product purchase :Test failed at : Order confirmation page");
				fail(e.getMessage());
				}
				Thread.sleep(5000);
				
				try
				{
			     String Order_conf= driver.findElement(By.cssSelector("div.f18.marb5")).getText();
				System.out.println(Order_conf);
				Thread.sleep(5000);
				//DODmail.sendMail();
				Thread.sleep(2000);
				//Writing the Order value to the file present in the C drive. 
				//WriterAndReader.writer(Order_conf, "FUTURE BAZAAR:Deal Of the Day");
				Thread.sleep(4000);
				 String orderno =(removeCharacters(Order_conf, "0123456789"));
				System.out.println(orderno);
				// String oo=WriterAndReader.orderno;
				//Mail.sendMail(orderno);
				}
				catch (Throwable e)
				{
					//PaymentPageFailureMail.sendFailureMail("Deal of The day: Product Purchase - Write Order Number");
				fail(e.getMessage());
				}
				System.out.println("Product purcase done");
				
				

	   }
	   
	   @AfterTest(alwaysRun=true)
	   private void quitdriver()
	   {
		   
		   driver.quit();
	   }
	
	   @BeforeTest(alwaysRun=true)
	   public void ItemCleanupTest() {
			try{	

			System.out.println(" **DOD - Item Cleanup");
			//driver.get("http://futurebazaar.com");
			driver.get(DODConsts.DOD_URL);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			Thread.sleep(2000);
			System.out.println(driver.getWindowHandle());
			System.out.println(driver.getTitle());
			//assertEquals("FutureBazaar: Discover great deals in Laptops, Mobiles, Computers, Electronics, Home Fashion and Clothing", driver.getTitle());
			Thread.sleep(3000);
			// File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    // Now you can do whatever you need to do with it, for example copy somewhere
		    //FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));
			System.out.println(" ***Click on My Account link");
			//waitForElementPresent(driver,"linkText",DODConsts.MY_ACCOUNT);
			driver.findElement(By.linkText(DODConsts.MY_ACCOUNT)).click();
			Thread.sleep(3000);
			System.out.println(" ***Click on Sign in Link");
				driver.findElement(By.id(DODConsts.SIGN_IN)).click();
				
					
			//Login to the Account
		  Thread.sleep(10000);
	      System.out.println(" ***Enter User Name");

	      driver.findElement(By.id(DODConsts.USER_NAME)).clear();
	      driver.findElement(By.id(DODConsts.USER_NAME)).sendKeys(DODConsts.USER_ID);
	      Thread.sleep(2000);

	      System.out.println(" ***Enter Password");
	      driver.findElement(By.id(DODConsts.PASSWORD)).clear();
	      driver.findElement(By.id(DODConsts.PASSWORD)).sendKeys(DODConsts.USER_PASSWORD);
	      Thread.sleep(2000);

	      System.out.println(" ***Click on Login Button");
	      driver.findElement(By.id(DODConsts.LOGIN)).click();
			System.out.println(" ***User logged in");
			
			Thread.sleep(3000);
			System.out.println(" ***Clean Up The Item Added In Cart In Previous Step");
			//waitForElementPresent(driver,"css",DODConsts.MY_SHOPPING_SACK);
			driver.findElement(By.cssSelector(DODConsts.MY_SHOPPING_SACK)).click();
					Thread.sleep(2000);	


					if(driver.getPageSource().contains("Your shopping cart is empty."))
					{
						System.out.println(" ***No Items In Cart!!");
						Thread.sleep(1000);
					}
			else
					{  
				       
						driver.findElement(By.cssSelector(DODConsts.REMOVE_ITEM)).click();
						Thread.sleep(3000);
						if(driver.getPageSource().contains("Remove item x"))
						{
							System.out.println(" ***Removing the other items");
							driver.findElement(By.cssSelector(DODConsts.REMOVE_ITEM)).click();
						}
						else
						Thread.sleep(1000);
						System.out.println(" ***Items Removed From Cart");
					}
					System.out.println(" ***Click on My Account link");
					
					driver.findElement(By.id("logo")).click();
					Thread.sleep(1000);
			driver.findElement(By.linkText(DODConsts.MY_ACCOUNT)).click();
					Thread.sleep(1000);
			System.out.println(" ***Click on Sign out Link");
			driver.findElement(By.id(DODConsts.LOG_OUT)).click();
			System.out.println(" ***User logged out ");


			}catch (Throwable e){
				//PaymentPageFailureMail.sendFailureMail("Deal of the Day Test Failed in Item Clean UP method- Item Clean Up Test Method");
				fail(e.getMessage());
			}
		
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
	
	public static void main(String[] args)
	{
		DealOfDay dod = new DealOfDay();
		//dod.dealCheck();
	}
	
}
