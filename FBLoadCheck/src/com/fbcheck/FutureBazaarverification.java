package com.fbcheck;

import static org.testng.AssertJUnit.fail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FutureBazaarverification {
	
	public static  FirefoxDriver driver;
	public static String name;
	public static int passcount;
	public static int failcount;
	public static int toalTESTrUNcount;
	public static int toalLOADcount;
	public static int count;
	
	public static float PASSaverage;
	public static float failaverage;
	
	public static float averageloadingtime;
	
	

	public static WebDriverWait wait=null;
	
	@Test
	public static void futureBazaarVerification() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
    /*  FirefoxProfile profile = new FirefoxProfile();
      profile.setPreference("network.proxy.type",1);
      profile.setPreference("network.proxy.http", "localhost");
      profile.setPreference("network.proxy.http_port",9091);
		
      */
    try{ 
    	//driver = new InternetExplorerDriver();
    //	driver = new FirefoxDriver();
    	
		
    	String userAgent = "pawan";
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override", userAgent);

		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE,profile);
		 driver = new FirefoxDriver(dc);
		wait =new WebDriverWait(driver,20);
		
		long end;
		 long start = System.currentTimeMillis(); 
		  
		   System.out.println(start);
		
		  
      driver.get("http://www.futurebazaar.com");
      System.out.println();
     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));
     	Assert.assertTrue(driver.getTitle().equalsIgnoreCase("FutureBazaar.com - Online Shopping India | Buy from Home & Living, Electronics, Kitchen Items, Kitchen and Home Appliances, Gift Vouchers"));
  	
        
       end = System.currentTimeMillis();
       System.out.println(end);
       
       long duration = end - start; 
       System.out.println(duration*0.001);
       System.out.println("Page took " + duration + " ms to load"); 
      //writing Duration time to the load file.
       String duration2=String.valueOf(duration);
       System.out.println(duration2);
       write("C:\\workspace\\ReportFIles\\LoadTime.txt",duration2);
       
      ////////////////////////////////////////////////////// 
       //writing pass and fail to the file.
      
    	//////////////////////////////////////////////
       write("C:\\workspace\\ReportFIles\\PassORfail.txt","pass");
   	
		time(String.valueOf(start),"C:\\workspace\\ReportFIles\\LoadTime.txt","C:\\workspace\\ReportFIles\\PassORfail.txt","C:\\workspace\\ReportFIles\\SENDorNOT.txt");
	
	}catch(Throwable t)
	{
		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
	    FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));
	  Thread.sleep(2000);
	  write("C:\\workspace\\ReportFIles\\PassORfail.txt","fail");
	  //getting the size
	   File file = new File("c:\\screenshot.png");
      long filesize = file.length();
      long filesizeInKB = filesize / 1024;
      //Send mail only if the size is grater than 2KB
      if(filesizeInKB>2)
      {
	    FailureMail.sendMail();
      }
	    fail(t.getMessage());
	}
		
		
	}
	
	public static void write(String file,String value) throws IOException
	{
		  
		  FileWriter ff = new FileWriter(file,true);
			BufferedWriter fff = new BufferedWriter(ff);
			
			fff.write(value);
			fff.newLine();
			fff.flush();
			System.out.println("Your file has been written"); 
			  
			
	}
	
	public static String readmailsending(String file) throws IOException
	{
		  FileInputStream fstream = new FileInputStream(file);
		  // Get the object of DataInputStream
		  DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine=br.readLine();
		//  System.out.println(strLine);
		return(strLine);
	}
	
	public static void WriteNOmailsending(String file) throws IOException
	{
		 FileWriter ff = new FileWriter(file);
			BufferedWriter fff = new BufferedWriter(ff);
			//fff.newLine();
			fff.write("SEND");
	    	
			fff.flush();
		
	}
	

	public static void time(String longtime,String loadfile,String passfailFile,String mailsenornotFILE) throws IOException
	{ boolean ee=false;
	boolean pp=false;	
	SimpleDateFormat formatter = new SimpleDateFormat("HH");
		// longModtime should be an public variable 
		String longModtime = formatter.format(new Date(Long.parseLong(longtime)));
		//"1328784301256"
		System.out.println("the time "+longModtime);
		
		if(longModtime.equals("04"))
		{
			//String aa =readmailsending(mailsenornotFILE);
			
			if(readmailsending(mailsenornotFILE).equalsIgnoreCase("SEND"))
			{
				System.out.println("NO MAIL");
								
			}else
			{
				//average time calculated.
				averageLOADtime(loadfile);
				
				//average pass fail 
				read(passfailFile);
				
				//sending mail
				System.out.println("mail sending for data");
				 System.out.println("total test run count "+toalTESTrUNcount);
					System.out.println("totall pass count"+passcount);
				  System.out.println("totall pass count"+failcount);
				  System.out.println("average loading time"+averageloadingtime);
				
				//writng the send to the Property file
				WriteNOmailsending(mailsenornotFILE);
				//SendMail nn = new SendMail();
				ReportSending.Mailsend(String.valueOf(toalTESTrUNcount),String.valueOf(passcount),String.valueOf(failcount),String.valueOf(averageloadingtime));
				
				
				//Removing all the files and creating new files.
				File f = new File(loadfile);
				
				  if(f.exists()){
				 ee =f.delete();
					  // f.createNewFile();
				  } 
				  if(ee)
				  {
				   f.createNewFile();
				  System.out.println("New file created");
				  }
				  
			//remove second file passfailFile
			             File ff = new File(passfailFile);
			
		            	  if(ff.exists()){
			             pp =ff.delete();
				      // f.createNewFile();
			                        } 
			         if(pp)
			         {
			         ff.createNewFile();
			           System.out.println("New file created");
			          }
			         
			         
			  }
		}else if(longModtime.equals("05"))
		{
			FileWriter ff = new FileWriter(mailsenornotFILE);
			BufferedWriter fff = new BufferedWriter(ff);
			//fff.newLine();
			fff.write("NOSEND");
	    	
			fff.flush();
			
		}
	}

	
	public static void averageLOADtime(String file)
	{   
		try{
			 			 
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(file);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				  count++;
				  
							  
				  int tot = Integer.parseInt(strLine);
				  toalLOADcount+=tot;
				  
				  System.out.println (strLine);
			  }
			  //Close the input stream
			  in.close();
			  
			  System.out.println("total count"+toalLOADcount);
	          System.out.println(count);
		 
			    }catch (Exception e){//Catch exception if any
			        System.err.println("Error: " + e.getMessage());
			  }
		
				
		averageloadingtime = (float)(toalLOADcount*0.001)/count;
		 
		System.out.println("average load timee"+averageloadingtime);
	}
	
	public static void read(String file)
	{
		 try{
			 
			 
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(file);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				  toalTESTrUNcount++;
				  if(strLine.equalsIgnoreCase("pass"))
				  {
					  passcount++;
				  }
				  
				  if(strLine.equalsIgnoreCase("fail"))
				  {
					 failcount++;
				  }
				  System.out.println (strLine);
			  }
			  //Close the input stream
			  in.close();
			  
			  System.out.println("total passcount"+passcount);
			  System.out.println("total failcount"+failcount);
		 
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		
		 

			PASSaverage = (float)(passcount*100)/toalTESTrUNcount;
			System.out.println("Pass Percentage for the Test"+PASSaverage);

			failaverage = (float)(failcount*100)/toalTESTrUNcount;
			System.out.println("fail percentage for the Test"+failaverage);
		 
	}
	
	@AfterTest(alwaysRun=true)
	   private void quitdriver()
	   {
		   
		   driver.quit();
	   }
	
	

}
































/*
public class FutureBazaarverification extends DODConsts{
	//public WebDriver driver=new FirefoxDriver();
	//public WebDriverWait wait=new WebDriverWait(driver,20);
	@Test
	public void futureBazaarVerification() throws IOException, InterruptedException
	{
		try{
		driver.get(DODConsts.DOD_URL);
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));
		//Thread.sleep(2000);
		//System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		Assert.assertEquals("FutureBazaar.com - Online shopping deals in Laptops, Mobiles Phones, Computers and Electronics", driver.getTitle());
		//Thread.sleep(3000);
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    // Now you can do whatever you need to do with it, for example copy somewhere
	    //FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));
	    //FailureMail.sendMail();
		}catch(Throwable t)
	{
		//if the page is not loaded in 20 sec the screen shot will be taken and send by mail.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    //Now you can do whatever you need to do with it, for example copy somewhere
	    FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));
	  Thread.sleep(2000);
	    
	    FailureMail.sendMail();
	    fail(t.getMessage());
	}
	}

	 @AfterTest(alwaysRun=true)
	   private void quitdriver()
	   {
		   
		   driver.quit();
	   }*/
	

