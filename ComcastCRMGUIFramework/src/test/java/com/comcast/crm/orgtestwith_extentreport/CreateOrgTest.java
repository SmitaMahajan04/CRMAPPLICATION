package com.comcast.crm.orgtestwith_extentreport;

/**
 * @author Shree
 * 
 */
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListenerImplimentationClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerutility.ListenerImplimentationClass.class)
public class CreateOrgTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		
		
		// read testScript data from excelFile
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create new organization");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+ "create new organization");
		// verify header msg Expected Result

		String headerMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerMsg.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// verify header orgname info Expected Result

		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}
	}

	@Test(groups="regressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
		// read testScript data from excelFile
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industries = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industries);

		// verify the industry and type info

		String actualIndustryDd = driver.findElement(By.id("mouseArea_Industry")).getText();

		if (actualIndustryDd.equals(industries)) {

			System.out.println(actualIndustryDd + " is verified==>PASS");
		} else {

			System.out.println(actualIndustryDd + " is not  verified==>FAIL");
		}

	}

	@Test(groups="regressionTest")
	public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException {
		// read testScript data from excelFile

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and create new organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgWithPhone(orgName, phoneNumber);

		// verify header phonenumber Expected Result
		String phoneNumberTxt = driver.findElement(By.id("mouseArea_Phone")).getText();
		if (phoneNumberTxt.contains(phoneNumber)) {
			System.out.println(phoneNumber + " is created==PASS");
		} else {
			System.out.println(phoneNumberTxt + " is not created==FAIL");
		}

	}

}
