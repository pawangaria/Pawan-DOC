package com.futurebazaar.sanity;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Testing {
	public static WebDriver driver=null;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		System.out.println("method");
		//HomePageCheck home = new HomePageCheck();
		//home.initalization("www","no");
		//home.NewArrivalsCheck();
		//home.BedAndBathCheck();
		//home.appliancesCheck();
		//home.KitchenAndDiningCheck();
		//home.OfficeCheck();
		//home.ClearanceCheck();
		SanityFutureBazaar ss = new SanityFutureBazaar();
		ss.initalization("pp","yes");
		//ss.signInSingOutCheck();
		//ss.myAccountCheck();
		//ss.productPurchase("no");
		// ss.CODproductpurchase("www","yes");
		ss.homePageCheck();
		ss.VerifyingAllCategoryLinksOnHomePage();
		ss.CheckForHomePageCategoryLinks();
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://www.imdb.com/");
			Thread.sleep(3000);
			driver.findElement(By.id("nblogin")).click();
			Thread.sleep(4000);
			System.out.println(driver.getWindowHandles().size());
			//driver.switchTo().frame("iframe.cboxIframe");
			//WebElement ww=driver.findElement(By.id("cboxWrapper"));
			WebElement ww=driver.findElement(By.xpath("//iframe[contains(@name,'cbox')]"));
			driver.switchTo().frame(ww);
			driver.findElement(By.linkText("Facebook")).click();
	}
	
}
