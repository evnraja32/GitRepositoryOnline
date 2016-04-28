package pages;


import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MergeLeadPage extends OpentapsWrappers{

	String xpath = null;

	public MergeLeadPage(String xpath) {

		if(!verifyTitle("Create Lead | opentaps CRM")){
			Reporter.reportStep("This is NOT Create Lead | opentaps CRM page", "FAIL");
			this.xpath = xpath;
		}

	}

	public MergeLeadPage() {
		// TODO Auto-generated constructor stub
		if(!verifyTitle("Merge Leads | opentaps CRM")){
			Reporter.reportStep("This is NOT Create Lead | opentaps CRM page", "FAIL");
		}
	}

	public FinLeadWindowHandler searchAndGetFirstLeadID(){
		clickByXpath(prop.getProperty("MergeLead.ImageObject.FromID"));
//		switchToLastWindow();
		return new FinLeadWindowHandler();
	}
	public FinLeadWindowHandler searchAndGetSecondLeadID(){
		clickByXpath(prop.getProperty("MergeLead.ImageObject.ToID"));
//		switchToLastWindow();
		return new FinLeadWindowHandler();
	}
	public MergeLeadPage clickOnMergeButton() throws InterruptedException{
		clickByLink(prop.getProperty("MergeLead.Button.Merge"));
		reportStatus("Checking for Alert is displayed", "PASS");
		Thread.sleep(10000);
		return this;
	}
	public VeiwLeadPage handleTheAlert() throws InterruptedException{
		try {
			switchToAlertAndAccept();
		} catch (UnhandledAlertException f) {
		    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert data: " + alertText);
		        alert.accept();
		    } catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
		}
//		System.out.println("In Alert = "+parentWindow);
//		switchToWindowAlert();
//		manageAlert("accept");
		return new VeiwLeadPage();
	}


}
