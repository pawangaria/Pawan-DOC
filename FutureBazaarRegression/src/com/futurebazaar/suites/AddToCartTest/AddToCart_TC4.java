package com.futurebazaar.suites.AddToCartTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
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
import com.futurebazaar.common.methods.Itemcleanup;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.BasicCommonTestUtils;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

 public class AddToCart_TC4  extends AddToCartSuitebase{
	
	String runmodes[]=null;
		static int count=-1;
		static boolean skip=false;
		static boolean fail=false;
		//boolean pass=false;
		static boolean istestpass=true;
		//public static WebDriver driver = null;
		
		
		@BeforeTest
		public void checkTestSkip()
		{
			if(!TestUtil.isTestCaseRunnable(suiteAddToCartXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				APP_LOGS.debug("skipping test AddToCart_TC4 (priceVerification In AddtoCart) as the runmode is NO");
				throw new SkipException("Skipping this testcase AddToCart_TC4 (priceVerification In AddtoCart) as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteAddToCartXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void priceVerificationINAddtoCart () throws InterruptedException
		{
			try{
			count++;
			//test the Runmode of the current Dataset
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				APP_LOGS.debug("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//Testing code which can be Selenium


			Map<String, String> Offerprice_all=new HashMap<String, String>(); 
			
	 		Map<String, String> Itemname_all=new HashMap<String, String>();
	 		
	 		Map<String, String> Markerprice_all=new HashMap<String, String>();
		   //Doing Fresh start from Homepage
		 
		   APP_LOGS.debug("Item remove method called to remove items from the cart if present.");
		      Itemcleanup.itemCleanup();
		      APP_LOGS.debug("test case started for the item price verification.");
		      driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
		      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
				 
		   APP_LOGS.debug("Navigating to Add to cart by clicking on 'Add to Cart' from HP");
		   String item_name_hp = driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_HP_XPATH"))).getText();
		   String offer_price_hp=driver.findElement(By.xpath(OR.getProperty("FIRST_ITEM_OFFER_PRICE_HP"))).getText();
		   String market_price_hp=driver.findElement(By.xpath(OR.getProperty("FIRST_ITEM_MARKET_PRCIE_HP"))).getText();
		   //This section of code is to add name and all price from Homepage to list   
		  driver.findElement(By.xpath(OR.getProperty("ADDTOCART_BUTTON_FIRST_XPATH"))).click();
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			
			
		   APP_LOGS.debug(item_name_hp);
		   APP_LOGS.debug(offer_price_hp);
		   APP_LOGS.debug(market_price_hp);
		   Itemname_all.put("item_name_hp",item_name_hp);
		   String offer_price_hp1  =BasicCommonTestUtils.removeCharacters(offer_price_hp, "0123456789");
		   String market_price_hp1 =BasicCommonTestUtils.removeCharacters(market_price_hp, "0123456789");
		   Offerprice_all.put("offer_price_hp1",offer_price_hp1);
		   Markerprice_all.put("market_price_hp1",market_price_hp1);
		   APP_LOGS.debug("Going to home page from Add to cart page");
 
		   //Now clicking on the "Add to Cart" button from homepage   
		   driver.findElement(By.linkText(OR.getProperty("ADD_MORE_ITEM_LINK"))).click();
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			
		   //Now clicking on the cart Dropdown from cart page 
		   Actions builder = new Actions(driver);
		   WebElement cartlink= driver.findElement(By.cssSelector(OR.getProperty("MY_SHOPPING_SACK_CSS")));
		   builder.clickAndHold(cartlink).perform();
		   
		    //NOW TAKING ALL THE VALUE FROM  CART DROPDOWN
		   APP_LOGS.debug("Taking the value from the Cart Drop from Home page");
		 	String item_name_cart_dd = driver.findElement(By.xpath(OR.getProperty("ITEM_NAME_CARTDD_XPATH"))).getText();
			String offer_price_cart_dd=driver.findElement(By.xpath(OR.getProperty("RS_UNDER_ITEM_NAME_CARTDD_XPATH"))).getText();
			String payable_price_cart_dd=driver.findElement(By.xpath(OR.getProperty("TOTAL_PAYABLE_CARTDD_XPATH"))).getText();
			String  offer_price_cart_dd1 =  BasicCommonTestUtils.removeCharacters(offer_price_cart_dd, "0123456789");
			String  payable_price_cart_dd1 =  BasicCommonTestUtils.removeCharacters(payable_price_cart_dd, "0123456789");
		
			//For 1 qty ,the offer price and payble price is same.
			Offerprice_all.put("payable_price_cart_dd1",payable_price_cart_dd1);
			Itemname_all.put("item_name_cart_dd",item_name_cart_dd);
			
			//Clicking on the "View My Cart" from cart drop down
			 APP_LOGS.debug("Clicking Cart Item link to add to Cart page");
			driver.findElement(By.xpath(OR.getProperty("VIEW_MY_CART_XAPTH"))).click();
		 	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
				  
			String 	item_name_cpage = driver.findElement(By.xpath(OR.getProperty("ITEM_NAME_CART_PAGE_XPATH"))).getText();	  
			String 	item_offer_price_cpage = driver.findElement(By.xpath(OR.getProperty("OFFER_PRICE_CARTPAGE_ITEM_XPATH"))).getText();  
			String 	item_market_price_cpage = driver.findElement(By.xpath(OR.getProperty("MARKET_PRICE_CARTPAGE_XAPTH"))).getText();	      
			//APP_LOGS.debug(item_market_price_cpage);
			String 	item_amt_price_cpage = driver.findElement(By.xpath(OR.getProperty("ITEM_AMOUNT_XPATH"))).getText();   
			String 	item_payable_price_cpage = driver.findElement(By.xpath(OR.getProperty("PAYBLE_AMT_CARTPAGE_XPATH"))).getText();   
			//APP_LOGS.debug(item_payable_price_cpage);
			String 	item_offer_price_odsum_cpage = driver.findElement(By.xpath(OR.getProperty("OFFER_PRICE_ODSUMMERY_XPATH"))).getText();
	      //  String 	item_market_price_odsum_cpage = driver.findElement(By.xpath(OR.getProperty("MAREKET_PRICE_ODSUMMERY_XPATH"))).getText();
			String 	item_payable_price_odsum_cpage = driver.findElement(By.xpath(OR.getProperty("PAYBLE_ODSUMMERY_XPATH"))).getText();
			//APP_LOGS.debug(item_payable_price_odsum_cpage);
			APP_LOGS.debug("Get values from the cart page");
			
			// String  item_market_price_odsum_cpage1=BasicCommonTestUtils.removeCharacters(item_market_price_odsum_cpage, "0123456789");
			 String  item_market_price_cpage1=BasicCommonTestUtils.removeCharacters(item_market_price_cpage, "0123456789");
			 String  item_offer_price_odsum_cpage1=BasicCommonTestUtils.removeCharacters(item_offer_price_odsum_cpage, "0123456789");
			 String  item_offer_price_cpage1=BasicCommonTestUtils.removeCharacters(item_offer_price_cpage, "0123456789");
			 String  item_amt_price_cpage1=BasicCommonTestUtils.removeCharacters(item_amt_price_cpage, "0123456789");
			 String  item_payable_price_cpage1=BasicCommonTestUtils.removeCharacters(item_payable_price_cpage, "0123456789");
			 String  item_payable_price_odsum_cpage1=BasicCommonTestUtils.removeCharacters(item_payable_price_odsum_cpage, "0123456789");
			 
			//Adding All from Cart page into list
	         Itemname_all.put("item_name_cpage",item_name_cpage);
		     Offerprice_all.put("item_amt_price_cpage1",item_amt_price_cpage1);
		     Offerprice_all.put("item_offer_price_cpage1",item_offer_price_cpage1);
			 Offerprice_all.put("item_offer_price_odsum_cpage1",item_offer_price_odsum_cpage1);
		     Offerprice_all.put("item_payable_price_cpage1",item_payable_price_cpage1);
			 Offerprice_all.put("item_payable_price_odsum_cpage1",item_payable_price_odsum_cpage1);
			 Markerprice_all.put("item_market_price_cpage1",item_market_price_cpage1);
			// Markerprice_all.put("item_market_price_odsum_cpage1",item_market_price_odsum_cpage1);
			
			 // verifying the valus by comparing valus from all the pages.
			APP_LOGS.debug("Market price Value"+BasicCommonTestUtils.comparisonmap(Markerprice_all,market_price_hp1));
			APP_LOGS.debug("Offer price Value"+BasicCommonTestUtils.comparisonmap(Offerprice_all,offer_price_hp1));
			APP_LOGS.debug("Item Name Value"+BasicCommonTestUtils.comparisonmap(Itemname_all,item_name_hp));
		
			
			    APP_LOGS.debug("*****AddToCart_TC4 (priceVerification In AddtoCart) test completed ******* ");
	        
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("####### Execution for the AddToCart_TC4 (priceVerification In AddtoCart) is falied ######");
				
				APP_LOGS.error("ERROR"+t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;

			}
			
					}
		
		
		
		
		
		@AfterMethod
		public void testdataReporter()
		{//APP_LOGS.debug("value of skip"+count+skip);
		//APP_LOGS.debug("test case fail"+count+fail);
		//APP_LOGS.debug("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteAddToCartXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteAddToCartXls, "Test Cases", TestUtil.getRowNum(suiteAddToCartXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteAddToCartXls, "Test Cases", TestUtil.getRowNum(suiteAddToCartXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    /*
		@DataProvider
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suiteAxls,this.getClass().getSimpleName());
		}*/
	
	

}
