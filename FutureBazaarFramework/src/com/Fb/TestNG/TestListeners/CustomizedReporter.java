package com.Fb.TestNG.TestListeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

public  class CustomizedReporter  implements IReporter
{
    private static PrintWriter    f_out;
    private static final String   OUT_FOLDER  = "E:\\";
	
	public void generateReport(List<XmlSuite> arg0, List<ISuite> suites, String outdir)
    {

		ISuiteResult results =suites.get(0).getResults().get("Mysuite");
        ITestContext context = results.getTestContext();

        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();

        // Print all test exceptions...
        for( ITestResult r: failedTests.getAllResults()) {
            System.out.println( r.getThrowable());
        }

	}

	
	
}