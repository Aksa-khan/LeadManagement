package com.ind.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ind.Base.TestBase;

public class IndusindLoginpage extends TestBase{
	
	@FindBy(xpath="//input[@id='username']")
	WebElement uname;
	
	
	@FindBy(xpath="//input[@id='password']")
	WebElement password1;
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement loginBtn; 
	
	@FindBy(xpath="//div[@class='slds-icon-waffle']")
	WebElement Applauncher;
	
	@FindBy(xpath="(//button[@class='slds-button'])[2]")
//	@FindBy(xpath="//lightning-button[@class='slds-button slds-p-horizontal--small']")
	WebElement viewall;
	
	@FindBy(xpath="(//p[@class='slds-truncate'])[8]")
	WebElement Sales;

	@FindBy(xpath="(//span[@class='slds-truncate'])[2]")
	WebElement Homeverify;
	
	public IndusindLoginpage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void login(String un,String pass) throws InterruptedException
	{
		TestBase.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		uname.sendKeys(un);
		password1.sendKeys(pass);
		loginBtn.click();
		
		Assert.assertEquals(loginverify(),"Home","Login fail");
		System.out.println("login successfully");
		
//	    Applauncher.click();
//	    Thread.sleep(5000);
//	    JavascriptExecutor js1=(JavascriptExecutor)TestBase.driver;
//	     js1.executeScript("arguments[0].click();",viewall);	
//	     
//	     JavascriptExecutor js2=(JavascriptExecutor)TestBase.driver;
//	     js2.executeScript("arguments[0].click();",Sales);	
//		
	}
	

	public String loginverify() {
		return Homeverify.getText();
	}
	
	
}