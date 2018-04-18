package Webservice;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Map;
//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpHost;
//import org.apache.commons.httpclient.URI;
//import org.apache.commons.httpclient.auth.BasicScheme;
//import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.jayway.jsonpath.JsonPath;

public class Rest 
{
	static Logger logger = Logger.getLogger(Rest.class);
//	DefaultHttpClient httpClient = new DefaultHttpClient();
	CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public String getReguest(String Host_URL, String URI, String str_tc_requestheaders, String str_tc_expectedstatuscode) throws IOException
	{
		
		String getrequestoutput = null;
		try 
		{
			URI = Host_URL + URI;
			System.out.println("URI" + URI);
			HttpGet getRequest = new HttpGet(URI);
			
			String[] Headers;
			if(str_tc_requestheaders.contains("#"))
			{
				Headers = str_tc_requestheaders.split("#");
				for(int i =0; i<Headers.length; i++)
	              {
	              	System.out.println(Headers[i]);
	              	String[] header = Headers[i].split("=");
	              	getRequest.addHeader(header[0], header[1]);
	              }
			}
			else
			{
				String[] header = str_tc_requestheaders.split("=");
				getRequest.addHeader(header[0], header[1]);
			}			
			HttpResponse response = httpClient.execute(getRequest);
			int statuscode = response.getStatusLine().getStatusCode();
			System.out.println("statuscode : " +statuscode);
			String actulastatuscode = String.valueOf(statuscode);
			System.out.println("actulastatuscode : " +actulastatuscode);
			if (actulastatuscode.equalsIgnoreCase(str_tc_expectedstatuscode)) 
			{
				logger.info("Get Request response code is 200");
			}
			else
			{
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			getrequestoutput = br.readLine();
//			JSONObject jObject  = new JSONObject(getresponse);
//			getresponse = jObject.toString();
//			logger.info("getresponse : "+ getresponse);
			
			
//			if (getrequestoutput.equalsIgnoreCase((getresponse))) 
//			{
//				logger.info("Matched Get Request body response");
//			}
//			else
//			{
//				throw new RuntimeException("Failed : Response body didn't matched"
//				   + getrequestoutput);
//			}
			
			
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		 } 
		catch (IOException e) 
		{
			e.printStackTrace();
		 }
		finally
		{
			httpClient.getConnectionManager().shutdown();
		}
	return getrequestoutput;
	}
		
	public String postRequest(String Host_URL, String URI, String RequestBody, String str_tc_requestheaders, String str_tc_expectedstatuscode) throws IOException
	{	
	
		String postrequestoutput = null ;
		try 
		{			
//			DefaultHttpClient httpClient = new DefaultHttpClient();
			URI = Host_URL + URI;
			HttpPost postRequest = new HttpPost(URI);
//			StringEntity input = new StringEntity("{\"email\":\"mlrssuperadmin@gmail.com\",\"password\":\"P@ssw0rd\"}");	
			StringEntity input = new StringEntity(RequestBody);
			String[] Headers;
			if(str_tc_requestheaders.contains("#"))
			{
				Headers = str_tc_requestheaders.split("#");
				for(int i =0; i<Headers.length; i++)
	              {
	              	System.out.println(Headers[i]);
	              	String[] header = Headers[i].split("=");
	              	postRequest.addHeader(header[0], header[1]);
	              }
			}
			else
			{
				String[] header = str_tc_requestheaders.split("=");
              	postRequest.addHeader(header[0], header[1]);
			}
			
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			int statuscode = response.getStatusLine().getStatusCode();
			String actulastatuscode = String.valueOf(statuscode);
			if (actulastatuscode.equalsIgnoreCase(str_tc_expectedstatuscode)) 
			{
				logger.info("Status code is : " + actulastatuscode);
			}
			else
			{
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			postrequestoutput = br.readLine();
//			JSONObject jObject  = new JSONObject(getresponse);
//			getresponse = jObject.toString();
//			logger.info("getresponse : "+ getresponse);
			
			
//			if (postrequestoutput.equalsIgnoreCase(getresponse)) 
//			{
//				logger.info("Matched Get Request body response");
//			}
//			else
//			{
//				throw new RuntimeException("Failed : Response body didn't matched"
//				   + postrequestoutput);
//			}
			
			
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		 } 
		catch (IOException e) 
		{
			e.printStackTrace();
		 }
		finally
		{
			httpClient.getConnectionManager().shutdown();
		}				
	return postrequestoutput;
	}

	public String putRequest(String Host_URL, String URI, String RequestBody, String str_tc_requestheaders, String str_tc_expectedstatuscode) throws IOException 
	{ 
		String putrequestoutput = null;
		
        try { 
        	URI = Host_URL + URI;
            HttpPut putrequest = new HttpPut(URI); 
            
            
            
            
            StringEntity input = new StringEntity(RequestBody);
			String[] Headers;
			if(str_tc_requestheaders.contains("#"))
			{
				Headers = str_tc_requestheaders.split("#");
				for(int i =0; i<Headers.length; i++)
	              {
	              	System.out.println(Headers[i]);
	              	String[] header = Headers[i].split("=");
	              	putrequest.addHeader(header[0], header[1]);
	              }
			}
			else
			{
				String[] header = str_tc_requestheaders.split("=");
				putrequest.addHeader(header[0], header[1]);
			}
			
			putrequest.setEntity(input);
			HttpResponse response = httpClient.execute(putrequest);
			int statuscode = response.getStatusLine().getStatusCode();
			String actulastatuscode = String.valueOf(statuscode);
			if (actulastatuscode.equalsIgnoreCase(str_tc_expectedstatuscode)) 
			{
				logger.info("Status code is : " + actulastatuscode);
			}
			else
			{
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			putrequestoutput = br.readLine();
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
//            String[] Headers;
//			if(str_tc_requestheaders.contains("#"))
//			{
//				Headers = str_tc_requestheaders.split("#");
//				for(int i =0; i<Headers.length; i++)
//	              {
//	              	System.out.println(Headers[i]);
//	              	String[] header = Headers[i].split("=");
//	              	request.addHeader(header[0], header[1]);
//	              }
//			}
//			else
//			{
//				String[] header = str_tc_requestheaders.split("=");
//				request.addHeader(header[0], header[1]);
//			}
//            
//            
//            
//            
//            
//            HttpResponse res = httpClient.execute(request); 
//            String content = EntityUtils.toString(res.getEntity()); 
//            return content; 
//        	} 
//        catch (Exception ex) 
//        { 
//            ex.printStackTrace(); 
//            return null; 
//        } 	
//	}
			
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		 } 
		catch (IOException e) 
		{
			e.printStackTrace();
		 }
		finally
		{
			httpClient.getConnectionManager().shutdown();
		}				
	return putrequestoutput;
	}
	
	

	public String deleteReguest(String Host_URL, String URI, String str_tc_requestheaders) throws Exception 
	{
		try 
		{
			URI = Host_URL + URI;
			HttpDelete request = new HttpDelete(URI);
			
			
			
			String[] Headers;
			if(str_tc_requestheaders.contains("#"))
			{
				Headers = str_tc_requestheaders.split("#");
				for(int i =0; i<Headers.length; i++)
	              {
	              	System.out.println(Headers[i]);
	              	String[] header = Headers[i].split("=");
	              	request.addHeader(header[0], header[1]);
	              }
			}
			else
			{
				String[] header = str_tc_requestheaders.split("=");
				request.addHeader(header[0], header[1]);
			}
			

			
			
			
			HttpResponse res = httpClient.execute(request);
			String content = EntityUtils.toString(res.getEntity());
			return content;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}

    public static void main(String a[]) throws IOException 
    {
    	
    	
//    	Rest rest = new Rest();
//    	String getrequestresponse = rest.getReguest("https://echo.getpostman.com/get?test=123");
//    	System.out.println("GET Request Response :"+ getrequestresponse);
//    	
//    	String postrequestresponse = rest.postReguest("https://echo.getpostman.com/get?test=123");
//    	System.out.println("POST Request Response :"+ postrequestresponse);
    	
    	


//        HttpClient httpClient = new HttpClient();
//
//        HttpMethod method = new GetMethod("https://echo.getpostman.com/get?test=123");
//
////        method.setRequestHeader("sourceSystem", "testSourceSystem");
////        method.setRequestHeader("versionNumber", "1.0");
////        method.setRequestHeader("securityToken", "testToken");
////        method.setRequestHeader("transactionId", "test");
//
//        int statusCode = httpClient.executeMethod(method);
//        System.out.println(statusCode);
//        
//        if (statusCode == 200)
//        {
//        	System.out.println("Sucessful status");
//        }
//        else
//        {
//        	System.out.println("Failure status");
//        }
//        System.out.println(method.getResponseBodyAsString());
//
//        HttpMethod putmethod = new PutMethod("https://echo.getpostman.com/put");
//        int putstatusCode = httpClient.executeMethod(putmethod);
//        System.out.println(putstatusCode);
//        System.out.println(putmethod.getResponseBodyAsString());
//        
//        if (putstatusCode == 200)
//        {
//        	System.out.println("Sucessful status");
//        }
//        else
//        {
//        	System.out.println("Failure status");
//        }
//        System.out.println(putmethod.getResponseBodyAsString());
    }
    
    
    public String compileJsonExpression(String expression,String payload)
    {
        Object ObservedJsonValue;
        JsonPath jsonPathObject = JsonPath.compile(expression);
        ObservedJsonValue= jsonPathObject.read(payload);
        return String.valueOf(ObservedJsonValue);
    }
    
    public String compileJsonListExpression(String expression, String payload)
    {
        String ObservedJsonValue;
        String ObservedJsonValue1;
        JsonPath jsonPathObject = JsonPath.compile(expression);
        ObservedJsonValue1 = (String)jsonPathObject.read(payload);
        System.out.println("ObservedJsonValue1: " + ObservedJsonValue1);
        
        
        JsonPath jsonPathObject1 = JsonPath.compile("$.id");
        String ObservedJsonValue2= jsonPathObject.read(ObservedJsonValue1);
        return ObservedJsonValue2;
    }
}
