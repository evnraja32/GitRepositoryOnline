package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class EditLeadPage extends OpentapsWrappers {

	public EditLeadPage() {

		if (!verifyTitle("View Lead | opentaps CRM")) {
			Reporter.reportStep("This is NOT login page", "FAIL");
		}

	}
	
	public EditLeadPage clickOnEditButton(){
		clickByLink(prop.getProperty("EditLeadPage.EditButton.Link"));
		return this;
	}
	
	public EditLeadPage editFirstDropDown(String id, String value) {
		selectById(prop.getProperty("EditLeadPage.FirstDropdown.Id"), value);
		clickByXpath(prop.getProperty("EditLeadPage.FirstDropdown.Xpath"));
		return this;
	}
	
	public EditLeadPage clickSecondDropDown(String id, String value) {
		selectById(prop.getProperty("EditLeadPage.SecondDropdown.Id"), value);
		clickByXpath(prop.getProperty("EditLeadPage.SecondDropdown.Xpath"));
		return this;
	} 
	public EditLeadPage verifyFirstLabel(String id, String value) {
		verifyTextContainsByXpath(prop.getProperty("EditLeadPage.FirstLabel.Id"), value);
		return this;
	} 
	
	public EditLeadPage verifySecondLabel(String id, String value) {
		verifyTextContainsById(prop.getProperty("EditLeadPage.SecondLabel.Id"), value);
		return this;
	} 
	
}
