
package com.comcast.crm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WebDriver driver =null;
	public static WebDriver sdriver =null;
	
	
	
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("Login");
//		String URL = fLib.getDataFromPropertiesFile("url");
//		String USERNAME = fLib.getDataFromPropertiesFile("username");
//		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		String URL =System.getProperty("url",fLib.getDataFromPropertiesFile("url"));
		String USERNAME =System.getProperty("username",fLib.getDataFromPropertiesFile("username"));
		String PASSWORD=System.getProperty("password",fLib.getDataFromPropertiesFile("password"));

		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("Logout");
		
	HomePage hp=new HomePage(driver);
	hp.logOu();
	}
	
	//this implentation is for cross browser testing,if we use this we have to always run our programm grom xml file
	//@Parameters("BROWSER")
//	@BeforeClass(groups= {"smokeTest","regressionTest"})
//	public void configBC(String browser) throws IOException
//	{
//		System.out.println("Launch Browser");
//		
//		String BROWSER = browser;
//				//fLib.getDataFromPropertiesFile("browser");
//
//		
//		if(BROWSER.equalsIgnoreCase("chrome"))
//		{
//			driver= new ChromeDriver();
//		}
//		else if(BROWSER.equalsIgnoreCase("firefox"))
//		{
//			driver= new FirefoxDriver();
//		}
//		else if(BROWSER.equalsIgnoreCase("edge"))
//		{
//			driver= new EdgeDriver();
//		}
//
//	}
	//this implimentation for testng class
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("Launch Browser");
		
		//String BROWSER =fLib.getDataFromPropertiesFile("browser");
		String BROWSER =System.getProperty("browser",fLib.getDataFromPropertiesFile("browser"));
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("Close Browser");
		driver.quit();
	}

	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("connect to db and report config");
		

	}
	
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS()
	{
		System.out.println("close db and report backup");
		
		
	}
	

}
