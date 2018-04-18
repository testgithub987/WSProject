package Examples;

import org.openqa.selenium.WebDriver;

public class SingletonPatternDemo 
{

	public static void main(String[] args) 
	{
		
	      //illegal construct
	      //Compile Time Error: The constructor SingleObject() is not visible
//		BaseClass object = new BaseClass();
		System.setProperty("webdriver.firefox.marionette","C:\\Users\\ezn5fix\\Downloads\\geckodriver.exe");
//		System.setProperty("webdriver.ie.driver","C:\\WebServicesProject\\WebServicesProject\\iedriver\\IEDriverServer.exe");
	      //Get the only object available
		WebDriver driver = (WebDriver) DriverManager.getInstance();
		driver.get("http://www.google.com");
	      driver.close();
	      
	   }
}
