package com.ind.testCases;

import java.io.IOException;

import org.testng.Assert;

//import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ind.Base.TestBase;
import com.ind.utilities.JiraPolicy;
import com.ind.pageObjects.IndusindLeadPage;
import com.ind.pageObjects.IndusindLoginpage;
import com.ind.utilities.ExcelUtilities;
import com.ind.utilities.ExtentListeners;
//import com.ind.utilities.ExtentListeners;



public class LeadTest extends TestBase{
	
	IndusindLoginpage loginpage;
	IndusindLeadPage leadpage;
	String websheet="Lead";
	public static ExtentListeners e=new ExtentListeners();
	// ExtentListeners e=new ExtentListeners();
	
	public LeadTest()
	{
		super();
	}
	
	@BeforeMethod
	public void login() throws InterruptedException
	{
		
		launch();
		loginpage=new IndusindLoginpage();
	    leadpage=new IndusindLeadPage();
	    loginpage.login(p.getProperty("username"),p.getProperty("password"));
	    //e.test.log(Status.INFO, "Login successfull.");  
	   
	}	
//	@Parameters(("browsername"))
//	@BeforeMethod
//	public void launching(String browsername) throws InterruptedException
//	{
//		
//		SetUP1(browsername);
//		 loginpage=new LoginPage();
//		 leadpage=new LeadPage();
//		 loginpage.login(p.getProperty("username"),p.getProperty("password"));
//		
//		
//	}
	
	@DataProvider
	public Object getLead() {
		Object[][] obj1=ExcelUtilities.getExcel(websheet);
		return obj1;
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dataProvider="getLead")
	public void createlead(String FirstName,String LastName,String Company,String Street,String City,String State,String Zipcode,String Country,String Title,String Email,String Phone,String Mobile) throws InterruptedException
	{
	
		//leadpage.newlead(FirstName, LastName, Company, Street, City, State, Zipcode, Country,Title,Email,Phone,Mobile);
		e.test.log(Status.INFO, "Login successfull.");
		leadpage.newlead(FirstName, LastName, Company, Street, City, State, Zipcode, Country,Title,Email,Phone,Mobile,e);
		String fullname="Mr. "+FirstName+" "+LastName;
		 Assert.assertEquals(leadpage.verifyedlead(),fullname,"Lead creation is failed");
		System.out.println("Lead Creation Done");
		
	    //e.test.log(Status.INFO, "Lead Creation Done.");  
		
	
	}
	

	@AfterMethod
	public void teardown() throws IOException
	{
		takeScreenshotAtEndOfTest("lead Conversion");
		//e.test.log(Status.INFO, "Lead Conversion Done.");
		
	//	driver.close();
	}

}
