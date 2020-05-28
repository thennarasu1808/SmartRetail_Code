package com.smartRetail.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {
public static WebDriver driver;
public static Properties config;
public static FileInputStream fis;
public static Logger log= Logger.getLogger("devpinoyLogger");

		/*Initializing the following
		 * WebDriver
		 * Properties
		 * Logs
		 * ExtentReports
		 * DB
		 * Excel
		 * Mail	
		 */

@BeforeSuite
public void setUp() throws IOException {
	if(driver==null) {
		try {
	    fis=new FileInputStream(System.getProperty("user.dir") + "\\src\\\\test\\resources\\properties\\config.properties");
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config file Loaded !!!");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		String browserName=config.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome browser launched");
			
		} else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Firefox browser launched");
		}
		
		String applicationUrl=config.getProperty("ur");
		driver.get(applicationUrl);
		log.debug("Navigate to url : "+applicationUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
		}
	}
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
			
		}
	
	}

	
@AfterSuite
public void tearDown() {
	
	
}
	
	}

