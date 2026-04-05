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
    private WebDriver driver;
    private WebDriverWait wait;
    private Properties prop;

    public PIMPage(WebDriver driver, Properties prop) {
        this.driver = driver;
        this.prop = prop;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // increased wait for CI stability
    }

    // WebElements
    @FindBy(xpath = "//span[text()='PIM']") private WebElement pimdash;
    @FindBy(xpath = "//button[normalize-space()='Add']") private WebElement add;
    @FindBy(xpath = "//input[@name='firstName']") private WebElement fname;
    @FindBy(xpath = "//input[@name='middleName']") private WebElement mname;
    @FindBy(xpath = "//input[@name='lastName']") private WebElement lname;
    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]") private WebElement eid;
    @FindBy(xpath = "//button[@type='submit']") private WebElement save;

    // Action method
    public void addEmployee(String first, String middle, String last, String id, String imagePath) {
        // Navigate to PIM dashboard
        wait.until(ExpectedConditions.elementToBeClickable(pimdash)).click();

        // Click Add button
        wait.until(ExpectedConditions.elementToBeClickable(add)).click();

        // Fill in employee details
        wait.until(ExpectedConditions.visibilityOf(fname)).sendKeys(first);
        wait.until(ExpectedConditions.visibilityOf(mname)).sendKeys(middle);
        wait.until(ExpectedConditions.visibilityOf(lname)).sendKeys(last);

        // Set Employee ID
        wait.until(ExpectedConditions.visibilityOf(eid)).clear();
        eid.sendKeys(id);

        // Upload employee image
        WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@type='file']")));
        uploadInput.sendKeys(imagePath);

        // Wait for any loader to disappear (if present)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        } catch (Exception e) {
            // Ignore if loader doesn't appear
        }

        // Click Save
        wait.until(ExpectedConditions.elementToBeClickable(save)).click();
    }
}