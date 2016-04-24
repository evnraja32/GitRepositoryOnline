package pages;


import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LeadTab extends OpentapsWrappers{

	public LeadTab() {

		if(!verifyTitle("My Leads | opentaps CRM")){
			Reporter.reportStep("This is NOT My Leads | opentaps CRM page", "FAIL");
		}

	}
	
	public CreateLeadPage clickOnCreateLead(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.CreateLead"));
		return new CreateLeadPage();
	}
	
	public FindLeadPage clickOnFindLead(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.FindLeads"));
		return new FindLeadPage();
	}
	
	public MergeLeadPage clickOnMergeLead(){
		clickByLink(prop.getProperty("LeadTab.NavigaTo.MergeLeads"));
		return new MergeLeadPage(prop.getProperty("FindLeadWindowHandler.Find.FromLeadField"));
	}
}
