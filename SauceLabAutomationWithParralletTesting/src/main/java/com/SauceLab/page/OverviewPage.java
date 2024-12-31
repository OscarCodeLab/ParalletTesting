package com.SauceLab.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 29, 2024
 */

public class OverviewPage extends BasePage {
    private Actions actions;
    private WebDriverWait wait;

    public OverviewPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        logger.info("OverviewPage initialized with WebDriver: {}", driver);
    }

    @FindBy(xpath = "//a[normalize-space()='FINISH']")
    private WebElement finishBtn;

    @FindBy(xpath = "//a[normalize-space()='CANCEL']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//div[@class='subheader']")
    private WebElement subheaderText;

    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement ponyExpressImg;

    public InventoryPage clickCancelBtn() {
        logger.info("Attempting to click on the Cancel button.");
        try {
            cancelBtn.click();
            logger.info("Cancel button clicked successfully.");
            return new InventoryPage(driver);
        } catch (Exception e) {
            logger.error("Error occurred while clicking the Cancel button: {}", e.getMessage());
            throw e;
        }
    }

    public void clickFinishBtn() {
    	logger.info("Attempting to click on the Checkout button.");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
			actions.moveToElement(finishBtn).click().build().perform();
			logger.info("Checkout button clicked successfully.");
		} catch (Exception e) {
			logger.error("Error occurred while clicking the Checkout button: {}", e.getMessage());
			throw e;
		}
		
	}

    public String isSubheaderTextDisplayed() {
        logger.info("Attempting to retrieve subheader text.");
        try {
            String text = subheaderText.getText();
            logger.info("Subheader text retrieved successfully: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving subheader text: {}", e.getMessage());
            return null;
        }
    }

    public boolean isPonyExpressImgDisplayed() {
        logger.info("Checking if Pony Express image is displayed.");
        try {
            wait.until(ExpectedConditions.visibilityOf(ponyExpressImg));
            boolean isDisplayed = ponyExpressImg.isDisplayed();
            logger.info("Pony Express image visibility status: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error occurred while checking Pony Express image visibility: {}", e.getMessage());
            return false;
        }
    }
}
