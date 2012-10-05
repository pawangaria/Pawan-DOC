package com.Fb.Base;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base {

	private static HelperUtil util = new HelperUtil();

	protected Properties OR = util.OR;
	public WebDriver driver = util.driver;
	protected WebDriverWait wait=util.wait;
    public Logger log=util.log;
    
}