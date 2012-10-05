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


	public class ProductDescPage_TC5  extends ProductDesPageSuiteBase{
		
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
			    	
					APP_LOGS.debug("skipping test ProductDescPage_TC5 ( SIMILAR PRODUCTS option check) as the runmode is NO");
					throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : ProductDescPage_TC5 (SIMILAR PRODUCTS Link Check)");
				
				}
				//Load the RunModes of the Test
				runmodes=TestUtil.getDataSetRunmodes(suiteProductDescPageXls, this.getClass().getSimpleName());
				
			}
			
			//@Test(dataProvider="getTestData")
			//data is provided by the XLS files 
			// all the columns values should be passed to the TestCase function as parameters.
			
			//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
			
			@Test(dataProvider="getTestData")
			public void similarProductsPDPCheck() throws InterruptedException
			{
				try{
				count++;
				//test the Runmode of the current Dataset
				
				if(!runmodes[count].equalsIgnoreCase("Y"))
				{ 
					skip=true;
					//APP_LOGS.debug("skipping a"+count);
					throw new SkipException("Run mode for the test set data is Set to NO"+count); 
				}
				//**************************
				
				//***********************************************
				//this method is used to verify the Quick View on the Search Result Page Option.  
				
				APP_LOGS.debug("******(ProductDescPage_TC5)Opening the home page by clicking the Futurebazaar logo on home Page");
				driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
				
				//wait for the HOme Page to Load.
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(OR.getProperty("HOME_PAGE_VERIFICATION_ID"))));	
				
				//Clicking on the Todays Deals to go to the Product description Page
				driver.findElement(By.xpath(OR.getProperty("TODAYS_DEALS_HOME_XPATH"))).click();
				
				WebElement similarpro = driver.findElement(By.className("similar_prod"));
				//verifying the SIMILAR PRODUCT TExt.
				String TExt1 = similarpro.findElement(By.tagName("h4")).getText();
				Assert.assertEquals(TExt1,"Similar Products");
				
				//GEtting the valus one by one from the LIst of products.
				List<WebElement> productlist = similarpro.findElements(By.className("similar_prod_item")); 
				APP_LOGS.debug(productlist.size());
				
				
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				
				//clicking on the Product Description
				if(driver.getPageSource().contains("Product Description"))
				{   APP_LOGS.debug("Clicking on the Product Description link on PDP");
					//driver.findElement(By.cssSelector(OR.getProperty("Product_Description_CSS"))).click();
					Assert.assertTrue(driver.getPageSource().contains("Product Description"),"Product Description is not found");
				}
		        
				//clicking on the KEY_FEATURES
				if(driver.getPageSource().contains("KEY FEATURES"))
				{
					APP_LOGS.debug("clicking on the KEY FEATURES link on PDP");
					driver.findElement(By.cssSelector(OR.getProperty("KEY_FEATURES_CSS"))).click();
				}
				
				APP_LOGS.debug("getting the name of the product from the link on PDP page under SIMILAR PRODUCTS option");
				String PDP_Link_Text =driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_LINK_XPATH"))).getText();
				String trimmed = PDP_Link_Text.substring(0, PDP_Link_Text.length()-2);
				System.out.println(trimmed);
		     //		APP_LOGS.debug(PDP_Link_Text);
				
				APP_LOGS.debug("clicking on the product link on the SIMILAR PRODUCTS option");
				driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_LINK_XPATH"))).click();
				
				//verifying the Product Description page for the New Product after clicking the Link. 
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				APP_LOGS.debug("getting name of the Product after clicking on the link from SIMILAR PRODUCTS option");
				String PDP_PAGE_TExt=driver.findElement(By.xpath(OR.getProperty("PDP_PAGE_PRODUCT_LINK_TEXT_VERIFCATION_XPATH"))).getText();
				//APP_LOGS.debug(PDP_PAGE_TExt);
				Assert.assertTrue(PDP_PAGE_TExt.contains(trimmed),"Similar product text link in PDP Page and PDP page Text for the Similar product is not matching");
				driver.navigate().back();
				
				//clicking the Image for the Product
				APP_LOGS.debug("SIMILAR PRODUCTS option product image Link verification");
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PDP_PAGE_VERIFICATION_XPATH"))));
				APP_LOGS.debug("clicking on the image link on SIMILAR PRODUCTS");
				driver.findElement(By.xpath(OR.getProperty("SIMAILAR_FIRST_IMAGE_XPATH"))).click();
				if(driver.getPageSource().contains("Key Features"))
				{
				Assert.assertTrue(driver.getPageSource().contains("Key Features"),"Unable to get the Product details page");
				}
				
				if(driver.getPageSource().contains("Product Description"))
				{
				Assert.assertTrue(driver.getPageSource().contains("Product Description"),"Unable to get the Product details page");
				}
				
				APP_LOGS.debug("**** ProductDescPage_TC5 Verified");
				
				}catch(Throwable t)
				{
					APP_LOGS.debug("*******Execution for the ProductDescPage_TC5 (SIMILAR PRODUCTS option Check) is falied*****");
					
					APP_LOGS.error("ERROR :" +t.getMessage());
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				    fail=true;
				    return;
				}
				
				
			}
			
			 		    
			
			
			
			
			@AfterMethod
			public void testdataReporter()
			{//APP_LOGS.debug("value of skip"+count+skip);
			// APP_LOGS.debug("test case fail"+count+fail);
			//APP_LOGS.debug("test case a"+count);
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
	
	
	

