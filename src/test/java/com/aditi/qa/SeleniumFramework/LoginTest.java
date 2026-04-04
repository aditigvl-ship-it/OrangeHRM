package com.aditi.qa.SeleniumFramework;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
		
@Test
public void verifyTest()
{
	
   LoginPage lp=new LoginPage(driver, prop);
     lp.login();
     Assert.assertTrue(lp.isLoginSuccessful(), "Dashboard is not dispayed");

}

}
