package com.futurebazaar.suites.GiftVouchersTest;


import java.util.List;
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
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.LowerMenuInOutLink;
import com.futurebazaar.common.methods.MenuInOutLink;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.BasicCommonTestUtils;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;

public class GiftVouchersTest_TC1 extends GiftVouchersSuiteBase{
	
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
			if(!TestUtil.isTestCaseRunnable(suiteGiftVoustesXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suiteGiftVoustesXls, "Test Cases",TestUtil.getRowNum(suiteGiftVoustesXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("Skipping test case GiftVouchersTest_TC1(GiftVouchers Test outer DD  Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase GiftVouchersTest_TC1(GiftVouchers Test outer DD  Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteGiftVoustesXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void GiftVoucherTestLink_DD() throws InterruptedException
		{
			//This method is used to click and verify the Fashion link on homepage			
			try{
			count++;
			//test the Runmode of the current Dataset if present.
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				APP_LOGS.debug("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
						 
		   //"****** Starting the Test for PopularTest_TC1(PopularTest outer link Check ******** 
						 
			 
			APP_LOGS.debug("****** Starting the Test for GiftVouchersTest_TC1(GiftVouchers outer link Check *******");
			
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
			// System.out.println("ok");
		     Actions builder = new Actions(driver);
		     WebElement cartlink= driver.findElement(By.xpath(OR.getProperty("GIFTVOUCHERS_MAIN_LINK_XPATH")));
		     builder.clickAndHold(cartlink).build().perform();
		     APP_LOGS.debug("Hovering cursor over GiftVouchers Drop down");

		 	// WebElement kk = driver.findElement(By.xpath("//div[4]/div/ul/li[6]/div"));
		 
		 
		 	 //WebElement kk1 = driver.findElement(By.className("t2_l2_wrapper w190"));
		 	// WebElement kk = kk.findElement(By.className("t2_l2_inner"));
		 	// WebElement  kk3 = kk2.findElement(By.className("t2_l2_inner_ul"));
			// List<WebElement> kk4 = kk1.findElements(By.tagName("a"));
			 //System.out.println(kk4.size());
//			int size1 = kk4.size();
//			builder.clickAndHold(cartlink).build().perform();
			// for(WebElement opt: kk4)
			 {
			//	 System.out.println(opt.findElement(By.tagName("a")).getText());
			// }
			 driver.findElement(By.xpath(OR.getProperty("GIFTVOUCHERS_MAIN_LINK_XPATH"))).click();
			 
			 String Prevno = driver.findElement(By.xpath(OR.getProperty("SEARCH_ITEM_NO"))).getText();
			 APP_LOGS.debug("prev no"+Prevno);
			 Prevno=BasicCommonTestUtils.removeCharacters(Prevno, "0123456789");
			 Integer Prevno_numeric = Integer.parseInt(Prevno);
			 APP_LOGS.debug("*****The Searched Item number is:"+Prevno_numeric);
		     
		  
		  
			 APP_LOGS.debug(" ****** getting the value of the count for the Search By Category");
			 WebElement we =driver.findElement(By.id("cat_filter"));
			// WebElement ee =we.findElement(By.className("padb10 filter_scroll"));
			 List<WebElement> pp = we.findElements(By.className("padl10"));
		     int size = pp.size();
		     // Assert.assertEquals(size, size1 ,"category count is not matched");
		     APP_LOGS.debug("No of Category is:"+size);
		     int totalsearch=0;
		     for (WebElement option : pp)
		     {  
		    	 //APP_LOGS.debug(option.findElement(By.tagName("a")).getText());
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
		     APP_LOGS.debug("Total count for the Search By Category"+ totalsearch);
		     APP_LOGS.debug(" verifying the count for the Search By Category");
		   Assert.assertTrue(Prevno_numeric.equals(totalsearch),"Total Count Value by category and the Search Count value does not match");
		 
		   APP_LOGS.debug("verified the count for the Search By Category with the search count");
		  
		  //for the By brand Search
		   APP_LOGS.debug("verifying the total By Brand search items by count");
		   WebElement brand =driver.findElement(By.id("brands_filter"));
		   WebElement brand2 = brand.findElement(By.className("filter_scroll"));
			List<WebElement> brandlist = brand2.findElements(By.tagName("li"));
		   int brandlistsize = brandlist.size();
		   APP_LOGS.debug("Brannd size"+brandlistsize);
		   
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
		   APP_LOGS.debug("Total count for the Search By BRAND "+ totalBrandsearch);
		 
		  //verifying the Total Count Value Search By BRAND and the Search Count value
		   APP_LOGS.debug("verifying the count for the Search By BRAND");
		   Assert.assertTrue(Prevno_numeric.equals(totalBrandsearch),"Total Count Value Search By BRAND and the Search Count value does not match");
		  
		   APP_LOGS.debug("verified the count for the Search By BRAND with the search count");}
		/*	    
		   System.out.println(" ***** Verifying the By Category option Collapse***");
		   System.out.println(driver.findElement(By.id("cat")).getText());
		   Assert.assertTrue(driver.findElement(By.id("cat")).getText().equalsIgnoreCase("BY CATEGorY"),"Category Text is not matching with the value");
		   System.out.println("Collaspse the By Category list");
		   driver.findElement(By.id("cat")).click();
		   System.out.println("****** Collaspse the By Category list Verified");
		  
		   
		   System.out.println(" ****** Verifying the By Brand  option Collapse");
		   Assert.assertTrue(driver.findElement(By.id("brands")).getText().equalsIgnoreCase("By Brand"),"By Brand Text is not matching with the value");
		   System.out.println("Collaspse the By Category list");
		   driver.findElement(By.id("brands")).click();
		   System.out.println("****** Collaspse the By Brand list"); 
		 	 
		    
		    
		    System.out.println("coming to this");
				
			*/	
			
				APP_LOGS.debug("****** Completed the Test for GiftVouchersTest_TC1(GiftVouchers Test outer link Check *******");
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the GiftVouchersTest_TC1(GiftVouchers Test outer link Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
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
				TestUtil.reportDataSetResult(suiteGiftVoustesXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suiteGiftVoustesXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suiteGiftVoustesXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed of fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suiteGiftVoustesXls, "Test Cases", TestUtil.getRowNum(suiteGiftVoustesXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suiteGiftVoustesXls, "Test Cases", TestUtil.getRowNum(suiteGiftVoustesXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
}
