package test;

public class DODConsts {
	 // Futurebazaar Constants
	 public static String DOD_URL="http://www.futurebazaar.com/";
	 public static final String SRC ="src";
	 public static final String SIGN_IN = "header_signin_signup";
	 public static final String USER_NAME = "username";
	 public static final String PASSWORD = "password";
	 public static final String LOGIN = "login";
	 public static final String LOG_OUT = "sout";
	 public static final String TRACK_ORDER = "Track Order";
	 public static String FF_PROXY;
	 public static String USER_ID="dealofdaytest@gmail.com";
	 public static String USER_PASSWORD="qatest123";
	 
	 public static final String EZONE = "Ezone";
	 public static final String BIG_BAZAAR = "BigBazaar";
	 public static final String PANTA_LOONS = "Pantaloons";
	 public static final String HOLII ="Holii";
	 
	 
	 public static final String MY_ORDERS="My Orders";
	 public static final String MY_SUBCRIPTIONS="//div[@id='my_account']/div/ul/li[2]/a";
	 public static final String MY_PROFILE = "//div[@id='my_account']/div/ul/li[3]/a";
	 public static final String ADDRESS_BOOK = "//div[@id='my_account']/div/ul/li[4]/a";
	 public static final String MANAGE_PASSWORD = "//div[@id='my_account']/div/ul/li[5]/a";
	 public static final String MY_SHOPPING_SACK="div.cart_items";
	 
	 // Ezone Constants
	 public static final String MY_ACCOUNT = "My Account";
	 public static final String MY_WISHLIST = "My Wishlist";
	
	 public static final String SEARCH_WINDOW="id_q";
	 //product which you want to search
	 public static String SEARCH_ITEM="mobile";
public static String SEARCH_ITEM2;
	 //public static final String SEARCH_ITEM="bags";
	 public static final String SEARCH="go";
	 public static final String CATEGORY = "Milestone";
	
	 //selecting the first product of the Search.
	 public static final String SELECT_FIRST_ITEM = "//li[@id='grid_page_1']/div/h3/a";
	 
	
	 public static final String BREADCRUMB = "breadcrumb";
	 
	 public static final String BUY_NOW = "//div[2]/div/button";
	 //public static final String BUY_NOW = "//div/button";
	 public static final String PROCEED = "proceed";
	 public static final String REMOVE_ITEM="button.linkButton";
	 
	 //user name and password given at the time of the Processing payment. 
	 public static final String USER_LOG_IN="mobileLabel";
	 public static final String USER_LOG_PASSWORD="userPassword";
	 public static final String LOG_IN_USER="defaultActionButton";
	 
	//PAYMENT options
		 public static final String NET_BANKING = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li";
		 public static final String CREDIT_CARD = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[2]";
		 public static final String CASH ="//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[3]";
		 public static final String DEBIT_CARD = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[4]";
		 public static final String CREDIT_CARD_EMI = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[5]";
		 public static final String CHEQUE = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[6]";
		 public static final String PAY_BY_PAYBACK = "//div[@id='content']/div[2]/div/form/div/div/div[2]/div/ul/li[7]";

		
	 
	 
	 
	 public static final String BUY_NOW2 = "button.btn_prod_buynow";
	//driver.findElement(By.cssSelector("img[@alt='Buy this product now']");
	 public static final String PROCEED_SHIP_DETAIL = "proceed";
	 public static final String PROCEED_TO_PAY = "proceed";
	 public static final String MAKE_PAYMENT = "proceed_to_pay";
	 public static final String PROD_QTY = "//table[@id='cart']/tbody/tr[2]/td[5]/form/input[3]";
	 public static final String PROD_UPDATE = "button.linkButton.update_qty";
	 
	 public static final String DEL_FNAME = "id_delivery_first_name";
	 public static final String DEL_LNAME = "id_delivery_last_name";
	 public static final String DEL_ADDRESS = "id_delivery_address";
	 public static final String DEL_CITY = "id_delivery_city";
	 public static final String DEL_PINCODE = "id_delivery_pincode";
	 public static final String DEL_STATE = "id_delivery_state";
	 public static final String DEL_COUNTRY = "id_delivery_country";
	 public static final String DEL_PHONE = "id_delivery_phone";
	 public static final String DEL_EMAIL = "id_email";
	 public static final String SEARCH_DOD_TITLE = "div.search_dod_name";
	 public static final String SEARCH_DOD_SELLPRICE = "div.fb.fdgray";
	 public static final String PDP_TITLE = "";
	 
	 public static final String PRODUCTPAGE_PRODUCT_SELLPRICE = "//div[@id='product']/div/div/div[2]/table/tbody/tr/td[2]";
	 public static final String PRODUCTPAGE_PRODUCT_TITLE = "//div[@id='product']/div/div/div[2]/h1";
	 public static final String PRODUCTPAGE_PRODUCT_MARKETPRICE = "td.price_value > span.fs";
	 public static final String PRODUCTPAGE_PRODUCT_STEALPRICE = "//div[@id='product']/div/div/div[2]/table/tbody/tr[3]/td[2]";
	 
	 public static final String HOMEPAGE_DOD_TITLE = ".//*[@id='menu']/ul/li[1]/div/div/div[1]/div[2]/div/a";
	 public static final String HOMEPAGE_DOD_SELLPRICE = ".//*[@id='menu']/ul/li[1]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[1]/div[1]";
	 public static final String HOMEPAGE_DOD_MARKETPRICE = ".//*[@id='menu']/ul/li[1]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[1]/div[2]/span[2]";
	 public static final String DOD_IMAGE = "//div[@id='menu']/ul/li/a/div";
	 
	 public static final String DOD_LINK_HOME="//div/div/div[2]/div/a";
	 public static final String DOD_HOME=".//*[@id='menu']/ul/li[1]/a/div";
	 
	 public static final String HOME_PAGE_VERIFICATION_ID="wrapper";
	 public static final String PDP_PAGE_VERIFICATION_XPATH=".//*[@id='content']";
	/* public DODConsts(){
		 ConstantProperties cp = new ConstantProperties();
		 
		 FF_PROXY=cp.FF_PROXY;
		 USER_ID=cp.DOD_USER_ID_LOGIN;
		 SEARCH_ITEM=cp.DOD_ITEM_SEARCH;
         SEARCH_ITEM2=cp.DOD_ITEM_SEARCH2;
		 DOD_URL=cp.DOD_URL;
		 USER_PASSWORD=cp.DOD_USER_PASSWORD;
		 //System.out.println(URL);
		 //System.out.println("from consts"+USER_PASSWORD);
		 //System.out.println("from consts"+URL);
	 	 }*/
}


