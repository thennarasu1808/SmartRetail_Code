package com.smartRetail.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.smartRetail.base.BaseClass;
import com.smartRetail.utilities.ExcelReader;

public class RevisePurchaseOrder extends BaseClass {

	public static Actions actions;

	@Test
	public void defRevisePurchaseOrder() throws InterruptedException {
		test=extent.createTest("defRevisePurchaseOrder");
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		
		
		// Explicitly wait until the purchasing tab is present
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			boolean invisible = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[2]/span")));
			if (invisible) {
			WebElement menuPurchasing = wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//td[contains(text(),'Purchasing')]"))));

			// Click purchasing tab
			menuPurchasing.click();
			log.debug("Purchasing tab is clicked");
			}
		}catch(ElementClickInterceptedException e) {
			e.printStackTrace();
		}
		
		
		Thread.sleep(3000);
		// Click on Search icon

		WebElement iconSearch = driver.findElement(By.xpath("//td[2]/table/tbody/tr/td[2]/table/tbody/tr/td/span/img"));
		iconSearch.click();
		Thread.sleep(6000);

		// Click Block out column filter and entering PO number
		ExcelReader reader = new ExcelReader(
				projectPath + "\\src\\test\\resources\\excel\\TestData_PO_Revision.xlsx");
		int rowCount = reader.getRowCount("PurchaseOrderToBeRevised");
		System.out.println("Row count is: " + rowCount);


		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String blockOut = reader.getCellData("PurchaseOrderToBeRevised", " blockout", rowNum);
			System.out.println(blockOut);
			
			String itemNum = reader.getCellData("PurchaseOrderToBeRevised", " itemnumber", rowNum);
			System.out.println(itemNum);
			
			String QntyOrdered = reader.getCellData("PurchaseOrderToBeRevised", " orderquantity", rowNum).replace(".0","");
			System.out.println(QntyOrdered);
			
			String fileGrp=reader.getCellData("PurchaseOrderToBeRevised", " filegroup", rowNum).replace(".0","");
			System.out.println(fileGrp);
	try {
	WebElement columnFilterBlockOut = driver
			.findElement(By.xpath("//*[@name = 'HBLK$148l']"));
	columnFilterBlockOut.click();
	columnFilterBlockOut.sendKeys(blockOut);
	columnFilterBlockOut.sendKeys(Keys.ENTER);
	Thread.sleep(5000);
	actions = new Actions(driver);
	WebElement rowPoNumber = driver.findElement(By.xpath("//div[3]/div/table/tbody/tr/td[2]/div"));
	actions.contextClick(rowPoNumber).perform();
	
}catch(StaleElementReferenceException e) {
	System.out.println(e.getMessage());
}
WebElement btnEdit = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Xy'])[1]/following::div[10]"));
actions.click(btnEdit).perform();
Thread.sleep(5000);
WebElement fileGroupNo = driver.findElement(By.xpath("//input[@name='FileGroup']"));
fileGroupNo.sendKeys(fileGrp);
Thread.sleep(3000);
WebElement btnOK = driver.findElement(By.xpath("(//*[(text() = 'OK' or . = 'OK')])[2]"));
btnOK.click();
Thread.sleep(10000);

try {
	System.out.println("Try Catch Block");
	WebElement tabItems = wait
			.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[(text() = 'Items' or . = 'Items')])[1]")));
			tabItems.click();
}catch (ElementClickInterceptedException e) {
	actions.moveToElement(driver.findElement(By.xpath("(//*[(text() = 'Items' or . = 'Items')])[1]"))).click();

}catch(NullPointerException e) {
	actions.moveToElement(driver.findElement(By.xpath("(//*[(text() = 'Items' or . = 'Items')])[1]"))).click();
}

Thread.sleep(3000);

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
driver.findElement(By.xpath("//*[(text() = 'Add Item' or . = 'Add Item')]")).click();
} else {

driver.findElement(By.xpath("//*[(text() = 'Add Item' or . = 'Add Item')]")).click();
}
Thread.sleep(2000);

WebElement txtItemNumber = wait
.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='ItemId']")));
txtItemNumber.click();
txtItemNumber.sendKeys(itemNum);
txtItemNumber.sendKeys(Keys.TAB);
Thread.sleep(3000);

WebElement txtQtyOrdered = driver.findElement(By.xpath("//*[@name = 'IQTY']"));
txtQtyOrdered.sendKeys(QntyOrdered);
Thread.sleep(3000);

driver.findElement(
By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Exit'])[2]/following::td[1]"))
.click();
Thread.sleep(8000);
System.out.println("clicked save and exit");

Actions a = new Actions(driver);

try {

// driver.switchTo().alert().accept();
System.out.println("TrY cATCH  ");
a.moveToElement(driver.findElement(By.xpath(
	"(.//*[normalize-space(text()) and normalize-space(.)='Book retail should not be greater than suggested retail.'])[1]/following::td[1]")))
	.click().build().perform();
System.out.println("Warning message 'OK' button Clicked");
} catch (Exception e) {
System.out.println("unexpected alert not present");
		}
Thread.sleep(10000);
try {
	driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Exit'])[1]/following::td[1]")).click();

	} catch (StaleElementReferenceException e) {
	
		WebElement saveAndExit=driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Exit'])[1]/following::td[1]"));
		saveAndExit.clear();
		System.out.println(e.getMessage());
	}
	}
}
}
