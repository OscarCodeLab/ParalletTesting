package com.SauceLab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 26, 2024
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.info("LoginPage initialized");
    }

    @FindBy(css = ".login_logo")
    private WebElement logo;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(css = "h3[data-test='error']")
    private WebElement loginErrorMsg;

    public boolean verifyLogo() {
        if (logo.isDisplayed()) {
            logger.info("Login logo is displayed");
            return true;
        } else {
            logger.error("Login logo is not displayed");
            return false;
        }
    }

    public InventoryPage clickLogin(String username, String password) {
        logger.info("Attempting to log in with username: " + username);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();
        
            logger.info("Login successful with username: " + username);
            return new InventoryPage(driver);  // Return the next page object if login is successful
        }
    }

