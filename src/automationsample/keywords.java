package automationsample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
public class keywords {
	String path = System.getProperty("user.dir");
	 
	WebDriver driver;
	 
	public void enter_URL(WebDriver driver,String TestData) throws IOException{
	driver.get(TestData);
	}
	 
	public void type(WebDriver driver, String ObjectName, String locatorType, String testdata) throws IOException{
	 
	driver.findElement(this.getObject(ObjectName,locatorType)).sendKeys(testdata);
	//driver.findElement(By.xpath("//")).sendKeys(testdata);
	 
	}
	public void wait(WebDriver driver,String ObjectName, String locatorType) throws IOException{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(ObjectName,locatorType))));
	}
	public void click(WebDriver driver,String ObjectName, String locatorType) throws IOException{
	driver.findElement(this.getObject(ObjectName,locatorType)).click();
	}
	public String get_currentURL(WebDriver driver){
	String URL = driver.getCurrentUrl();
	System.out.println("print URL "+URL);
	return URL;
	}
	By getObject(String ObjectName, String locatorType) throws IOException{
	 
//	File file = new File(path+"\\Object Repository.properties");
	FileInputStream file = new FileInputStream(path+"\\Object Repository.properties");
	System.out.println(path);
//	FileInputStream fileInput = new FileInputStream(file);
	 
	Properties prop = new Properties();
	prop.load(file);
	System.out.println("Value of 'login_use': "+prop.getProperty(ObjectName));

	//find by xpath
	if(locatorType.equalsIgnoreCase("XPATH")){
	 
	return By.xpath(prop.getProperty(ObjectName));
	
	}
	
	//find by class
	else if(locatorType.equalsIgnoreCase("CLASSNAME")){
	 
	return By.className(prop.getProperty(ObjectName));
	 
	}
	//find by name
	else if(locatorType.equalsIgnoreCase("NAME")){
	 
	return By.name(prop.getProperty(ObjectName));
	 
	}
	//Find by css
	else if(locatorType.equalsIgnoreCase("CSS")){
	 
	return By.cssSelector(prop.getProperty(ObjectName));
	 
	}
	//find by link
	else if(locatorType.equalsIgnoreCase("LINK")){
	 
	return By.linkText(prop.getProperty(ObjectName));
	 
	}
	//find by partial link
	else if(locatorType.equalsIgnoreCase("PARTIALLINK")){
	 
	return By.partialLinkText(prop.getProperty(ObjectName));
	 
	}
	return null;
	 
	}
	 
	}