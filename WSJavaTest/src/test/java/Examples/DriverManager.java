package Examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class DriverManager 
{

	//create an object of SingleObject
	   private static WebDriver driver = new FirefoxDriver();
	   //make the constructor private so that this class cannot be
	   //instantiated
	   private DriverManager()
	   {
		   
	   }

	   //Get the only object available
	   public static WebDriver getInstance(){
	      return driver;
	   }

//	   public void showMessage(){
//	      driver.get("http://www.google.com/");
//	   }

	   
	   
	   
	   
	   
	   
	   
	   
}
