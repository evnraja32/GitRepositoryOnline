package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class VeiwLeadPage extends OpentapsWrappers{

	public VeiwLeadPage() {
		// TODO Auto-generated constructor stub

		if(!verifyTitle("Find Leads | opentaps CRM")){
			Reporter.reportStep("This is NOT Find lead page", "FAIL");
		}

	}


	// Click lead id in the table below
	public  VeiwLeadPage clickLeadIDByXpath(){
		clickByClassName(prop.getProperty("ViewLead.Delete.Class"));
		return new VeiwLeadPage();
	}
	
	public EditLeadPage verifyLeadID(){
		boolean flag = false;
		verifyTextContainsByName("id", driver.getCurrentUrl().split("=")[1]);
		return new EditLeadPage();
	}

}
