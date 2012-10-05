package com.futurebazaar.sanity;

import static org.testng.AssertJUnit.fail;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UrlChecks {
	public WebDriver driver;
	public String fs=null;
	public WebDriverWait wait = null;
	
	@BeforeClass
	private void setUP()
	{
		fs = File.separator;
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY ,System.getProperty("user.dir")+fs+"lib"+fs+"chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
	}
	
    
	@Test
	public void PFutureBazaarCheck() throws IOException
	{
		try{
		System.out.println("Stsrting the TestCase for P.futurebazaar.cm");
		driver.get("http://p.futurebazaar.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_username")));
		driver.findElement(By.id("id_username")).sendKeys("futurebazaarqa@gmail.com");
		driver.findElement(By.id("id_password")).sendKeys("fbqa");
		System.out.println("user name and password enterd");
			driver.findElement(By.xpath("//input[@value='Sign in']")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("custNewUser")));

	  	System.out.println("Entering user Mobile no and click TAB");
		driver.findElement(By.id("custNewUser")).sendKeys("9920694762");
		System.out.println("login button clicked");
		driver.findElement(By.id("cc_login")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("html/body/div[4]/div/div[1]"),"Hi"));
		//AssertJUnit.assertTrue(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText().contains("Hi")," user is not able to Login from My Account Option ");
		Assert.assertTrue(driver.findElement(By.xpath("html/body/div[4]/div/div[1]")).getText().contains("Hi")," user is not able to Login from My Account Option ");
		System.out.println("TEst Completed successfully");		
		}catch(Exception t)
		{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"PFutureBazaarfail.png"));

				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
				fail(t.getMessage());
		}
		
	}
	
	@Test(dependsOnMethods="PFutureBazaarCheck",alwaysRun=true)
	public void BulkFutureBazaarCheck() throws IOException
	{
		try{
	System.out.println("Starting the Test for Bulk.futurebazaar.com");	
	driver.get("http://bulk.futurebazaar.com/accounts/login/");
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_username")));
	driver.findElement(By.id("id_username")).sendKeys("futurebazaarqa@gmail.com");
	driver.findElement(By.id("id_password")).sendKeys("fbqa");
	System.out.println("user name and password enterd");
		driver.findElement(By.xpath("//input[2]")).click();
		Thread.sleep(2000);
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("custNewUser")));

  	System.out.println("Entering user Mobile no and click TAB");
	driver.findElement(By.id("custNewUser")).sendKeys("9920694762");
	System.out.println("login button clicked");
	driver.findElement(By.id("cc_login")).click();
	Thread.sleep(2000);
	wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("html/body/div[4]/div/div[1]"),"Hi"));
	Assert.assertTrue(driver.findElement(By.xpath("html/body/div[4]/div/div[1]")).getText().contains("Hi")," user is not able to Login from My Account Option ");
	System.out.println("TEst Completed successfully");	
		}catch(Exception t)
		{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"BULKFutureBazaarfail.png"));

				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
				fail(t.getMessage());
		}
	}
	
	@Test(dependsOnMethods="BulkFutureBazaarCheck",alwaysRun=true)
	public void BigBazaarCheck() throws IOException
	{
		try{
	System.out.println("Starting Testcase for BigBazaar.com");
	driver.get("http://www.bigbazaar.com/mumbai/");
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("content")));
	driver.findElement(By.id("shop_on_pincode")).sendKeys("400083");
	driver.findElement(By.xpath(".//*[@id='list_pincode_form']/button")).click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("content")));
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Sign Up ")));
	Assert.assertTrue(driver.findElement(By.linkText("Track Order")).getText().equalsIgnoreCase("Track Order"));
	System.out.println("TEst Completed successfully");
		}catch(Exception t)
		{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"BigBazaarfail.png"));

				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
				fail(t.getMessage());
		}
		}
	
	@Test(dependsOnMethods="BigBazaarCheck",alwaysRun=true)
	public void mFuturebazaarCheck() throws IOException
	{
	  try{
		  
	  		System.out.println("Starting Testcase for M.Futurebazaar.com");
		driver.get("http://m.futurebazaar.com/");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mt15")));
		
		Assert.assertTrue(driver.getPageSource().contains("Track Order"));
		System.out.println("TEst Completed successfully");
		}catch(Exception t)
		{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"MobileFutureBazaarfail.png"));

				//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
				fail(t.getMessage());
		}
	  }
	
	@Test(dependsOnMethods="mFuturebazaarCheck",alwaysRun=true)
	public void ItzCashFutureBazaarCheck() throws IOException
	{   try{
		
	
		System.out.println("Starting Testcase for ItzCashFuturebazaar.com");
		driver.get("http://itz.futurebazaar.com/accounts/login/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_username")));
		driver.findElement(By.id("id_username")).sendKeys("sujaybhagwatjob@gmail.com");
		driver.findElement(By.id("id_password")).sendKeys("sujay");
		System.out.println("user name and password enterd");
		driver.findElement(By.xpath("//input[2]")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));

		Assert.assertTrue(driver.getCurrentUrl().contains("itz-homepage"),"Failure at the time of the Itz Agent Login");
		System.out.println("TEst Completed successfully");
	}catch(Exception t)
	{
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"ItzCashFutureBazaarfail.png"));

		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
		
		fail(t.getMessage());
	}	
	}	
	
	@Test(dependsOnMethods="ItzCashFutureBazaarCheck",alwaysRun=true)
	public void AdminSupportFutureBazaarCheck() throws IOException
	{   try{
		
	
		System.out.println("Starting Testcase for Admin.futurebazaar.com");
		driver.get("http://admin.futurebazaar.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_username")));
		driver.findElement(By.id("id_username")).sendKeys("narendra.adki@futuregroup.in");
		driver.findElement(By.id("id_password")).sendKeys("1234");
		System.out.println("user name and password enterd");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));
		Assert.assertTrue(driver.findElement(By.id("userinfo")).getText().contains("Hi")," user is not able to Login from My Account Option ");

		Assert.assertTrue(driver.getCurrentUrl().contains("support"),"Failure at the time of the Itz Agent Login");
		System.out.println("TEst Completed successfully");
	}catch(Exception t)
	{
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+fs+"Snapshot For URL failure"+fs+"AdminFutureBazaarfail.png"));

		//PaymentPageFailureMail.sendFailureMail("Future Bazaar Sanity Test Failed in Home Page-New Arrivals Link ");
		
		fail(t.getMessage());
	}	
	}	
	
 
public static void main(String[] args) throws IOException
{
	UrlChecks uu = new UrlChecks();
	uu.setUP();
	uu.BigBazaarCheck();
}
 
}