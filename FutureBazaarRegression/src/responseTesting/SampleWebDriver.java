package responseTesting;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.gargoylesoftware.htmlunit.util.NameValuePair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SampleWebDriver {
 protected int localProxyPort = 5368;
  protected ProxyLight proxy;

  // LRU response table. Note: this is not thread-safe.
  // Use ConcurrentLinkedHashMap instead: http://code.google.com/p/concurrentlinkedhashmap/
  private LinkedHashMap<String, Response> responseTable = new LinkedHashMap<String, Response>() {
    protected boolean removeEldestEntry(Map.Entry eldest) {
      return size() > 100;
    }
  };

  public Response fetch(String url) {
    if (proxy == null) {
      initProxy();
    }
     FirefoxProfile profile = new FirefoxProfile();

    /**
     * Get the native browser to use our proxy
     */
    profile.setPreference("network.proxy.type", 1);
    profile.setPreference("network.proxy.http", "localhost");
    profile.setPreference("network.proxy.http_port", localProxyPort);

    FirefoxDriver driver = new FirefoxDriver(profile);

    // Now fetch the URL
    driver.get(url);

    Response proxyResponse = responseTable.remove(driver.getCurrentUrl());

    return proxyResponse;
  }

  private void initProxy() {
    proxy = new ProxyLight();

    this.proxy.setPort(localProxyPort);

    // this response filter adds the intercepted response to the cache
    this.proxy.getResponseFilters().add(new ResponseFilter() {
      public void filter(Response response) {
        responseTable.put(response.getRequest().getUrl(), response);
      }
    });

    // add request filters here if needed

    // now start the proxy
    try {
      this.proxy.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SampleWebDriver driver = new SampleWebDriver();
    //Response res = driver.fetch("http://www.futurebazaar.com");
    
    Map<String, String> mm =driver.fetch("http://www.futurebazaar.com").getHeaders();
     
    for (Map.Entry<String, String> entry : mm.entrySet()) {
        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
	   System.out.println("in the");
  //  System.out.println(res.getHeaders());
  }
}

 