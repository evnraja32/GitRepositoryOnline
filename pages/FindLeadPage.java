package pages;


//import pages.FindLead.ViewLeadPage;
import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FindLeadPage extends OpentapsWrappers {
	
	public FindLeadPage() {

		if(!verifyTitle("Find Leads | opentaps CRM")){
			Reporter.reportStep("This is NOT Find lead page", "FAIL");
		}

	}
	
	// Enter lead ID
	public FindLeadPage enterLeadIDByXpath(String leadID){
		verifyTextContainsByXpath(prop.getProperty("FindLead.LeadID.Xpath"), leadID);
		return this;
	}
	
	// Enter First name
		public FindLeadPage enterFirstNameByXpath(String firstname){
			verifyTextContainsByXpath(prop.getProperty("FindLead.FirstName.Xpath"), firstname);
			return this;
		}
		
  // Enter last name
		public FindLeadPage enterLastNameByXpath(String lastname){
			verifyTextContainsByXpath(prop.getProperty("FindLead.LastName.Xpath"), lastname);
			return this;
		}
		
	 // Click find lead
		public FindLeadPage clickFindLeadByXpath(){
			clickByXpath(prop.getProperty("FindLead.ClickLead.Xpath"));
			return this;
		}		
		
	

}
