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


	public class ProductDescPage_TC2  extends ProductDesPageSuiteBase{
		
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
			    	
					APP_LOGS.debug("Skipping test ProductDescPage_TC2 (Quick View On Search Result Page) as the runmode is NO");
					throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : ProductDescPage_TC1 (Quick View On HOme Page)");
				
				}
				//Load the RunModes of the Test
				runmodes=TestUtil.getDataSetRunmodes(suiteProductDescPageXls, this.getClass().getSimpleName());
				
			}
			
			//@Test(dataProvider="getTestData")
			//data is provided by the XLS files 
			// all the columns values should be passed to the TestCase function as parameters.
			
			//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
			
			@Test(dataProvider="getTestData")
			public void quickViewSearchResultPageCheck() throws InterruptedException
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
				APP_LOGS.debug("****** Starting the Test case ProductDescPage_TC2 (QuickView SearchResult Page Check)");
			        
				
				APP_LOGS.debug("Opening the home page by clicking the Futurebazaar logo on home Page");
				driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
				
				//wait for the HOme Page to Load.
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
				
				//Searching the Product 
				APP_LOGS.debug("Searching the random Product");
				driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));

				driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
				
				//verification of the search Result Page
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("SEARCH_RESULT_PAGE_VERFICATION_ID"))));
				APP_LOGS.debug("Search Page Opened");
				
				
				APP_LOGS.debug("Getting the value of the Product Name and Link from Search Result");
				String search_Link_Text =driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_LINK_XPATH"))).getText();
				System.out.println(search_Link_Text);
				search_Link_Text = search_Link_Text.substring(0, search_Link_Text.length()-2).trim();
				
				//Taking mouse to the product page to see the Quick View Link.
				APP_LOGS.debug("Mouse Hover to the First Product From The Search Result");
				WebElement quickview = driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_IMAGE_XPATH")));
				Actions builder = new Actions(driver);
				builder.moveToElement(quickview).build().perform();
				//builder.clickAndHold(quickview).build().perform();
				
				APP_LOGS.debug("Clicking on the Quick View From the Search Result Page");
				driver.findElement(By.xpath(OR.getProperty("SEARCH_FIRST_PRODUCT_QUICK_VIEW_XPATH"))).click();
				
				//cart_popup
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SEARCH_QUICK_VIEW_VERIFICATION_XPATH"))));
				//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator))
				for(String handle : driver.getWindowHandles())
				{
					driver.switchTo().window(handle);
					
					System.out.println(driver.getTitle());
					
					}
			    Thread.sleep(3000);
			    APP_LOGS.debug("Getting the value of the product from the Quuick View POP up ");
				String POP_UP_TExt=driver.findElement(By.xpath(OR.getProperty("SEARCH_QUICK_VIEW_VERIFICATION_PRODUCT_LINK_XPATH"))).getText();
				Assert.assertTrue(POP_UP_TExt.contains(search_Link_Text),"Product text on search Page and on POP up is not matching");
				
				//Buy NOW button
				APP_LOGS.debug("Veriyfing the BUy NOW Button on the Quick View on Search Result Page");
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))));
				Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("PRODUCTDETAILS_BUY_NOW_XPATH"))).isDisplayed(),"BUY NOW button on the quick View pop up is not displayed");
						
				// clicking on the Read Full Product Details link.
				APP_LOGS.debug("Clicking on the Read Full Des. link on the Quick View On Search Result Page");
				driver.findElement(By.linkText(OR.getProperty("SEARCH_QUICK_VIEW_READ_FULL_DESC_LINK"))).click();
				
				//Verifying the Product Description page.
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				APP_LOGS.debug("product Description Page Opened and Verifying the Product Details Page");
				String PDP_TExt=driver.findElement(By.xpath(OR.getProperty("PDP_PAGE_PRODUCT_LINK_TEXT_VERIFCATION_XPATH"))).getText();
				System.out.println(PDP_TExt);
				Assert.assertTrue(PDP_TExt.equalsIgnoreCase(POP_UP_TExt),"PDP page text and Quick View pop up text is not matching");
				
				System.out.println("Quick View on Search Result test Successfull");
				APP_LOGS.debug("*******Completed TEST CASE for the ProductDescPage_TC2 (Quick View On Search Result Page) ****");
				
			 
					//***********************************************
				}catch(Throwable t)
				{
					APP_LOGS.debug("*******Execution for the ProductDescPage_TC2 (Quick View On Search Result Page) is falied*****");
					
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
	
	
	

