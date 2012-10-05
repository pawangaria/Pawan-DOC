package com.futurebazaar.suites.AddToCartTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

 public class AddToCart_TC2  extends AddToCartSuitebase{
	
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
				APP_LOGS.debug("skipping test AddToCart_TC2(Search Page to CartItem) as the runmode is NO");
				throw new SkipException("Skipping this testcase AddToCart_TC2(Search Page to CartItem) as the runmode is NO for this Testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteAddToCartXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void UpdateINAddToCart() throws InterruptedException
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
			//testing code which can be Selenium
			 //Removing the items in the cart by calling the remove item method.
		      APP_LOGS.debug("Item remove method called to remove items from the cart");
		      Itemcleanup.itemCleanup();
		

			//Clicking on HOme Page logo
			driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
		  
		    //this is the item clean up with out any user login
			/*driver.findElement(By.id("id_q")).sendKeys("mobile");
			driver.findElement(By.id("go")).click();
			driver.findElement(By.xpath("//li[@id='grid_page_1']/div/h3/a")).click();
			driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//td/div/button")).click();
			driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
			*/
		  
		    
			   
			   APP_LOGS.debug("Navigating to Add to cart by clicking on 'Add to Cart' from HP");	
		       driver.findElement(By.xpath(OR.getProperty("ADDTOCART_BUTTON_FIRST_XPATH"))).click();
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
		       APP_LOGS.debug(" 'This is  cart page'  is verified");
		       
		       //Getting Amount value from cart page
		        String Amount_before = driver.findElement(By.xpath(OR.getProperty("ITEM_AMOUNT_XPATH"))).getText();
		        String amount_str  = BasicCommonTestUtils.removeCharacters(Amount_before,"0123456789");	        
		        Integer amount_bf = Integer.parseInt(amount_str);
		        APP_LOGS.debug("Numeric value of amount before is"+ amount_bf);	                
		     
		        APP_LOGS.debug("updating the value of the items in the cart");	  
		      //  System.out.println(driver.findElement(By.xpath(OR.getProperty("QTY_TEXT_BOX_XPATH"))).isDisplayed());
		        //System.out.println("valueee"+driver.findElement(By.xpath(OR.getProperty("QTY_TEXT_BOX_XPATH"))).getText());
		       //WebElement QTYcombo =driver.findElement(By.xpath(OR.getProperty("QTY_TEXT_BOX_XPATH")));
		       Select select = new Select(driver.findElement(By.xpath(OR.getProperty("QTY_TEXT_BOX_XPATH"))));
		       select.selectByValue(OR.getProperty("QTY_UPDATE"));
		      // driver.findElement(By.xpath(OR.getProperty("QTY_TEXT_BOX_XPATH"))).sendKeys(OR.getProperty("QTY_UPDATE"));
		       //driver.findElement(By.cssSelector(OR.getProperty("UPDATE_LINK_CSS"))).click();
		       APP_LOGS.debug("Page is getting refreshed with the Value");
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));	
			   
		       APP_LOGS.debug("Getting the item amount after clicking on update");
		       
		       String Amount_after = driver.findElement(By.xpath(OR.getProperty("ITEM_AMOUNT_XPATH"))).getText();
		       amount_str  = BasicCommonTestUtils.removeCharacters(Amount_after,"0123456789");
		       Integer amount_aft = Integer.parseInt(amount_str);
		   	   APP_LOGS.debug("Numeric value of Amount after update is "+amount_aft);
		   	   
		   	   //APP_LOGS.debug();
		       Integer Qty_nu =Integer.parseInt(OR.getProperty("QTY_UPDATE"));
		         
	           	    	    
		        if(amount_aft == (Qty_nu*amount_bf))
		         {
		        	Assert.assertTrue(true);
		         }
		        else
		        {
		        	Assert.assertTrue(false);
		        }
		        APP_LOGS.debug("update option is verified in item cart page");
		        driver.navigate().back();
			
			//Going to homepage for 'add to cart'  from SRP
			
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
		       APP_LOGS.debug("***********Entering Keyword in search Box**************");
		       driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));
		       driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
	   	       APP_LOGS.debug("Search Button is clicked");
	           APP_LOGS.debug("We are on SRP"); 
			
			   //We are on Search Result Page
		   	    APP_LOGS.debug("Going to Add to cart by clikcing on 'ADD TO CART' button");
		       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SRP_FIRSTPRODUCT_IMG_WAIT_XPATH"))));
			    driver.findElement(By.xpath(OR.getProperty("ADDTOCART_BUTTON_FIRST_XPATH"))).click();
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			
			    //Clicking on 'Add more items link'    
			    APP_LOGS.debug("verifying the Add more item link on the cart page");
			    driver.findElement(By.linkText(OR.getProperty("ADD_MORE_ITEM_LINK"))).click();
			    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));
			    
			
			    APP_LOGS.debug("GOING TO CART PAGE BY CLICKING ON CART LOGO FROM HP");
			    String item_no= driver.findElement(By.cssSelector(OR.getProperty("MY_SHOPPING_SACK_CSS"))).getText();
			   driver.findElement(By.xpath(OR.getProperty("CART_LOGO_XPATH"))).click();
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("REVIEWCART_TAB_VERIFICATION"))));
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("CART_VERIFICATION_XPATH"))));
			     
			     // String item_no= driver.findElement(By.cssSelector(OR.getProperty("MY_SHOPPING_SACK_CSS"))).getText();
			      APP_LOGS.debug(item_no);
			      APP_LOGS.debug("item cart value should be 2 ");
			      String no_itm =BasicCommonTestUtils.removeCharacters(item_no,"0123456789"); 	   
			      Integer no = Integer.parseInt(no_itm);
			      //Comparing item number in the cart 
			      if(no == 2)
			      Assert.assertTrue(true,"Assertion is false  in if part(if failed)");
			      else 
			      Assert.assertTrue(false,"Assertion is false in else part(item number mismatch)");
			      APP_LOGS.debug("The no of items in the cart is verifed");
	   	      
			      //Removing the items in the cart by calling the remove item method.
			      APP_LOGS.debug("Item remove method called to remove items from the cart");
			      Itemcleanup.itemCleanup();
		   
			    APP_LOGS.debug("*****AddToCart_TC2 (Search Page/Home page to CartItem) test completed ******* ");
	        
		    	      	
			//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("####### Execution Failed for the AddToCart_TC2 (Search Page to CartItem ) is falied ######");
				
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
