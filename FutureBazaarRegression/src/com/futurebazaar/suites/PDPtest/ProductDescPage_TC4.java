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


	public class ProductDescPage_TC4  extends ProductDesPageSuiteBase{
		
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
			    	
					APP_LOGS.debug("skipping test ProductDescPage_TC4 ( Navigation to PDP check) as the runmode is NO");
					throw new SkipException("Skipping this testcase as the runmode is NO for this testcase : ProductDescPage_TC4 (Navigation to PDP check)");
				
				}
				//Load the RunModes of the Test
				runmodes=TestUtil.getDataSetRunmodes(suiteProductDescPageXls, this.getClass().getSimpleName());
				
			}
			
			//@Test(dataProvider="getTestData")
			//data is provided by the XLS files 
			// all the columns values should be passed to the TestCase function as parameters.
			
			//public void testA(String col1,String col2,String col3,String col4 ) throws InterruptedException
			
			@Test(dataProvider="getTestData")
			public void PDPpageNvigationCheck() throws InterruptedException
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
				//This Test case is used to verify the Product Details Page navigation from the different pages like Home page and Search Result Page.
				APP_LOGS.debug("*******Execution for the ProductDescPage_TC4 (Navigation to PDP Check) Started");
				
				driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
				//Method call to the Home Page to Product page verification.
				GotoPDPfromHP();
				
				//Method call to the Search Result Page to Product page verification. 
				GotoPDPfromSRP();
				//***********************************************
				  
				APP_LOGS.debug("*******Completed TEST CASE for the ProductDescPage_TC4 (Navigation to PDP Check) ****");
				
			
				
				}catch(Throwable t)
				{
					APP_LOGS.debug("*******Execution for the ProductDescPage_TC4 (Navigation to PDP Check) is falied*****");
					APP_LOGS.error("ERROR :" +t.getMessage());
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				    fail=true;
				    return;
				}
				
				
			}
			
			 		    
			public void GotoPDPfromHP() throws InterruptedException{
				try{
				   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("TODAY_DEAL_IMG_WAIT_XPATH"))));
					APP_LOGS.debug("Navigating to PDP by clicking on product image from HP\n");	
					String product_name = driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_HP_XPATH"))).getText();
					driver.findElement(By.xpath(OR.getProperty("FIRST_PORDUCTIMAGE_HP_XPATH"))).click();
					//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("PRODUCT_IMG_XPATH"))));
					//APP_LOGS.debug("The product name is:"+product_name);
					this.Temp_navigation(product_name);
					 
					 
					//Navigate to PDP by clicking on product
					
				      APP_LOGS.debug("Navigating to PDP by clicking on product name from HP");
				      
					  product_name = driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_HP_XPATH"))).getText();  
					 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("TODAY_DEAL_IMG_WAIT_XPATH"))));
					  driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_HP_XPATH"))).click();
					 
					  
				      this.Temp_navigation(product_name);
				}catch(Throwable t)
				{
					//APP_LOGS.debug("*******Execution for the ProductDescPage_TC4 (Navigation to PDP Check) is falied*****");
					
					APP_LOGS.error(t.getMessage());
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				    fail=true;
				    return;
				}  
				    }
			
			public void GotoPDPfromSRP() throws InterruptedException
			{
				try{
				  String product_name;
				 
				  APP_LOGS.debug("****GO TO PDP form SRP method is invoked*****");
				 
		          APP_LOGS.debug("****Entering Keyword in search Box******");
				  driver.findElement(By.id(OR.getProperty("SEARCH_FIELD_ID"))).sendKeys(CONFIG.getProperty("SearchItem"));
				  driver.findElement(By.id(OR.getProperty("SEARCH_GO_ID"))).click();
				  APP_LOGS.debug("Search Button is clicked");
		          APP_LOGS.debug("We are on SRP");
				
								
				//We are on Search Result Page
			   	    APP_LOGS.debug("Going to PDP by clikcing on image of product from Search Result Page");
			        product_name = driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_SRP_XPATH"))).getText();
			        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SRP_FIRSTPRODUCT_IMG_WAIT_XPATH"))));
				    driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTIMAGE_SRP_XPATH"))).click();	
				    APP_LOGS.debug("The product name is:"+product_name);
				    APP_LOGS.debug("*****Temp_navigation method start*****");
				    String current_url = driver.getCurrentUrl();
				    
				    Boolean bool=current_url.contains("pd");
				    Assert.assertTrue(bool);   
				    // APP_LOGS.debug("\nThe boolean tag vlaue for checking page url is :"+ bool+"\n");
				    APP_LOGS.debug("Coming to PDP page");
				    String product_name1 = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
				    
				    Assert.assertEquals(product_name,product_name1,"The product name is not same as displayed");
				    APP_LOGS.debug("Verification of the prdouct page is correct ");
				    
				    APP_LOGS.debug("Clicking on back button");
				    
				    driver.navigate().back();
				    APP_LOGS.debug("Going to PDP by clikcing on name of product from Search Result Page");
				    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("SRP_FIRSTPRODUCT_IMG_WAIT_XPATH"))));
				    product_name = driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_SRP_XPATH"))).getText();  
					driver.findElement(By.xpath(OR.getProperty("FIRST_PRODUCTNAME_SRP_XPATH"))).click();
					APP_LOGS.debug("The product name is:"+product_name);
					
					current_url = driver.getCurrentUrl();
					    
				    bool=current_url.contains("pd");
				    Assert.assertTrue(bool);
				  //  APP_LOGS.debug("\nThe boolean tag vlaue for checking page url is :"+ bool+"\n");
				    APP_LOGS.debug("Coming to PDP page");
				    product_name1 = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
			        Assert.assertEquals(product_name,product_name1,"The product name is not same as displayed");
				    APP_LOGS.debug("Verification of the prdouct page is correct ");
				}catch(Throwable t)
				{
					//APP_LOGS.debug("*******Execution for the ProductDescPage_TC4 (Navigation to PDP Check) is falied*****");
					
					APP_LOGS.error("ERROR :" +t.getMessage());
					ErrorUtil.addVerificationFailure(t,this.getClass().getSimpleName());
				    fail=true;
				    return;
				}
		   }
			
			public  void  Temp_navigation(String product_name) throws InterruptedException 
			{
				
				APP_LOGS.debug("*****Temp_navigation method start******");
			    String current_url = driver.getCurrentUrl();
			    
			    Boolean bool=current_url.contains("pd");
			    Assert.assertTrue(bool);
			    //APP_LOGS.debug("\nThe boolean tag vlaue for checking page url is :"+ bool+"\n");
			    APP_LOGS.debug("Coming to PDP page\n");
			    String product_name1 = driver.findElement(By.xpath(OR.getProperty("PRODUCTNAME_ON_PDP_XPATH"))).getText();
			    
			    Assert.assertEquals(product_name,product_name1,"The product name is not same as displayed");
			    APP_LOGS.debug("Verification of the prdouct page is correct");
			     
			    //Assert.assertTrue(product_name.equalsIgnoreCase(product_name1),"The product name is not same as displayed");
			    
			    
			    driver.findElement(By.id(OR.getProperty("FUTUREBAZAAR_LOGO_ID"))).click();
			    current_url=driver.getCurrentUrl();
		        APP_LOGS.debug("****Temp_navigation method Ends*****"); 
			    
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
	
	
	

