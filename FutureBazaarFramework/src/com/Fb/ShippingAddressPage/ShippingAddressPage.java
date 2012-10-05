/**
 * 
 */
package com.Fb.ShippingAddressPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Fb.Base.Base;
import com.Fb.Base.CommonActionUtil;

/**
 * Page object class which represent the Shipping Address Page at the time of user check out
 * where you can edit the default shipping address, select the Saved shipping address.
 * You can go to the Payment page. 
 * @author PawaG
 *
 */
public class ShippingAddressPage extends Base {
	
	/**
	 * Enter the User First name 
	 * @param firstname
	 */
	public void enterFirstName(String firstname)
	{
		driver.findElement(By.id(OR.getProperty("FIRST_NAME_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("FIRST_NAME_TEXTBOX"))).sendKeys(firstname);
	}
	
	/**
	 * get the First NAme of the Customer.
	 * @return
	 */
	public String getFirstName()
	{
		
		String FirstName = driver.findElement(By.id(OR.getProperty("FIRST_NAME_TEXTBOX"))).getText();
	    return FirstName;
	}
	
	/**
	 * Enter the users Last Name
	 * @param lastname
	 */
	public void enterLastName(String lastname)
	{
		driver.findElement(By.id(OR.getProperty("LAST_NAME_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("LAST_NAME_TEXTBOX"))).sendKeys(lastname);
		
	}
	
	/**
	 * get the Last name of the customer from the shipping details page
	 * @return
	 */
	public String getLastName()
	{
		
		String LastName = driver.findElement(By.id(OR.getProperty("LAST_NAME_TEXTBOX"))).getText();
	    return LastName;
	}
	/**
	 * Enter the address for the shipping 
	 * @param address
	 */
	public void enterAddress(String address)
	{
		driver.findElement(By.id(OR.getProperty("ADDRESS_TEXTFIELD"))).clear();
		driver.findElement(By.id(OR.getProperty("ADDRESS_TEXTFIELD"))).sendKeys(address);

	}
	
	/**
	 * get the Shipping address 
	 * @return
	 */
	public String getAddress()
	{
		
		String Address = driver.findElement(By.id(OR.getProperty("ADDRESS_TEXTFIELD"))).getText();
	    return Address;
	}
	
	/**
	 * Enter the PIn code of your city
	 * @param pincode
	 */
	public void enterPinCode(String pincode)
	{
		driver.findElement(By.id(OR.getProperty("PINCODE_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("PINCODE_TEXTBOX"))).sendKeys(pincode);
		
	}
	
	/**
	 * get the Pin Code value from the pin code text which is in shipping page
	 * @return
	 */
   	public String getPinCode()
	{
		
		String PinCode = driver.findElement(By.id(OR.getProperty("PINCODE_TEXTBOX"))).getText();
	    return PinCode;
	}
	/**
	 * Enter City Name in the shipping address
	 * @param cityName
	 */
	public void enterCityName(String cityName)
	{
		driver.findElement(By.id(OR.getProperty("CITY_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("CITY_TEXTBOX"))).sendKeys(cityName);
		
	}
    /**
     * get the city name from the city field of shipping address page.
     * @return
     */
	public String getCityName()
	{
		
		String CityName = driver.findElement(By.id(OR.getProperty("CITY_TEXTBOX"))).getText();
	    return CityName;
	}
	
	/**
	 * Enter Phone NO for the Shipping address
	 * @param PhoneNo
	 */
 	public void enterPhoneNO(String PhoneNo)
	{
 		driver.findElement(By.id(OR.getProperty("PHONE_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("PHONE_TEXTBOX"))).sendKeys(PhoneNo);
		
	}
	
 	/**
 	 * get the Phone NO. fro the phone no text field.
 	 * @return
 	 */
 	public String getPhoneNO()
	{
		
		String PhoneNO = driver.findElement(By.id(OR.getProperty("PHONE_TEXTBOX"))).getText();
	    return PhoneNO;
	}
	/**
	 * Enter Email Address in the Shipping Details Page
	 * @param EmailAddress
	 */
	public void enterEmailAddress(String EmailAddress)
 	{
		driver.findElement(By.id(OR.getProperty("EMAIL_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("EMAIL_TEXTBOX"))).sendKeys(EmailAddress);
		
	}
	/**
	 * get the Email address from the Email address text box.
	 * @return
	 */
	public String getEmailAddress()
	{
		
		String EmailName = driver.findElement(By.id(OR.getProperty("EMAIL_TEXTBOX"))).getText();
	    return EmailName;
	}
	/**
	 * Enter Country in the Shipping Details Page
	 * @param country
	 */
	public void enterCountryName(String country)
 	{
		driver.findElement(By.id(OR.getProperty("COUNTRY_TEXTBOX"))).clear();
		driver.findElement(By.id(OR.getProperty("COUNTRY_TEXTBOX"))).sendKeys(country);
		
	}
	
	/**
	 * get the country name selected in the country field.
	 * @return
	 */
	public String getCountryName()
	{
		
		String CountryName = driver.findElement(By.id(OR.getProperty("COUNTRY_TEXTBOX"))).getText();
	    return CountryName;
	}
	/**
	 * Select the State VAlue from the List of the States in the Shipping Details Page.
	 * Pass the State name which you want to select.
	 * @param stateName
	 */
	public void selectStateInDropDownList(String stateName)
	{
		WebElement StatesDropDown = driver.findElement(By.id(OR.getProperty("STATE_LIST_DROPDOWN")));
		new CommonActionUtil().selectItemFromList(StatesDropDown,stateName);
	}
	
	/**
	 * get the State value selected in the State Drop Down. 
	 * @return
	 */
	public String getTheSelectedStateValueFromDropDown()
	{   	WebElement StatesDropDown = driver.findElement(By.id(OR.getProperty("STATE_LIST_DROPDOWN")));

		String StateValue = new CommonActionUtil().getTheSelectedValueFromList(StatesDropDown);
	    return StateValue;
	}
	
	public void clickProceedButtonToGoToPaymentPage()
	{
		driver.findElement(By.id(OR.getProperty("PROCEED_TO_PAYMENT_PAGE_BUTTON"))).click();
	}
	
}
