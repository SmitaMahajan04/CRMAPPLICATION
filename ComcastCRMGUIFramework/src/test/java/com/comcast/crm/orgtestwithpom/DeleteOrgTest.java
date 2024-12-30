package com.comcast.crm.orgtestwithpom;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	
	
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


	String orgName=eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNumber();
	



		
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
		
	LoginPage	lp=new LoginPage(driver);
	lp.loginToApp(URL, USERNAME, PASSWORD);
		//navigate to organization module
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		
		//click on create organisation button
		
	OrganizationsPage op=new OrganizationsPage(driver);
	op.getCreateNewOrgBtn().click();
		
		//enter all the details and create new organization
		CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgName);
		
		
		
		//verify header msg Expected Result
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String ActOrgNmae=oip.getHeaderMsg().getText();
		
		if(ActOrgNmae.contains(orgName))
		{
			
			System.out.println(orgName+"  is verified==.PASS");
		}
		
		else
		{
			System.out.println(orgName+"  is verified==.FAIL");
		}
		
		//go back to organization page
		
		
		hp.getOrgLink().click();
		
		//search for organization
		op.getSearchEdt().sendKeys(orgName);
		wLib.select(op.getSearchDd(), "Organization Name");
		op.getSearchBtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
		
		driver.switchTo().alert().accept();
		//verify header orgname info Expected Result		
		
		
		//logout
		
		/*Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();*/
		
	hp.logOu();
	driver.quit();

	}

}
