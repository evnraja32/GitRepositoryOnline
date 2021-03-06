package pages;


import utils.Reporter;
import wrappers.OpentapsWrappers;

public class HomePage extends OpentapsWrappers{

	public HomePage() {

		if(!verifyTitle("Opentaps Open Source ERP + CRM")){
			Reporter.reportStep("This is NOT Home page", "FAIL");
		}

	}
	// Verify the username
	public HomePage verifyLoggedinUserName(String userName){
		verifyTextContainsByXpath(prop.getProperty("Home.UserName.Xpath"), userName);
		return this;
	}
	
	public CRMHomePage clickCRMLink(){
		clickByLink(prop.getProperty("HomePage.CRMLink.Link"));
		return new CRMHomePage();
	}
	
	
	public LoginPage clickLogout(){
		clickByClassName(prop.getProperty("HomePage.Logout.Class"));
		return new LoginPage();
	}
	
	public LeadTab clickOnLeadTab(){
		clickByLink(prop.getProperty("HomePage.NavigateTo.LeadTab"));
		return new LeadTab();
	}
	
}
