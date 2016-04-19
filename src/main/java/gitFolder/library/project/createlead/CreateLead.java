package createlead;

import org.junit.Test;

public class CreateLead extends ProjectWrapper{

	@Test
	public void testCase1CreateLead() throws InterruptedException{
		String GtText;

		launchApp("firefox", "http://demo1.opentaps.org/");

		findWebElementByID("username");
		sendKeysToWebElement("DemoSalesManager");

		findWebElementByID("password");
		sendKeysToWebElement("crmsfa");

		findWebElementByCssSelector("input.decorativeSubmit");
		clickOnElement();

		findWebElementByLinkText("CRM/SFA");
		clickOnElement();
		//=========================================
		Thread.sleep(10000);

		findWebElementByXpath("//a[contains(text(),'Create Lead')]");
		clickOnElement();

		//==========================================
		findWebElementByID("createLeadForm_companyName");
		sendKeysToWebElement(readExcel("TestData_main","CreateLead",1,2).getStringCellValue());

		findWebElementByID("createLeadForm_firstName");
		sendKeysToWebElement(readExcel("TestData_main","CreateLead",1,3).getStringCellValue());

		findWebElementByID("createLeadForm_lastName");
		sendKeysToWebElement(readExcel("TestData_main","CreateLead",1,4).getStringCellValue());

		findWebElementByID("createLeadForm_dataSourceId");
		selectDdElementByVisibleText(readExcel("TestData_main","CreateLead",1,5).getStringCellValue());

		findWebElementByID("createLeadForm_marketingCampaignId");
		selectDdElementByVisibleText(readExcel("TestData_main","CreateLead",1,6).getStringCellValue());

		findWebElementByID("createLeadForm_primaryEmail");
		sendKeysToWebElement(readExcel("TestData_main","CreateLead",1,7).getStringCellValue());

		findWebElementByID("createLeadForm_primaryPhoneNumber");
		sendKeysToWebElement(readExcel("TestData_main","CreateLead",1,8).getStringCellValue());

		findWebElementByClassName("smallSubmit");
		clickOnElement();
		//=====================
		browserWait(50);

		findWebElementByID("viewLead_companyName_sp");
		GtText = getWebElement().getText().substring(7,12);
		System.out.println(GtText);
		closeTheBrowser();

	}


}
