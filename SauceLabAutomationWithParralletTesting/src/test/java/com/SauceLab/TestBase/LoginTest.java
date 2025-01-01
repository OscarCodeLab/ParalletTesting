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
	
	@Test(priority = 1)
    public void verifyImage() {
        login = new LoginPage(getDriver());
        boolean isLogoDisplayed = login.verifyLogo();
        Assert.assertTrue(isLogoDisplayed, "Logo is not displayed!");
    }
	
	 @Test(priority = 2, dataProvider = "dp", dataProviderClass = DataProviders.class)
	    public void sauceLoginsTest(String username, String password) {
	        login = new LoginPage(getDriver());
	        inventory = new InventoryPage(getDriver());

	        inventory = login.clickLogin(username, password);
	        String actual = inventory.getText();
	        String expected = "Products";
	        Assert.assertEquals(actual, expected);
	    }
}
