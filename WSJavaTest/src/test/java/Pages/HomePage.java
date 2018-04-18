package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * Login Page - which contain all the locators and methods related to same
 * 
 */
public class HomePage 
{
	WebDriver driver;
	
	public By NewRepository_Link=By.xpath("//a[contains(text(),'New repository')]");
	public By RepositoryName_TextBox=By.id("repository_name");
	public By RepositoryDesc_TextBox=By.id("repository_description");
	public By CreateRepositoryButton=By.xpath("//button[contains(text(),'Create repository')]");
	public By title=By.xpath("//title[contains(text(),'PavanEpam/tset')]");
	
	
	public HomePage(WebDriver driver){
		this.driver=driver;
	}
	
	public  void setRepositoryName(String RepositoryName) {
		 driver.findElement(RepositoryName_TextBox).sendKeys("NewGitRepository");
	}
	public void setRepositoryDescription(String RepositoryDesc){
		 driver.findElement(RepositoryDesc_TextBox).sendKeys("NewGitRepository Description");
	}
	public void clickNewRepositoryLink(){
		driver.findElement(CreateRepositoryButton).click();
	}
	public String verifyTitle(){
		return driver.findElement(title).getText();
	}
	
	public void createRepositoryAction(String RepositoryName,String RepositoryDesc){
		setRepositoryName(RepositoryName);
		setRepositoryDescription(RepositoryDesc);
		clickNewRepositoryLink();
		
	}

}
