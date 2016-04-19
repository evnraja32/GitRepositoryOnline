package createlead;

import org.junit.Test;

public class FindLeads extends ProjectWrapper {

	

	@Test
	public void testCase2FindLeads() throws InterruptedException {
		// Login to Opentaps
		launchApp("firefox", "http://demo1.opentaps.org/");

		findWebElementByID("username");
		sendKeysToWebElement("DemoSalesManager");

		findWebElementByID("password");
		sendKeysToWebElement("crmsfa");

		findWebElementByCssSelector("input.decorativeSubmit");
		clickOnElement();

		findWebElementByLinkText("CRM/SFA");
		clickOnElement();

		// find Lead using firstname
		findWebElementByLinkText("Leads");
		clickOnElement();
		Thread.sleep(10000);
		findWebElementByLinkText("Find Leads");
		clickOnElement();

		findWebElementByXpath("(//input[@name='firstName'])[3]");
		sendKeysToWebElement(readExcel("TestData_main","FindLeads",1, 0).getStringCellValue());

		findWebElementByXpath("//button[contains(text(), 'Find Leads')]");
		clickOnElement();

		Thread.sleep(3000);
		findWebElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		clickOnElement();

		verifyWindowTitle("View Lead | opentaps CRM");

		findWebElementByLinkText("Edit");
		clickOnElement();

		findWebElementByID("addDataSourceForm_dataSourceId");
		selectDdElementByVisibleText(readExcel("TestData_main","FindLeads", 1, 1).getStringCellValue());

		Thread.sleep(3000);
		findWebElementByXpath("//select[@id='addDataSourceForm_dataSourceId']/following::input[1]");
		clickOnElement();

		findWebElementByID("addMarketingCampaignForm_marketingCampaignId");
		selectDdElementByVisibleText(readExcel("TestData_main","FindLeads", 1, 2).getStringCellValue());

		Thread.sleep(3000);
		findWebElementByXpath("//select[@id='addMarketingCampaignForm_marketingCampaignId']/following::input[1]");
		clickOnElement();

		// -----------------Reusable-------------//
		findWebElementByLinkText("Find Leads");
		clickOnElement();

		findWebElementByXpath("(//input[@name='firstName'])[3]");
		sendKeysToWebElement(readExcel("TestData_main","FindLeads", 1, 0).getStringCellValue());

		findWebElementByXpath("//button[contains(text(), 'Find Leads')]");
		clickOnElement();

		Thread.sleep(3000);
		findWebElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		clickOnElement();
		// -----------------Reusable-------------//

		findWebElementByID("viewLead_dataSources_sp");
		if(verifyElementTextContains(readExcel("TestData_main","FindLeads", 1, 1).getStringCellValue())){
			System.out.println("Source Value is added successfully");
		}else{
			System.out.println("Source Value is not added");
		}

		findWebElementByID("viewLead_marketingCampaigns_sp");
		if(verifyElementTextContains(readExcel("TestData_main","FindLeads", 1, 2).getStringCellValue())){
			System.out.println("Market Campaign Value is added successfully");
		}else{
			System.out.println("Market Campaign Value is not added");
		}

		closeTheBrowser();
	}
	
}
