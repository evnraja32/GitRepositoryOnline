package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FinLeadWindowHandler extends OpentapsWrappers{

	public FinLeadWindowHandler() {

		if(!verifyTitle("Find Leads")){
			Reporter.reportStep("This is NOT Find Lead page", "FAIL");
		}

	}

	public FinLeadWindowHandler findLeadByID(String data){
		enterByXpath(prop.getProperty("FindLeadWindowHandler.ImageObject.ID"), data);
		return this;
	}
	
	public MergeLeadPage clickSearchResult(){
		clickByLink(prop.getProperty("FindLeadWindowHandler.SearchResult.FirstID"));
		return new MergeLeadPage();
	}
	
	
}
