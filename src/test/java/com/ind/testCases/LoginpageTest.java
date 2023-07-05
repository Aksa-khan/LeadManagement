package com.ind.testCases;


	import org.testng.Assert;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import com.ind.Base.TestBase;
	import com.ind.pageObjects.IndusindLoginpage;



	public class LoginpageTest extends TestBase {
		
	IndusindLoginpage loginpage;
		
		public LoginpageTest()
		{
			super();
		}
		
//		@BeforeMethod
//		public void setup() {
//			launch();
//			loginpage=new IndusindLoginpage();
//			 
//		}
		
//		@Parameters(("browsername"))
//		@BeforeTest
//		public void newlaunch(String browsername)
//		{
//			SetUP(browsername);
//			 loginpage=new IndusindLoginpage();
//		}
		
		
		
		
//		@Test(priority=1)
//		public void verifyLoginPage() 
//		{
//			//loginpage.loginverify();
//			//Assert.assertEquals(loginpage.loginverify(),"Setup","Login fail");
//		}
	//	
		@Test
		public void loginT() throws InterruptedException
		{
			loginpage.login(p.getProperty("username"),p.getProperty("password"));
		//	loginpage.loginverify();
			Assert.assertEquals(loginpage.loginverify(),"Home","Login fail");
			System.out.println("login successfully");
		}
		
//		@AfterMethod
//	    public void tearDown() 
//		{
//			//driver.close();
//		}
		
		

	}

