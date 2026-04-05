package com.aditi.qa.SeleniumFramework;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;

@Listeners(com.aditi.qa.SeleniumFramework.listeners.Listeners.class)
public class PIMTest extends BaseClass {

    String resourcePath = System.getProperty("user.dir") +
            File.separator + "src" +
            File.separator + "test" +
            File.separator + "resources" +
            File.separator;

    @Test
    public void addEmp() {

        // Login
        LoginPage pg = new LoginPage(driver, prop);
        pg.login();

        // PIM actions
        PIMPage pim = new PIMPage(driver, prop);

        pim.addEmployee("Aditi", "A", "New", "100", resourcePath + "a.jpg");
        pim.addEmployee("Datta", "B", "New", "101", resourcePath + "b.jpg");
        pim.addEmployee("Chiku", "Arun", "New", "102", resourcePath + "a.jpg");
    }
}