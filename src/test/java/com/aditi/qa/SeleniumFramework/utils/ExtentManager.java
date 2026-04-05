package com.aditi.qa.SeleniumFramework.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentReports getReportInstance()
	{
		String path = System.getProperty("user.dir") + "/Reports/index.html";;
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Aditi");
		return extent;
		
	}
	
}
