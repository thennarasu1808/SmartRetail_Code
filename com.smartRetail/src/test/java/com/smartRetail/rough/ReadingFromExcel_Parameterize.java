package com.smartRetail.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import com.smartRetail.base.TestBase;
import com.smartRetail.utilities.ExcelReader;

public class ReadingFromExcel_Parameterize extends TestBase{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement menuPurchasing = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//td[contains(text(),'Purchasing')]"))));
		System.out.println("Navigated to the Home page");
		Thread.sleep(5000);
	//	Assert.assertTrue(isElementPresent(By.xpath("//td[contains(text(),'Purchasing')]")),"Login not successful");
		log.debug("Home page loaded");
		menuPurchasing.click();
			
		WebElement menuNewRevisePO = driver.findElement(By.xpath("//td[contains(text(),'New/Revised')]"));
		menuNewRevisePO.click();
		Thread.sleep(3000);
		WebElement txtFileGroup = driver.findElement(By.name("FileGroup"));
		txtFileGroup.click();
		txtFileGroup.sendKeys(config.getProperty("filegroup"));
		Thread.sleep(2000);
		txtFileGroup.sendKeys(Keys.ENTER);
		
		String projectPath=System.getProperty("user.dir");
		ExcelReader reader=new ExcelReader(projectPath+"\\src\\test\\resources\\excel\\TestData_PO_Ceation.xlsx");
       int rowCount= reader.getRowCount("PurchaseOrderCreation");
    
       String parentWindowHandle = driver.getWindowHandle();
       System.out.println("Parent window's handle -> " + parentWindowHandle);
       for (String handle : driver.getWindowHandles()) {
    	    driver.switchTo().window(handle);
    	    }      
       
       WebElement poBlockOut = driver.findElement(By.name("HBLK"));
       poBlockOut.click();
       poBlockOut.sendKeys("TKA1234");
		
        for(int rowNum=2;rowNum<=rowCount;rowNum++) {
        	String blockout=reader.getCellData("PurchaseOrderCreation"," blockout", rowNum);
        	System.out.println(blockout);
        	String vendor=reader.getCellData("PurchaseOrderCreation"," vendor", rowNum);
        	System.out.println(vendor); 
        	
        	String shipTo=reader.getCellData("PurchaseOrderCreation"," shipto", rowNum);
        	System.out.println(shipTo);
        	
           	String department=reader.getCellData("PurchaseOrderCreation"," department", rowNum);
        	System.out.println(department);
        	
        	
        	
        	
        }

	}

}
