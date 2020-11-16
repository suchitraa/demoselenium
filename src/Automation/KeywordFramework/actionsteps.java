package Automation.KeywordFramework;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;	
 

import automationsample.keywords;

 
public class actionsteps {
 
WebDriver driver;
String path = System.getProperty("user.dir");
 
keywords keyword = new keywords();

 
@Test
public void readExcelandexecute() throws IOException, InterruptedException{
 
//From excelfile
String excelFilePath = path+"\\Test Cases.xlsx";
FileInputStream fileInputStream = new FileInputStream(excelFilePath);
 
XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
 
int testcasescount = workbook.getNumberOfSheets()-1;
 
System.out.println("Total test cases :"+testcasescount);
 
for (int testcase=0;testcase<testcasescount;testcase++){
System.setProperty("webdriver.chrome.driver", path+"\\chromedriver\\chromedriver.exe");
driver = new ChromeDriver();
 
XSSFSheet worksheet = workbook.getSheetAt(testcase);
 
System.out.println("worksheet Number "+testcase+":"+worksheet.getSheetName());
 
int row = worksheet.getLastRowNum();
int column = worksheet.getRow(1).getLastCellNum();
 
for(int i=1;i<=row;i++){
 
LinkedList<String> Testexecution = new LinkedList<>();
 
System.out.println("Row value :"+i+"It has first cell value as : "+worksheet.getRow(i).getCell(0));
 
for(int j=0;j<column-1;j++){
System.out.println("Column index :"+j);
Cell Criteria = worksheet.getRow(i).getCell(j);
  
String CriteriaText;
if(Criteria==null){
CriteriaText = null;
}else{ 
CriteriaText = Criteria.getStringCellValue();
}
Testexecution.add(CriteriaText); 
System.out.println("List :"+Testexecution);
}
System.out.println("List :"+Testexecution);
 
String TestStep = Testexecution.get(0);
String ObjectName = Testexecution.get(1);
String LocatorType = Testexecution.get(2);
String Testdata = Testexecution.get(3);
//String AssertionType = Testexecution.get(4);
//String ExpectedValue = Testexecution.get(5);
//String ActualValue = Testexecution.get(6);
// 
perform(TestStep,ObjectName,LocatorType,Testdata);
 
System.out.println("Row"+i+" is read and action performed");
}
 
driver.close();
System.out.println("************************TEST CASE "+worksheet.getSheetName()+" is executed*******************");
}
}
 
public void perform(String operation, String objectName, String locatorType, String testdata) throws IOException, InterruptedException {
 
switch (operation) {
case "enter_URL":
//Perform click
keyword.enter_URL(driver,testdata);
break;
 
case "get_currentURL":
//Set text on control
keyword.get_currentURL(driver);
break;
 
case "type":
keyword.type(driver, objectName, locatorType, testdata);
 
case "click":
keyword.click(driver, objectName, locatorType);
 
/* case "wait":
keyword.wait(driver, objectName, locatorType);*/
 
case "implicitWait":
Thread.sleep(8000);
 
default:
break;
}
 
//if(operation.contains("AssertURL")){
// 
//switch(assertionType){
// 
//case "contains":
//assertion.AssertURLContains(driver, keyword.get_currentURL(driver), expectedValue);
//case "equals":
//assertion.AssertURLEquals(driver, keyword.get_currentURL(driver), expectedValue);
// 
//}
// 
//}
// 
//if(operation.contains("AssertElement"){
// 
//assertion.AssertElement(driver, assertionType, objectName, locatorType);
// 
//}
 
}
 
}