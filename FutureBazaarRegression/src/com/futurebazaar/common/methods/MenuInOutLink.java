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


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.testing.util.ErrorUtil;


public class MenuInOutLink extends TestBase {

	//This method is used to click all the inner links One by One, after Mouse hover to all
	public static void MenuInnerLink(String Xpath_Outer,String Xpath_Inner)
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
	     //System.out.println("coming to this");
	     builder.clickAndHold(cartlink2).build().perform();
	  //   System.out.println(cartlink2.findElement(By.xpath(Xpath_Inner)).getText());
	     builder.clickAndHold(cartlink2).build().perform();
	     WebElement cartlink3 = cartlink2.findElement(By.xpath(Xpath_Inner));
	     builder.clickAndHold(cartlink3).build().perform();
	     cartlink3.click();
	     
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cat_filter")));
        String   Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
		Prevno=BasicCommonTestUtils.removeCharacters(Prevno, "0123456789");
		Integer Prevno_numeric = Integer.parseInt(Prevno);
		//APP_LOGS.debug("*****The Searched Item number is:"+Prevno_numeric);
	    //APP_LOGS.debug(" ****** getting the value of the Category count");
	    WebElement we =driver.findElement(By.id("cat_filter"));
	    List<WebElement> pp = we.findElements(By.className("padl20"));
	    WebElement Basecategory = we.findElement(By.className("padl0"));
	    WebElement Parentcategory = we.findElement(By.className("padl10"));
	    
	    //Assering base category name
	    //APP_LOGS.debug("Base Category is:"+Basecategory.getText());
	   APP_LOGS.debug("Asserting the base category");
	   String base =Basecategory.getText().toString();
	  // APP_LOGS.debug("Base is :"+base);
	   String basefront = driver.findElement(By.xpath(Xpath_Outer)).getText();
	  // APP_LOGS.debug("Basefront is :"+basefront);    
       Assert.assertTrue(base.toUpperCase().contains(basefront),"Base category mismatch");
	  // APP_LOGS.debug("Base category is verified ");
	  //Asserting parent category name
	  // APP_LOGS.debug("Parent Category is:"+Parentcategory.getText());
	   APP_LOGS.debug("Asserting the parent category");
	   String parent= Parentcategory.getText().toString();
	   Assert.assertEquals(parent,name,"parent category doesn't match");
	  //APP_LOGS.debug("Parent Category is verified");
	  //Asserting item count within category
       int size = pp.size();
      //APP_LOGS.debug("pp size is "+ size); 
  
