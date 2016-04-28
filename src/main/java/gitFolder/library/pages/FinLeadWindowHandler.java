package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FinLeadWindowHandler extends OpentapsWrappers{

	public FinLeadWindowHandler() {
		System.out.println("Befor window = "+parentWindow);
		switchToLastWindow();
		System.out.println("After window = "+parentWindow);
		if(!verifyTitle("Find Leads")){
			Reporter.reportStep("This is NOT Find Lead page", "FAIL");
		}

	}

	public FinLeadWindowHandler findLeadByID(String data) throws InterruptedException{
//		switchToLastWindow();
		enterByXpath(prop.getProperty("FindLeadWindowHandler.ImageObject.ID"), data+"\n");
		Thread.sleep(5000); 
		return this;
	}
	
	public MergeLeadPage clickSearchResult() {
		clickByXpath(prop.getProperty("FindLeadWindowHandler.SearchResult.FirstID"));
//		switchToPrimaryWindow();
		System.out.println("After clicking = "+parentWindow); 
		
		driver.switchTo().window(parentWindow);
		return new MergeLeadPage();
	}
	

}
