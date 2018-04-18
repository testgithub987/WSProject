package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * Login Page - which contain all the locators and methods related to same
 * 
 */
public class LoginPage 
{
	WebDriver driver;
	
	public By sUsername=By.xpath("//input[@id='login_field']");
	public By sPassword=By.xpath("//input[@id='password']");
	public By login=By.xpath("//input[@name='commit']");
	
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	public  void setUserName(String user) {
		 driver.findElement(sUsername).sendKeys(user);
	}
	public void setPassword(String pass){
		 driver.findElement(sPassword).sendKeys(pass);
	}
	public void clickLogin(){
		driver.findElement(login).click();
	}
	public String verifyTitle(){
		return driver.getTitle();
	}
	
	public void loginAction(String username,String password){
		setUserName(username);
		setPassword(password);
		clickLogin();
		
	}

}

//Vitali_Shulha@epam.com