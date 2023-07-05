package com.ind.testCases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ind.Base.TestBase;
import com.ind.pageObjects.IndusindLeadConvert;
import com.ind.pageObjects.IndusindLoginpage;
import com.ind.utilities.ExcelUtilities;

//import com.ss.Pages.LeadConvert;
//import com.ss.Pages.LoginPage;
//import com.ss.Utilities.ExcelUtilities;

public class ConvertTest extends TestBase{
	
	IndusindLoginpage loginpage;
	IndusindLeadConvert leadconvert;
	String websheet="Contact";
	
	public ConvertTest()
	{
		super();
	}
	
	@BeforeMethod
	public void login() throws InterruptedException
	{
		
		launch();
		loginpage=new IndusindLoginpage();
		leadconvert=new IndusindLeadConvert();
		loginpage.login(p.getProperty("username"),p.getProperty("password"));
		
	}
	
	@DataProvider
	public Object getContact()
	{
		Object[][] obj1=ExcelUtilities.getExcel(websheet);
		return obj1;
	}
	
	@Test(dataProvider="getContact")
	public void success(String First,String Last) throws InterruptedException, IOException 
	{
		leadconvert.converting(First, Last);
		//e.test.log(Status.INFO, "Lead Conversion Done.");
	}
		

}