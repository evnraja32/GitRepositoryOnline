package pages;


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
		if(!verifyTitle("Create Lead | opentaps CRM")){
			Reporter.reportStep("This is NOT Create Lead | opentaps CRM page", "FAIL");
		}
	}

	public MergeLeadPage searchAndGetFirstLeadID(String fromID){
		clickByLink(prop.getProperty("MergeLead.ImageObject.FromID"));
		switchToNextWindow();
		new FinLeadWindowHandler().findLeadByID(fromID).clickSearchResult();
		switchToPrimaryWindow();
		return this;
	}

	public MergeLeadPage searchAndGetSecondLeadID(String toID){
		clickByLink(prop.getProperty("MergeLead.ImageObject.ToID"));
		switchToNextWindow();
		new FinLeadWindowHandler().findLeadByID(toID).clickSearchResult();
		switchToPrimaryWindow();
		return this;
	}
	
	public MergeLeadPage clickOnMergeButton(){
		clickByLink(prop.getProperty("MergeLead.Button.Merge"));
		return this;
	}
	
	public EditLeadPage handleTheAlert(){
		switchToAlertAndAccept();
		return new EditLeadPage();
	}

}
