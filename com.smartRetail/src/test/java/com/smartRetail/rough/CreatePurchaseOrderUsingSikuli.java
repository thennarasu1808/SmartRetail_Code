package com.smartRetail.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.smartRetail.base.BaseClass;
import com.smartRetail.utilities.ExcelReader;

public class CreatePurchaseOrderUsingSikuli extends BaseClass {

	@Test
	public void defCreatePurchaseOrder() throws InterruptedException, FindFailed {
	String projectPath=System.getProperty("user.dir");
System.out.println(projectPath);
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		
		//Clicking Purchasing tab using Sikuli
		Screen screen=new Screen();
		Pattern img_PurchasingTab=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_purchasing_tab.png");
		screen.wait(img_PurchasingTab,30000);
		Thread.sleep(30000);
		screen.click(img_PurchasingTab);
		System.out.println("Purchasing tab clicked");
		Thread.sleep(3000);
		
		//Clicking New/Revised tab
		Pattern img_New_RevisedTab=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_New-Revised_tab.png");
		screen.wait(img_New_RevisedTab,10000);
		screen.click(img_New_RevisedTab);
		System.out.println("New/Revised tab clicked");
				
		//Enter Filegroup
		WebElement txtFileGroup = driver.findElement(By.name("FileGroup"));
		txtFileGroup.click();
		Thread.sleep(2000);
		txtFileGroup.sendKeys(config.getProperty("filegroup"));
		Thread.sleep(3000);
		txtFileGroup.sendKeys(Keys.ENTER);
		log.debug(" Filegroup is entered");
		Thread.sleep(2000);

		//Click on New order tab
		WebElement btnNewOrder = driver.findElement(By.xpath("//div[contains(text(),'New Order')]"));
		btnNewOrder.click();
		log.debug(" New order tab is clicked");
		Thread.sleep(5000);

		
		// Fetching the data from Excel sheet
		ExcelReader reader = new ExcelReader(projectPath + "\\src\\test\\resources\\excel\\TestData_PO_Ceation.xlsx");
		int rowCount = reader.getRowCount("PurchaseOrderCreation");
		System.out.println("Row count is: " +rowCount);
		
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String blockout = reader.getCellData("PurchaseOrderCreation", "blockout", rowNum);
			System.out.println(blockout);

			String vendor = reader.getCellData("PurchaseOrderCreation", "vendor", rowNum).replace(".0", "");
			System.out.println(vendor);

			String shipTo = reader.getCellData("PurchaseOrderCreation", "shipto", rowNum).replace(".0", "");
			System.out.println(shipTo);

			String department = reader.getCellData("PurchaseOrderCreation", "department", rowNum).replace(".0", "");
			System.out.println(department);

			String QntyOrdered = reader.getCellData("PurchaseOrderCreation", "orderquantity", rowNum).replace(".0",
					"");
			System.out.println(QntyOrdered);

			String itemNum = reader.getCellData("PurchaseOrderCreation", "itemnumber", rowNum);
			System.out.println("Item no is : "+itemNum);

			try {
				WebElement poBlockOut = driver.findElement(By.name("HBLK"));
				poBlockOut.click();
				poBlockOut.sendKeys(blockout);
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				WebElement ImgPoNumber = driver.findElement(By.xpath(
						" (.//*[normalize-space(text()) and normalize-space(.)='Unassigned'])[1]/following::img[1]"));
				ImgPoNumber.click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				WebElement poVendor = driver.findElement(By.name("HVEN"));
				poVendor.click();
				poVendor.sendKeys(vendor);
				Thread.sleep(3000);
				poVendor.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				WebElement poShiptoStore = driver.findElement(By.name("HSTR"));
				poShiptoStore.click();
				poShiptoStore.sendKeys(shipTo);
				Thread.sleep(3000);
				poShiptoStore.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				WebElement poDepartment = driver.findElement(By.name("HDPT"));
				poDepartment.click();
				poDepartment.sendKeys(department);
				Thread.sleep(3000);
				poDepartment.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			WebElement btnSave = driver.findElement(By.xpath("//*[(text() = 'Save' or . = 'Save')]"));
			btnSave.click();
			Thread.sleep(8000);

			Pattern img_ItemsTab=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_Items_tab.png");
			screen.wait(img_ItemsTab,10000);
			screen.click(img_ItemsTab);
			Thread.sleep(8000);
			
			WebElement txtGroupBy = driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group by'])[3]/following::div[1]"));
			String groupByTextValue = txtGroupBy.getText();
			System.out.println("Gropu by text :" + groupByTextValue);
			// txtGroupBy.click();

			if (!groupByTextValue.equalsIgnoreCase("Style/Color/Size")) {
				driver.findElement(By.xpath(
						"(.//*[normalize-space(text()) and normalize-space(.)='Group by'])[3]/following::div[1]"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[(text() = 'Style/Color/Size' or . = 'Style/Color/Size')]")).click();
				;
				Thread.sleep(2000);
				
				Pattern Img_AddItem=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_Add_Item_Btn.png");
				screen.wait(Img_AddItem,10000);
				screen.click(Img_AddItem);
				Thread.sleep(3000);
			} else {

				driver.findElement(By.xpath("//*[(text() = 'Add Item' or . = 'Add Item')]")).click();
			}
			Thread.sleep(3000);
			WebElement txtItemNumber = driver.findElement(By.xpath("//input[@name='ItemId']"));
			txtItemNumber.click();
			txtItemNumber.sendKeys(itemNum);
			txtItemNumber.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			WebElement txtQtyOrdered = driver.findElement(By.xpath("//*[@name = 'IQTY']"));
			txtQtyOrdered.sendKeys(QntyOrdered);
			Thread.sleep(3000);
			
			Pattern img_SaveAndExitTab=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_Save_and_Exit_tab.png");
			screen.wait(img_SaveAndExitTab,10000);
			screen.click(img_SaveAndExitTab);
			Thread.sleep(8000);
			System.out.println("clicked save and exit");

			//Actions a = new Actions(driver);

			try {
				Pattern warningMsg=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\OkButton_WarningMsg.png");
				screen.wait(warningMsg,10000);
				screen.click(warningMsg);

				/*// driver.switchTo().alert().accept();
				System.out.println("TrY cATCH  ");
				a.moveToElement(driver.findElement(By.xpath(
						"(.//*[normalize-space(text()) and normalize-space(.)='Book retail should not be greater than suggested retail.'])[1]/following::td[1]")))
						.click().build().perform(); */
				System.out.println("Warning message 'OK' button Clicked");
			} catch (Exception e) {
				System.out.println("unexpected alert not present");
							}
			Thread.sleep(15000);	
			
			  try {
			  					
					Pattern img_SaveAndExitTab2=new Pattern(projectPath+"\\src\\test\\resources\\snapShots\\SR_Save_and_Exit2_tab.png");
					screen.wait(img_SaveAndExitTab2,10000);
					screen.click(img_SaveAndExitTab2);
			  //driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Exit'])[1]/following::td[1]")).click();
			  
			  } catch (StaleElementReferenceException e) {
			  
			  WebElement saveAndExit=driver.findElement(By.
			  xpath("(.//*[normalize-space(text()) and normalize-space(.)='Exit'])[1]/following::td[1]"
			  )); saveAndExit.clear(); System.out.println(e.getMessage()); }
			 
			}
			
		
			}
}
