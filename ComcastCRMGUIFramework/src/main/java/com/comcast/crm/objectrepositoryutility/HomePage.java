package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	//initialization of constructer
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText ="Organizations")
	private	WebElement orgLink;
	
	@FindBy(linkText ="Contacts")
	private	WebElement contactLink;
	
	
	@FindBy(linkText ="More")
	private	WebElement moreLink;
	
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	
	
	
	public WebElement getAdminImg() {
		return adminImg;
	}




	public WebElement getSignOutLnk() {
		return signOutLnk;
	}

	@FindBy(linkText ="Campaigns")
	private	WebElement campaignsLink;
	public WebDriver getDriver() {
		return driver;
	}
	
	


	public WebElement getOrgLink() {
		return orgLink;
	}


	public WebElement getContactLink() {
		return contactLink;
	}


	public WebElement getMoreLink() {
		return moreLink;
	}


	public WebElement getCampaignsLink() {
		return campaignsLink;
	}
	
	public void navigateToCampaignPage()
	{
		Actions act =new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignsLink.click();
	}
	
	public void logOu() {
		
	Actions act=new Actions(driver);
	act.moveToElement(adminImg).perform();;
	signOutLnk.click();
	}
	
	

}
