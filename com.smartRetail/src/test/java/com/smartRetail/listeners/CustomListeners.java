package com.smartRetail.listeners;


//import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustomListeners implements ITestListener {
	
	
    public void onFinish(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

 
    public void onStart(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

 	
    public void onTestFailure(ITestResult result) {					

    	System.out.println("Test case FAILED and Test case details are "+result.getName());
        		
    }		

 
    public void onTestSkipped(ITestResult result) {					
    	System.out.println("Test case SKIPPED and Test case details are "+result.getName());			
        		
    }		

  	
    public void onTestStart(ITestResult result) {					
    	System.out.println("Test case STARTED and Test case details are "+result.getName());				
        		
    }		

		
    public void onTestSuccess(ITestResult result) {					
    	System.out.println("Test case SUCCESS and Test case details are "+result.getName());			
        		
    }		
	

}
