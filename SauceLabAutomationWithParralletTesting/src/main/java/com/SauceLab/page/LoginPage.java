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

    /**
     * Verifies if the login logo is displayed on the page.
     *
     * @return true if the logo is displayed, false otherwise
     */
    public boolean verifyLogo() {
        if (logo.isDisplayed()) {
            logger.info("Login logo is displayed");
            return true;
        } else {
            logger.error("Login logo is not displayed");
            return false;
        }
    }

    /**
     * Attempts to log in and handles both valid and invalid login scenarios.
     *
     * @param username the username to log in with
     * @param password the password to log in with
     * @return an InventoryPage object if login is successful, or null if login fails
     */
    public InventoryPage clickLogin(String username, String password) {
        logger.info("Attempting to log in with username: " + username);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();

        if (isLoginErrorDisplayed()) {
            logger.warn("Login failed for username: " + username + ". Error: " + getLoginErrorMessage());
            return null;
        }

        logger.info("Login successful with username: " + username);
        return new InventoryPage(driver); // Navigate to the inventory page if login succeeds
    }

    /**
     * Checks if the login error message is displayed.
     *
     * @return true if the error message is displayed, false otherwise
     */
    private boolean isLoginErrorDisplayed() {
        try {
            return loginErrorMsg.isDisplayed();
        } catch (Exception e) {
            return false; // No error message found
        }
    }

    /**
     * Retrieves the login error message.
     *
     * @return the error message text
     */
    private String getLoginErrorMessage() {
        try {
            return loginErrorMsg.getText();
        } catch (Exception e) {
            return "No error message found";
        }
    }
}
