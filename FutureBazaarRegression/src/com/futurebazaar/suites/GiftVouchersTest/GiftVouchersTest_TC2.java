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

public class GiftVouchersTest_TC2 extends GiftVouchersSuiteBase{
	
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
				APP_LOGS.debug("Skipping test case GiftVouchersTest_TC2(GiftVouchers Test Inner DD  Check) as the runmode is NO");
				throw new SkipException("Skipping this testcase GiftVouchersTest_TC2(GiftVouchers Test Inner DD  Check) as the runmode is NO for this testcase");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suiteGiftVoustesXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test
		public void GiftVoucherInnerLink_DD() throws InterruptedException
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
						 
			 
			APP_LOGS.debug("****** Starting the Test for GiftVouchersTest_TC1(GiftVouchers Inner link Check *******");
			

			Actions builder = new Actions(driver);
			WebElement cartlink= driver.findElement(By.xpath(OR.getProperty("GIFTVOUCHERS_MAIN_LINK_XPATH")));
			
		    builder.clickAndHold(cartlink).build().perform();
		    APP_LOGS.debug("Hovering cursor over GiftVouchers Drop down");
		    WebElement cartlink1 =driver.findElement(By.xpath("html/body/div[4]/div/ul/li[6]/div"));
			//WebElement kk1 = driver.findElement(By.className("t2_l2_wrapper w190"));
		 	WebElement kk = cartlink1.findElement(By.className("t2_l2_inner"));
		 	//WebElement  kk3 = kk.findElement(By.className("t2_l2_inner_ul"));
			List<WebElement> kk4 = kk.findElements(By.tagName("li"));
			APP_LOGS.debug("The Size for the list is "+kk4.size());
		    int size1 = kk4.size();
			builder.clickAndHold(cartlink).build().perform();
		   
		  for(int i=1;i<=size1;i++)
		  {
			  APP_LOGS.debug("Clicking on the link_number:"+i);
		 
		 // Xpath for the Images One by one from the for loop.
	     String Xpath="//div[4]/div/ul/li[6]/div/div/ul/li["+i+"]/a/img";
	   
		 HL_inner(Xpath);	
		}
					
				APP_LOGS.debug("****** Completed the Test for GiftVouchersTest_TC2(GiftVouchers Test Inner DD link Check *******");
			
			}catch(Throwable t)
			{
				APP_LOGS.debug("******Execution of the GiftVouchersTest_TC2(GiftVouchers Test Inner DD link Check) is falied******");
				APP_LOGS.error("ERROR "+ t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
		}
			
public static void HL_inner(String Xpath) throws InterruptedException
{     
	
	 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
     Actions builder = new Actions(driver);
  
     WebElement cartlink= driver.findElement(By.xpath("//li[6]/a/div"));

     builder.clickAndHold(cartlink).build().perform();	
     WebElement cartlink1= cartlink.findElement(By.xpath(Xpath));
     //String name= cartlink1.getText();
 
   
    // builder.clickAndHold(cartlink1).build().perform();
  
     //System.out.println("Clicking on :" +name);

     cartlink= driver.findElement(By.xpath("//li[6]/a/div"));
     builder.clickAndHold(cartlink).build().perform();
     cartlink1.click();
                
    WebElement we =driver.findElement(By.id("cat_filter"));
    WebElement Basecategory = we.findElement(By.className("padl0"));
  
    
    //Asserting base category name
    APP_LOGS.debug("Base Category is:"+Basecategory.getText());
    APP_LOGS.debug("Asserting the base category");
    String base =Basecategory.getText().toString();
    Assert.assertEquals(base, "Gift Vouchers","Base category doesn't match");
    APP_LOGS.debug("Base category is verified ");
   
   
  
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
