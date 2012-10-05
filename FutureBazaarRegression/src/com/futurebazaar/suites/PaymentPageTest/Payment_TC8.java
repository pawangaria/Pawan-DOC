package com.futurebazaar.suites.PaymentPageTest;

import java.awt.Window;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.futurebazaar.base.TestBase;
import com.futurebazaar.common.methods.CreditCard;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;
import com.futurebazaar.testing.util.ErrorUtil;
import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;

public class Payment_TC8 extends PaymentSuitebase{
	public int noOFbanks;
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
			if(!TestUtil.isTestCaseRunnable(suitePaymentPageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
				//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
			{
				TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases",TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()),"Skip");
				APP_LOGS.debug("skipping test Payment_TC2 (NetBanking) as the runmode is NO");
				throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : Payment_TC2 (netbanking)");
			
			}
			//Load the RunModes of the Test
			runmodes=TestUtil.getDataSetRunmodes(suitePaymentPageXls, this.getClass().getSimpleName());
			
		}
		
		//@Test(dataProvider="getTestData")
		//data is provided by the XLS files 
		// all the columns values should be passed to the TestCase function as parameters.
		
		//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
		
		@Test(dataProvider="getTestData")
		public void netbanking() throws InterruptedException
		{
			try{
			count++;
			//test the Runmode of the current Dataset
			
			if(!runmodes[count].equalsIgnoreCase("Y"))
			{ 
				skip=true;
				System.out.println("skipping a"+count);
				throw new SkipException("Run mode for the test set data is Set to NO"+count); 
			}
			//**************************
			//testing code which can be Selenium
		
			//***********************************************
			//This method is used to verify the NetBanking page option 
			
			
			//Productpurchase.purchaseItem(CONFIG.getProperty("testuser"),CONFIG.getProperty("testuserpassword"),CONFIG.getProperty("SearchItem"));
			APP_LOGS.debug("********Starting the Testcase (Payment_TC8)for  Net banking option");
		   				
		   				APP_LOGS.debug("****verifying the Net banking OPtion ******");
		   				//this method clicks on the every link on the payment page and verify each link.
		   				WebElement select = driver.findElement(By.className("payment_options"));
		   			    List<WebElement> allOptions = select.findElements(By.tagName("li"));
		   			    for (WebElement option : allOptions) {
		   			    	
		   			    	if(option.getText().equalsIgnoreCase("Pay by Net Banking"))
		   			    			{
		   			    		option.click();
		   			    		Thread.sleep(2000);
		   			    		System.out.println("in the net banking option");
		   			    		//taking the count of the Bank names
		   			    		
		   			    			WebElement banklisttake = driver.findElement(By.id("bank"));
		   			    			List<WebElement> bank = banklisttake.findElements(By.tagName("option"));
		   			    			System.out.println(bank.size());
		   			    			for(WebElement banklist : bank)
		   			    			{
		   			    				//System.out.println(banklist.getText());
		   			    				noOFbanks++;
		   			    			}
		   			    			// the total no of banks count in the netBanking list are  " 37 "
		   			    			// so checking the list with the default list
		   			    			APP_LOGS.debug("Verifying the no of count of the banks in the list, it Should be 34");
		   			    			
		   				            Assert.assertEquals(noOFbanks, 34);
		   				          
		   			    			//selecting the bank at the index of 4
		   			    			Select selectmonth = new Select(getIDobject("NETBANKING_NAME_ID"));
		   				            
		   				            selectmonth.selectByIndex(4);
		   				            Assert.assertTrue(driver.getPageSource().contains("We will redirect you to the bank or payment partner"));
		   				            APP_LOGS.debug("*****Completed ( Payment_TC8 )Net banking page is verified");
		   				       
		   			    			}	
		   			}
		   				//***********************************************
			}catch(Throwable t)
			{
				APP_LOGS.debug("*******Execution for the Payment_TC8(for Net banking) is falied*****");
				APP_LOGS.error(t.getMessage());
				ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
			    fail=true;
			    return;
			}
			
			
		}
		
		
		
		
		@AfterMethod
		public void testdataReporter()
		{//System.out.println("value of skip"+count+skip);
		//System.out.println("test case fail"+count+fail);
		//System.out.println("test case a"+count);
			if(skip)
				TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Skip");
			else if(fail)
			{
		    	TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Fail");
		        istestpass=false; //checking for the TestCase is failed or passed
			}
		        else
		       TestUtil.reportDataSetResult(suitePaymentPageXls,this.getClass().getSimpleName(),count+2,"Pass");
		   	   	        
			skip=false;
		   	fail=false;
		    
		}
	    @AfterTest
	    //Writing the final result for the TestCase whether it is passed or fail.
	    public void testReporter()
	    {
	    	if(istestpass)
	    	{
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "PASS");
	    	}else
	    		TestUtil.reportDataSetResult(suitePaymentPageXls, "Test Cases", TestUtil.getRowNum(suitePaymentPageXls,this.getClass().getSimpleName()), "Fail");
	    	
	    }
	    
	    
		
		//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
	    @DataProvider
	    public Object[][] getTestData()
		{
		     
			return TestUtil.getData(suitePaymentPageXls,this.getClass().getSimpleName());
		}
	
	

}
