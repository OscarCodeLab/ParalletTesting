package com.SauceLab.TestBase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.SauceLab.BaseClass.Base;
import com.SauceLab.Utilities.DataProviders;
import com.SauceLab.page.CartPage;
import com.SauceLab.page.InformationPage;
import com.SauceLab.page.InventoryPage;
import com.SauceLab.page.LoginPage;
import com.SauceLab.page.OverviewPage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 26, 2024
 */
public class LoginTest extends Base {

    private LoginPage login;
    private InventoryPage inventory;
    private CartPage cart;
    private InformationPage infor;
    private OverviewPage over;
    
    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver (ensure it's browser-specific)
        login = new LoginPage(getDriver());
        inventory = new InventoryPage(getDriver());
        cart = new CartPage(getDriver());
        infor = new InformationPage(getDriver());
        over = new OverviewPage(getDriver());
    }

    @Test(priority = 1)
    public void verifyImage() {
        boolean isLogoDisplayed = login.verifyLogo();
        Assert.assertTrue(isLogoDisplayed, "Logo is not displayed!");
    }

    @Test(priority = 2, dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void sauceLoginTest(String username, String password) {

        inventory = login.clickLogin(username, password);
    }

    @Test(priority = 3)
    public void verifySort() throws InterruptedException {
        String actual = inventory.getText();
        String expected = "Products";
        Assert.assertEquals(actual, expected);

        inventory.sortContainer();
        cart = inventory.gotoCartPage();
    }

    @Test(priority = 4)
    public void cartPageTest() {
        String actual = cart.isCartHeaderTextDisplayed();
        String expected = "Your Cart";
        Assert.assertEquals(actual, expected);

        infor = cart.clickCheckoutButton();
    }

    @Test(priority = 5, dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void informationPageTest(String firstname, String lastname, String postalCode) {

        String actual = infor.verifyText();
        String expected = "Checkout: Your Information";
        Assert.assertEquals(actual, expected);

        over = infor.fillInformationfields(firstname, lastname, postalCode);
    }

    @Test(priority = 6)
    public void overviewPageTest() {
        String actual = over.isSubheaderTextDisplayed();
        String expected = "Checkout: Overview";
        Assert.assertEquals(actual, expected);

        over.clickFinishBtn();
        boolean imageVisible = over.isPonyExpressImgDisplayed();
        Assert.assertTrue(imageVisible, "Pony Express image is not displayed!");
    }
    
}
