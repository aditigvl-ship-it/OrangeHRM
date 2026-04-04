package com.aditi.qa.SeleniumFramework;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginPage {
	
	WebDriver driver;
	PageFactory page;
	WebDriverWait wait;
	 Properties prop;
	public LoginPage(WebDriver driver,Properties prop)
	{
		this.driver=driver;
		this.prop=prop;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	//Login Page element
	@FindBy(xpath="//input[@name='username']")
	WebElement uname;
	@FindBy(xpath="//input[@name='password']")
	WebElement pass;
	@FindBy(xpath="//button[text()=' Login ']")
	WebElement button;
	//Actions
	public void login()
	{
		wait.until(ExpectedConditions.visibilityOf(uname));
		uname.sendKeys(prop.getProperty("username").trim());
		wait.until(ExpectedConditions.visibilityOf(pass));
	    pass.sendKeys(prop.getProperty("password").trim());
		wait.until(ExpectedConditions.visibilityOf(button)).click();;				
	}
	public boolean isLoginSuccessful()
	{
		try
		{
		WebElement dashboard=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
		return dashboard.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
}
