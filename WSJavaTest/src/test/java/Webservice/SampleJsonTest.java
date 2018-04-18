package Webservice;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Iterator;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SampleJsonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
	try 
		{
            // read the json file	
            FileReader reader = new FileReader(System.getProperty("user.dir")+"\\Test_Suite\\Tek.json");
            
            JSONParser jsonParser = new JSONParser();	
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject structure = (JSONObject)jsonObject.get("RestResponse");
            System.out.println("structure"+ structure);
            JSONArray lang1= (JSONArray) structure.get("result");	
            Iterator j = lang1.iterator();	
            while (j.hasNext()) {
                JSONObject innerObj = (JSONObject) j.next();              
                if(innerObj.get("country").equals("USA") && innerObj.get("name").equals("Alabama"))
                {
                	System.out.println("largest_city : "+ innerObj.get("largest_city") +"\n"+" Capital : " + innerObj.get("capital"));
                }
            }		
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();	
        }				
	}
}


//package Webservice;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Iterator;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class SampleJsonTest {
//
//	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
//	{
//		JSONParser parser = new JSONParser();
//        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(System.getProperty("user.dir")+"\\Test_Suite\\Tek.json"));       
//        for (Object o : jsonArray) 
//        {
//            JSONObject jsondata = (JSONObject) o;
////           if(jsondata.get("execute").equals("yes"))
////           {
//               JSONArray arrays = (JSONArray) jsondata.get("result");
//               for (Object object : arrays) 
//               {
//            	   JSONObject testdata = (JSONObject) object;
//            		System.out.println("Testcase Descrition --> : " + testdata.get("id"));
//            		
//            		
////            	} 
//           }        	
//        }	
//	}
//
//}