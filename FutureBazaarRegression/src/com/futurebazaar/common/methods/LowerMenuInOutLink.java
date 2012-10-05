package com.futurebazaar.common.methods;
import com.futurebazaar.testing.util.BasicCommonTestUtils;
import java.util.List;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LowerMenuInOutLink extends TestBase {

	
	public static void LowerMenu_InnerLink(String Xpath_Outer,String Xpath_Inner) throws IOException, InterruptedException
	{     
		
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
		 Actions builder = new Actions(driver);
		  
	     WebElement cartlink= driver.findElement(By.xpath(Xpath_Outer));
         builder.clickAndHold(cartlink).build().perform();	
	     WebElement cartlink1= cartlink.findElement(By.xpath(Xpath_Inner));
	  
	 
	   
	     builder.clickAndHold(cartlink).build().perform();
	     String name= cartlink1.getText();
	     APP_LOGS.debug("Clicking on :" +name);
	
	     WebElement cartlink2= driver.findElement(By.xpath(Xpath_Outer));
	     builder.clickAndHold(cartlink2).build().perform();
	     APP_LOGS.debug(cartlink2.findElement(By.xpath(Xpath_Inner)).getText());
	     builder.clickAndHold(cartlink2).build().perform();
	     WebElement cartlink3 = cartlink2.findElement(By.xpath(Xpath_Inner));
	     builder.clickAndHold(cartlink3).build().perform();
	     cartlink3.click();
	     
	     String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
		 Prevno=BasicCommonTestUtils.removeCharacters(Prevno, "0123456789");
		 Integer Prevno_numeric = Integer.parseInt(Prevno);
	  //System.out.println("*****The Searched Item number is:"+Prevno_numeric);
		
	     
	  //System.out.println(" ****** getting the value of the Category count");
	    WebElement we =driver.findElement(By.id("cat_filter"));
	    List<WebElement> pp = we.findElements(By.className("padl10"));
	    //WebElement Basecategory = we.findElement(By.className("padl0"));
	    WebElement Parentcategory = we.findElement(By.className("padl0"));
	    
	    //Assering base category name
	    //System.out.println("Base Category is:"+Basecategory.getText());
	    //System.out.println("Asserting the base category");
	    //String base =driver.findElement(By.xpath("Popular_breadcumlink_xpath")).getText();
	    //Assert.assertEquals(base, "‹ Home & Living","Base category doesn't match");
	    //System.out.println("Base category is verified ");
	    //System.out.println("Asserting the base category");
	    //String base =Basecategory.getText().toString();
	    
		//System.out.println("Base is :"+base);
		//String basefront = driver.findElement(By.xpath("//div[@id='menu']/ul/li[2]/a/div")).getText();
		//System.out.println("Basefront is :"+basefront);    
	    //Assert.assertTrue(base.toUpperCase().contains(basefront),"Base category mismatch");
	   
	    //Asserting parent category name
	    //System.out.println("Parent Category is:"+Parentcategory.getText());
	    APP_LOGS.debug("Asserting the parent category");
	    String parent= Parentcategory.getText().toString();
	    Assert.assertEquals(parent,name,"parent category doesn't match");
	    APP_LOGS.debug("Parent Category is verified");
	    
	    //Asserting item count within category
        int size = pp.size();
        System.out.println("pp size is "+ size); 
   
        int totalsearch=0;
        for (WebElement option : pp)
        {  
       //System.out.println(option.findElement(By.tagName("a")).getText());
        String input = option.findElement(By.tagName("a")).getText();
        int start = input.indexOf("(");
        int end = input.indexOf(")");
       String getval = input.substring(start+1,end);
      //System.out.println(getval);
      int value = Integer.parseInt(getval);
      totalsearch+=value;
       }
      //System.out.println("Total item count withing Category is"+ totalsearch);
        APP_LOGS.debug(" **** Verifying the item count within Category");
      Assert.assertTrue(Prevno_numeric.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
       //System.out.println("****verified the itemcount within Category with the search count");
      
       //for the By brand Search
      APP_LOGS.debug("verifying the total By Brand search items by count");
       WebElement brand =driver.findElement(By.id("brands_filter"));
       WebElement brand2 = brand.findElement(By.className("filter_scroll"));
       List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
       int brandlistsize = brandlist.size();
       //System.out.println("Brannd size"+brandlistsize);
       
        int totalBrandsearch=0;
      //getting the values for the count of brands.
       for (WebElement option : brandlist)
       {   //System.out.println(option.findElement(By.tagName("label")).getText());
         String input = option.findElement(By.tagName("label")).getText();
         int start = input.indexOf("(");
         int end = input.indexOf(")");
         String getval = input.substring(start+1,end);
         int value = Integer.parseInt(getval);
         totalBrandsearch+=value;
      }
       APP_LOGS.debug("Total count for the Search By BRAND "+ totalBrandsearch);
     
      //verifying the Total Count Value Search By BRAND and the Search Count value
      //System.out.println("******verifying the count for the Search By BRAND");
       Assert.assertTrue(Prevno_numeric.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
       System.out.println("******verified the count for the Search By BRAND with the search count");   
       
       
     }  
	 public static void LowerMenu_OuterLink(String Xpath_Outer ,String Xpath_Block) throws IOException,InterruptedException
	  {
		     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			
		     Actions builder = new Actions(driver);
		     WebElement cartlink=driver.findElement(By.xpath(Xpath_Outer));
		     builder.clickAndHold(cartlink).build().perform();
		     		 	 
		 	 WebElement kk = driver.findElement(By.xpath(Xpath_Block));
		 	 WebElement kk2 = kk.findElement(By.className("t1_l2_inner"));
			// WebElement  kk3 = kk2.findElement(By.className("t2_l2_inner_ul"));
			 List<WebElement> kk4 = kk2.findElements(By.className("t1_l2"));
			 APP_LOGS.debug("Size of the List is "+kk4.size());
			 int size1=kk4.size();
			 
			/* for(WebElement opt: kk4)
			 {
				 System.out.println(opt.findElement(By.tagName("a")).getText());
			 }*/
			 driver.findElement(By.xpath(Xpath_Outer)).click();
			 
			 String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
			 APP_LOGS.debug("Prev no"+Prevno);
			 Prevno=BasicCommonTestUtils.removeCharacters(Prevno,"0123456789");
			 Integer Prevno_numeric = Integer.parseInt(Prevno);
			 //System.out.println("*****The Searched Item number is:"+Prevno_numeric);
		     
		  
		  
		   // System.out.println(" ****** getting the value of the count for the Search By Category");
			 WebElement we =driver.findElement(By.id("cat_filter"));
			 //WebElement we  =we1.findElement(By.className("padb10 filter_scroll"));
			 List<WebElement> pp = we.findElements(By.tagName("li"));
		     int size = pp.size();
		     Assert.assertEquals(size, size1 ,"category count is not matched");
		   // System.out.println("No of Category is:"+size);
		     int totalsearch=0;
		     for (WebElement option : pp)
		     {  
		    //System.out.println(option.findElement(By.tagName("a")).getText());
		     String input = option.findElement(By.tagName("a")).getText();
		     int start = input.indexOf("(");
		     int end = input.indexOf(")");
		  
		  
		     String getval = input.substring(start+1,end);
		     //System.out.println(getval);
		     int value = Integer.parseInt(getval);
		     totalsearch+=value;
		  
		    //option.findElement(By.tagName("a")).click();
		  	
		  	    	
		  }
		  
		  //verifying the Total Count Value by category and the Search Count value
		  // System.out.println("Total count for the Search By Category"+ totalsearch);
		     APP_LOGS.debug(" ****** verifying the count for the Search By Category");
		   Assert.assertTrue(Prevno_numeric.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
		 
		//   System.out.println("******verified the count for the Search By Category with the search count");
		  
		  //for the By brand Search
		   APP_LOGS.debug("verifying the total By Brand search items by count");
		   WebElement brand =driver.findElement(By.id("brands_filter"));
		   WebElement brand2 = brand.findElement(By.className("filter_scroll"));
			List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
		   int brandlistsize = brandlist.size();
		   
		   APP_LOGS.debug("Brannd Search Item size"+brandlistsize);
		   
		    int totalBrandsearch=0;
		  //getting the values for the count of brands.
		   for (WebElement option : brandlist)
		   {   //System.out.println(option.findElement(By.tagName("label")).getText());
		     String input = option.findElement(By.tagName("label")).getText();
		     int start = input.indexOf("(");
		     int end = input.indexOf(")");
		     String getval = input.substring(start+1,end);
		  //   System.out.println(getval);
		     int value = Integer.parseInt(getval);
		     totalBrandsearch+=value;
		  
		  //option.findElement(By.tagName("a")).click();
		      	    	
		  }
		//   System.out.println("Total count for the Search By BRAND "+ totalBrandsearch);
		 
		  //verifying the Total Count Value Search By BRAND and the Search Count value
		   APP_LOGS.debug("******verifying the count for the Search By BRAND");
		   Assert.assertTrue(Prevno_numeric.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
		  
		   APP_LOGS.debug("******verified the count for the Search By BRAND with the search count");
		
			}
	
}
