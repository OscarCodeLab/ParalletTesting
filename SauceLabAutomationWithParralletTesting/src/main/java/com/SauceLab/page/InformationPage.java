/**
 * 
 */
package com.SauceLab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SauceLab.base.BasePage;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 29, 2024
 */

public class InformationPage extends BasePage{

	public InformationPage(WebDriver driver) {
		super(driver);
		 logger.info("InformationPage initialized with WebDriver: {}", driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@class='subheader']")
	WebElement InformationText;
	
	@FindBy(id = "first-name")
    WebElement firstnameField;
	
	@FindBy(id = "last-name")
    WebElement lastnameField;
	
	@FindBy(id = "postal-code")
    WebElement postalCodeField;
	
	@FindBy(xpath = "//input[@value='CONTINUE']")
    WebElement continueButton;
	
	@FindBy(xpath = "//a[normalize-space()='CANCEL']")
	WebElement cancelButton;
	
	public OverviewPage fillInformationfields(String firstName, String lastName, String postalCode) {
        logger.info("Filling information fields with: FirstName={}, LastName={}, PostalCode={}", firstName, lastName, postalCode);
        try {
            firstnameField.sendKeys(firstName);
            logger.debug("Entered first name: {}", firstName);
            lastnameField.sendKeys(lastName);
            logger.debug("Entered last name: {}", lastName);
            postalCodeField.sendKeys(postalCode);
            logger.debug("Entered postal code: {}", postalCode);
            continueButton.click();
            logger.info("Clicked on the Continue button.");
        } catch (Exception e) {
            logger.error("Error occurred while filling information fields: {}", e.getMessage());
        }
        return new OverviewPage(driver);
        
    }
	
    public String verifyText() {
        try {
            String text = InformationText.getText();
            logger.info("Verified information text: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Error occurred while verifying information text: {}", e.getMessage());
            return null;
        }
    }
}
