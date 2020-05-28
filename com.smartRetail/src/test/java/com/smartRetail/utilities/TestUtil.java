package com.smartRetail.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.smartRetail.base.TestBase;

public class TestUtil extends TestBase {
	
	public static String ScreenshotPath;
	public static void captureScreenshot() throws IOException {
		
		File  scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\test-output\\Screenshots\\error.jpg"));
	
		
	}

	

}
