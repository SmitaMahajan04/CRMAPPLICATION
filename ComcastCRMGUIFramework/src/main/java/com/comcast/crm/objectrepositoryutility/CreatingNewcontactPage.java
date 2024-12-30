package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewcontactPage {
	
	WebDriver driver;
	//initialization of constructer
	public CreatingNewcontactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	WebDriverUtility wLib=new WebDriverUtility();
	
@FindBy(name="firstname")
 private WebElement firstNameEdt;
 
 
 @FindBy(name="lastname")
 private WebElement lastNameEdt;
 
 @FindBy(name = "account_name")
 private WebElement orgNameEdt;
 
 @FindBy(xpath = "//input[@name='account_name']//following-sibling::img")
 private WebElement orgNameImg;
 
 @FindBy(name="search_text")
 private WebElement orgSearchEdt;
 
 
 @FindBy(name="search_field")
 private WebElement orgSearchDd;
 
 @FindBy(name="search")
 private WebElement orgSearchBtn;
 
 
 
 
 @FindBy(name="support_start_date")
 private WebElement startDateEdt;
 
 
 @FindBy(name="support_end_date")
 private WebElement endDateEdt;
 
 @FindBy(name="mobile")
 private WebElement phoneEdt;
 
@FindBy(name="button")
 private WebElement saveBtn;

@FindBy(id="mouseArea_Last Name")
private WebElement lastNameAfterCreatingContactEdt;

 

 
public WebDriverUtility getwLib() {
	return wLib;
}

public WebElement getLastNameAfterCreatingContactEdt() {
	return lastNameAfterCreatingContactEdt;
}

public WebDriver getDriver() {
	return driver;
}

public WebElement getFirstNameEdt() {
	return firstNameEdt;
}

public WebElement getLastNameEdt() {
	return lastNameEdt;
}

public WebElement getOrgNameEdt() {
	return orgNameEdt;
}

public WebElement getOrgNameImg() {
	return orgNameImg;
}

public WebElement getOrgSearchEdt() {
	return orgSearchEdt;
}

public WebElement getOrgSearchDd() {
	return orgSearchDd;
}

public WebElement getOrgSearchBtn() {
	return orgSearchBtn;
}

public WebElement getStartDateEdt() {
	return startDateEdt;
}

public WebElement getEndDateEdt() {
	return endDateEdt;
}

public WebElement getPhoneEdt() {
	return phoneEdt;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

public void createContact(String lastName)  //method overloading
{
	//firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	saveBtn.click();
}

public void createContactWitOrg(String lastName,String orgName)  //method overloading
{
	//firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	orgNameImg.click();
	wLib.switchToTabOnURL(driver, "Accounts&action");
	orgSearchEdt.sendKeys(orgName);
	orgSearchBtn.click();
	orgSearchDd.click();
	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	//switch to parent window
	wLib.switchToTabOnURL(driver, "Contacts&action");
	
	saveBtn.click();
}


public void createContact(String lastName,String phoneNum)  //method overloading
{
	//firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	phoneEdt.sendKeys(phoneNum);
	saveBtn.click();
}

public void createContact(String lastName,String startDates,String endDates) throws InterruptedException
{
	//firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	startDateEdt.clear();
	Thread.sleep(2000);
	startDateEdt.sendKeys(startDates);
	Thread.sleep(2000);
	endDateEdt.clear();
	Thread.sleep(2000);
	endDateEdt.sendKeys(endDates);
		saveBtn.click();
}

 
 
 
 
}
