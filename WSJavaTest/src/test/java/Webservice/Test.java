package Webservice;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class Test {
	
	static String json = "{\"store\": { \"books\": [ {\"author\": \"Stephen King\", \"title\": \"IT\"}, {\"author\": \"Agatha Christie\", \"title\": \"The ABC Murders\"} ] } }";
	static String jsonPathExpression = "$.store.books[?(@.title=='The ABC Murders')]"; 
//	JsonNode jsonNode = JsonPath.parse(json).read(jsonPathExpression, JsonNode.class);

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Test t = new Test();
		List a = t.compileJsonExpression(jsonPathExpression, json); 
		for (int inT=0;inT<a.size();inT++)
	      {
	          System.out.println(a.get(inT));
	      }
		

	}
	
    public List compileJsonExpression(String jsonPathExpression,String json)
    {
//        String ObservedJsonValue;
        JsonPath jsonPathObject = JsonPath.compile(jsonPathExpression);
        List ObservedJsonValue= jsonPathObject.read(json);
        return ObservedJsonValue;
    }

}
