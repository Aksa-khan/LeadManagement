package com.ind.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.Optional;

import com.ind.utilities.JiraPolicy;
import com.ind.utilities.JiraServiceProvider;

//import com.ind.utilities.ExtentListeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.rcarz.jiraclient.Issue;


public class TestBase
{

	static XSSFReader reader;
	public static WebDriver driver;
	public static Properties p;
//	public static ExtentListeners e=new ExtentListeners();
//	public  static EventFiringWebDriver e_driver;
	
	public TestBase()
	{
		try {
			p=new Properties();
		//	FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"C:\\Users\\Hp\\Desktop\\Salesforce Automation jan2022\\Automation_Salesforce-main\\Payroll360\\src\\main\\java\\Properties\\Config.properties" );
			
			FileInputStream f=new FileInputStream("D:\\Salesforce Project\\IndusindBank\\IndusindBank\\Configuration\\Config.properties");

			p.load(f);
		//	;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	public static void launch()
	{
		
		WebDriverManager.chromedriver().setup();
	     ChromeOptions options = new ChromeOptions();
	     options.addArguments("--disable-notifications");
	     driver=new ChromeDriver(options);
	     driver.manage().deleteAllCookies();
	     driver.get(p.getProperty("url"));
	     driver.manage().window().maximize();
	     driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	     driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//	     e_driver = new EventFiringWebDriver(driver);
	 	//eventListener = new WebEventListener();
	 	//	e_driver.register((WebDriverEventListener) eventListener);
//	 		driver = e_driver;
		
	}
	
	
	public static boolean verify(By webEle) {
		boolean check=driver.findElement(webEle).isDisplayed();
		System.out.println(check);
		return check;

	}

	public static void clickOnElement(By webEle)
	{
		driver.findElement((webEle)).click();
	}

	public static void sendKey(By webEle,String text) {
		driver.findElement(webEle).sendKeys(text);
	}

	public static void clickoncheckbox(By webEle) {

		boolean check=driver.findElement(webEle).isSelected();
		if(!check)
		{
			driver.findElement(webEle).click();

		}
	}
	public String titleTest() {
		return driver.getTitle();
	}

	public static void pressEnter() {
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER);
	}
	
	public static void scrollupto(By webEle) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",webEle);

	}

	public static void clickonElementbyjs(WebElement webEle) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", webEle);
	}
	
	
	
	public static void takeScreenshotAtEndOfTest(String filename) throws IOException 
	{    
		//Convert webdriver to TakeScreenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + filename + ".png"));
	}
	
	public WebDriver Brokenlinks() throws InterruptedException, IOException
    {                 
        try 
        {
            List<WebElement> links = driver
                    .findElements(By.xpath("//a[@data-aura-class='forceOutputLookup']"));
            System.out.println(links.size());

 

            for(WebElement link:links)
            {
                String url = link.getAttribute("href");
                URL Link=new URL(url);
                HttpURLConnection httpcon=(HttpURLConnection) Link.openConnection();
                httpcon.connect();
                int response = httpcon.getResponseCode();
                if(response>=400)
                {
                    System.out.println(url+" "+"link is broken");
                }
            }
            System.out.println("All Account contact and Opportunity links are valid link");

 

        } catch (Exception e) 
        {
            System.out.println("The URL is not valid.");
            System.out.println(e.getMessage());
        }
        return driver;
    }
	
	public static Issue generateJiraTicket( ITestResult result)
	{
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		System.out.println("JiraPolicy : "+jiraPolicy);
		boolean isTicketReady = jiraPolicy.logTicketReady();
		Issue NewIssue=null;
		

		if(isTicketReady)
		{
			//raise jira ticket:
			System.out.println("is ticket ready for jira :"+isTicketReady);
			JiraServiceProvider jiraSp= new JiraServiceProvider(TestBase.p.getProperty("jira_url"),TestBase.p.getProperty("jira_Mail") ,TestBase.p.getProperty("jira_API_key") , "LM");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() + "Something went wrong on Lead creation Please contact Admin";

			String issueDescription1 = result.getThrowable().getMessage()+"\n";
			String issueDescription="Lead Creation Fails due to duplicate record";
			//issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			issueDescription.concat(" beacuase "+result.getThrowable().getMessage());

			NewIssue= jiraSp.CreateJiraTicket("Bug",issueSummary, issueDescription, "Aksha Khan");
		}
		return NewIssue;
	}

	
	
	
	

}
