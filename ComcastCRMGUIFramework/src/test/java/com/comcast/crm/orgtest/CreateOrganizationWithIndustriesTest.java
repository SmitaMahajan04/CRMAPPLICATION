package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest {
	
	
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
String orgName=eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
	
	String industries=eLib.getDataFromExcel("org", 4, 3);
	
	String type=eLib.getDataFromExcel("org", 4, 4);
	


		
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
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);//
		
		
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select sel=new Select(industryDropdown);
		sel.selectByVisibleText(industries);
		
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select sel1=new Select(typeDropdown);
		sel1.selectByVisibleText(type);

		
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the industry and type info
		
		String actualIndustryDd = driver.findElement(By.id("mouseArea_Industry")).getText();
		String actualTypeDd = driver.findElement(By.id("mouseArea_Type")).getText();
		if(actualIndustryDd.equals(industries))
		{
			
			System.out.println(actualIndustryDd+" is verified==>PASS");
		}
		else
{
			
			System.out.println(actualIndustryDd+" is not  verified==>FAIL");
		}
			
		
		//verify type dd
		if(actualTypeDd.equals(type))
		{
			
			System.out.println(actualTypeDd+" is verified==>PASS");
		}
		else
{
			
			System.out.println(actualTypeDd+" is not  verified==>FAIL");
		}

		//logout
		
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		

	}

}
