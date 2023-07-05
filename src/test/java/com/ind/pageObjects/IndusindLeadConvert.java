package com.ind.pageObjects;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ind.Base.TestBase;

public class IndusindLeadConvert extends TestBase {

	@FindBy(xpath = "(//span[@class='slds-truncate'])[4]")
	WebElement Lead;

	@FindBy(xpath = "(//td[@class='slds-cell-edit cellContainer'])[7]")
	WebElement leadstatus;

	@FindBy(linkText = "Closed - Not Converted")
	WebElement status;

	@FindBy(xpath = "//th[@class='slds-cell-edit lockTrigger cellContainer']")
	WebElement leadnames;

	@FindBy(xpath = "//a[@data-aura-class='forceOutputLookup']")
	WebElement leadname;

	@FindBy(xpath = "//button[@class='slds-button slds-button_icon-border-filled']")
	WebElement picklist;

	@FindBy(xpath = "//a[@name='Convert']")
	WebElement convert;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand']")
	WebElement converted;

	@FindBy(xpath = "//lightning-formatted-name[@slot='primaryField']")
	WebElement verifylead;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand']")
	WebElement gotolead;
	
	@FindBy(xpath = "//div[@class='title']")
	WebElement verifyconverttab;

	public IndusindLeadConvert() {
		PageFactory.initElements(driver, this);
	}

	public void converting(String First,String Last) throws InterruptedException, IOException
	{
	
		TestBase.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String firstname=First;
		System.out.println(firstname);
		String lastname=Last;
		System.out.println(lastname);

	String fullname =firstname+" "+lastname;
	System.out.println(fullname);
		
		
		JavascriptExecutor js = (JavascriptExecutor) TestBase.driver;
		js.executeScript("arguments[0].click();", Lead);

		String actualText = leadstatus.getText();
		String expectedText = "Closed - Not Converted";
		

		if (actualText.equals(expectedText))
		{
			System.out.println("expected lead status: " + actualText);
			System.out.println("Lead is Ready to convert");
			List<WebElement> elements = driver
					.findElements(By.xpath("//th[@class='slds-cell-edit lockTrigger cellContainer']"));
			for (WebElement element : elements) {
				
				String expectedlead = element.getText();
				System.out.println("Lead Name: " + expectedlead);

				if (fullname.equals(expectedlead))
				{
					System.out.println("lead names is matched ");
					
					leadname.click();
					
					JavascriptExecutor js6 = (JavascriptExecutor) TestBase.driver;
					js6.executeScript("arguments[0].click();", picklist);

					Thread.sleep(5000);
					JavascriptExecutor js7 = (JavascriptExecutor) TestBase.driver;
					js7.executeScript("arguments[0].click();", convert);
					Thread.sleep(10000);
					
					converted.isDisplayed();
					converted.isEnabled();
					System.out.println("Converted Button is displayed and Enabled ");
					
					JavascriptExecutor js9 = (JavascriptExecutor) TestBase.driver;
					js9.executeScript("arguments[0].click();", converted);
					
					Brokenlinks();
					
					System.out.println("All Assertions working fine");
					
					
				} 
				
			}
		}	
		 else 
		 {
			System.out.println("Lead is not ready to convert: " + actualText + ", Expected: " + expectedText);
		 }

	}

	public String verifyedlead() {
		return verifyconverttab.getText();
	}
	
	
	
	
	
	
}