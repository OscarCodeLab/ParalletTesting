/**
 * 
 */
package com.SauceLab.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 31, 2024
 */

public class ClosePage extends BasePage{
	 private WebDriverWait wait;
	public ClosePage(WebDriver driver) {
		super(driver);
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// TODO Auto-generated constructor stub
	}
	

    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement ponyExpressImg;
    
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
