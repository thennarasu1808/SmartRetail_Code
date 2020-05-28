package com.smartRetail.rough;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
	
public static WebDriver driver;
public static WebDriverWait wait;
public static Alert alert;
public static Actions actions;
public static void launchBrowser(String browser) {
	
	if(browser=="chrome") {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
	}else if (browser=="firefox") {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
}
public static void openUrl(String url) {
	driver.get(url);
	driver.manage().window().maximize();
}

public static void click(String locator) {
	
	if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(locator)).click();
	}else if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(locator)).click();
	}	else if(locator.endsWith("_ID")){
		driver.findElement(By.id(locator)).click();
	}else if(locator.endsWith("_NAME")){
		driver.findElement(By.name(locator)).click();
	}
}

public static void type(String locator, String value) {
	
	if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}else if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(locator)).sendKeys(value);
	}	else if(locator.endsWith("_ID")){
		driver.findElement(By.id(locator)).sendKeys(value);
	}else if(locator.endsWith("_NAME")){
		driver.findElement(By.name(locator)).sendKeys(value);
	}
}

public static void clear(String locator) {
	if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(locator)).clear();
	}else if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(locator)).clear();
	}	else if(locator.endsWith("_ID")){
		driver.findElement(By.id(locator)).clear();
	}
}

public static void getText(String locator) {
	if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(locator)).getText();
	}else if(locator.endsWith("_CSS")){
		driver.findElement(By.xpath(locator)).getText();
	}	else if(locator.endsWith("_ID")){
		driver.findElement(By.xpath(locator)).getText();
	}
	
}

public static void waitForElement(WebElement element) {

 wait = new WebDriverWait(driver, 120);
wait.until(ExpectedConditions.elementToBeClickable(element));
}

public static void waitTillElementFound(WebElement ElementTobeFound,
int seconds) {
wait = new WebDriverWait(driver, 120);
wait.until(ExpectedConditions.visibilityOf(ElementTobeFound));
}

public static void takeScreenshot(String Destination)
throws Exception {
File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(file, new File(Destination));
}

public static void pressKeyDown(WebElement element) {
element.sendKeys(Keys.DOWN);
}

public static void pressKeyEnter(WebElement element) {
element.sendKeys(Keys.ENTER);

}

public static void pressKeyUp(WebElement element) {
element.sendKeys(Keys.UP);
}

public static void moveToTab(WebElement element) {
element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
}

public static void waitTillPageLoad(int i) {

driver.manage().timeouts().pageLoadTimeout(i, TimeUnit.SECONDS);

}

public static void keyboardEvents(WebElement webelement, Keys key,
String alphabet) {
webelement.sendKeys(Keys.chord(key, alphabet));

}

public static void forward() {
driver.navigate().forward();
}

public static void back() {
driver.navigate().back();
}

public static void refresh() {
driver.navigate().refresh();	
}

public static void Click(WebElement element) {
try {
boolean elementIsClickable = element.isEnabled();
while (elementIsClickable) {
element.click();
}

} catch (Exception e) {
System.out.println("Element is not enabled");
e.printStackTrace();
}
}

public static boolean alertAccept() {
try {
 alert = driver.switchTo().alert();
String str = alert.getText();
System.out.println("Alert text is: "+str);

alert.accept();
return true;

} catch (Exception e) {

System.out.println("no alert ");
return false;

}
}

public static boolean alertDismiss() {
try {
 alert = driver.switchTo().alert();
String str = alert.getText();
System.out.println(str);

alert.dismiss();
return true;

} catch (Exception e) {

System.out.println("no alert ");
return false;

}
}

public static void checkCheckbox(WebElement checkbox) {
boolean checkstatus;
checkstatus = checkbox.isSelected();
if (checkstatus == true) {
System.out.println("Checkbox is already checked");
} else {
checkbox.click();
System.out.println("Checked the checkbox");
}
}
public static void selectRadiobutton(WebElement Radio) {
boolean checkstatus;
checkstatus = Radio.isSelected();
if (checkstatus == true) {
System.out.println("RadioButton is already checked");
} else {
Radio.click();
System.out.println("Selected the Radiobutton");
}
}

public static void uncheckCheckbox(WebElement checkbox) {
boolean checkstatus;
checkstatus = checkbox.isSelected();
if (checkstatus == true) {
checkbox.click();
System.out.println("Checkbox is unchecked");
} else {
System.out.println("Checkbox is already unchecked");
}
}

public static void deselectRadioButton(WebElement Radio) {
boolean checkstatus;
checkstatus = Radio.isSelected();
if (checkstatus == true) {
Radio.click();
System.out.println("Radio Button is deselected");
} else {
System.out.println("Radio Button was already Deselected");
}
}
public static void dragAndDrop(WebElement source,
WebElement destination) {
 actions = new Actions(driver);
 actions.dragAndDrop(source, destination);
}

public static void mouseHover(WebElement hoverOn)
throws InterruptedException {
Actions builder = new Actions(driver);
builder.moveToElement(hoverOn).perform();
Thread.sleep(2000);

}
public static void doubleClick(WebElement element)
throws InterruptedException {
actions = new Actions(driver);
actions.doubleClick(element).perform();
Thread.sleep(2000);

}

public static String getToolTip(WebElement toolTipElement)
throws InterruptedException {
String tooltip = toolTipElement.getAttribute("title");
System.out.println("Tool text : " + tooltip);
return tooltip;
}

public static void selectByIndex(WebElement element, int index) {
Select select = new Select(element);
select.selectByIndex(index);
}

public static void selectByValue(WebElement element, String value) {
	Select select =new Select(element)	;
	select.selectByValue(value);
}
public static void selectByText(WebElement element, String Text) {
	Select select =new Select(element)	;
	select.selectByValue(Text);
}





}


	


