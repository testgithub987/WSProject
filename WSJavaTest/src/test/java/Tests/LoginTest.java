package Tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utility.BaseClass;

public class LoginTest extends BaseClass {

	LoginPage loginpage;

	@Parameters({"username","password"})
	@Test
	public void LoginTestCase(String username,String password) throws Exception 
	{
		try
			{
				System.out.println("username : " + username);
			 	loginpage = new Pages.LoginPage(getDriver());
			 	loginpage.loginAction(username,password);
				Assert.assertEquals(loginpage.verifyTitle(), "GitHub");
				writeToLog("INFO", "Home Page title verified");
			}
		catch(Exception e)
		{
			e.printStackTrace();
			getscreenshot();
		}
	
	}

}
