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
	 public void setUp() throws IOException {
	     prop = new Properties();
	     FileInputStream fis = new FileInputStream(
	         System.getProperty("user.dir") + "/src/main/resources/config.properties");
	     prop.load(fis);

	     String browser = prop.getProperty("browser").trim();

	     if (browser.equalsIgnoreCase("chrome")) {
	         WebDriverManager.chromedriver().setup();
	         ChromeOptions options = new ChromeOptions();
	         options.addArguments("--headless=new");
	         options.addArguments("--no-sandbox");
	         options.addArguments("--disable-dev-shm-usage");
	         options.addArguments("--disable-gpu");
	         options.addArguments("--window-size=1920,1080");

	         driver = new ChromeDriver(options);  // ✅ pass options here
	     } else if (browser.equalsIgnoreCase("firefox")) {
	         WebDriverManager.firefoxdriver().setup();
	         driver = new FirefoxDriver();
	     } else if (browser.equalsIgnoreCase("edge")) {
	         WebDriverManager.edgedriver().setup();
	         driver = new EdgeDriver();
	     } else {
	         throw new IllegalArgumentException("Browser Not Supported:" + browser);
	     }

	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

	     driver.get(prop.getProperty("url").trim());
	 }
	 
	 @AfterMethod
	 public void tearDown()
	 {		 driver.quit(); 
		 
	 }
}
