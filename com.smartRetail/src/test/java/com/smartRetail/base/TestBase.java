package com.smartRetail.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.smartRetail.rough.GenericMethods;

public class TestBase extends GenericMethods{

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static 	String projectPath = System.getProperty("user.dir");
	public  static WebDriverWait  wait;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReporter extent;
	public static ExtentTest extentTest;

	@BeforeSuite
	public void setUp() throws InterruptedException {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("config property file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR property file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("chrome browser launched !!!");

			} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox browser launched !!!");
			}

			driver.get(config.getProperty("url"));
			log.debug("Navigated to: " + config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

			// Login to the Application

			log.debug("Inside Login Test!!");
			// Enter Username
			WebElement username = driver.findElement(By.xpath(OR.getProperty("txtUsername")));
			username.sendKeys(config.getProperty("username"));
			log.debug("User name entered!!");

			// Enter Password
			WebElement password = driver.findElement(By.xpath(OR.getProperty("txtPassword")));
			password.sendKeys(config.getProperty("password"));
			log.debug("Password entered!!");

			WebElement Environment = driver.findElement(By.xpath(OR.getProperty("listBoxEnvironment")));
			Environment.click();
			log.debug("Environment down arrow clicked!!");
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
			log.debug("Environment Selected is :  " + Environment.getText());

			// Click on Login button
			WebElement loginButton = driver.findElement(By.xpath(OR.getProperty("btnLogin")));
			loginButton.click();
			log.debug("Login button clicked!!");
			log.debug("Login successful!!");

		}

	}

	public  boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;

		}

	}

	
	
	  @AfterSuite 
	  
	  public void tearDown() { 
		  if (driver != null) { driver.quit(); }
	  log.debug("Test execution completed!!");
	  
	  }
	 

}
