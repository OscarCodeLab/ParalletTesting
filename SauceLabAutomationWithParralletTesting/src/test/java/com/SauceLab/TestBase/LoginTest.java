package com.SauceLab.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.SauceLab.BaseClass.Base;
import com.SauceLab.Utilities.DataProviders;
import com.SauceLab.page.InventoryPage;
import com.SauceLab.page.LoginPage;

public class LoginTest extends Base{
 private LoginPage login;
 private InventoryPage inventory;
	
	 @Test(dataProvider = "dp", dataProviderClass = DataProviders.class)
	    public void sauceLoginsTest(String username, String password) {
	        login = new LoginPage(getDriver());
	        inventory = new InventoryPage(getDriver());
	        
	        boolean isLogoDisplayed = login.verifyLogo();
	        Assert.assertTrue(isLogoDisplayed, "Logo is not displayed!");

	        inventory = login.clickLogin(username, password);
	        String actual = inventory.getText();
	        String expected = "Products";
	        Assert.assertEquals(actual, expected);
	    }
}
