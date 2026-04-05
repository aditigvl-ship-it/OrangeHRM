package com.aditi.qa.SeleniumFramework;

import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.TimeoutException;   // ✅ correct one


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    // Elements
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
    public void addEmployee(String first, String second, String last, String id, String imagepath) throws TimeoutException {

        wait.until(ExpectedConditions.elementToBeClickable(pimdash)).click();
        wait.until(ExpectedConditions.elementToBeClickable(add)).click();

        wait.until(ExpectedConditions.visibilityOf(fname)).sendKeys(first);
        mname.sendKeys(second);
        lname.sendKeys(last);

        wait.until(ExpectedConditions.visibilityOf(eid));
        eid.clear();
        eid.sendKeys(id);

        // Upload image
        if (imagepath != null && !imagepath.isEmpty()) {
            WebElement upload = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
            upload.sendKeys(imagepath);
        }

     // Click Save safely
        By loader = By.cssSelector("div.oxd-form-loader");

     // If loader appears, wait until it disappears
     try {
         wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
     } catch (TimeoutException e) {
         // Loader never appeared, continue
     }
    }


     

    }
