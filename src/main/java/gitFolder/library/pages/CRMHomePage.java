package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CRMHomePage extends OpentapsWrappers{


	public  CRMHomePage() {
		if(!verifyTitle("My Home | opentaps CRM")){
			Reporter.reportStep("This is NOT My Home | opentaps CRM page", "FAIL");
		}

	}
	
	public CreateLeadPage clickCreateLead(){
		clickByLink(prop.getProperty("CreateLeadPage.Click.CreateLead"));
		return new CreateLeadPage();
	}
	
	public LeadTab clickLeadTab() throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.NavigateTo.LeadTab"));
		Thread.sleep(5000);
		return new LeadTab();
	}
}
