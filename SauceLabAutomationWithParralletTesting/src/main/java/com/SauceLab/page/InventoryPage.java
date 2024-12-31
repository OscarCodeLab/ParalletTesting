package com.SauceLab.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 26, 2024
 */

public class InventoryPage extends BasePage {
    private WebDriverWait wait;
    //private JavascriptExecutor executor;
    private Actions actions;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        logger.info("InventoryPage initialized");
        // Directly casting the driver to JavascriptExecutor
        //this.executor = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement getProductSortContainerdropdown;

    @FindBy(xpath = "//div[@class='pricebar']//button")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[@class='product_label']")
    private WebElement productText;
    
    @FindBy(xpath = "//*[name()='path' and contains(@fill,'currentCol')]")
    private WebElement cartButton;

    public void sortContainer() {
        // Select the sorting option
    	logger.info("Sorting the container by price low to high.");
        Select s = new Select(getProductSortContainerdropdown);
        s.selectByValue("lohi");

        // Ensure there are at least 4 buttons available
        for (int i = 0; i < 4; i++) {
        	try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(i)));
            actions.moveToElement(button).perform();
            wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            logger.info("Added item {} to cart", i + 1);
        } catch (Exception e) {
            logger.error("Failed to add item {} to cart: {}", i + 1, e.getMessage());
        }
        	}
        
    }
    
    public String getText() {
    	logger.debug("Fetching product text");
    	return productText.getText();
    }
    
    public CartPage gotoCartPage() {
    	cartButton.click();
    	return new CartPage(driver);
    }
}



