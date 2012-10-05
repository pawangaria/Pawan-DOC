package com.futurebazaar.sanity;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class test {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver= new FirefoxDriver();
		driver.navigate().to("http://manager.publishthis.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='email']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[starts-with(@id, 'bluebutton') and text()='Login']")).click();
		Thread.sleep(10000);
		//Thread.sleep(10000);
		//driver.manage().wait();
		driver.findElements(By.linkText("Go to Dashboard")).get(0).click();
		Thread.sleep(5000);
		//driver.findElement(By.id("gbqfq")).sendKeys("all");
		//driver.findElement(By.id("gbqfb")).click();
		//Thread.sleep(1000);
		//EventFiringWebDriver fire = new EventFiringWebDriver(driver);
		//fire.getKeyboard();
		//fire.executeScript("scroll(0,20000)");
		 driver.findElement(By.xpath("//*[starts-with(@id, 'lightgraybutton') and text()='Create Feed']")).click();
        
		 Thread.sleep(5000);
		 driver.findElement(By.xpath("//button[contains(@id,'ext-gen')")).click();
         driver.findElement(By.xpath("//*[@name='title']")).sendKeys("hjasgdhsdfghas");
         driver.findElement(By.xpath("//input[starts-with(@id,'ext-gen')]")).sendKeys("apple");
         List<WebElement> options= driver.findElements(By.className("displayName"));
         System.out.println(options.size());
         for(int i=0;i<options.size();i++){
                 if(options.get(i).getText().equalsIgnoreCase("New York City")){
                         options.get(i).click();
                 }
         driver.findElement(By.xpath("//*[text()='Next']"));
       //Scroll to till last
 		//DefaultSelenium sel = new WebDriverBackedSelenium(driver,"http://manager.publishthis.com/dashboard");
 		//sel.se
 		//driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]")).sendKeys(Keys.PAGE_DOWN);
 		//JavascriptExecutor js = (JavascriptExecutor) driver;
 		//js.executeScript("javascript:window.scrollBy(250,350)");
 		
 		//Selenium selenium = new WebDriverBackedSelenium(driver,"http://manager.publishthis.com/Dashboard");
           //  selenium.getEval("window.scrollBy(0,1000)'");
 		    driver.findElement(By.xpath("/html/body/div[2]/ul/li[2]/a")).getLocation();
 		    Thread.sleep(1000);
 		    driver.findElement(By.xpath("html/body/div[2]/ul/li[2]/a")).click();
 		    
 		   //int elementPosition = element.getLocation().getY();
 		   //String js = String.format("window.scroll(0, %s)", elementPosition);
 		   //((JavascriptExecutor)driver).executeScript(js);
 		   //element.click();
 		
 		//driver.findElement(By.id("fblehl")).click();
 		
         }
}}