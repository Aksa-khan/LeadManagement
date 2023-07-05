package com.ind.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.ind.Base.TestBase;
//import com.ind.utilities.ExtentListeners;
import com.ind.utilities.ExtentListeners;

public class IndusindLeadPage extends TestBase{
	
	@FindBy(xpath="(//span[@class='slds-truncate'])[4]")
	WebElement Lead;
	
	@FindBy(linkText = "New")
	WebElement Newlead;
	
	@FindBy(xpath="(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[1]")
	WebElement salutation;
	
	@FindBy(xpath="(//input[@class='slds-input'])[3]")
	WebElement firstname;
	
	@FindBy(xpath="(//input[@class='slds-input'])[4]")
	WebElement lastname;
	
	@FindBy(xpath="(//input[@class='slds-input'])[6]")
	WebElement company;
	
	@FindBy(xpath="(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[4]")
	WebElement Status;
	
	@FindBy(xpath="(//textarea[@class='slds-textarea'])[1]")
	WebElement street;
	
    @FindBy(xpath="(//input[@class='slds-input'])[13]")
    WebElement city;
    
    @FindBy(xpath="(//input[@class='slds-input'])[14]")
    WebElement state;
    
    @FindBy(xpath="(//input[@class='slds-input'])[15]")
    WebElement zipcode;
	
    @FindBy(xpath="(//input[@class='slds-input'])[16]")
    WebElement country;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
	WebElement save;
    
    @FindBy(xpath="(//span[@class='slds-text-align_right slds-p-left_x-small'])[1]")
    WebElement Addtask;
    
    @FindBy(xpath="//input[@class='slds-combobox__input slds-input slds-combobox__input-value']")
    WebElement tasksub;
    
    @FindBy(xpath="//a[@class='select']")
    WebElement taskstatus;
    
    @FindBy(xpath="//button[@class='slds-button slds-button--brand cuf-publisherShareButton uiButton']")
    WebElement tasksave;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_icon-border-filled']")
    WebElement picklist;
    
    @FindBy(xpath="//a[@name='Convert']")
    WebElement convert;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
    WebElement converted;
    
  @FindBy(xpath="//lightning-formatted-name[@slot='primaryField']")
  WebElement verifylead;
  
  @FindBy(linkText = "Open - Not Contacted")
	WebElement verifystatus;
  
  @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
  WebElement gotolead;
  
    @FindBy(xpath = "(//input[@class='slds-input'])[5]")
	WebElement mobileno;
    @FindBy(xpath = "(//input[@class='slds-input'])[2]")
   	WebElement phone;
	
	@FindBy(xpath = "(//input[@class='slds-input'])[8]")
	WebElement title;

	@FindBy(xpath = "(//input[@class='slds-input'])[9]")
	WebElement email;
	
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[2]")
	WebElement leadsource;
	
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[3]")
	WebElement industry;
	
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[4]")
	WebElement leadstatus;
	
	@FindBy(xpath = "//th[@class='slds-cell-edit lockTrigger cellContainer']")
	WebElement leadnames;
    
    public IndusindLeadPage()
    {
    	PageFactory.initElements(driver, this);
    }
    
  
    public void newlead(String FirstName,String LastName,String Company,String Street,String City,String State,String Zipcode,String Country,String Title,String Email,String Phone,String Mobile,ExtentListeners e) throws InterruptedException 
    {
    	
        TestBase.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
     
		
		JavascriptExecutor js=(JavascriptExecutor)TestBase.driver;
	     js.executeScript("arguments[0].click();",Lead);
    	
    	  Newlead.click();
    	 e.test.log(com.aventstack.extentreports.Status.INFO, "New Button Clicked Successfully.");
    	 
    
    	phone.sendKeys(Phone);
    	 e.test.log(com.aventstack.extentreports.Status.INFO, "Phone Entered Successfully.");
    	mobileno.sendKeys(Mobile);
    	e.test.log(com.aventstack.extentreports.Status.INFO, "Mobile Entered Successfully.");
    	
    	Thread.sleep(5000);
    	JavascriptExecutor js3=(JavascriptExecutor)TestBase.driver;
	    js3.executeScript("arguments[0].click();",salutation);	
    	salutation.sendKeys(Keys.ARROW_DOWN);
    	salutation.sendKeys(Keys.ENTER);
    	
    	firstname.sendKeys(FirstName);
    	e.test.log(com.aventstack.extentreports.Status.INFO, "First Name entered Successfully.");
    	
    	lastname.sendKeys(LastName);
    	e.test.log(com.aventstack.extentreports.Status.INFO, "Last Name entered Successfully.");
    	
        company.sendKeys(Company);
        e.test.log(com.aventstack.extentreports.Status.INFO, "Company Name entered Successfully.");
        
        title.sendKeys(Title);
        e.test.log(com.aventstack.extentreports.Status.INFO, "Profile Name entered Successfully.");
        
    	email.sendKeys(Email);
    	e.test.log(com.aventstack.extentreports.Status.INFO, "Email entered Successfully.");
        
//        Status.click();
//        JavascriptExecutor js1=(JavascriptExecutor)TestBase.driver;
//	     js1.executeScript("arguments[0].click();",Status);	
//        Status.sendKeys(Keys.ARROW_DOWN);
//        Status.sendKeys(Keys.ARROW_DOWN);
//        Status.sendKeys(Keys.ENTER);
    
        street.sendKeys(Street);
        
        city.sendKeys(City);
        
        state.sendKeys(State);
        
        zipcode.sendKeys(Zipcode);
        
        country.sendKeys(Country);
        e.test.log(com.aventstack.extentreports.Status.INFO, "Address entered Successfully.");
        
        save.click();
        e.test.log(com.aventstack.extentreports.Status.INFO, "Lead created Successfully.");
        
        String fullname="Mr. "+FirstName+" "+LastName;
		Assert.assertEquals(verifyedlead(),fullname,"lead creation is failed");
		System.out.println("Created Lead is verifyed");
	       e.test.log(com.aventstack.extentreports.Status.INFO, "Created Lead is verifyed.");
		
		String ExpectedStatus="Open - Not Contacted";
		Assert.assertEquals(verifyleadstatus(),ExpectedStatus,"lead status is wrong");
		System.out.println("Created Leadstatus is verifyed"); 
	       e.test.log(com.aventstack.extentreports.Status.INFO, "Created Leadstatus is verifyed.");
	
    }	
    
    public String verifyedlead() {
		return verifylead.getText();
	}
    
    public String verifyleadstatus() {
		return verifystatus.getText();
	}

}