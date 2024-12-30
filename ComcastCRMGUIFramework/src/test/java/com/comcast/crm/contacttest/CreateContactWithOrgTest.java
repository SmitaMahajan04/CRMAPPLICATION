package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {
	
	
	public static void main(String[] args) throws IOException {
		//create object fileutility class to call the method from it

		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
				
		//step 3 : get the value based on key

	String BROWSER = fLib.getDataFromPropertiesFile("browser");
	String URL = fLib.getDataFromPropertiesFile("url");
	String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD = fLib.getDataFromPropertiesFile("password");

	//read testScript data from excelFile
	String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
	String contactLastName=eLib.getDataFromExcel("contact", 7, 3);



		
		WebDriver driver =null;
		
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

		//login to app
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to organization module
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create organisation button
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter all the details and create new organization
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//verify header msg Expected Result
		
		String headerMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerMsg.contains(orgName))
		{
			System.out.println(orgName+" is created==PASS");
		}
		else
		{
			System.out.println(orgName+" is not created==FAIL");	
		}
		
		
		//navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();
		
		//click on create Contact button
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all the details and create new organization
		
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		
		driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img")).click();
		
		//switch to child window
		
		wLib.switchToTabOnURL(driver, "Accounts&action");
		
		
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//if we use here text at this time
		//txt is dyamic and it will change every time for that to handle this this dynamic WE we use this type
		//of code.
		
		
		//switch to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		

		
		//verify header msg Expected Result
		
		
		
				String headerMsg1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerMsg1.contains(contactLastName))
				{
					System.out.println(contactLastName+" is created==PASS");
				}
				else
				{
					System.out.println(orgName+" is not created==FAIL");	
				}
				
		
		
		//verify header orgname info Expected Result		
		
	String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actualOrgName);
	if(actualOrgName.trim().equals(orgName))//trim for ignoring the space
	{
		System.out.println(orgName+" is created==PASS");
	}
	else
	{
		System.out.println(orgName+" is not created==FAIL");	
	}
		
		//logout
		
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}

}
