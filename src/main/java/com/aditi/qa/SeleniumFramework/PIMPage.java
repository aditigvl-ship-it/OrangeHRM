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

public class PIMPage {

    WebDriver driver;
    WebDriverWait wait;
    Properties prop;

    public PIMPage(WebDriver driver, Properties prop) {
        this.driver = driver;
        this.prop = prop;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // WebElements
    @FindBy(xpath = "//span[normalize-space()='PIM']")
    WebElement pimdash;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement add;

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement fname;

    @FindBy(xpath = "//input[@name='middleName']")
    WebElement mname;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lname;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    WebElement eid;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement save;

    // Action
    public void addEmployee(String first, String second, String last, String id, String imagepath) {

        // wait for dashboard after login
    	if (driver.getCurrentUrl().contains("dashboard")) {
    	    wait.until(ExpectedConditions.elementToBeClickable(pimdash)).click();
    	}

        // click PIM
        wait.until(ExpectedConditions.visibilityOf(pimdash));
        wait.until(ExpectedConditions.elementToBeClickable(pimdash)).click();

        // click Add
        wait.until(ExpectedConditions.elementToBeClickable(add)).click();

        // fill form
        wait.until(ExpectedConditions.visibilityOf(fname)).sendKeys(first);
        wait.until(ExpectedConditions.visibilityOf(mname)).sendKeys(second);
        wait.until(ExpectedConditions.visibilityOf(lname)).sendKeys(last);

        wait.until(ExpectedConditions.visibilityOf(eid));
        eid.clear();
        eid.sendKeys(id);

        // upload image
        WebElement uploadInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        uploadInput.sendKeys(imagepath);

        // wait loader gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        // save
        wait.until(ExpectedConditions.elementToBeClickable(save)).click();
    }
}