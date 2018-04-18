package Tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Utility.BaseClass;
import Utility.PropertyFileReading;

/*
 * class : CreateNewReposityTest
 * Description : This class is used to create new repository in git hub and it will read the values from property file
 */
public class CreateNewReposityTest extends BaseClass
{
	HomePage homepage;
	public String RepositoryName;
	public String RepositoryDesc;
	@Test
	public void CreateNewReposityTestCase(String RepositoryName,String RepositoryDesc) throws Exception 
	{
		RepositoryName = PropertyFileReading.getPropertyValue("RepositoryName");
		RepositoryDesc = PropertyFileReading.getPropertyValue("RepositoryDesc");
		try
			{
				homepage = new Pages.HomePage(getDriver());							
				homepage.createRepositoryAction(RepositoryName, RepositoryDesc);
				Assert.assertEquals(homepage.verifyTitle(), "Create repository");
				writeToLog("INFO", "Home Page title verified");		
			}
		catch(Exception e)
		{
			e.printStackTrace();
			getscreenshot();
		}
	}
}