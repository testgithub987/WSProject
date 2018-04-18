//package Utility;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentManager {
//    
//    static ExtentReports extent;
//    
//    
//    public static ExtentReports getInstance() {
//        return extent;
//    }
//    
//    public static ExtentReports createInstance(String fileName) {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
//        htmlReporter.config().setChartVisibilityOnOpen(true);
//        htmlReporter.config().setTheme(Theme.STANDARD);
//        htmlReporter.config().setDocumentTitle(fileName);
//        htmlReporter.config().setEncoding("utf-8");
//        htmlReporter.config().setReportName(fileName);
//        
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//        
//        return extent;
//    }
//    
//}


package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./extentreport.html";
	
	
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
	
        htmlReporter = new ExtentHtmlReporter(filePath);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);		
        htmlReporter.config().setDocumentTitle("Rest API Test Automation Report");
        htmlReporter.config().setReportName("Test Execution cycle");
        return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
}