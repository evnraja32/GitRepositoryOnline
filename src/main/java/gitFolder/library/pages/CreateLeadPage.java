package pages;


import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CreateLeadPage extends OpentapsWrappers{

	public CreateLeadPage() {

		if(!verifyTitle("Create Lead | opentaps CRM")){
			Reporter.reportStep("This is NOT Create Lead | opentaps CRM page", "FAIL");
		}

	}
	
	public CreateLeadPage enterCompanyName(String data){
		enterById(prop.getProperty("CreateLeadPage.CompanyName.Id"), data);
		return this;
	}
	
	public CreateLeadPage enterFirstName(String data){
		enterById(prop.getProperty("CreateLeadPage.FirstName.Id"), data);
		return this;
	}
	
	public CreateLeadPage enterLastName(String data){
		enterById(prop.getProperty("CreateLeadPage.LastName.Id"), data);
		return this;
	}
	
	public VeiwLeadPage clickCreateLead() throws InterruptedException{
		clickByClassName(prop.getProperty("CreateLeadPage.clickButton.classname"));
		Thread.sleep(5000);
		return new VeiwLeadPage();
	}
}
