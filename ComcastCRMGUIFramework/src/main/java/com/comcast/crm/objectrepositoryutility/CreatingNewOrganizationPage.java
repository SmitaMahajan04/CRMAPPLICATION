package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	//initialization of constructer
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	
	@FindBy(name="industry")
	private WebElement industryEdt;
	
	@FindBy(name="phone")
	private WebElement phoneEdt;
	
	
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getIndustryEdt() {
		return industryEdt;
	}

	@FindBy(name="button")
	private WebElement saveBtn;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName)  //method overloading
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industry)
	{
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industryEdt);
		sel.selectByValue(industry);
		saveBtn.click();
	}
	
	public void createOrgWithPhone(String orgName,String phone)  //method overloading
	{
		orgNameEdt.sendKeys(orgName);
		phoneEdt.sendKeys(phone);
		saveBtn.click();
	}
	

}
