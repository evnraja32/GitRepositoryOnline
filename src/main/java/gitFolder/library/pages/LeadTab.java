package pages;


import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LeadTab extends OpentapsWrappers{

	public LeadTab() {

		if(!verifyTitle("My Leads | opentaps CRM")){
			Reporter.reportStep("This is NOT My Leads | opentaps CRM page", "FAIL");
		}

	}
	
	public CreateLeadPage clickOnCreateLeadTab(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.CreateLead"));
		return new CreateLeadPage();
	}
	
	public FindLeadPage clickOnFindLeadTab(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.FindLeads"));
		return new FindLeadPage();
	}
	
	public MergeLeadPage clickOnMergeLeadTab(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.MergeLeads"));
		return new MergeLeadPage();
	}
}
