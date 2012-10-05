package com.futurebazaar.suites.PDPtest;


	import org.testng.annotations.Test;
	import org.testng.AssertJUnit;
	import org.testng.annotations.Test;

	import java.awt.Window;
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
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;


	import com.futurebazaar.base.TestBase;
	import com.futurebazaar.common.methods.CreditCard;
	import com.futurebazaar.common.methods.DebitCard;
	import com.futurebazaar.common.methods.PaymentPage;
	import com.futurebazaar.common.methods.Productpurchase;
	import com.futurebazaar.testing.util.ErrorUtil;
	import com.futurebazaar.testing.util.TestUtil;
import com.opera.core.systems.scope.protos.WmProtos.WindowInfo;


	public class ProductDescPage_TC3  extends ProductDesPageSuiteBase{
		
		String runmodes[]=null;
			static int count=-1;
			static boolean skip=false;
			static boolean fail=false;
			//boolean pass=false;
			static boolean istestpass=true;
			//public static int count=0;
		    public static int box=1;
			
			//public static WebDriver driver = null;
			
			
			@BeforeTest
			public void checkTestSkip()
			{
				if(!TestUtil.isTestCaseRunnable(suiteProductDescPageXls,this.getClass().getSimpleName()))//suites XlSX name is passed by the object from base class. and name of the TestCase.
					//Instead of passing TestCase name,using this "this.getClass().getSimpleName()" to get the name of the class as the name of the class is the TestCase name.
				{   
				    
				    //writing the Skip in the Xls file for the test case
				  
			    	
			    	TestUtil.reportDataSetResult(suiteProductDescPageXls, "Test Cases",TestUtil.getRowNum(suiteProductDescPageXls,this.getClass().getSimpleName()),"Skip");
			    	
					APP_LOGS.debug("skipping test ProductDescPage_TC3 ( Product Brand Link check) as the runmode is NO");
					throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : ProductDescPage_TC3 (Product Brand Link Check)");
				
				}
				//Load the RunModes of the Test
				runmodes=TestUtil.getDataSetRunmodes(suiteProductDescPageXls, this.getClass().getSimpleName());
				
			}
			
			//@Test(dataProvider="getTestData")
			//data is provided by the XLS files 
			// all the columns values should be passed to the TestCase function as parameters.
			
			//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
			
			@Test(dataProvider="getTestData")
			public void BrandNamePDPCheck() throws InterruptedException
			{
				try{
				count++;
				//test the Runmode of the current Dataset
				
				if(!runmodes[count].equalsIgnoreCase("Y"))
				{ 
					skip=true;
					//System.out.println("skipping a"+count);
					throw new SkipException("Run mode for the test set data is Set to NO"+count); 
				}
				//**************************
				
				//***********************************************
				//this method is used to verify the Quick View on the Search Result Page Option.  
				APP_LOGS.debug("****** Starting the Test case ProductDescPage_TC3 (Product Brand Link check)");
				   
				APP_LOGS.debug("Opening the home page by clicking the Futurebazaar logo on home Page");
				driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
				
				//wait for the HOme Page to Load.
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
				
				//Opening the Product details Page.
				driver.findElement(By.xpath(OR.getProperty("HOME_FIRST_PRODUCT_LINK_XPATH"))).click();
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				
				APP_LOGS.debug("Verifying the Brand value is Link or not and Displayed");
				Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PRODUCT_BRAND_LINK_XPATH"))).isDisplayed(),"the brand link is not displayed on PDP page");
				APP_LOGS.debug("clicking on the Product brand link");
				String brandnamePDP= driver.findElement(By.xpath(OR.getProperty("PRODUCT_BRAND_LINK_XPATH"))).getText();
				driver.findElement(By.xpath(OR.getProperty("PRODUCT_BRAND_LINK_XPATH"))).click();
				
				APP_LOGS.debug("Verifying the search result page for the brand");
				String searchPDP=driver.findElement(By.xpath(OR.getProperty("SEARCH_BRAND_TEXT_VERIFICATION_XPATH"))).getText();
				//System.out.println("search PDP"+searchPDP);
			    String searchresult_link =driver.findElement(By.id(OR.getProperty("SEARCH_RESULT_PAGE_VERFICATION_ID"))).getText();
				//System.out.println(searchresult_link);
				APP_LOGS.debug("Verifying the Brand Name on PDP and after clicking the link and on search result page");
				Assert.assertTrue(searchresult_link.contains(searchPDP),"Product brand from PDP and the search result does not match ");
				
			    
				//*****************************************
				APP_LOGS.debug("******Brand option on PDP test Successfull");
				
			    APP_LOGS.debug("Going back to the PDP page for the TEsting of the Quantity drop down");
				driver.navigate().back();
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				Assert.assertTrue(driver.getPageSource().contains("Add Quantity"),"Add Quantity text is not displayed on PDP");				
				APP_LOGS.debug("Sealecting the value (2) from the Quantity drop down");
                Select select = new Select(driver.findElement(By.name(OR.getProperty("QUANTITY_DROPDOWN_NAME"))));
				select.selectByValue("2");
				APP_LOGS.debug("value selected in drop down");
				//System.out.println(select.getAllSelectedOptions());
				//System.out.println("selected value");
				//System.out.println(driver.findElement(By.name(OR.getProperty("QUANTITY_DROPDOWN_NAME"))).getText());
				
				//***********************************************
				APP_LOGS.debug("verifying the Availability on PDP");
				Assert.assertTrue(driver.getPageSource().contains("Availability"),"Availability text is not displayed on PDP");
				APP_LOGS.debug("*******Completed TEST CASE for the ProductDescPage_TC3 (Quick View On Search Result Page) ****");
				
				
				}catch(Throwable t)
				{
					APP_LOGS.debug("*******Execution for the ProductDescPage_TC3 (Product Brand Link Check) is falied*****");
					
					APP_LOGS.error("ERROR :" +t.getMessage());
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				    fail=true;
				    return;
				}
				
				
			}
			
			 		    
			
			
			
			
			@AfterMethod
			public void testdataReporter()
			{//System.out.println("value of skip"+count+skip);
			// System.out.println("test case fail"+count+fail);
			//System.out.println("test case a"+count);
				if(skip)
					TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Skip");
				else if(fail)
				{
			    	TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Fail");
			        istestpass=false; //checking for the TestCase is failed or passed
				}
			        else
			       TestUtil.reportDataSetResult(suiteProductDescPageXls,this.getClass().getSimpleName(),count+2,"Pass");
			   	   	        
				skip=false;
			   	fail=false;
			    
			}
		    
			@AfterTest
		    //Writing the final result for the TestCase whether it is passed or fail.
		    public void testReporter()
		    {
		    	if(istestpass)
		    	{
		    		TestUtil.reportDataSetResult(suiteProductDescPageXls, "Test Cases", TestUtil.getRowNum(suiteProductDescPageXls,this.getClass().getSimpleName()), "PASS");
		    	}else
		    		TestUtil.reportDataSetResult(suiteSearchPageXls, "Test Cases", TestUtil.getRowNum(suiteSearchPageXls,this.getClass().getSimpleName()), "Fail");
		    
		    	
		    	
		    }
		    
		    
			
			//Data provider gets the values from the XLS file for the TestCase which have Dataset for test.
		    @DataProvider
		    public Object[][] getTestData()
			{
			     
				return TestUtil.getData(suiteProductDescPageXls,this.getClass().getSimpleName());
			}
		
		

	}
	
	
	

