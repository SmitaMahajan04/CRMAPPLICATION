package com.comcast.crm.contacttestwithtestng;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewcontactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups="smokeTest")
	public void CreateContactTest() throws IOException {
		// read testScript data from excelFile

		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
	

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter all the details and create new organization
		CreatingNewcontactPage cnp = new CreatingNewcontactPage(driver);
		cnp.createContact(lastName);

		// verify header lastname Expected Result

		String actLastName = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + " is created==PASS");
		} else {
			System.out.println(lastName + " is not created==FAIL");
		}

	}

	@Test(groups="regressionTest")

	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException, Throwable {

		// read testScript data from excelFile
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter all the details and create new organization

		String startDate = jLib.getSystemDateYYYYMMDD();
		String EndDate = jLib.getRequiredDateYYYYMMDD(30);

		System.out.println(EndDate);
		System.out.println(startDate);

		CreatingNewcontactPage cnp = new CreatingNewcontactPage(driver);
		cnp.createContact(lastName, startDate, EndDate);

		// verify startdate and enddate in Expected Result

		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.contains(startDate)) {
			System.out.println(startDate + " is created==PASS");
		} else {
			System.out.println(startDate + " is not created==FAIL");
		}

		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println(EndDate);
		System.out.println(actEndDate);
		if (actEndDate.contains(EndDate)) {
			System.out.println(EndDate + " is created==PASS");
		} else {
			System.out.println(EndDate + " is not created==FAIL");
		}

	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {
		// read testScript data from excelFile
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgName);

		// verify orgname in header msg Expected Result

		String headerMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerMsg.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// navigate to Contact module
		hp.getContactLink().click();

		// click on create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter all details and create new contact
		CreatingNewcontactPage cnop = new CreatingNewcontactPage(driver);
		cnop.createContactWitOrg(contactLastName, orgName);
		// read from excel
		// String lastName = eLib.getDataFromExcel("contact", 4, 2) +
		// jLib.getRandomNumber();

//		cnop.getLastNameEdt().sendKeys(lastName);
//		cnop.getOrgNameImg().click();
//		//switch to child window
//		wLib.switchToTabOnURL(driver, "Accounts&action");
//		cnop.getOrgSearchEdt().sendKeys(orgName);
//		cnop.getOrgSearchBtn().click();
//		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//if we use here text at this time
//		//txt is dyamic and it will change every time for that to handle this this dynamic WE we use this type
//		//of code.
//		
//		//switch to parent window
//				wLib.switchToTabOnURL(driver, "Contacts&action");
//			cnop.getSaveBtn().click();
//		
//		
		// cnop.createContactWitOrg(contactLastName, orgName);

		// verify orgname info Expected Result

		String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName);
		if (actualOrgName.trim().equals(orgName))// trim for ignoring the space
		{
			System.out.println(orgName + " is matching==PASS");
		} else {
			System.out.println(orgName + " is not matching==FAIL");
		}

	}
}
//}
