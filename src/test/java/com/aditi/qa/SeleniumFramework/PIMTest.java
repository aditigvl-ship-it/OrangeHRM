package com.aditi.qa.SeleniumFramework;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(com.aditi.qa.SeleniumFramework.listeners.Listeners.class)
public class PIMTest extends BaseClass{

	@Test
	public void addEmp()
	{
	//obj
	LoginPage pg=new LoginPage(driver, prop);
	pg.login();
	PIMPage pim=new PIMPage(driver, prop);
	pim.addEmployee("Aditi", "A", "New", "100", "C:\\Users\\aditi\\OneDrive\\Pictures\\a.jpg");
	pim.addEmployee("Datta", "B", "New", "101", "C:\\Users\\aditi\\OneDrive\\Pictures\\b.jpg");
	pim.addEmployee("Chiku", "Arun", "new", "102","C:\\Users\\aditi\\OneDrive\\Pictures\\a.jpg");
	
	
	}
}
