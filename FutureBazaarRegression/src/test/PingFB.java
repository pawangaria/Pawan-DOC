package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class PingFB {
	
	public class ExtendedHtmlUnitDriver extends HtmlUnitDriver
	{
	public ExtendedHtmlUnitDriver(boolean enableJavascript)
	{
	super(enableJavascript);
	}

	public void setHeader(String name, String value)
	{
	this.getWebClient().addRequestHeader(name, value);
	}
	}
	
	
	
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		//WebDriver driver = new FirefoxDriver();
		WebClient ww = new WebClient();
		int  code = ww.getPage("http://www.futurebazaar.com/").getWebResponse().getStatusCode();
		
		System.out.println(code);
	/*
		WebRequest req = new WebRequest(new URL("http://futurebazaar.com"));
		System.out.println(req.getAdditionalHeaders());
		System.out.println(req.getRequestParameters());
		*/
		/*
		List<NameValuePair> code = ww.getPage("http://www.futurebazaar.com/").getWebResponse().getResponseHeaders();
		System.out.println(code);
	*/
	}

}
