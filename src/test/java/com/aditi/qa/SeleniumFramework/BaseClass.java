package com.aditi.qa.SeleniumFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	 protected Properties prop;
	 protected WebDriver driver;
	 
	 @BeforeMethod
	 public void setUp() throws IOException
	 {
		prop= new Properties();
		FileInputStream fis = new FileInputStream(
	   System.getProperty("user.dir") + "/src/main/resources/config.properties");
		prop.load(fis); 
		 // Initialize the WebDriver based on browser defined in config.properties 
		String browser= prop.getProperty("browser").trim();
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		 else if (browser.equalsIgnoreCase("edge")) { 
             driver = new EdgeDriver(); 
		 }
		 else
		 {
			 throw new IllegalArgumentException("Browser Not Supported:" + browser); 
		 }
		
		  // ✅ Browser setup
		 WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();

	    // 🔥 MUST for Linux / GitHub Actions
	    options.addArguments("--headless=new");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-gpu");
	    options.addArguments("--window-size=1920,1080");
		 
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

	    // ✅ Open URL from properties
	    driver.get(prop.getProperty("url").trim());
	 }
	 
	 @AfterMethod
	 public void tearDown()
	 {		 driver.quit(); 
		 
	 }
}
