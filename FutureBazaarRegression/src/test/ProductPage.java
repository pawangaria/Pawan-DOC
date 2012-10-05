package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProductPage 
{	
	public WebDriver driver;
	String productTitle;
	String productSellPrice;
	String productMarketPrice;
	String productStealPrice;
	
	public ProductPage(WebDriver driver) 
	{
		this.driver = driver;
		setProductMarketPrice();
		setProductStealPrice();
		setProductSellPrice();
		setProductTitle();
	}
	
	public String getProductTitle() 
	{
		return productTitle;
	}
	
	public void setProductTitle() 
	{   System.out.println("*** getting the DOD title from product page");
		this.productTitle = driver.findElement(By.xpath(DODConsts.PRODUCTPAGE_PRODUCT_TITLE)).getText();
	}
	
	public String getProductSellPrice() 
	{
		return productSellPrice;
	}
	
	public void setProductSellPrice() 
	{
		System.out.println("*** getting the DOD sell price from product page");
		this.productSellPrice = driver.findElement(By.xpath(DODConsts.PRODUCTPAGE_PRODUCT_SELLPRICE)).getText();
	}
	
	public String getProductMarketPrice() 
	{
		return productMarketPrice;
	}
	
	public void setProductMarketPrice() 
	{ System.out.println("*** getting the DOD market price from product page");
		this.productMarketPrice = driver.findElement(By.cssSelector(DODConsts.PRODUCTPAGE_PRODUCT_MARKETPRICE)).getText();
	}
	
	public String getProductStealPrice() 
	{
		return productStealPrice;
	}
	
	public void setProductStealPrice() 
	{
		System.out.println("*** getting the DOD Steal price from product page");
		this.productStealPrice = driver.findElement(By.xpath(DODConsts.PRODUCTPAGE_PRODUCT_STEALPRICE)).getText();
	}
}
