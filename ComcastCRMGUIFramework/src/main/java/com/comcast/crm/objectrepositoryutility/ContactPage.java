
package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	WebDriver driver;
	//initialization of constructer
	public ContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="search_text")
	private WebElement searchEdt;

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;
	
	
	
	
 

	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}


	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getSearchEdt() {
		return searchEdt;
	}


	public WebElement getSearchDd() {
		return searchDd;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	@FindBy(name="search_field")
	private WebElement searchDd;
	
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	
	
	 

}
