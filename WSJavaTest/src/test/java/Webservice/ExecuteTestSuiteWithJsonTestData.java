package Webservice;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import Webservice.Rest;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utility.ExtentManager;
import Utility.PropertyFileReading;
import Utility.TestExecutionMode;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ExecuteTestSuiteWithJsonTestData 
{
    /*public static void main(String a[])
    {
        new ExecuteTestSuite().beforeTest();
    }*/
    static Logger logger = Logger.getLogger(ExecuteTestSuite.class);
    private static String filePath = "./extentreport.html";
    public static ExtentReports extent;
    public static ExtentTest test;
    String log4jConfigFile = null;
    
    String str_tc_description = "";
    String str_tc_methodname = "";
    String str_tc_requestbody = "";
    String str_tc_requestheaders = "";
	String str_tc_endpointurl = "";
	String str_tc_expectedstatuscode = "";
	String str_tc_expectedelementpath = "";
	String str_tc_expectedvalue = "";
//    String log4jConfigFile = getProjectDirectory() +"\\properties\\log4j.xml";
//    DOMConfigurator.configure(log4jConfigFile);
    String getresponse = "";
    static String[] str_tc_expectedelementpaths = null;
    static String expectedelementpath = null;
    static String Value2 = null;
    static String Host_URL= null;

    
    @BeforeSuite
    public void beforeSuite()
    {
//	    	extent = new ExtentReports();
//	    	extent = new ExtentReports(“D://Report//advanceExtentReport.html”,true);
//	    	System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
    	extent = ExtentManager.GetExtent();

    }
    @Test
    public void runSuite() throws IOException, BiffException 
    {

    try 
    	{
	        //Variable declaration
//	        String str_tc_description = "";
//	        String str_tc_methodname = "";
//	        String str_tc_requestbody = "";
//	        String str_tc_requestheaders = "";
//	    	String str_tc_endpointurl = "";
//	    	String str_tc_expectedstatuscode = "";
//	    	String str_tc_expectedelementpath = "";
//	    	String str_tc_expectedvalue = "";
//	        String log4jConfigFile = getProjectDirectory() +"\\properties\\log4j.xml";
//	        DOMConfigurator.configure(log4jConfigFile);
//	        String getresponse = "";
//	        String[] str_tc_expectedelementpaths = null;
//	        String expectedelementpath = null;
//	        String Value2 = null;
	        
	        
	        String log4jConfigFile = getProjectDirectory() +"\\properties\\log4j.xml";
	        DOMConfigurator.configure(log4jConfigFile);

            List<String> testCasesToExecuteList = null;
            //Reading the content from properties file
            PropertyFileReading propFileReadObj = new PropertyFileReading();
            logger.info("########### Intialization Script - Start ###########");
            File projectSuite = new File(getProjectDirectory() + "\\Test_Suite\\Test_Suite.xls");
            System.out.println("getProjectDirectory()"+getProjectDirectory());
            Workbook workbookObj = Workbook.getWorkbook(projectSuite);
            Sheet worksheetObj = workbookObj.getSheet("Tests");
            Integer requestRowCount = worksheetObj.getRows();
            Integer requestColumnCount = worksheetObj.getColumns();
            System.out.println("requestRowCount :"+requestRowCount);
            System.out.println("requestColumnCount :"+requestColumnCount);
            logger.info("Request XML Sheet row count : " + requestRowCount);
            int ifTcCount = 0;
            String getExecutionType = propFileReadObj.getPropertyValue("TYPE_OF_EXECUTION").toUpperCase();
            Host_URL = propFileReadObj.getPropertyValue("Host_URL").toLowerCase();
            // to get List of  test scripts that will get executed based on the executed mode that was set in config.properties file            
//            testCasesToExecuteList = new TestExecutionMode().getExecutionType(worksheetObj, requestRowCount, getExecutionType);
            
            String TestDateFilevar = propFileReadObj.getPropertyValue("TestDateFile").toLowerCase();
            

       
            
            
            if(TestDateFilevar.equalsIgnoreCase("json"))
            {
            	JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(System.getProperty("user.dir")+"\\Test_Suite\\TestSuite.json"));
//            	JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(projectSuite));
                for (Object o : jsonArray) 
                {
                    JSONObject jsondata = (JSONObject) o;
                   if(jsondata.get("execute").equals("yes"))
                   {
                       JSONArray arrays = (JSONArray) jsondata.get("Testcase_Testdata");
                       for (Object object : arrays) 
                       {
                    	   JSONObject testdata = (JSONObject) object;
                    		System.out.println("Testcase Descrition --> : " + testdata.get("TC_DESCRIPTION"));
                    		System.out.println("Testcase ID --> : " + testdata.get("TC_ID"));
                    		
                    		str_tc_description = (String) testdata.get("TC_DESCRIPTION");
                            str_tc_methodname = (String) testdata.get("Method_Name");
                            logger.info("Method Name : " + str_tc_methodname);
                            str_tc_requestbody = (String) testdata.get("Reguest_Body");
                            logger.info("Request Body : " + str_tc_requestbody);
                            str_tc_requestheaders = (String) testdata.get("Request_Headers");
                            logger.info("Request Headers : " + str_tc_requestheaders);
                            str_tc_endpointurl = (String) testdata.get("EndPoint_URL");
                            logger.info("End Point URL : " + str_tc_endpointurl);
                            str_tc_expectedstatuscode = (String) testdata.get("Expected_Status_Code");
                            logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
                            str_tc_expectedelementpath = (String) testdata.get("Expected_ElementPath");
                            logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);
                            str_tc_expectedvalue = (String) testdata.get("Expected_Value");
                            logger.info("Expected Value : " + str_tc_expectedvalue);
                            
                            RestMethodCall(Host_URL, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue);
                            
                            
                    	} 
                   }        	
                }
            }
            else
            {
            	testCasesToExecuteList = new TestExecutionMode().getExecutionType(worksheetObj, requestRowCount, getExecutionType);
            	if (testCasesToExecuteList.size() > 0) 
                {
                	System.out.println("testCasesToExecuteList.size :" + testCasesToExecuteList.size());
                    for (String tcId : testCasesToExecuteList) 
                    {
                    	String testCaseId = tcId;
                    	System.out.println("Test Case ID to Execute:---> " + tcId);
                    	logger.info("Test Case ID to Execute:---> " + tcId);                     	
                          ifTcCount++; //increment the counter based on test case iteration
                          Date startTime = new Date();
                          str_tc_description = worksheetObj.getCell(worksheetObj.findCell("TC_DESCRIPTION").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          str_tc_methodname = worksheetObj.getCell(worksheetObj.findCell("Method Name").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Method Name : " + str_tc_methodname);
                          str_tc_requestbody = worksheetObj.getCell(worksheetObj.findCell("Reguest Body").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Request Body : " + str_tc_requestbody);
                          str_tc_requestheaders = worksheetObj.getCell(worksheetObj.findCell("Request Headers").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Request Headers : " + str_tc_requestheaders);
                          str_tc_endpointurl = worksheetObj.getCell(worksheetObj.findCell("End Point URL").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("End Point URL : " + str_tc_endpointurl);
                          str_tc_expectedstatuscode = worksheetObj.getCell(worksheetObj.findCell("Expected Status Code").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
                          str_tc_expectedelementpath = worksheetObj.getCell(worksheetObj.findCell("Expected Element Path & Name").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);
                          str_tc_expectedvalue = worksheetObj.getCell(worksheetObj.findCell("Expected Value").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Expected Value : " + str_tc_expectedvalue);
                                              
//                    }
                    
            
            // to get List of  test scripts that will get executed based on the executed mode that was set in config.properties file            
//            testCasesToExecuteList = new TestExecutionMode().getExecutionType(worksheetObj, requestRowCount, getExecutionType);
            //declare counter for number of test cases
           
            // Fetching data based on test case that is selected to execute
//            if (testCasesToExecuteList.size() > 0) 
//            {
//            	System.out.println("testCasesToExecuteList.size :" + testCasesToExecuteList.size());
//                for (String tcId : testCasesToExecuteList) 
//                {
//                        String testCaseId = tcId;
//                        System.out.println("Test Case ID to Execute:---> " + tcId);
//                        logger.info("Test Case ID to Execute:---> " + tcId);                     	
//                            ifTcCount++; //increment the counter based on test case iteration
//                            Date startTime = new Date();
//                            str_tc_description = worksheetObj.getCell(worksheetObj.findCell("TC_DESCRIPTION").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            str_tc_methodname = worksheetObj.getCell(worksheetObj.findCell("Method Name").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Method Name : " + str_tc_methodname);
//                            str_tc_requestbody = worksheetObj.getCell(worksheetObj.findCell("Reguest Body").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Request Body : " + str_tc_requestbody);
//                            str_tc_requestheaders = worksheetObj.getCell(worksheetObj.findCell("Request Headers").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Request Headers : " + str_tc_requestheaders);
//                            str_tc_endpointurl = worksheetObj.getCell(worksheetObj.findCell("End Point URL").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("End Point URL : " + str_tc_endpointurl);
//                            str_tc_expectedstatuscode = worksheetObj.getCell(worksheetObj.findCell("Expected Status Code").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
//                            str_tc_expectedelementpath = worksheetObj.getCell(worksheetObj.findCell("Expected Element Path & Name").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);
//                            str_tc_expectedvalue = worksheetObj.getCell(worksheetObj.findCell("Expected Value").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
//                            logger.info("Expected Value : " + str_tc_expectedvalue);                                                       
//                            Rest restservice= new Rest();
                            
                            RestMethodCall(Host_URL, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue);
                            // GET Method
 
                            
                            
                            
                            
                            
                            
//                            if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("GET"))
//                            {
//                             try
//                             {
//                            	test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
//                            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null ))
//                            		{
//                            			logger.info("Request body is not required for this case");                            
//                            		}
//                            	else
//                            		{
//                            			;
//                            		}
//                            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null ))
//                            		{
//                            			logger.info("Headers are not required for this case");                            
//                            		}
//                            	String getrequestoutput = restservice.getReguest(Host_URL, str_tc_endpointurl, str_tc_requestheaders, str_tc_expectedstatuscode);                         	                            	
//                            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
//                            	{
//	                            	
//	                            	if (str_tc_expectedvalue.equalsIgnoreCase(getrequestoutput))
//	                                {                        	   
//	                           		logger.info("Actual and Expected Response for GET Request Matched");
//	                           		test.pass("<font color=\"green\"></font>"+ "<font color=\"green\">" + "Actual Response : <br>"+ getrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
//	                                }
//	                            	else
//	                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ getrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
//                            	}
//                            	else
//                            	{                            		
//                            		if (str_tc_expectedelementpath.contains("#"))
//                            		{
//                            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
//                            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
//		                            	{
//		                            		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
//		                            		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
//		                            	}
//                            		}
//                            		else
//                            		{
//		                            	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
//		                            	Value2 = str_tc_expectedelementpath.split("=")[1];
//                            		}
//                            	String Value= restservice.compileJsonExpression(expectedelementpath, getrequestoutput);
//                        		if(Value.equalsIgnoreCase(Value2))
//                        		{
//	                           		logger.info("Actual and Expected Response for GET Request Matched");
//	                           		test.pass("<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" +"Actual Response : <br>"+ expectedelementpath + ":" +Value + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
//                        		}
//                        		else
//	                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ getrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                          	
//		                      }	
//                            }
//	                         catch(Exception e)
//	                         {	
//	                        	e.printStackTrace();
//	                         	test.log(Status.ERROR, e.getMessage());
//	                         }                            	
//                           }    	                            	                            	                
//                            
//                            // POST Method
//                            if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("POST"))
//                        	{ 
//                            	try
//                            	{
//                            		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
//                            	
//	                            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
//	                            	{
//	                            	 logger.info("Request body is not required for this case");                            
//	                            	}
//	                            	else
//	                            	{
//	                            	 ;
//	                            	}
//	                            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
//	                            	{
//	                            	 logger.info("Headers are not required for this case");                            
//	                            	}
//	                            	String postrequestoutput = restservice.postRequest(Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode);
//	                            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
//	                            	{		                            	
//		                            	if (str_tc_expectedvalue.equalsIgnoreCase(postrequestoutput))
//		                                {                        	   
//		                           		logger.info("Actual and Expected Resonse for POST Request Matched");
//		                           		test.pass("Actual Response : <br>"+ postrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
//		                                }
//		                            	else
//		                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ postrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
//	                            	}
//	                            	else
//	                            	{                            		
//	                            		if (str_tc_expectedelementpath.contains("#"))
//	                            		{
//	                            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
//	                            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
//			                            	{
//			                            		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
//			                            		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
//			                            	}
//	                            		}
//	                            		else
//	                            		{
//			                            	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
//			                            	Value2 = str_tc_expectedelementpath.split("=")[1];
//	                            		}
//
//	                            	String Value= restservice.compileJsonExpression(expectedelementpath, postrequestoutput);
//	                            	if(Value.equalsIgnoreCase(Value2))
//	                            	{
//			                           	logger.info("Actual and Expected Resonse for POST Request Matched");
//			                           	test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
//	                            	}
//	                            	else
//			                            test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ postrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
//	                            }
//                            }
//                                            		
//                            catch(Exception e)
//                            {
//                            	e.printStackTrace();
//                            	test.log(Status.ERROR, e.getMessage());
//                            }
//                        }
//                            // DELETE Method
//                            if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("DELETE"))
//                        	{ 
//                            	try
//                            	{
//                            		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
//                            	
//	                            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
//	                            	{
//	                            	 logger.info("Request body is not required for this case");                            
//	                            	}
//	                            	else
//	                            	{
//	                            	 ;
//	                            	}
//	                            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
//	                            	{
//	                            	 logger.info("Headers are not required for this case");                            
//	                            	}
//	                            	String deleterequestoutput = restservice.deleteReguest(Host_URL, str_tc_endpointurl, str_tc_requestheaders);
//	                            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
//	                            	{		                            	
//		                            	if (str_tc_expectedvalue.equalsIgnoreCase(deleterequestoutput))
//		                                {                        	   
//		                           		logger.info("Actual and Expected Resonse for POST Request Matched");
//		                           		test.pass("Actual Response : <br>"+ deleterequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
//		                                }
//		                            	else
//		                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ deleterequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
//	                            	}	                            	
//	                            	else
//	                            	{                            		
//	                            		if (str_tc_expectedelementpath.contains("#"))
//	                            		{
//	                            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
//	                            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
//			                            	{
//			                            		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
//			                            		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
//			                            	}
//	                            		}
//	                            		else
//	                            		{
//			                            	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
//			                            	Value2 = str_tc_expectedelementpath.split("=")[1];
//	                            		}
//		                            String Value= restservice.compileJsonExpression(expectedelementpath, deleterequestoutput);
//		                            if(Value.equalsIgnoreCase(Value2))
//		                            {
//		                            	logger.info("Actual and Expected Resonse for POST Request Matched");
//				                        test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
//		                           	}
//		                            else
//		                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ deleterequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                         	                           	
//	                            }
//                            }               		
//                            catch(Exception e)
//                            {
//                            	e.printStackTrace();
//                            	test.log(Status.ERROR, e.getMessage());
//                            }
//                        }  
//                         // PUT Method
//                            if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("PUT"))
//                        	{ 
//                            	try
//                            	{
//                            		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
//                            	
//	                            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
//	                            	{
//	                            	 logger.info("Request body is not required for this case");                            
//	                            	}
//	                            	else
//	                            	{
//	                            	 ;
//	                            	}
//	                            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
//	                            	{
//	                            	 logger.info("Headers are not required for this case");                            
//	                            	}
//	                            	String putrequestoutput = restservice.putRequest(Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode);
//	                            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
//	                            	{
//		                            	
//		                            	if (str_tc_expectedvalue.equalsIgnoreCase(putrequestoutput))
//		                                {                        	   
//		                           		logger.info("Actual and Expected Resonse for PUT Request Matched");
//		                           		test.pass("Actual Response : <br>"+ putrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
//		                                }
//		                            	else
//		                            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ putrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
//	                            	}
//	                            	else
//	                            	{                            		
//	                            		if (str_tc_expectedelementpath.contains("#"))
//	                            		{
//	                            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
//	                            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
//			                            	{
//			                            		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
//			                            		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
//			                            	}
//	                            		}
//	                            		else
//	                            		{
//			                            	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
//			                            	Value2 = str_tc_expectedelementpath.split("=")[1];
//	                            		}
//			                            String Value= restservice.compileJsonExpression(expectedelementpath, putrequestoutput);
//			                            if(Value.equalsIgnoreCase(Value2))
//			                            {
//			                            	logger.info("Actual and Expected Resonse for PUT Request Matched");
//					                        test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
//			                            }
//			                            else
//					                        test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ putrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                        	                           	
//                            	}
//                        	}               		
//                            catch(Exception e)
//                            {
//                            	e.printStackTrace();
//                            	test.log(Status.ERROR, e.getMessage());
//                            }
//                        }
                            
                            
                            
                            
                            
                    }
                  } 
            }
        }
    catch (Exception e)
    {
        e.printStackTrace();
        logger.error("error occured due to : " + e);                    
    }

        extent.flush();
    }
    
    //Get the project working directory
    public static String getProjectDirectory() 
    {
        String projectDir = System.getProperty("user.dir");
        return projectDir;
    }
    
    
    
    //Get the project working directory
            public void RestMethodCall(String Host_URL, String str_tc_description, String str_tc_methodname, String str_tc_requestbody, String str_tc_requestheaders, String str_tc_endpointurl, String str_tc_expectedstatuscode, String str_tc_expectedelementpath, String str_tc_expectedvalue) 
		    {
		    	
		    	Rest restservice= new Rest();
		        // GET Method
		        if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("GET"))
		        {
		         try
		         {
		        	test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
		        	if((str_tc_requestbody == "" )||(str_tc_requestbody == null ))
		        		{
		        			logger.info("Request body is not required for this case");                            
		        		}
		        	else
		        		{
		        			;
		        		}
		        	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null ))
		        		{
		        			logger.info("Headers are not required for this case");                            
		        		}
		        	String getrequestoutput = restservice.getReguest(Host_URL, str_tc_endpointurl, str_tc_requestheaders, str_tc_expectedstatuscode);                         	                            	
		        	if(str_tc_expectedelementpath == "" ||str_tc_expectedelementpath == null || str_tc_expectedelementpath.equals(""))
		        	{
		            	
		            	if (str_tc_expectedvalue.equalsIgnoreCase(getrequestoutput))
		                {                        	   
		           		logger.info("Actual and Expected Response for GET Request Matched");
		           		test.pass("<font color=\"green\"></font>"+ "<font color=\"green\">" + "Actual Response : <br>"+ getrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
		                }
		            	else
		            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ getrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
		        	}
		        	else
		        	{                            		
		        		if (str_tc_expectedelementpath.contains("#"))
		        		{
		        			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
		        			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
		                	{
		                		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
		                		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
		                	}
		        		}
		        		else
		        		{
		                	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
		                	Value2 = str_tc_expectedelementpath.split("=")[1];
		        		}
		        	String Value= restservice.compileJsonExpression(expectedelementpath, getrequestoutput);
		    		if(Value.equalsIgnoreCase(Value2))
		    		{
		           		logger.info("Actual and Expected Response for GET Request Matched");
		           		test.pass("<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" +"Actual Response : <br>"+ expectedelementpath + ":" +Value + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
		    		}
		    		else
		            	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ getrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                          	
		          }	
		        }
		         catch(Exception e)
		         {	
		        	e.printStackTrace();
		         	test.log(Status.ERROR, e.getMessage());
		         }                            	
		       }    	                            	                            	                
		        
		        // POST Method
		        if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("POST"))
		    	{ 
		        	try
		        	{
		        		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
		        	
		            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
		            	{
		            	 logger.info("Request body is not required for this case");                            
		            	}
		            	else
		            	{
		            	 ;
		            	}
		            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
		            	{
		            	 logger.info("Headers are not required for this case");                            
		            	}
		            	String postrequestoutput = restservice.postRequest(Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode);
		            	if(str_tc_expectedelementpath == null || str_tc_expectedelementpath.equals(""))
		            	{		                            	
		                	if (str_tc_expectedvalue.equalsIgnoreCase(postrequestoutput))
		                    {                        	   
		               		logger.info("Actual and Expected Resonse for POST Request Matched");
		               		test.pass("Actual Response : <br>"+ postrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
		                    }
		                	else
		                	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ postrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
		            	}
		            	else
		            	{                            		
		            		if (str_tc_expectedelementpath.contains("#"))
		            		{
		            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
		            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
		                    	{
		                    		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
		                    		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
		                    	}
		            		}
		            		else
		            		{
		                    	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
		                    	Value2 = str_tc_expectedelementpath.split("=")[1];
		            		}
		
		            	String Value= restservice.compileJsonExpression(expectedelementpath, postrequestoutput);
		            	if(Value.equalsIgnoreCase(Value2))
		            	{
		                   	logger.info("Actual and Expected Resonse for POST Request Matched");
		                   	test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
		            	}
		            	else
		                    test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ postrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
		            }
		        }
		                        		
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        	test.log(Status.ERROR, e.getMessage());
		        }
		    }
		        // DELETE Method
		        if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("DELETE"))
		    	{ 
		        	try
		        	{
		        		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
		        	
		            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
		            	{
		            	 logger.info("Request body is not required for this case");                            
		            	}
		            	else
		            	{
		            	 ;
		            	}
		            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
		            	{
		            	 logger.info("Headers are not required for this case");                            
		            	}
		            	String deleterequestoutput = restservice.deleteReguest(Host_URL, str_tc_endpointurl, str_tc_requestheaders);
		            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
		            	{		                            	
		                	if (str_tc_expectedvalue.equalsIgnoreCase(deleterequestoutput))
		                    {                        	   
		               		logger.info("Actual and Expected Resonse for POST Request Matched");
		               		test.pass("Actual Response : <br>"+ deleterequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
		                    }
		                	else
		                	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ deleterequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
		            	}	                            	
		            	else
		            	{                            		
		            		if (str_tc_expectedelementpath.contains("#"))
		            		{
		            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
		            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
		                    	{
		                    		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
		                    		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
		                    	}
		            		}
		            		else
		            		{
		                    	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
		                    	Value2 = str_tc_expectedelementpath.split("=")[1];
		            		}
		                String Value= restservice.compileJsonExpression(expectedelementpath, deleterequestoutput);
		                if(Value.equalsIgnoreCase(Value2))
		                {
		                	logger.info("Actual and Expected Resonse for POST Request Matched");
		                    test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
		               	}
		                else
		                	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ deleterequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                         	                           	
		            }
		        }               		
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        	test.log(Status.ERROR, e.getMessage());
		        }
		    }  
		     // PUT Method
		        if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("PUT"))
		    	{ 
		        	try
		        	{
		        		test = extent.createTest(str_tc_description, "Verify "+ str_tc_description + "Test case");
		        	
		            	if((str_tc_requestbody == "" )||(str_tc_requestbody == null))
		            	{
		            	 logger.info("Request body is not required for this case");                            
		            	}
		            	else
		            	{
		            	 ;
		            	}
		            	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null))
		            	{
		            	 logger.info("Headers are not required for this case");                            
		            	}
		            	String putrequestoutput = restservice.putRequest(Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode);
		            	if((str_tc_expectedelementpath == "" )||(str_tc_expectedelementpath == null))
		            	{
		                	
		                	if (str_tc_expectedvalue.equalsIgnoreCase(putrequestoutput))
		                    {                        	   
		               		logger.info("Actual and Expected Resonse for PUT Request Matched");
		               		test.pass("Actual Response : <br>"+ putrequestoutput + " <br> Matched "+ "<br> Expected Response : <br>" + str_tc_expectedvalue);
		                    }
		                	else
		                	test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ putrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");
		            	}
		            	else
		            	{                            		
		            		if (str_tc_expectedelementpath.contains("#"))
		            		{
		            			str_tc_expectedelementpaths = str_tc_expectedelementpath.split("#");
		            			for (int i =0 ; i<str_tc_expectedelementpaths.length; i++)
		                    	{
		                    		expectedelementpath = (str_tc_expectedelementpaths[i].split("=")[0]).toString();
		                    		Value2 = str_tc_expectedelementpaths[i].split("=")[1];
		                    	}
		            		}
		            		else
		            		{
		                    	expectedelementpath = str_tc_expectedelementpath.split("=")[0];
		                    	Value2 = str_tc_expectedelementpath.split("=")[1];
		            		}
		                    String Value= restservice.compileJsonExpression(expectedelementpath, putrequestoutput);
		                    if(Value.equalsIgnoreCase(Value2))
		                    {
		                    	logger.info("Actual and Expected Resonse for PUT Request Matched");
		                        test.pass("Actual Response : <br>"+ expectedelementpath + ":" +Value + "<font color=\"green\"><br> Matched : <br></font>"+ "<font color=\"green\">" + "Expected Response : <br>" + expectedelementpath + ":" + Value2);
		                    }
		                    else
		                        test.log(Status.FAIL, "<font color=\"red\">Actual Response : <br></font>"+ "<font color=\"red\">"+ putrequestoutput+"</font>"+"<br> <font size=\"5\" color=\"red\" font-weight: \"bold\"> Actual Response Doesn't Matched Expected Response </font><br>"+ "<font color=\"red\">Expected Response : </font><br>" + "<font color=\"red\">"+ str_tc_expectedvalue +"</font>");                        	                           	
		        	}
		    	}               		
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        	test.log(Status.ERROR, e.getMessage());
		        }
		    }  		
		  }
            
}