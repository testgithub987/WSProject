package Project.SampleTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
 * Class : PropertyFileReading
 * Description : This class is used for property file reading and getting the values from the file - Provide the key and get the value from file
 */
 public class PropertyFileReading 
 {
	String Property = null;
	static Properties prop = new Properties();
	static{
		try{
			FileReader reader = new FileReader(System.getProperty("user.dir") + "\\properties\\Config.properties");
			prop.load(reader);
		}
		catch(IOException io)
		{
			System.out.println("Exception while loading properties from file");
		}
	}
	/*
	 * method : getPropertyValue
	 * parameters : String
	 * Description : Provide the key and get the value from property file
	 */
	public static String getPropertyValue(String Property_name) throws IOException
	{
		String Property_Value = "";
		Property_Value=prop.getProperty(Property_name);
		return Property_Value;
	}
}