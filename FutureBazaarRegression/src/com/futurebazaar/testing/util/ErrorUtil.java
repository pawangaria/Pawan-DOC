package com.futurebazaar.testing.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.futurebazaar.base.TestBase;

public class ErrorUtil {
	private static Map<ITestResult,List> verificationFailuresMap = new HashMap<ITestResult,List>();
	private static Map<ITestResult,List> skipMap = new HashMap<ITestResult,List>();

	
	     public static void addVerificationFailure(Throwable e,String classname) {
				List verificationFailures = getVerificationFailures();
				verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
				verificationFailures.add(e);
			    
				//Taking a snapshot for the Failure of the Testcase and the file name will be the Testcase name
				File scrFile = ((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);
			    //Now you can do whatever you need to do with it, for example copy somewhere
			    try {
					FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\FailureSNAPSHOT\\"+classname+".png"));
	

} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		  
		  public static List getVerificationFailures() {
				List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
				return verificationFailures == null ? new ArrayList() : verificationFailures;
			}
		 
		  
}
