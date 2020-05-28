package com.smartRetail.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.smartRetail.base.BaseClass;
import com.smartRetail.utilities.ExcelReader;

public class ReceivePurchaseOrder extends BaseClass {
	public static Actions action;

	@Test
	public void defReceivePurchaseOrder() throws InterruptedException {
		test=extent.createTest("defReceivePurchaseOrder");
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		try {
			Thread.sleep(5000);

			WebDriverWait wait = new WebDriverWait(driver, 120);
			boolean invisible = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[2]/span")));
			if (invisible) {
				WebElement menuWarehouse = wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//td[contains(text(),'Warehouse')]"))));
				JavascriptExecutor jse = (JavascriptExecutor) driver;

				jse.executeScript("arguments[0].scrollIntoView()", menuWarehouse);
				menuWarehouse.click();
				log.debug("Warehouse Tab Clicked");
			}
			/*
			 * WebElement tabPicker =driver.findElement(By.xpath("//div[4]/div[3]/img"));
			 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
			 * "//div[4]/div[3]/img"))); JavascriptExecutor jse2 =
			 * (JavascriptExecutor)driver;
			 * jse2.executeScript("arguments[0].scrollIntoView()", tabPicker);
			 * tabPicker.click();
			 */

		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		}
		/*
		 * WebElement wareHouse= driver.findElement(By.
		 * xpath("(.//*[normalize-space(text()) and normalize-space(.)='Replenishment'])[2]/following::div[4]"
		 * )); wareHouse.click();
		 */

		Thread.sleep(15000);

		ExcelReader reader = new ExcelReader(projectPath + "\\src\\test\\resources\\excel\\TestData_PO_Receiving.xlsx");
		int rowCount = reader.getRowCount("PurchaseOrderToBeReceived");
		System.out.println("Row count is: " + rowCount);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String poNumber = reader.getCellData("PurchaseOrderToBeReceived", " ponumber", rowNum);
			System.out.println(poNumber);

			String receptQty = reader.getCellData("PurchaseOrderToBeReceived", " receiptquantity", rowNum).replace(".0",
					"");
			System.out.println(receptQty);
			String invoiceNum = reader.getCellData("PurchaseOrderToBeReceived", " invoice", rowNum).replace(".0", "");
			System.out.println(invoiceNum);
			log.debug("Navigated to the Warehouse page");
			try {
				boolean invisible = wait
						.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[2]/span")));
				if (invisible) {

					driver.findElement(By.xpath("//*[@name = 'HONO$148l']")).click();
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//*[@name = 'HONO$148l']")).sendKeys(poNumber);
			log.debug("PO number entered in the column filter");
			driver.findElement(By.xpath("//*[@name = 'HONO$148l']")).sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.debug("Implicit wait of 10 sec applied");
			driver.findElement(By.xpath("(//div[4]/div/table/tbody/tr/td[5])[1]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@name = 'HIN#']")).click();
			log.debug("Invoice number entered");
			driver.findElement(By.xpath("//*[@name = 'HIN#']")).sendKeys(invoiceNum);
			log.debug("Invoice number entered");
			/*
			 * WebElement poLineItem = driver.findElement(By.xpath(
			 * "(.//*[normalize-space(text()) and normalize-space(.)='Filter: None'])[2]/following::div[40]"
			 * )); action = new Actions(driver);
			 * action.moveToElement(poLineItem).doubleClick().build().perform(); try {
			 * WebElement
			 * receptQnty=driver.findElement(By.xpath("//*[@name = 'ReceiptQty']"));
			 * JavascriptExecutor jse2 = (JavascriptExecutor)driver;
			 * jse2.executeScript("arguments[0].scrollIntoView()", receptQnty);
			 * receptQnty.click(); }catch(Exception e) { e.printStackTrace(); }
			 */

			driver.findElement(By.xpath("//div[3]/div/div[2]/div/div[3]/div/table/tbody/tr/td/table/tbody/tr/td/img"))
					.click();
			log.debug("Receive PO icon clicked");

			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Select receiving options.'])[2]/following::div[12]"))
					.click();
Thread.sleep(3000);
			/*
			 * if(driver.findElement(By.
			 * xpath("(.//*[normalize-space(text()) and normalize-space(.)='Print receiving manifest in grid format?'])[1]/following::td[6]"
			 * )).isDisplayed()){ try { System.out.println("TrY cATCH  ");
			 * action.moveToElement(driver.findElement(By.xpath(
			 * "(.//*[normalize-space(text()) and normalize-space(.)='Select putaway specifications.'])[2]/following::td[1]"
			 * ))) .click().build().perform();
			 * System.out.println("Its a Warehouse, so accept button clicked"); } catch
			 * (Exception e) { System.out.println("unexpected alert not present"); }
			 */

			/*if (driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Print receiving manifest in grid format?'])[1]/following::td[6]"))
					.isDisplayed()) {*/
			/*
			 * try { System.out.println("TrY cATCH  ");
			 * action.moveToElement(driver.findElement(By.xpath(
			 * "(.//*[normalize-space(text()) and normalize-space(.)='OK'])")))
			 * .click().build().perform(); System.out.println("OK button clicked"); } catch
			 * (Exception e) { System.out.println("unexpected alert not present"); }
			 */

			}
		}

	}


