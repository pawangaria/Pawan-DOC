package test;

public class FBConsts {
	 // Futurebazaar Constants
	 public static String URL="http://futurebazaar.com";
	 public static final String FB_LOGO="logo";
	 public static final String SRC ="src";
	 public static final String SIGN_IN = "header_signin_signup";
	 public static final String USER_NAME = "username";
	 public static final String PASSWORD = "password";
	 public static final String LOGIN = "login";
	 public static final String LOG_OUT = "sout";
	 public static final String TRACK_ORDER = "Track Order";
	 public static String FF_PROXY;
	 public static String USER_ID="pawan1@gmail.com";
	 public static String USER_PASSWORD="p0513231068";
	 
	 public static final String EZONE = "Ezone";
	 public static final String BIG_BAZAAR = "BigBazaar";
	 public static final String PANTA_LOONS = "Pantaloons";
	 public static final String HOLII ="Holii";
	 
	 
	 public static final String MY_ORDERS="My Orders";
	 public static final String MY_SUBCRIPTIONS="My Subcriptions";
	 public static final String MY_PROFILE = "//div[@id='my_account']/div/ul/li[4]/a";
	 public static final String ADDRESS_BOOK = "//div[@id='my_account']/div/ul/li[5]/a";
	 public static final String MANAGE_PASSWORD = "//div[@id='my_account']/div/ul/li[6]/a";
	 public static final String MY_SHOPPING_SACK="div.cart_items";
	 
	 // Ezone Constants
	 public static final String MY_ACCOUNT = "My Account";
	 public static final String MY_WISHLIST = "#tab_wishlist > a";
	
	 public static final String SEARCH_WINDOW="id_q";
	 //product which you want to search
	 public static String SEARCH_ITEM="mobile";
public static String SEARCH_ITEM2;

	 //public static final String SEARCH_ITEM="bags";
	 public static final String SEARCH="go";
	 public static final String CATEGORY = "Milestone";
	
	 //selecting the first product of the Search.
	 public static final String SELECT_FIRST_ITEM = "//li[@id='grid_page_1']/div/div/a/img";
	 
	
	 public static final String BREADCRUMB = "breadcrumb";
	 
	 public static final String BUY_NOW = "//div[2]/button";
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
	 public static final String SEARCH_DOD_SELLPRICE = "//div[2]/div/div/div/div[2]";
	 public static final String PDP_TITLE = "";
	 
	 public static final String PRODUCTPAGE_PRODUCT_SELLPRICE = "//tr[2]/td[2]";
	 public static final String PRODUCTPAGE_PRODUCT_TITLE = "//h1";
	 public static final String PRODUCTPAGE_PRODUCT_MARKETPRICE = "span.fs.fb";
	 public static final String PRODUCTPAGE_PRODUCT_STEALPRICE = "//div[@id='product']/div/div/table[2]/tbody/tr[3]/td[2]";
	 
	 public static final String HOMEPAGE_DOD_TITLE = "//li[@id='grid_page_']/div/h3/a";
	 public static final String HOMEPAGE_DOD_SELLPRICE = "//li[@id='grid_page_']/div/div[3]/div/div[2]/span[2]";
	 public static final String HOMEPAGE_DOD_MARKETPRICE = "span.fs";
	 public static final String DOD_IMAGE = "//li/div/div/a/img";
	 
	/* public FBConsts(){
		 ConstantProperties cp = new ConstantProperties();
		 
		 FF_PROXY=cp.FF_PROXY;
		 USER_ID=cp.FUTUREBAZAAR_USER_ID_LOGIN;
		 SEARCH_ITEM=cp.FUTUREBAZAAR_ITEM_SEARCH;
         SEARCH_ITEM2=cp.FUTUREBAZAAR_ITEM_SEARCH2;
		 URL=cp.FUTUREBAZAAR_URL;
		 USER_PASSWORD=cp.FUTUREBAZAAR_USER_PASSWORD;
		 //System.out.println("from consts"+USER_PASSWORD);
		 //System.out.println("from consts"+URL);
	 	 }*/
}


