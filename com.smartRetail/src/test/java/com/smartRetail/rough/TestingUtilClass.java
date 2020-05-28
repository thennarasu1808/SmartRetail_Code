package com.smartRetail.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class TestingUtilClass extends  GenericMethods{
	public static Properties OR = new Properties();
	public static Properties config = new Properties();

	public static FileInputStream fis;
	public static void main(String[] args) {
		
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		launchBrowser("chrome");
	openUrl("http://www.google.com");
	
	
	click( OR.getProperty("txtGoogle_NAME"));
	System.out.println("google clicked");
	
	try {
		
		type(OR.getProperty("txtGoogle_NAME"), config.getProperty("username"));
	
//driver.findElement(By.name("q")).sendKeys("selenium");
	}catch(Exception e) {
		e.getMessage();
	}
	
	//mouseHover("txtGoogle_NAME");

	}

}
