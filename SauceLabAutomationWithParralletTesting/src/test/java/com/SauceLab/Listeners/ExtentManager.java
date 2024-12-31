package com.SauceLab.Listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.SauceLab.BaseClass.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager extends Base {
	
		private static ExtentReports extent;
		public static String fileName;
		
		

		    public static ExtentReports createInstance(String fileName) throws IOException {
		        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		        
		        
				/*
				 * htmlReporter.config().setTheme(Theme.STANDARD);
				 * htmlReporter.config().setDocumentTitle(fileName);
				 * htmlReporter.config().setEncoding("utf-8");
				 * htmlReporter.config().setReportName(fileName);
				 */
		        
		        extent = new ExtentReports();
		        extent.attachReporter(htmlReporter);
		        
		        
		        extent.setSystemInfo("HostName", "MyHost");
		        extent.setSystemInfo("ProjectName", "MyStoreProject");
		        extent.setSystemInfo("Tester", "Opeyemi");
		        extent.setSystemInfo("OS", "Win11");
		        extent.setSystemInfo("Browser", "Chrome");
		        
		        
		        return extent;
		    }
		    
		    public static void endReport() {
		        if (extent != null) {
		            extent.flush();
		        }
		    }

		    
		    public static String captureScreenshot() throws IOException {
		        Date d = new Date();
		        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		        
		        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        String screenshotPath = System.getProperty("user.dir") + "//Screenshot//" + fileName;
		        FileUtils.copyFile(screenshot, new File(screenshotPath));
		        return screenshotPath;
		    }
		    
		  

			public static void captureElementScreenshot(WebElement element) throws IOException {
				
				Date d = new Date();
				String fileName = d.toString().replace(":", "_").replace(" ", "_")+".png";

				
				
				File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(".//screenshot//"+"Element_"+fileName));
			}

	}