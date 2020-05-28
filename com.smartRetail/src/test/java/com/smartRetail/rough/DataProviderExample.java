package com.smartRetail.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smartRetail.utilities.ExcelReader;

public class DataProviderExample {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static 	String projectPath = System.getProperty("user.dir");
	public  static WebDriverWait  wait;
	public static ExcelReader reader=new ExcelReader("C:\\Users\\tkarunakaran\\Desktop\\Test-Data.xlsx");
	@BeforeSuite
	
			@Test(dataProvider="getData")
	public void readData(String userName, String password) {
		
	}
	
		
		@DataProvider
		public Object[][] getData(){
		String sheetName= "testdata1";
			int rows=reader.getRowCount("testdata1");
			System.out.println("No of rows : " +rows);
			int cols=reader.getColumnCount("testdata1");
			System.out.println("No of columns : " +cols);
			Object[][] data=new Object[rows=1][cols];
			
			for(int rowNum=2; rowNum<=rows;rowNum++) {
				for(int colNum=0;colNum<cols;colNum++) {
					data[rowNum-2][colNum]=reader.getCellData(sheetName, colNum, rowNum);
				}
			}
			return data;
		}

	}


