package createlead;

import org.junit.Test;

public class ProjectLead extends ProjectWrapper{

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
		readExcel("TestData_main","Merge",1,8).setCellValue(GtText);
		

	}
	
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
	
	@Test
	public void testCase3DeleteLead() throws InterruptedException
	{
		launchApp("firefox","http://demo1.opentaps.org/opentaps/control/main");
		//enter username
		findWebElementByID("username");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,0).getStringCellValue());
		//enter password
		findWebElementByID("password");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,1).getStringCellValue());
		//Click Login
		findWebElementByClassName("decorativeSubmit");
		clickOnElement();
		//click on crm/sfa button
		findWebElementByID("button");
		clickOnElement();
		//Implicit wait and create leads
		browserWait(40);
		findWebElementByLinkText("Create Lead");
		clickOnElement();
		//enter company name,first name and last name
		findWebElementByID("createLeadForm_companyName");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,2).getStringCellValue());
		//enter first name
		findWebElementByID("createLeadForm_firstName");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,3).getStringCellValue());
		//enter last name
		findWebElementByID("createLeadForm_lastName");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,4).getStringCellValue());
		//enter phone number
		findWebElementByID("createLeadForm_primaryPhoneNumber");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,5).getStringCellValue());
		//enter email id
		findWebElementByID("createLeadForm_primaryEmail");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,6).getStringCellValue());
		//Click Create Lead button
		findWebElementByClassName("smallSubmit");
		clickOnElement();		

		//Code for Find Leads
		findWebElementByXpath("//a[text()='Find Leads']");
		clickOnElement();
		//Click on phone tab
		Thread.sleep(3000);
		findWebElementByXpath("//span[contains(text(),'Phone')]");
		clickOnElement();
		//Type phone number
		findWebElementByXpath("//input[@name='phoneNumber']");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,5).getStringCellValue());
		//click on findleads button
		findWebElementByXpath("//button[text()='Find Leads']");
		clickOnElement();
		//Click on the first element
		Thread.sleep(3000);
		findWebElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a");
		clickOnElement();
		//Click on delete button
		Thread.sleep(3000);
		findWebElementByClassName("subMenuButtonDangerous");
		clickOnElement();
		//Confirm if the element is deleted
		//Code for Find Leads
		findWebElementByXpath("//a[text()='Find Leads']");
		clickOnElement();
		//Click on phone tab
		Thread.sleep(3000);
		findWebElementByXpath("//span[contains(text(),'Phone')]");
		clickOnElement();
		//Type phone number
		findWebElementByXpath("//input[@name='phoneNumber']");
		sendKeysToWebElement(readExcel("TestData_main","DeleteLead",1,5).getStringCellValue());
		//click on findleads button
		findWebElementByXpath("//button[text()='Find Leads']");
		clickOnElement();
		//Verify if no record displayed
		Thread.sleep(5000);
		findWebElementByClassName("x-paging-info");
		System.out.println(getWebElement().getText());
		System.out.println(verifyElementText("No records to display"));
		closeTheBrowser();
	}

	@Test
	public void  testCase4MergeLeads() throws InterruptedException{

		launchApp("firefox", "http://demo1.opentaps.org/");

		findWebElementByID("username");
		sendKeysToWebElement("DemoSalesManager");

		findWebElementByID("password");
		sendKeysToWebElement("crmsfa");

		findWebElementByCssSelector("input.decorativeSubmit");
		clickOnElement();

		findWebElementByLinkText("CRM/SFA");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByLinkText("Leads");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByLinkText("Merge Leads");
		clickOnElement();

		//========================
		String leadName[] = {"From Lead","To Lead"};

		for(int i = 0 ; i <= 1 ; i++ ){
			findWebElementByXpath("//td[@class='titleCell']/span[text()='"+leadName[i]+"']/following::img");
			clickOnElement();

			prepareAllWindowHandlers();

			switchToLastWindow();

			Thread.sleep(5000);

			findWebElementByName("id");
			String data = readExcel("TestData_main","Merge", 1, i).getStringCellValue();
			sendKeysToWebElement(data+"\n");

			Thread.sleep(5000);

			findWebElementByLinkText(data);
			clickOnElement();

			switchToPrimaryWindow();
		}

		findWebElementByLinkText("Merge");
		clickOnElement();

		switchToWindowAlert();
		manageAlert("accept");

		Thread.sleep(10000);

		findWebElementByLinkText("Find Leads");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByName("id");
		String data = readExcel("TestData_main","Merge", 1, 0).getStringCellValue();
		sendKeysToWebElement(data+"\n");

		Thread.sleep(10000);

		findWebElementByClassName("x-paging-info");
		if(getWebElement().getText().equals("No records to display")){
			System.out.println("The item "+data+" is not displayed");
		}else if (getWebElement().getText().contains("Displaying records")) {
			System.out.println("The item "+data+" is displayed");
		}

				closeTheBrowser();
		//		System.out.println(driver.getTitle());


	}

}
