package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import utils.Reporter;

public class GenericWrappperTemplate {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,sHubUrl,sHubPort;

	private WebElement element;
	private String parentWindow = null;
	private Alert a = null;
	Select select = null;
	
	public GenericWrappperTemplate() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//===== Browser ===================
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));
	}

	public boolean launchApp(String browser){
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			if(browser.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chorme.driver", "./drivers/chromedriver.exe");
				driver= new ChromeDriver();
			}

			driver.get(sUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			parentWindow = driver.getWindowHandle();
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		}catch(Exception e) {
			printException(e,"Unable to open browser");
			reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	public void browserWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	public void closeTheWindow() {
		driver.close();
	}

	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	//===== Browser =====================

	//===================================== Finding Elements ======================

	private By by(String locatorType, String locatorValue) throws NoSuchElementException, StaleElementReferenceException{
		By by = null;
		
		if(locatorType.equals("id"))
			by = By.id(locatorValue);	
		else if(locatorType.equals("classname"))
			by = By.className(locatorValue);		
		else if(locatorType.equals("linktext"))
			by = By.linkText(locatorValue);		
		else if(locatorType.equals("partiallinktext"))
			by = By.partialLinkText(locatorValue);	
		else if(locatorType.equals("name"))
			by = By.name(locatorValue);	
		else if(locatorType.equals("tagname"))
			by = By.tagName(locatorValue);	
		else if(locatorType.equals("xpath"))
			by = By.xpath(locatorValue);	
		else if(locatorType.equals("cssselector"))
			by = By.cssSelector(locatorValue);
		else
			reportStep("Unable to find the \nElement Type : "+locatorType+"\nElement Value : "+locatorValue, "FAIL");
		reportStep("Found the Element \nElement Type : "+locatorType+"\nElement Value : "+locatorValue, "PASS");
			
		return by;
	}

	public WebElement getWebElementBy(String locatorType, String locatorValue){
		try{
			element = driver.findElement(by(locatorType, locatorValue));
		}catch(NoSuchElementException e ){
			printException(e, "Unable o find the webElement Using: "+locatorType+" && locator Value: "+locatorValue);
		}catch(StaleElementReferenceException se){
			printException(se, "Unable o find the webElement Using: "+locatorType+" && locator Value: "+locatorValue);
		}	return element;
	}

	public List<WebElement> getWebElementsListBy(String locatorType, String locatorValue){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(by(locatorType, locatorValue));
		}catch(NoSuchElementException e ){
			printException(e, "Unable o find the webElements Using: "+locatorType+" && locator Value: "+locatorValue);
		}catch(StaleElementReferenceException se){
			printException(se, "Unable o find the webElements Using: "+locatorType+" && locator Value: "+locatorValue);
		}

		return elements;
	}

	//===================================== Finding Elements ======================

	//====== Browser Actions ============
	public boolean sendKeysToWebElement(String keysToSend){
		boolean flag  = false;
		try {
			element.clear();
			element.sendKeys(keysToSend);
			flag = true;
			reportStep("The data : "+keysToSend+" has Successfully entered", "PASS");
		} catch (Exception e){
			printException(e, "Error at sendKeysToWebElement");
			reportStep("The data : "+keysToSend+" has not Successfully entered", "FAIL");
		}
		return flag;
	}

	public boolean clickOnElement(){
		boolean flag  = false;
		try {
			element.click();
			flag = true;
			reportStep("The element : "+element.getText()+" has Clicked Successfully", "PASS");
		} catch (Exception e){
			printException(e, "Error at clickOnElement Method");
			reportStep("he element : "+element.getText()+" has not Successfully Clicked", "FAIL");
		}
		return flag;
	}

	public boolean mouseOverByLinkText(String linkName) {
	boolean bReturn = false;
	try{
		new Actions(driver).moveToElement(element).build().perform();
		reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

		bReturn = true;

	} catch (Exception e) {
		reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
	}
	return bReturn;
}
	
	public boolean selectDdElementByIndex(int targetElementIndex){
		boolean flag = false;
		try {
			select = new Select(element);
			select.selectByIndex(targetElementIndex);
			flag = true;
			reportStep("The element is selected with index :"+targetElementIndex, "PASS");
		} catch (Exception e){
			printException(e, "Error at selectDdElementByIndex");
			reportStep("The value: "+targetElementIndex+" could not be selected.", "FAIL");
		}
		return flag;
	}

	public boolean selectDdElementByValue(String selectValue){
		boolean flag  = false;
		try {
			select = new Select(element);
			select.selectByValue(selectValue);
			flag = true;
			reportStep("The element is selected with value :"+selectValue, "PASS");
		} catch (Exception e){
			printException(e, "Error at selectDdElementByValue");
			reportStep("The value: "+selectValue+" could not be selected.", "FAIL");
		}
		return flag;
	}

	public boolean selectDdElementByVisibleText(String selectValue){
		boolean flag  = false;
		try {
			select = new Select(element);
			select.selectByVisibleText(selectValue);
			flag = true;
			reportStep("The element is selected with value :"+selectValue, "PASS");
		} catch (Exception e){
			printException(e, "Error at selectDdElementByVisibleText");
			reportStep("The value: "+selectValue+" could not be selected.", "FAIL");
		}
		return flag;
	}

	public String getDdSelectedOption(){
		return select.getFirstSelectedOption().getText();
	}

	public boolean verifyElementText(String targetText) {
		boolean bReturn = false;
		String actualText = element.getText();
		if (actualText.equalsIgnoreCase(targetText)){
			reportStep("The text: "+actualText+" matches with the value :"+targetText, "PASS");
			bReturn = true;
		}else{
			reportStep("The text: "+actualText+" matches with the value :"+targetText,  "FAIL");
		}
		return bReturn;
	}

	public boolean verifyElementTextExactMatch(String targetText) {
		boolean bReturn = false;
		String actualText = element.getText();
		if (actualText.equals(targetText)){
			reportStep("The text: "+actualText+" matches with the value :"+targetText, "PASS");
			bReturn = true;
		}else{
			reportStep("The text: "+actualText+" matches with the value :"+targetText,  "FAIL");
		}
		return bReturn;
	}

	public boolean verifyElementTextPartialMatch(String targetText) {
		boolean bReturn = false;
		String actualText = element.getText();
		if (actualText.contains(targetText)){
			reportStep("The text: "+actualText+" contains the value :"+targetText, "PASS");
			bReturn = true;
		}else{
			reportStep("The text: "+actualText+" contains the value :"+targetText,  "FAIL");
		}
		return bReturn;
	}

	public boolean verifyUrl(String targetURL) {
		boolean bReturn = false;
		String actualUrl = driver.getCurrentUrl();
		if (actualUrl.contains(targetURL)){
			reportStep("URL Mathced\nActual URL: \n"+actualUrl+"\nTarget URL :\n"+targetURL, "PASS");
			bReturn = true;
		}else{
			reportStep("URL doesn't matches\nActual URL: \n"+actualUrl+"\nTarget URL :\n"+targetURL,  "FAIL");
		}
		return bReturn;
	}

	public boolean verifyWebElementDisplay(){
		boolean flag = element.isDisplayed();
		if(flag)
			reportStep("Element has displayed",  "PASS");
		else
			reportStep("Element has not displayed",  "FAIL");

		return flag; 
	}

	public boolean verifyTitle(String targetTitle) {
		boolean bReturn = false;
		try{
			if (driver.getTitle().equals(targetTitle)){
				reportStep("The title of the page matches with the value :"+targetTitle, "PASS");
				bReturn = true;
			}else
				reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+targetTitle, "SUCCESS");

		}catch (Exception e) {
			reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	public String getCurrentPageURL(){
		return driver.getCurrentUrl();
	}
	//====== Browser Actions ============

	//====== Windows Handling ===========

	public Set<String> prepareAllWindowHandlers(){
		Set<String> windowHandlers = null;
		try{
			parentWindow = driver.getWindowHandle();
			windowHandlers = driver.getWindowHandles();
			reportStep("Window Handles identified successfully", "PASS");
		}catch(NoSuchWindowException e){
			printException(e,"Exception at getLast Window Method"+e.getMessage());
			reportStep("Window Handles identified successfully", "FAIL");
		}
		return windowHandlers;
	}

	public String getParentWindowHandle(){
		return parentWindow;
	}

	//- switchToPrimaryWindow
	public RemoteWebDriver switchToPrimaryWindow() {
		try{
			driver = (RemoteWebDriver) driver.switchTo().window(parentWindow);
			reportStep("Window Switched to Primary Window", "PASS");
		}catch(NoSuchWindowException e){
			printException(e,"Exception at getLast Window Method"+e.getMessage());
			reportStep("Failed in switching to Primary Window", "FAIL");
		}

		return driver; 
	}

	//- switchToLastWindow
	public RemoteWebDriver switchToLastWindow() {

		try{
			for( String eachWindow : prepareAllWindowHandlers()){ //for each window in window handles
				driver.switchTo().window(eachWindow);	 //we are Switching to respective window
			}
			reportStep("Window Switched to Last Window", "PASS");
		}catch(NoSuchWindowException e){
			printException(e,"Exception at getLast Window Method"+e.getMessage());
			reportStep("Failed in switching to Last Window", "FAIL");
		}
		return driver;
	}

	//====== Windows Handling ===========

	//====== Handling Frames =============

	public RemoteWebDriver switchToFrameByNameOrID(String nameOrId){
		try{
			driver.switchTo().frame(nameOrId);
			reportStep("Switched to Frame by NameOrId", "PASS");
		} catch (Exception e){
			printException(e, "The WebElement has gone stale.");
			reportStep("Swithced to Frame by NameOrId Failed", "FAIL");
		}

		return driver;
	}

	public RemoteWebDriver switchToFrameByindex(int index){
		try{
			driver.switchTo().frame(index);
			reportStep("Switched to Frame by Index", "PASS");
		} catch (Exception e){
			printException(e, "The WebElement has gone stale.");
			reportStep("Swithced to Frame by Index Failed", "FAIL");
		}
		return driver;
	}

	public RemoteWebDriver switchToFrameByWebElement(WebElement frameElement){
		try {
			driver.switchTo().frame(frameElement);
			reportStep("Switched to Frame by WebElement", "PASS");
		} catch (Exception e){
			printException(e, "The WebElement has gone stale.");
			reportStep("Swithced to Frame by WebElement Failed", "FAIL");
		}
		return driver;
	}

	public RemoteWebDriver switchToDefaultContent(){
		try {
			driver = (RemoteWebDriver) driver.switchTo().defaultContent();
			reportStep("Switched to Default Content", "PASS");
		} catch (Exception e){
			printException(e, "The WebElement has gone stale.");
			reportStep("Swithced to Default Content Failed", "FAIL");
		}

		return driver;
	}

	//====== Handling Frames =============

	//====== Handling Alerts ============

	public String switchToWindowAlert(){
		try{
			a = driver.switchTo().alert();
			reportStep("Alert is displayed", "PASS");
		}
		catch(NoAlertPresentException e)
		{
			printException(e, "No alert on the screen");
			reportStep("No Such Alert is displayed", "FAIL");
		}
		return a.getText();
	}

	public boolean manageAlert(String action){
		boolean flag = false;
		if(action.equalsIgnoreCase("accept")){
			a.accept();
			flag = true;
			reportStep("Switched to Default Content", "PASS");
		}
		else if(action.equalsIgnoreCase("dismiss")){
			a.dismiss();
			flag = true;
			reportStep("Switched to Default Content", "PASS");
		}
		return flag;
	}

	public boolean enterAlertText(String keysToSend){
		boolean flag  = false;
		try {
			a.sendKeys(keysToSend);
			flag = true;
			reportStep("The data : "+keysToSend+" has Successfully entered", "PASS");
		} catch (Exception e){
			printException(e, "Error at sendKeysToWebElement");
			reportStep("The data : "+keysToSend+" has not Successfully entered", "FAIL");
		}
		return flag;
	}

	//====== Handling Alerts ============

	//====== Handling Excel Files =======

	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;
	private File excelFile = null;
	private FileInputStream fis = null;
	private FileOutputStream fout = null;

	public void prepareExcelSheet(String fileName,String sheetName){
		excelFile = new File("./data/"+fileName+".xlsx");
		try{
			fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

		}catch(IOException e){
			printException(e, "Error in excel book");
		}
	}

	public String readExcel(int rowIndex, int colnum) 
	{
		row = sheet.getRow(rowIndex);
		cell = row.getCell(colnum);
		return cell.getStringCellValue();
	}

	public void appendDataAt(int cellIndex,String data){
		try{
			sheet.createRow(sheet.getLastRowNum()+1).createCell(cellIndex).setCellValue(data);
			fout = new FileOutputStream(excelFile);
		}
		catch(Exception e){
			printException(e, "Error in appending to excel method");
		}
	}

	public XSSFWorkbook createWorkBook(String fileName) {
		try{
			fout = new FileOutputStream(new File("./data/"+fileName+".xlsx"));
			workbook = new XSSFWorkbook();
		}catch(Exception e){
			printException(e, "Work Book Creation Failed");
		} 
		return workbook;
	}

	public XSSFSheet createWorkSheet(String sheetName){
		try{
			sheet = workbook.createSheet(sheetName);
		}catch(Exception e){
			printException(e, "Work Sheet Creation Failed");
		} 
		return sheet;
	}

	public XSSFRow createRowAt(int rowNum){
		try{
			row = sheet.createRow(rowNum);
		}catch(Exception e){
			printException(e, "Work Sheet Creation Failed in creating Row");
		} 
		return row;
	}

	public void setCellValueAt(int cellNum, String cellValue){
		try{
			row.createCell(cellNum).setCellValue(cellValue);
		}catch(Exception e){
			printException(e, "Work Sheet Creation Failed in creating cell value");
		} 
	}

	public void commitChangesToExcel(){
		try {
			workbook.write(fout);

			fout.close();
			workbook.close();

		} catch (IOException e) {
			printException(e, "Writing to excel failed");
		}
	}


	//====== Handling Excel Files =======
	
	//====== Exception Handles ==========

	public  void  printException(Throwable e, String errorMessage) {

		System.err.println(errorMessage);
		e.printStackTrace();

	}

	public void reportStep(String desc,String status){
		Reporter.reportStep(desc, status.toUpperCase());
	}

	//====== Exception Handles ==========

	
//
//	//===== finding elements ============
//
//	public void findWebElementByID(String id){
//		try{
//			element = driver.findElement(By.id(id));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by ID");
//		}
//	}
//
//	public void findWebElementByName(String name){
//		try{
//			element = driver.findElement(By.name(name));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by name");
//		}
//	}
//
//	public void findWebElementByClassName(String className){
//		try{
//			element = driver.findElement(By.className(className));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by class name");
//		}
//	}
//
//	public void findWebElementByLinkText(String linkText){
//		try{
//			element = driver.findElement(By.linkText(linkText));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by linkText");
//		}
//	}
//
//	public void findWebElementByPartialLinkText(String partialLinkText){
//		try{
//			element = driver.findElement(By.partialLinkText(partialLinkText));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by partialLink");
//		}
//	}
//
//	public void findWebElementByXpath(String xpath){
//		try{
//			element = driver.findElement(By.xpath(xpath));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by xpath");
//		}
//	}
//
//	public void findWebElementByCssSelector(String cssSelector){
//		try{
//			element = driver.findElement(By.cssSelector(cssSelector));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by css selector");
//		}
//	}
//
//	public void findWebElementByTagName(String tagName){
//		try{
//			element = driver.findElement(By.tagName(tagName));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find element by tagname");
//		}
//	}
//
//	public WebElement getWebElement(){
//		return element;
//	}
//
//	public void setWebElement(WebElement element){
//		this.element = element;
//	}
//
//	public List<WebElement> findWebElementsByID(String id){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.id(id));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by ID");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByName(String name){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.name(name));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by name");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByClassName(String className){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.className(className));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by class name");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByLinkText(String linkText){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.linkText(linkText));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by link text");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByPartialLinkText(String partialLinkText){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.partialLinkText(partialLinkText));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by partial Link Text");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByCssSelector(String cssSelector){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.cssSelector(cssSelector));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by Css Selector");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByXpath(String xpath){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.xpath(xpath));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by xpath");
//		}
//		return elements;
//	}
//
//	public List<WebElement> findWebElementsByTagName(String tagName){
//		List<WebElement> elements = null;
//		try{
//			elements = driver.findElements(By.tagName(tagName));
//		}catch(NoSuchElementException e){
//			printException(e, "Unable to find elements by TagName");
//		}
//		return elements;
//	}
//
//	//====== finding elements ===========
//




}