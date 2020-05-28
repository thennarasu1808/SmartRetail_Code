package com.smartRetail.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author TKarunakaran
 *
 */

public class BaseClass {
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	public static 	String projectPath = System.getProperty("user.dir");
	public  static WebDriverWait  wait;

	public BaseClass() {
		try {
		//	prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	//log.debug("Config file loaded");
	try {
		//	prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//log.debug("OR file loaded");
	}
	
	@BeforeSuite
	public void setUp() throws Exception {
	
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestResultReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.loadXMLConfig("./extent-config.xml");

		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("System Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "IPTSFILS");
		extent.setSystemInfo("User Name", config.getProperty("username"));

	
		htmlReporter.config().setDocumentTitle("Smart Retail Automation");
		htmlReporter.config().setReportName("Smart Retail Regression Test");
		htmlReporter.config().setTheme(Theme.DARK);

		//log.debug("Extent Report initialized");
	}

	@BeforeClass

	public void browserSetup() throws Exception {
		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			
			//log.debug("Chrome browser launched");
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			//log.debug("Firefox browser launched");
		}

		driver.get(config.getProperty("url"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		String url=driver.getCurrentUrl();
		//log.debug("Lauching Application"+url);
		// Login to the Application

		//log.debug("Inside Login Test!!");
		// Enter Username
		WebElement username = driver.findElement(By.xpath(OR.getProperty("txtUsername")));
		username.sendKeys(config.getProperty("username"));
		//log.debug("User name entered!!");

		// Enter Password
		WebElement password = driver.findElement(By.xpath(OR.getProperty("txtPassword")));
		password.sendKeys(config.getProperty("password"));
		//log.debug("Password entered!!");

		WebElement Environment = driver.findElement(By.xpath(OR.getProperty("listBoxEnvironment")));
		Environment.click();
		//log.debug("Environment down arrow clicked!!");
		Thread.sleep(2000);

		String envName = config.getProperty("environment");

		if (envName.equalsIgnoreCase("IPTSFILS")) {
			WebElement EnvFILS = driver.findElement(By.xpath("//*[@id='isc_PickListMenu_0_row_1']"));
			EnvFILS.click();

		} else if (envName.equalsIgnoreCase("IPTSFILN")) {
			WebElement EnvFILN = driver.findElement(By.xpath("//*[@id='isc_PickListMenu_0_row_4']"));
			EnvFILN.click();

		} else if (envName.equalsIgnoreCase("IPTSFILL")) {
			WebElement EnvFILL = driver.findElement(By.xpath("//*[@id='isc_PickListMenu_0_row_6']"));
			EnvFILL.click();
		}

		Thread.sleep(2000);
		/*
		 * WebElement SelectedEnv = driver.findElement(By.xpath("//*[@id=\"isc_Q\"]"));
		 * String SelectedEnvironmnet = SelectedEnv.getText();
		 */
		//log.debug("Environment Selected is :  " + Environment.getText());

		// Click on Login button
		WebElement loginButton = driver.findElement(By.xpath(OR.getProperty("btnLogin")));
		loginButton.click();
		//log.debug("Login button clicked!!");
		//log.debug("Login successful!!");

	}



	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case Failed is " +result.getName());
			test.log(Status.FAIL, "Test Case failed is "+result.getThrowable());
			
            String ScreenShotPath=getScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Snapshot below: " + test.addScreenCaptureFromPath(ScreenShotPath));
		
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " has been Passed");
			// test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case : " + result.getName() + " has been skipped");
		}

		extent.flush();

	}

	/*@AfterClass
	public void quit() throws Exception {

		driver.close();

	}*/

	@AfterSuite
	public void tearDown() throws Exception {
		extent.flush();
	driver.quit();


	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
	
		String destination = System.getProperty("user.dir") + "\\test-output\\Screenshots" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}
}
