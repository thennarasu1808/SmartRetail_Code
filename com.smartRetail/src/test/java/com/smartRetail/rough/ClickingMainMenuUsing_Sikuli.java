package com.smartRetail.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.smartRetail.base.BaseClass;



public class ClickingMainMenuUsing_Sikuli {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	public static 	String projectPath = System.getProperty("user.dir");
	public  static WebDriverWait  wait;
	
	
	public static void main (String args[]) {
		
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
			
			//Clicking Purchasing tab using Sikuli
			Screen screen=new Screen();
			Pattern img_PurchasingTab=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_Main_Menu_Arrow.png");
			screen.wait(img_PurchasingTab,30000);
			Thread.sleep(30000);
			screen.click(img_PurchasingTab);
			System.out.println("Main menu  clicked");
			Thread.sleep(3000);

}
}
	
}
