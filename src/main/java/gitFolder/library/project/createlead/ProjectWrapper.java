package createlead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class ProjectWrapper {

	RemoteWebDriver driver;
	private WebElement element;
	private String parentWindow = null;
	private Alert a = null;
	Select select = null;

	//===== Browser ===================
	public void launchApp(String browser, String url){
		try{
			if(browser.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chorme.driver", "./drivers/chromedriver.exe");
				driver= new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);

		}catch(WebDriverException e){
			printException(e,"Unable to open browser");
		}
	}

	public void browserWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	public void closeTheWindow() {
		driver.close();
	}

	public void closeTheBrowser() {
		driver.quit();

	}

	//===== Browser =====================

	//===== finding elements ============

	public void findWebElementByID(String id){
		try{
			element = driver.findElement(By.id(id));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by ID");
		}
	}

	public void findWebElementByName(String name){
		try{
			element = driver.findElement(By.name(name));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by name");
		}
	}

	public void findWebElementByClassName(String className){
		try{
			element = driver.findElement(By.className(className));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by class name");
		}
	}

	public void findWebElementByLinkText(String linkText){
		try{
			element = driver.findElement(By.linkText(linkText));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by linkText");
		}
	}

	public void findWebElementByPartialLinkText(String partialLinkText){
		try{
			element = driver.findElement(By.partialLinkText(partialLinkText));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by partialLink");
		}
	}

	public void findWebElementByXpath(String xpath){
		try{
			element = driver.findElement(By.xpath(xpath));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by xpath");
		}
	}

	public void findWebElementByCssSelector(String cssSelector){
		try{
			element = driver.findElement(By.cssSelector(cssSelector));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by css selector");
		}
	}

	public void findWebElementByTagName(String tagName){
		try{
			element = driver.findElement(By.tagName(tagName));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find element by tagname");
		}
	}

	public WebElement getWebElement(){
		return element;
	}

	public void setWebElement(WebElement element){
		this.element = element;
	}

	public List<WebElement> findWebElementsByID(String id){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.id(id));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by ID");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByName(String name){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.name(name));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by name");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByClassName(String className){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.className(className));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by class name");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByLinkText(String linkText){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.linkText(linkText));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by link text");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByPartialLinkText(String partialLinkText){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.partialLinkText(partialLinkText));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by partial Link Text");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByCssSelector(String cssSelector){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.cssSelector(cssSelector));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by Css Selector");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByXpath(String xpath){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.xpath(xpath));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by xpath");
		}
		return elements;
	}

	public List<WebElement> findWebElementsByTagName(String tagName){
		List<WebElement> elements = null;
		try{
			elements = driver.findElements(By.tagName(tagName));
		}catch(NoSuchElementException e){
			printException(e, "Unable to find elements by TagName");
		}
		return elements;
	}

	//====== finding elements ===========

	//====== Browser Actions ============
	public void sendKeysToWebElement(String keysToSend){
		element.clear();
		element.sendKeys(keysToSend);
	}

	public void clickOnElement(){
		element.click();
	}

	public void selectDdElementByIndex(int targetElementIndex){
		try {
			select = new Select(element);
			select.selectByIndex(targetElementIndex);
		} catch (NoSuchElementException e) {
			printException(e, "Cannot locate option with index : "+targetElementIndex);
		}catch(UnexpectedTagNameException e){
			printException(e, "Tag Used doesn't belongs to Select");
		}
	}

	public void selectDdElementByValue(String value){
		try {
			select = new Select(element);
			select.selectByValue(value);
		} catch (NoSuchElementException e) {
			printException(e, "Cannot locate option with value : "+value);
		}catch(UnexpectedTagNameException e){
			printException(e, "Tag Used doesn't belongs to Select");
		}
	}

	public void selectDdElementByVisibleText(String visibleText){
		try {
			select = new Select(element);
			select.selectByVisibleText(visibleText);
		} catch (NoSuchElementException e) {
			printException(e, "Cannot locate option with visibleText : "+visibleText);
		}catch(UnexpectedTagNameException e){
			printException(e, "Tag Used doesn't belongs to Select");
		}
	}

	public String getDdSelectedOption(){
		return select.getFirstSelectedOption().getText();
	}


	public boolean verifyElementText(String targetText) {
		return element.getText().equals(targetText);
	}
	
	public boolean verifyElementTextContains(String targetText) {
		return element.getText().contains(targetText);
	}


	public boolean verifyUrl(String targetURL) {
		return driver.getCurrentUrl().equals(targetURL); 
	}


	public boolean verifyWebElementDisplay(){
		return element.isDisplayed(); 
	}


	public boolean verifyWindowTitle(String targetTitle) {
		return driver.getTitle().equals(targetTitle);
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
		}catch(NoSuchWindowException e){
			printException(e,"Exception at getLast Window Method"+e.getMessage());
		}
		return windowHandlers;
	}

	public String getParentWindowHandle(){
		return parentWindow;
	}

	//- switchToPrimaryWindow
	public RemoteWebDriver switchToPrimaryWindow() {
		return (RemoteWebDriver) driver.switchTo().window(parentWindow);
	}

	//- switchToLastWindow
	public RemoteWebDriver switchToLastWindow() {

		try{
			for( String eachWindow : prepareAllWindowHandlers()){ //for each window in window handles
				driver.switchTo().window(eachWindow);	 //we are Switching to respective window
			}
		}catch(NoSuchWindowException e){
			printException(e,"Exception at getLast Window Method"+e.getMessage());
		}
		return driver;
	}

	//====== Windows Handling ===========

	//====== Handling Frames =============

	public RemoteWebDriver switchToFrameByNameOrID(String nameOrId){
		try{
			driver.switchTo().frame(nameOrId);
		} catch (NoSuchFrameException e) {

			printException(e, "The given element is neither an IFRAME nor a FRAME element");

		} catch(StaleElementReferenceException e){
			printException(e, "The WebElement has gone stale.");
		}

		return driver;
	}

	public RemoteWebDriver switchToFrameByindex(int index){
		try{
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {

			printException(e, "The given element is neither an IFRAME nor a FRAME element");

		} catch(StaleElementReferenceException e){
			printException(e, "The WebElement has gone stale.");
		}

		return driver;
	}

	public RemoteWebDriver switchToFrameByNameOrID(WebElement frameElement){

		try {
			driver.switchTo().frame(frameElement);
		} catch (NoSuchFrameException e) {

			printException(e, "The given element is neither an IFRAME nor a FRAME element");

		} catch(StaleElementReferenceException e){
			printException(e, "The WebElement has gone stale.");
		}

		return driver;
	}

	public RemoteWebDriver switchToDefaultContent(){

		return (RemoteWebDriver) driver.switchTo().defaultContent();
	}

	//====== Handling Frames =============

	//====== Handling Alerts ============


	public String switchToWindowAlert(){
		try{
			a = driver.switchTo().alert();
		}
		catch(NoAlertPresentException e)
		{
			printException(e, "No alert on the screen");
		}
		return a.getText();
	}


	public void manageAlert(String action){
		if(action.equalsIgnoreCase("accept")){
			a.accept();
		}
		else if(action.equalsIgnoreCase("dismiss")){
			a.dismiss();
		}
	}
	


	public void enterAlertText(String keysToSend){
		a.sendKeys(keysToSend);
	}

	//====== Handling Alerts ============

	//====== Handling Excel Files =======

	public XSSFSheet prepareExcelSheet(String fileName,String sheetName){
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;

		try{
			FileInputStream fis = new FileInputStream(new File("./data/"+fileName+".xlsx"));
			workbook = new XSSFWorkbook(fis);

			sheet = workbook.getSheet(sheetName);

		}catch(IOException e){
			printException(e, "Error in excel book");
		}
		return sheet;
	}

	public XSSFRow getRowAt(XSSFSheet excelSheet,int rowIndex){
		return excelSheet.getRow(rowIndex);
	}

	public XSSFCell getCellAt(XSSFRow currentRow,int cellnum){
		return currentRow.getCell(cellnum);
	}

	public XSSFCell readExcel(String filename,String sheetName, int rowIndex, int colnum) 
	{
		XSSFRow currentRow = null;
		try{
		FileInputStream data = new FileInputStream(new File("./data/"+filename+".xlsx"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(data);
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		 currentRow = sheet.getRow(rowIndex);
		 
		}catch(IOException e){
			printException(e,"Error in retriving data");
		}
		return currentRow.getCell(colnum);
	}

	//====== Handling Excel Files =======

	public  void  printException(Throwable e, String errorMessage) {

		System.out.println(errorMessage);
		e.printStackTrace();

	}

}