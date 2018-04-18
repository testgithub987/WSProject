package Webservice;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import Webservice.Rest;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Project.SampleTest.ExtentManager;
import Project.SampleTest.PropertyFileReading;
import Project.SampleTest.TestExecutionMode;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExecuteTestSuite
{
    /*public static void main(String a[])
    {
        new ExecuteTestSuite().beforeTest();
    }*/
    static Logger logger = Logger.getLogger(ExecuteTestSuite.class);
    public static ExtentReports extent;
    public static ExtentTest test;
    String log4jConfigFile = null;
    String str_tc_id = "";
    String str_tc_description = "";
    String str_tc_methodname = "";
    String str_tc_requestbody = "";
    String str_tc_requestheaders = "";
	String str_tc_endpointurl = "";
	String str_tc_expectedstatuscode = "";
	String str_tc_expectedelementpath = "";
	String str_tc_expectedvalue = "";
    String getresponse = "";
    static String[] str_tc_expectedelementpaths = null;
    static String expectedelementpath = null;
    static String Value2 = null;
    static String Value1 = null;
    static String Host_URL= null;
   
    @BeforeSuite
    public void beforeSuite()
    {
    		extent = ExtentManager.GetExtent();
    }
    @Test
    public void runSuite() throws IOException, BiffException 
    {
    try 
    	{
	        String log4jConfigFile = getProjectDirectory() +"\\properties\\log4j.xml";
	        DOMConfigurator.configure(log4jConfigFile);
            List<String> testCasesToExecuteList = null;
            PropertyFileReading propFileReadObj = new PropertyFileReading();
            logger.info("########### Intialization Script - Start ###########");
            File projectSuite = new File(getProjectDirectory() + "\\Test_Suite\\Test_Suite.xls");
            System.out.println("getProjectDirectory()"+getProjectDirectory());
            Workbook workbookObj = Workbook.getWorkbook(projectSuite);
            Sheet worksheetObj = workbookObj.getSheet("Tests");
            Integer requestRowCount = worksheetObj.getRows();
            Integer requestColumnCount = worksheetObj.getColumns();
            int ifTcCount = 0;
            String getExecutionType = propFileReadObj.getPropertyValue("TYPE_OF_EXECUTION").toUpperCase();
            Host_URL = propFileReadObj.getPropertyValue("Host_URL").toLowerCase();          
            String TestDateFilevar = propFileReadObj.getPropertyValue("TestDateFile").toLowerCase();

            switch(TestDateFilevar) 
            {
            case "json" :
            	System.out.println("Entered json file as Test Data file in config.properties file");
            	JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(System.getProperty("user.dir")+"\\Test_Suite\\TestSuite.json"));
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
                            str_tc_requestheaders = (String) testdata.get("Request_Headers");
                            logger.info("Request Headers : " + str_tc_requestheaders);
                            str_tc_endpointurl = (String) testdata.get("EndPoint_URL");
                            logger.info("End Point URL : " + str_tc_endpointurl);
                            str_tc_expectedstatuscode = (String) testdata.get("Expected_Status_Code");
                            logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
                            str_tc_expectedelementpath = (String) testdata.get("Expected_ElementPath");
                            logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);                           
                            RestMethodCall(Host_URL, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue);   
                    	} 
                   }        	
                }
               break;

            case "xls" :
            	System.out.println("Entered xls file as Test Data file in config.properties file");
                System.out.println("requestRowCount :"+requestRowCount);
                System.out.println("requestColumnCount :"+requestColumnCount);
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
                          str_tc_requestheaders = worksheetObj.getCell(worksheetObj.findCell("Request Headers").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Request Headers : " + str_tc_requestheaders);
                          str_tc_endpointurl = worksheetObj.getCell(worksheetObj.findCell("End Point URL").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("End Point URL : " + str_tc_endpointurl);
                          str_tc_expectedstatuscode = worksheetObj.getCell(worksheetObj.findCell("Expected Status Code").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
                          str_tc_expectedelementpath = worksheetObj.getCell(worksheetObj.findCell("Expected Element Path & Name").getColumn(), worksheetObj.findCell(tcId).getRow()).getContents().trim();
                          logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);
                          RestMethodCall(Host_URL, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue);
                    }
                }
               break;
               
            case "xml" :
            	try {
            		System.out.println("Entered xml file as Test Data file in config.properties file");
                    File inputFile = new File(System.getProperty("user.dir")+"\\Test_Suite\\TestSuite.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputFile);
                    doc.getDocumentElement().normalize();
                    System.out.println("Suite Name :" + doc.getDocumentElement().getNodeName());
                    System.out.println("Suite Description :" + doc.getDocumentElement().getAttribute("Description"));                   
                    NodeList nList = doc.getElementsByTagName("TestCase");
                    System.out.println("----------------------------");                    
                    for (int temp = 0; temp < nList.getLength(); temp++) 
                    {
                       Node nNode = nList.item(temp);           
                       if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                       {
                          Element eElement = (Element) nNode;
                          if(eElement.getAttribute("execute").toUpperCase().equalsIgnoreCase("YES"))
                          {
                        	  str_tc_id = eElement.getElementsByTagName("TC_ID").item(0).getTextContent();
                      		  logger.info("Test Case ID : " + str_tc_id);
                      		  str_tc_description = eElement.getElementsByTagName("TC_DESCRIPTION").item(0).getTextContent();
                      		  logger.info("Test Case Description : " + str_tc_description);
                              str_tc_methodname = eElement.getElementsByTagName("Method_Name").item(0).getTextContent();
                              logger.info("Method Name : " + str_tc_methodname);
                              str_tc_requestheaders = eElement.getElementsByTagName("Request_Headers").item(0).getTextContent();
                              logger.info("Request Headers : " + str_tc_requestheaders);
                              str_tc_endpointurl = eElement.getElementsByTagName("EndPoint_URL").item(0).getTextContent();
                              logger.info("End Point URL : " + str_tc_endpointurl);
                              str_tc_expectedstatuscode = eElement.getElementsByTagName("Expected_Status_Code").item(0).getTextContent();
                              logger.info("Expected Status Code : " + str_tc_expectedstatuscode);
                              str_tc_expectedelementpath = eElement.getElementsByTagName("Expected_ElementPath").item(0).getTextContent();
                              logger.info("Expected Element Path in JSon Response : " + str_tc_expectedelementpath);                           
                              RestMethodCall(Host_URL, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue);             
                          }
                       }
                    }
                 } catch (Exception e) {
                    e.printStackTrace();
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
    
    public void RestMethodCall(String Host_URL, String str_tc_description, String str_tc_methodname, String str_tc_requestbody, String str_tc_requestheaders, String str_tc_endpointurl, String str_tc_expectedstatuscode, String str_tc_expectedelementpath, String str_tc_expectedvalue) 
    {   	
    	Rest restservice= new Rest();
        // GET Method
        if((str_tc_methodname.toUpperCase()).equalsIgnoreCase("GET"))
        {
         try
         {
        	if((str_tc_requestheaders == "" )||(str_tc_requestheaders == null ))
        		{
        			logger.info("Headers are not required for this case");                            
        		}
        	String getrequestoutput = restservice.getReguest(Host_URL, str_tc_endpointurl, str_tc_requestheaders, str_tc_expectedstatuscode);                         	                            	
        	System.out.println("getrequestoutput : "+getrequestoutput);
        	JSONParser jsonParser = new JSONParser();	
            JSONObject jsonObject = (JSONObject) jsonParser.parse(getrequestoutput);	
            System.out.println("jsonObject : " +jsonObject);
          
            JSONObject structure = (JSONObject)jsonObject.get("RestResponse");
            System.out.println("structure"+ structure);
            
            if (str_tc_expectedelementpath.contains("#"))
    		{
	        	Value1 = str_tc_expectedelementpath.split("#")[0];
	        	Value2 = str_tc_expectedelementpath.split("#")[1];
	            JSONArray lang1= (JSONArray) structure.get("result");	
	            Iterator j = lang1.iterator();	
	            while (j.hasNext()) 
	            {
	                JSONObject innerObj = (JSONObject) j.next();              
	                if(innerObj.get("country").equals(Value1) && innerObj.get("name").equals(Value2))
	                {
	                	System.out.println("");
	                	System.out.println("***********************************************************************************************");
	                	System.out.println("largest_city : "+ innerObj.get("largest_city") +"\n"+"Capital : " + innerObj.get("capital"));
	                	System.out.println("");
	                	System.out.println("***********************************************************************************************");
	                }
	            }	            
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