       int totalsearch=0;
       for (WebElement option : pp)
       {  
     //APP_LOGS.debug(option.findElement(By.tagName("a")).getText());
       String input = option.findElement(By.tagName("a")).getText();
       int start = input.lastIndexOf("(");
       int end = input.lastIndexOf(")");


      String  getval = input.substring(start+1,end);
     //System.out.println(getval);
       int value = Integer.parseInt(getval);
       totalsearch+=value;
   }
     APP_LOGS.debug("Total item count withing Category is"+ totalsearch);
     //APP_LOGS.debug(" **** Verifying the item count within Category");
     Assert.assertTrue(Prevno_numeric.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
     APP_LOGS.debug("****verified the itemcount within Category with the search count");
     
      //for the By brand Search
    // APP_LOGS.debug("verifying the total By Brand search items by count");
      WebElement brand =driver.findElement(By.id("brands_filter"));
      WebElement brand2 = brand.findElement(By.className("filter_scroll"));
      List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
      int brandlistsize = brandlist.size();
      
   //  APP_LOGS.debug("Brannd size"+brandlistsize);
      
       int totalBrandsearch=0;
     //getting the values for the count of brands.
      for (WebElement option : brandlist)
      {   //System.out.println(option.findElement(By.tagName("label")).getText());
        String input = option.findElement(By.tagName("label")).getText();
        int start = input.lastIndexOf("(");
        int end = input.lastIndexOf(")");
        String getval = input.substring(start+1,end);
        int value = Integer.parseInt(getval);
        totalBrandsearch+=value;
     
     
     }
   //  APP_LOGS.debug("Total count for the Search By BRAND "+ totalBrandsearch);
    
     //verifying the Total Count Value Search By BRAND and the Search Count value
   //  APP_LOGS.debug("******verifying the count for the Search By BRAND");
      Assert.assertTrue(Prevno_numeric.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
      APP_LOGS.debug("******verified the count for the Search By BRAND with the search count");   
   
	}
	
	//This method is used to click on the main link after Mouse hover and Comparing the Search Count.
	public static void MenuMainLink(String Xpath_Outer ,String Xpath_Block)
	{
	    
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
	    Actions builder = new Actions(driver);
	    WebElement cartlink= driver.findElement(By.xpath(Xpath_Outer));
	    builder.clickAndHold(cartlink).build().perform();
        WebElement kk = driver.findElement(By.xpath(Xpath_Block));
	 

	 	// WebElement kk1 = kk.findElement(By.className("t2_l2_wrapper w190"));
	 	 WebElement kk2 = kk.findElement(By.className("t2_l2_inner"));
		 WebElement  kk3 = kk2.findElement(By.className("t2_l2_inner_ul"));
		 List<WebElement> kk4 = kk3.findElements(By.className("t2_l2"));
		//APP_LOGS.debug(kk4.size());
		 int size1 = kk4.size();
		/* for(WebElement opt: kk4)
		 {
			//APP_LOGS.debug(opt.findElement(By.tagName("a")).getText());
		 }*/
		 driver.findElement(By.xpath(Xpath_Outer)).click();	
		 //Asserting the page
		 String Base = driver.findElement(By.xpath(Xpath_Outer)).getText();
		 
		 //APP_LOGS.debug("Base is :"+Base);
		 String Breadcumb = driver.findElement(By.xpath(OR.getProperty("FOOTER_BREADCRUMB_LINK"))).getText();
		// System.out.println("Breadcumb is :"+Breadcumb);
		 //Assering the page by comparing the breadcumb link with  that of base linktext
		 APP_LOGS.debug("Asserting the page");
		 Assert.assertTrue(Base.toLowerCase().equalsIgnoreCase(Breadcumb));
		 String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
	
		 Prevno=BasicCommonTestUtils.removeCharacters(Prevno, "0123456789");
		 Integer Prevno_numeric = Integer.parseInt(Prevno);
	   APP_LOGS.debug("*****The Searched Item number is:"+Prevno_numeric);
	   //APP_LOGS.debug(" ****** getting the value of the count for the Search By Category");
		 WebElement we =driver.findElement(By.id("cat_filter"));
		 List<WebElement> pp = we.findElements(By.className("padl10"));
	     int size = pp.size();
	     Assert.assertEquals(size, size1 ,"category count is not matched");
	   //APP_LOGS.debug("No of Category is:"+size);
	     int totalsearch=0;
	     for (WebElement option : pp)
	     {  
	    //APP_LOGS.debug(option.findElement(By.tagName("a")).getText());
	     String input = option.findElement(By.tagName("a")).getText();
	     int start = input.lastIndexOf("(");
	     int end = input.lastIndexOf(")");
	     String getval = input.substring(start+1,end);
	     //System.out.println(getval);
	     int value = Integer.parseInt(getval);
	     totalsearch+=value;
	    //option.findElement(By.tagName("a")).click();    	
	  }
	  
	  //verifying the Total Count Value by category and the Search Count value
	 APP_LOGS.debug("Total count for the Search By Category"+ totalsearch);
	 // APP_LOGS.debug(" ****** verifying the count for the Search By Category");
	   Assert.assertTrue(Prevno_numeric.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
	 
	  APP_LOGS.debug("******verified the count for the Search By Category with the search count");
	  
	   //for the By brand Search
	  // APP_LOGS.debug("verifying the total By Brand search items by count");
	   WebElement brand =driver.findElement(By.id("brands_filter"));
	   WebElement brand2 = brand.findElement(By.className("filter_scroll"));
		List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
	    int brandlistsize = brandlist.size();
	    int totalBrandsearch=0;
	  //getting the values for the count of brands.
	   for (WebElement option : brandlist)
	   {   //System.out.println(option.findElement(By.tagName("label")).getText());
	     String input = option.findElement(By.tagName("label")).getText();
	     int start = input.lastIndexOf("(");
	     int end = input.lastIndexOf(")");
	     String getval = input.substring(start+1,end);
	  //  APP_LOGS.debug(getval);
	     int value = Integer.parseInt(getval);
	     totalBrandsearch+=value;
	  
	  //option.findElement(By.tagName("a")).click();
	      	    	
	  }
	//  APP_LOGS.debug("Total count for the Search By BRAND "+ totalBrandsearch);
	 
	  //verifying the Total Count Value Search By BRAND and the Search Count value
	  APP_LOGS.debug("******Verifying the count for the Search By BRAND");
	   Assert.assertTrue(Prevno_numeric.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
	  
	//  APP_LOGS.debug("******Verified the count for the Search By BRAND with the search count");
	
		
		
		
	}

}
