package com.SauceLab.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.SauceLab.BaseClass.Base;
import com.SauceLab.Utilities.DataProviders;
import com.SauceLab.page.CartPage;
import com.SauceLab.page.ClosePage;
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
    private ClosePage close;

    @Test(priority = 1)
    public void verifyImage() {
        login = new LoginPage(getDriver());
        boolean isLogoDisplayed = login.verifyLogo();
        Assert.assertTrue(isLogoDisplayed, "Logo is not displayed!");
    }

    @Test(priority = 2, dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void sauceLoginTest(String username, String password) {
        login = new LoginPage(getDriver());
        inventory = new InventoryPage(getDriver());

        inventory = login.clickLogin(username, password);
    }

    @Test(priority = 3)
    public void verifySort() throws InterruptedException {
        cart = new CartPage(getDriver());
        String actual = inventory.getText();
        String expected = "Products";
        Assert.assertEquals(actual, expected);

        inventory.sortContainer();
        cart = inventory.gotoCartPage();
    }

    @Test(priority = 4)
    public void cartPageTest() {
        infor = new InformationPage(getDriver());
        String actual = cart.isCartHeaderTextDisplayed();
        String expected = "Your Cart";
        Assert.assertEquals(actual, expected);

        infor = cart.clickCheckoutButton();
    }

    @Test(priority = 5, dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void informationPageTest(String firstname, String lastname, String postalCode) {
        over = new OverviewPage(getDriver());

        String actual = infor.verifyText();
        String expected = "Checkout: Your Information";
        Assert.assertEquals(actual, expected);

        over = infor.fillInformationfields(firstname, lastname, postalCode);
    }

    @Test(priority = 6)
    public void overviewPageTest() {
    	close = new ClosePage(getDriver());
        String actual = over.isSubheaderTextDisplayed();
        String expected = "Checkout: Overview";
        Assert.assertEquals(actual, expected);

       close =  over.clickFinishBtn();
        boolean imageVisible = close.isPonyExpressImgDisplayed();
        Assert.assertTrue(imageVisible, "Pony Express image is not displayed!");
    }
    
    
}
