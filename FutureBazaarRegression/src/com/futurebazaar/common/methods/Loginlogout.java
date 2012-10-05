package com.futurebazaar.common.methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.futurebazaar.base.TestBase;

import com.sun.enterprise.appclient.Main;

public class Loginlogout extends TestBase{
	/*
	//Singleton object to initalize the base class
	private static Loginlogout instance = new Loginlogout(); 
	
	public static Loginlogout getInstance(){
		return instance;
	}
	private Loginlogout(){
		try {
			TestBase.initialize();
		} catch (Exception e) {
			System.out.println("TestBase initialize method exception " + e.getMessage());
			e.printStackTrace();
		}
	}*/
	//*******************************************************
	//Singleton object to initalize the base class
	/*private static Loginlogout instance = new Loginlogout(); 
		
		public static Loginlogout getInstance(){
			return instance;
		}*/
		/*private Loginlogout(){
			try {
				//Webdriverutility wb = new Webdriverutility();
			} catch (Exception e) {
				System.out.println("TestBase initialize method exception " + e.getMessage());
				e.printStackTrace();
			}
		}*/
		
		
		
	public static void loginUser(String username,String password)
	{
		//method used to login by the UserId as Email 
		APP_LOGS.debug("Login by the userEmail method");
		
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getIDobject("USER_NAME_ID").sendKeys(username);
		APP_LOGS.debug("user id as an email is entered");
		getIDobject("USER_PASSWORD_ID").sendKeys(password);
		APP_LOGS.debug("users password is entered");
		getIDobject("USER_LOGIN_ID").click();
		APP_LOGS.debug("Clicked on Sign in link");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
		wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
		System.out.println(driver.findElement(By.cssSelector("li.mart2 > span")).getText());
		//System.out.println(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText());
	    Assert.assertTrue(driver.getPageSource().contains("Hi,"), "User is not logged in Properly");
	    APP_LOGS.debug("User Logged in Successfully");
	}
     
