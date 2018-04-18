package Webservice;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class SampleXmlTest {

   public static void main(String[] args) {

      try {
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
            System.out.println("\nCurrent Element :" + nNode.getNodeName());           
            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
               Element eElement = (Element) nNode;
               System.out.println("Execute condition : " + eElement.getAttribute("execute"));
               if(eElement.getAttribute("execute").toUpperCase().equalsIgnoreCase("YES"))
               {
               System.out.println("TC-ID : " + eElement.getElementsByTagName("TC_ID").item(0).getTextContent());
               System.out.println("TC_DESCRIPTION : " + eElement.getElementsByTagName("TC_DESCRIPTION").item(0).getTextContent());
               System.out.println("Method_Name : " + eElement.getElementsByTagName("Method_Name").item(0).getTextContent());
               System.out.println("Reguest_Body : " + eElement.getElementsByTagName("Reguest_Body").item(0).getTextContent());
               System.out.println("Request_Headers : " + eElement.getElementsByTagName("Request_Headers").item(0).getTextContent());
               System.out.println("EndPoint_URL : " + eElement.getElementsByTagName("EndPoint_URL").item(0).getTextContent());
               System.out.println("Expected_Status_Code : " + eElement.getElementsByTagName("Expected_Status_Code").item(0).getTextContent());
               System.out.println("Expected_ElementPath : " + eElement.getElementsByTagName("Expected_ElementPath").item(0).getTextContent());
               System.out.println("Expected_Value : " + eElement.getElementsByTagName("Expected_Value").item(0).getTextContent());              
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
