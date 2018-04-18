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
	CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public String getReguest(String Host_URL, String URI, String str_tc_requestheaders, String str_tc_expectedstatuscode) throws IOException
	{
		
		String getrequestoutput = null;
		try 
		{
			URI = Host_URL + URI;
			System.out.println("URI : " + URI);
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
			String actulastatuscode = String.valueOf(statuscode);
			if (actulastatuscode.equalsIgnoreCase(str_tc_expectedstatuscode)) 
			{
				logger.info("Get Request response code is 200");
			}
			else
			{
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}			
			HttpEntity httpEntity = response.getEntity();
			getrequestoutput = EntityUtils.toString(httpEntity);			
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