	public static void loginByFacebook(String FBUserid,String Password)
	{//this method is used to log in by Facebook account 
		APP_LOGS.debug("login by the facebook Account method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getclassobject("FACEBOOK_LOGINLINK_CLASS").click();
		APP_LOGS.debug("Clicked on facebook link");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		//navigating to the facebook window
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			System.out.println(driver.getTitle());
			
			}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getIDobject("FACEBOOK_USERID_ID").sendKeys(FBUserid);
		APP_LOGS.debug("user id as an email is entered on facebook popup");
		getIDobject("FACEBOOK_PASSWORD_ID").sendKeys(Password);
		APP_LOGS.debug("users password is entered on facebook");
	
	    		
		WebElement element = getnameobject("FACEBOOK_LOGIN_NAME");
		element.submit();
		//element.sendKeys("",Keys.ENTER);
		//element.sendKeys("",Keys.ENTER);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		APP_LOGS.debug("Clicked on Sign in link on facebook");
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			System.out.println(driver.getTitle());
			
			}
					
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(OR.getProperty("FUTUREBAZAAR_HEADER_CLASS"))));
	//	wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
		//System.out.println(driver.findElement(By.cssSelector("li.mart2 > span")).getText());
		//System.out.println(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText());
	    Assert.assertTrue(driver.getPageSource().contains("Hi,"), "User is not logged in Properly");
	    APP_LOGS.debug("User Logged in Successfully");
	
		
	}
	
	public static void loginByPhoneNo(String username,String password)
	{//this method is used to log in by the mobile no
		//method used to login by the UserId as Email 
				APP_LOGS.debug("login by the loginby Phone no method");
				getlinktextobject("MY_ACCOUNT_LINK").click();
				APP_LOGS.debug("My Account link clicked");
				getIDobject("USER_NAME_ID").sendKeys(username);
				APP_LOGS.debug("user id as an mobile on. is entered");
				getIDobject("USER_PASSWORD_ID").sendKeys(password);
				APP_LOGS.debug("users password is entered");
				getIDobject("USER_LOGIN_ID").click();
				APP_LOGS.debug("Clicked on Sign in link");
	}
	
	public static void logOut()
	{//this method can be used for the logout in BY Email or mobile 
		APP_LOGS.debug("Log out by the logout method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getIDobject("SIGN_OUT_ID").click();
		APP_LOGS.debug("User logged out");
	}
	
	public static void logoutFacebook()
	{//this method is used to log out from the facebook login
		APP_LOGS.debug("facebook user Log out by the logoutFacebook method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		driver.findElement(By.cssSelector("#f_logout > img")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		APP_LOGS.debug("FB User logged out");
	}
	
	//*******************************************************
	//operation from the sign up link present in the my account 
	
	public static void loginByMyAccount(String username,String password)
	{//this method is used to log in from the Sign up link from my Account on home page
		APP_LOGS.debug("login by the userEmail method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getIDobject("SIGN_UP_ID").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(OR.getProperty("ACCOUNT_LOGIN_ID"))));
		
		APP_LOGS.debug("Clicked on the sign up link from My account");
		getIDobject("ACCOUNT_USER_NAME_ID").sendKeys(username);
		APP_LOGS.debug("user id as an email is entered");
		getIDobject("ACCOUNT_PASSWORD_ID").sendKeys(password);
		APP_LOGS.debug("users password is entered");
		getIDobject("ACCOUNT_LOGIN_ID").click();
		APP_LOGS.debug("Clicked on Sign in link");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.mart2 > span")));
		wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("li.mart2 > span"),"Hi,"));
		System.out.println(driver.findElement(By.cssSelector("li.mart2 > span")).getText());
		//System.out.println(driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]")).getText());
	    Assert.assertTrue(driver.getPageSource().contains("Hi,"), "User is not logged in Properly from Login PAge");
	    APP_LOGS.debug("User Logged in Successfully");
		}
	
	public static void userRegistration(String username,String password)
	{//this method is used to register a new user to futurebazaar
		APP_LOGS.debug("login by the userEmail method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getIDobject("SIGN_UP_ID").click();
		APP_LOGS.debug("Clicked on the sign up link from My account");
		getIDobject("NEW_USER_ID").sendKeys(username);
		getxpathobject("NEW_USER_PASSWORD_XPATH").sendKeys(password);
		getIDobject("NEW_USER_PASSWORD_CONFIRMATION_ID").sendKeys(password);
        getxpathobject("NEW_USER_SIGN_UP_XPATH").click();  	
	   APP_LOGS.debug("user registered");
	}
	//**************************************************
	
	//
	public static void loginGuestflow(String username,String password)
	{//this method is used to log in the shopping flow by email or mobile
		APP_LOGS.debug("login by the loginGuestflow method");
	    
		APP_LOGS.debug("Entering User Name");
	    
	    driver.findElement(By.id(OR.getProperty("GUEST_USER_NAME_ID"))).clear();
		driver.findElement(By.id(OR.getProperty("GUEST_USER_NAME_ID"))).sendKeys(username);
        
		APP_LOGS.debug("Clicking on already a customer check box to open Password field");
	    
		driver.findElement(By.id(OR.getProperty("GUEST_ALREADY_CUSTOMER_CHECKBOX_ID"))).click();
		
	    APP_LOGS.debug("user id as an mobile on. is entered");
	    driver.findElement(By.id(OR.getProperty("GUEST_USER_PASSWORD_ID"))).clear();
		driver.findElement(By.id(OR.getProperty("GUEST_USER_PASSWORD_ID"))).sendKeys(password);
	    
		
				
		APP_LOGS.debug("Click on Continue Button");
		driver.findElement(By.xpath(OR.getProperty("GUEST_LOGIN_XPATH"))).click();
	}
	
	public static void userRegisrationGuestflow(String username,String password)
	{//this method is used to register a new user to futurebazaar from Guest flow
		APP_LOGS.debug("new user registration by the  method");
				
		getIDobject("GUEST_NEW_USER_ID").sendKeys(username);
		getxpathobject("GUEST_NEW_USER_PASSWORD_ID").sendKeys(password);
		getIDobject("GUEST_NEW_USER_CONFIRM_PASSWORD_ID").sendKeys(password);
        getxpathobject("GUEST_NEW_LOGIN_ID").click();  	
	   APP_LOGS.debug("user registered");
	
	}	
	
	public static void forgotPassword(String username)
	{   //this method is used to get forgot password link from my account menu by clicking on forgot password link
		APP_LOGS.debug("Forgot Password From My Account method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		driver.findElement(By.id(OR.getProperty("FORGOT_PASSWORD_ID"))).click();
		APP_LOGS.debug("Forgot Password link clicked");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("FORGOT_PASSWORD_PAGE_VERIFICATION_XPATH"))));
		Assert.assertTrue(driver.getPageSource().contains("Forgot your password?"), "After clicking Forgot password,Forgot password page is not displayed ");
		driver.findElement(By.name(OR.getProperty("FORGOT_EMAIL_NAME"))).sendKeys(username);
		APP_LOGS.debug("Send Instruction Button Clicked ");
		driver.findElement(By.cssSelector(OR.getProperty("FORGOT_SEND_INST_CSS"))).click();
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("FORGOT_PASSWORD_PAGE_VERIFICATION_XPATH"))));
		 Assert.assertTrue(driver.getPageSource().contains("Check your email and mobile"), "After giving mobile NO Forgot password,Password Reset page is not displayed ");
			          
		APP_LOGS.debug("Email or message has been sent for the forgot password");
	}
		
	
	public static void forgotPasswordByMyAccount(String username)
	{   //this method is used to get forgot password link from my account menu by clicking Sign up
		APP_LOGS.debug("Forgot Password From My Account -> Login Page method");
		getlinktextobject("MY_ACCOUNT_LINK").click();
		APP_LOGS.debug("My Account link clicked");
		getIDobject("SIGN_UP_ID").click();
		APP_LOGS.debug("Clicked on the sign up link from My account");
        getIDobject("ACCOUNT_FORGOT_PASSWORD_ID").click();
    	APP_LOGS.debug("Forgot Password link clicked");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("FORGOT_PASSWORD_PAGE_VERIFICATION_XPATH"))));
      Assert.assertTrue(driver.getPageSource().contains("Forgot your password?"), "After clicking Forgot password,Forgot password page is not displayed ");
      driver.findElement(By.name(OR.getProperty("FORGOT_EMAIL_NAME"))).sendKeys(username);
		driver.findElement(By.cssSelector(OR.getProperty("FORGOT_SEND_INST_CSS"))).click();
		APP_LOGS.debug("Send Instruction Button Clicked ");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("FORGOT_PASSWORD_PAGE_VERIFICATION_XPATH"))));
		  Assert.assertTrue(driver.getPageSource().contains("Check your email and mobile"), "After giving mobile NO Forgot password,Password Reset page is not displayed ");
	          
		APP_LOGS.debug("Email has been sent for the forgot password");
	}
	
	public static void loginGuestflowFacebook(String FBUserid,String Password)
	{//this method is used to log in by the facebook account in the shopping flow or from the home page 
		//this method is used to log in by Facebook account 
				APP_LOGS.debug("login by the facebook Account method");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				getxpathobject("FACEBOOK_HOME_LOGIN_XPATH").click();
				APP_LOGS.debug("Clicked on facebook link");
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
				//navigating to the facebook window
				for(String handle : driver.getWindowHandles())
				{
					driver.switchTo().window(handle);
					
					System.out.println(driver.getTitle());
					
					}
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getIDobject("FACEBOOK_USERID_ID").sendKeys(FBUserid);
				APP_LOGS.debug("user id as an email is entered on facebook popup");
				getIDobject("FACEBOOK_PASSWORD_ID").sendKeys(Password);
				APP_LOGS.debug("users password is entered on facebook");
			
			    		
				WebElement element = getnameobject("FACEBOOK_LOGIN_NAME");
				element.sendKeys("",Keys.ENTER);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				APP_LOGS.debug("Clicked on Sign in link on facebook");			
				
	}
	
	//Check the status of the user if logged in or not.
	public static boolean checkuserloginStatus()
	{
		
		String we = getCSSobject("WELCOME_TEXT_CSS").getText();
        if(driver.getPageSource().contains("Hi,"))
        {
        	return true;
        }
        return false;
	}
	
	public static void main(String [] args) throws Exception
	{ 
		
	}
	
}
