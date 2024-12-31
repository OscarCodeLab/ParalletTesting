package com.SauceLab.page;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar Date: Dec 29, 2024
 */

public class CartPage extends BasePage {
	private Actions actions;
	private WebDriverWait wait;
	private JavascriptExecutor jsExecutor;
	public CartPage(WebDriver driver) {
		super(driver);
		actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    this.jsExecutor = (JavascriptExecutor) driver;
		logger.info("CartPage initialized with WebDriver: " + driver);
	}

	@FindBy(xpath = "//div[@class='subheader']")
	WebElement cartHeaderText;

	@FindBy(xpath = "//a[normalize-space()='CHECKOUT']")
	WebElement checkoutButton;

	@FindBy(xpath = "//a[normalize-space()='Continue Shopping']")
	WebElement continueShoppingButton;

	public String isCartHeaderTextDisplayed() {
		String text = cartHeaderText.getText();
		logger.info("Cart header text displayed: " + text);
		return text;
	}

	public InformationPage clickCheckoutButton() {
	    logger.info("Attempting to click on the Checkout button.");
	    try {
	        // Wait until the element is clickable
	        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

	        // Scroll the element into view
	        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

	        // Perform the click using JavaScript for precision
	        jsExecutor.executeScript("arguments[0].click();", checkoutButton);

	        logger.info("Checkout button clicked successfully.");
	    } catch (Exception e) {
	        logger.error("Error occurred while clicking the Checkout button: {}", e.getMessage());
	        throw new RuntimeException("Failed to click on the Checkout button.", e);
	    }
	    return new InformationPage(driver);
	}


	public InventoryPage clickContinueShoppingButton() {
		logger.info("Attempting to click on the Continue Shopping button.");
		actions.moveToElement(continueShoppingButton).build().perform();
		logger.info("Continue Shopping button clicked.");

		return new InventoryPage(driver);
	}
}
