package com.futurebazaar.common.methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.futurebazaar.base.*;

//this class is used to clean up cart items depend upon the requirement
public class Itemcleanup extends TestBase{
	
	
	//private static Itemcleanup instance = new Itemcleanup(); 
	
	//public static Itemcleanup getInstance(){
		//return instance;
	//}
          
	public static void itemCleanup()
	{
		//this is the item clean up with out any user login
		APP_LOGS.debug("Cleaning items from the cart with out user log in");
		
		APP_LOGS.debug("Clicking on the cart");
		/*driver.findElement(By.id("id_q")).sendKeys("mobile");
		driver.findElement(By.id("go")).click();
		driver.findElement(By.xpath("//li[@id='grid_page_1']/div/h3/a")).click();
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//td/div/button")).click();
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		*/
		System.out.println("COMING 1");
		driver.findElement(By.id("logo")).click();
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("HOME_PAGE_VERIFICATION_ID")));
	
		getCSSobject("MY_SHOPPING_SACK_CSS").click();
		
		if(driver.getPageSource().contains("Your shopping cart is empty."))
		{
			APP_LOGS.debug("Shopping cart is empty.");
		}else
		{	System.out.println("coming 2");
		 driver.findElement(By.id("logo")).click();
			
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("HOME_PAGE_VERIFICATION_ID")));
		String no = driver.findElement(By.cssSelector("div.cart_items")).getText();
		getCSSobject("MY_SHOPPING_SACK_CSS").click();
		System.out.println("no is:"+no);
		
		String ll[]=no.split(" ");
        String v2=ll[0];
        int val = Integer.parseInt(v2);
        for(int i=1;i<=val;i++)
        {
        	System.out.println("in before");
		getCSSobject("ITEM_REMOVE_CSS").click();
		System.out.println("iin after");
        APP_LOGS.debug(i+"item cleaned");
        }
        
		APP_LOGS.debug("Item cleaned up from the cart");
		}
		
	}
	
	public static void itemCleanupForUser(String username,String password)
	{
		//this is the item clean up with out any user login
				APP_LOGS.debug("Cleaning items from the cart for the logged in user");
				APP_LOGS.debug("logging in by user ");
				//Loginlogout.loginByMyAccount(username,password);
				Loginlogout.loginUser(username,password);
				APP_LOGS.debug("Clicking on the cart");
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				System.out.println("in the clean up");
				
				
				try{
					
				getCSSobject("MY_SHOPPING_SACK_CSS").click();
				
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				if(driver.getPageSource().contains("Your shopping cart is empty."))
				{
					APP_LOGS.debug("Shopping cart is empty.");
				}else
				{
					String no = driver.findElement(By.cssSelector("div.cart_items")).getText();
					String ll[]=no.split(" ");
			        String v2=ll[0];
			        int val = Integer.parseInt(v2);
			        for(int i=1;i<=val;i++)
			        {
					getCSSobject("ITEM_REMOVE_CSS").click();
			        APP_LOGS.debug(i+"item cleaned");
			        }
								
				APP_LOGS.debug("Removing the items from the cart");
				}
	}
	
	
	
	
	public static void main(String [] args) throws Exception
	{ 
		
	}
	
	
	
	
	
	
	
	
	
	

}
