package com.SauceLab.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.SauceLab.Utilities.ExcelReader;

public class Base {

    // ThreadLocal for WebDriver and WebDriverWait to ensure parallel test execution
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    public Properties prop;
    public static Logger log = LogManager.getLogger(Base.class);
    public ExcelReader excel = new ExcelReader("C:\\Users\\hp\\git\\ParallelTest\\SauceLabAutomationWithParralletTesting\\src\\test\\resources\\LoginMaterial.xlsx");

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws IOException {
        // Load configuration
        loadConfiguration();

        // Initialize the browser
        initializeBrowser(browser);

        // Set up WebDriver configurations
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Set WebDriverWait for synchronization
        wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(5)));

        log.info("Test setup complete for browser: " + browser);
    }

    private void loadConfiguration() throws IOException {
        prop = new Properties();
        String configPath = System.getProperty("user.dir") + "/Configurations/config.properties";

        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
            log.info("Configuration loaded successfully.");
        } catch (IOException e) {
            log.error("Error loading configuration file: ", e);
            throw e;
        }
    }

    private void initializeBrowser(String browser) {
        log.info("Initializing browser: " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", getBrowserPreferences());
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setExperimentalOption("prefs", getBrowserPreferences());
                driver.set(new EdgeDriver(edgeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            default:
                log.error("Unsupported browser: " + browser);
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    private Map<String, Object> getBrowserPreferences() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        return prefs;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        
            getDriver().quit();
           // driver.remove();
            //wait.remove();
            log.info("Browser closed and resources cleaned up.");
        }
}
