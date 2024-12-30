package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {// Rule-1 create a seperate java class
	WebDriver driver;
	//initialization of constructer
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Rule-2 Object Creation
	
	@FindBy(name="user_name")
	WebElement userNameEdt;
	
	@FindBy(name="user_password")
	WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	WebElement loginBtn;

		
	//Rule:3 Object initialization (this will done in the script)
	
	//Rule:4 Object Encapsulation
	

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

//Rule 5:provide action
	
	public void loginToApp(String url,String userName, String passWord) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(url);
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(passWord);
		loginBtn.click();
		
		
	}
}
