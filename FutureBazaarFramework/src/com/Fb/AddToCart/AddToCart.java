package com.Fb.AddToCart;

import org.openqa.selenium.By;

import com.Fb.Base.Base;

public class AddToCart extends Base{

	public void verifyAddtocart()
	{
		
	}
	
	public void clickONProceedButton()
	{
		driver.findElement(By.name(OR.getProperty("PROCEED_TO_NEXT_PAGE"))).click();
	}
}
