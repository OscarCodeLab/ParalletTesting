/**
 * 
 */
package com.SauceLab.base;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Author: Kolawole Opeyemi Oscar
 * Date: Dec 26, 2024
 */

public class BasePage {
	public WebDriver driver;
	protected Logger logger = LogManager.getLogger(getClass());  // Logger declared here
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//logger.info("{} initialized", this.getClass().getSimpleName());  // Logs the initialization of each page
	}
	
}
